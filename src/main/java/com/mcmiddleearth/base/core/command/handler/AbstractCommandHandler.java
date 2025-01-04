package com.mcmiddleearth.base.core.command.handler;

import com.google.common.base.Joiner;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.command.TabCompleteRequest;
import com.mcmiddleearth.base.core.command.builder.HelpfulLiteralBuilder;
import com.mcmiddleearth.base.core.command.node.HelpfulNode;
import com.mcmiddleearth.base.core.message.McmeColors;
import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.message.MessageHoverEvent;
import com.mcmiddleearth.base.core.message.MessageStyle;
import com.mcmiddleearth.base.core.plugin.McmePlugin;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public abstract class AbstractCommandHandler {

    private final CommandDispatcher<McmeCommandSender> commandDispatcher = new CommandDispatcher<>();

    private final String command;

    private final McmePlugin plugin;

    public AbstractCommandHandler(String command, McmePlugin plugin)
    {
        this.command = command;
        this.plugin = plugin;
        commandDispatcher.register(createCommandTree( HelpfulLiteralBuilder.literal(command)));
    }

    /**
     * This method is called by the constructor to create a command tree based on the command passed to the constructor
     * Additional command trees may be added directly to the command dispatcher.
     * @param baseCommandNodeBuilder the base command node builder
     * @return same builder as passed in parameter
     */
    protected abstract HelpfulLiteralBuilder createCommandTree(HelpfulLiteralBuilder baseCommandNodeBuilder);

    public CommandDispatcher<McmeCommandSender> getCommandDispatcher() { return commandDispatcher; }

    public String getCommand() {
        return command;
    }

    public void execute(McmeCommandSender sender, String[] args) {
        execute(sender, command, args);
    }

    public void execute(McmeCommandSender sender, String command, String[] args) {
        try {
            String message = String.format("%s %s", command, Joiner.on(' ').join(args)).trim();
            ParseResults<McmeCommandSender> result = commandDispatcher.parse(message, sender);
            result.getExceptions().entrySet().stream()
                    .findFirst().ifPresent(error -> sender.sendMessage(plugin.createErrorMessage()
                                                          .add(error.getValue().getMessage())));
            if(result.getExceptions().isEmpty()) {
                if(!result.getContext().getNodes().isEmpty()
                        && (result.getContext().getCommand()==null
                            || result.getContext().getRange().getEnd() < result.getReader().getString().length())) {
                    //check for possible child nodes to collect suggestions and bake better error message
                    Message helpMessage;
                    boolean help = false;
                    String parsedCommand = "/" + result.getReader().getString()
                            .substring(0, result.getContext().getRange().getEnd());
                    if(result.getReader().getRemaining().trim().equals("help")){
                        helpMessage = plugin.createInfoMessage().add("Help for command "+parsedCommand+":");
                        help = true;
                    } else {
                        helpMessage = plugin.createInfoMessage().add("Invalid command syntax.",McmeColors.ERROR);
                    }
                    CommandNode<McmeCommandSender> parsedNode = result.getContext().getNodes().get(result.getContext().getNodes().size() - 1).getNode();
                    Collection<CommandNode<McmeCommandSender>> children = (result.getContext().getNodes().isEmpty()?new ArrayList<>(): parsedNode.getChildren()
                            .stream().filter(node -> node.canUse(result.getContext().getSource())).toList());
                    Map<CommandNode<McmeCommandSender>,String> use = commandDispatcher.getSmartUsage(parsedNode,result.getContext().getSource());
                    if (children.isEmpty()) {
                        if (result.getContext().getCommand() == null) {
                            helpMessage.add(" Maybe you don't have permission.");
                        } else if(!help) {
                            helpMessage.add(" Maybe you want to do:\n").add(parsedCommand);
                        }
                    } else {
                        if(!help) {
                            helpMessage.add(" Maybe you want to do:");
                        }
                        for(Map.Entry<CommandNode<McmeCommandSender>,String> entry: use.entrySet()) {
                            String usageMessage = "";
                            helpMessage.add("\n");
                            String[] visitedNodes = parsedCommand.split(" ");
                            Iterator<ParsedCommandNode<McmeCommandSender>> iterator = result.getContext().getNodes().listIterator();
                            for (String visitedNode : visitedNodes) {
                                ParsedCommandNode<McmeCommandSender> node = iterator.next();
                                Message nodeMessage = plugin.createMessage().add(" "+visitedNode,
                                        (node.getNode() instanceof LiteralCommandNode?McmeColors.LITERAL:McmeColors.ARGUMENT));
                                if ((node.getNode() instanceof HelpfulNode helpfulNode)) {
                                    if(helpfulNode.getTooltip() != null) {
                                        nodeMessage.addHover(new MessageHoverEvent(MessageHoverEvent.Action.TEXT,
                                                plugin.createMessage()
                                                      .add(((HelpfulNode) node.getNode()).getTooltip()
                                                              .setDefaultStyle(new MessageStyle(McmeColors.TOOLTIP)))));
                                        }
                                    if(!helpfulNode.getHelpText().isEmpty()) {
                                        usageMessage = ((HelpfulNode) node.getNode()).getHelpText();
                                    }
                                }/* else {
                                    helpMessage.event((HoverEvent)null);
                                }*/
                                helpMessage.add(nodeMessage);
                            }
                            String[] possibleNodes = entry.getValue().replace('|', ' ').split(" ");
                            CommandNode<McmeCommandSender> node = parsedNode;
                            CommandNode<McmeCommandSender> lastNode = parsedNode;
                            for(String possibleNode: possibleNodes) {
                                CommandNode<McmeCommandSender> temp = node;
                                if(node != null) {
                                    node = findDirectChild(node, possibleNode.replaceAll("[()\\[\\]<>]", ""));
                                    if (node == null) {
                                        node = findDirectChild(lastNode, possibleNode.replaceAll("[()\\[\\]<>]", ""));
                                    } else {
                                        lastNode = temp;
                                    }
                                    Message nodeMessage = plugin.createMessage().add(" " + possibleNode,
                                            (node instanceof LiteralCommandNode ? McmeColors.LITERAL : McmeColors.ARGUMENT));
                                    if (node instanceof HelpfulNode helpfulNode) {
                                        if (helpfulNode.getTooltip() != null) {
                                            nodeMessage.addHover(new MessageHoverEvent(MessageHoverEvent.Action.TEXT,
                                                    plugin.createMessage()
                                                            .add(((HelpfulNode) node).getTooltip()
                                                                    .setDefaultStyle(new MessageStyle(McmeColors.TOOLTIP)))));
                                        }
                                        if (!helpfulNode.getHelpText().isEmpty()) {
                                            usageMessage = ((HelpfulNode) node).getHelpText();
                                        }
                                    } /* else {
                                        helpMessage.event((HoverEvent) null);
                                    }*/
                                    helpMessage.add(nodeMessage);
                                }
                            }
                            if(!usageMessage.isEmpty()) {
                                Message hoverMessage = plugin.createMessage()
                                                             .add(" : "+usageMessage, McmeColors.HELP);
                                         /*  .addHover(new MessageHoverEvent(MessageHoverEvent.Action.TEXT,
                                        plugin.createMessage()..add(usageTooltip, McmeColors.TOOLTIP))*/
                                helpMessage.add(hoverMessage);
                            }
                        }
                    }
                    sender.sendMessage(helpMessage);
                } else if(result.getContext().getCommand() == null) {
                    sender.sendMessage(plugin.createErrorMessage()
                          .add("Invalid command. Maybe you don't have permission."));
                } else {
                    commandDispatcher.execute(result);
                }
            }
        } catch (CommandSyntaxException e) {
            sender.sendMessage(plugin.createMessage().add("Internal command parser exception!"));
        }
    }

    public void onTabComplete(TabCompleteRequest request) {
        try {
            ParseResults<McmeCommandSender> result = commandDispatcher.parse(request.getCursor().substring(1), request.getSender());
            if(result.getContext().getNodes().isEmpty()) {
                return;
            }
            List<Suggestion> completionSuggestions
                    = commandDispatcher.getCompletionSuggestions(result).get().getList();
            request.getSuggestions().addAll(completionSuggestions.stream().map(Suggestion::getText).toList());
        } catch (InterruptedException | ExecutionException e) {
            request.getSender().sendMessage(plugin.createMessage().add("Command tab complete error."+e));
        }
    }

    private CommandNode<McmeCommandSender> findDirectChild(CommandNode<McmeCommandSender> root, String name) {
        for(CommandNode<McmeCommandSender> node: root.getChildren()) {
            if(node.getName().equals(name)) {//found != null) {
                return node;//found;
            }
        }
        return null;
    }

    private void printTree(CommandNode<McmeCommandSender> node) {
        Logger log = Logger.getLogger(this.getClass().getSimpleName());
        log.info(printNode(node, "", "  "));
    }

    private String printNode(CommandNode<McmeCommandSender> node, String message, String indentation) {
        message = message + indentation+node.getClass().getSimpleName()+" "+node.getName()
                +"\n"+indentation+"-use: "+node.getUsageText();
        if(node instanceof HelpfulNode) {
            message = message
                    +"\n"+indentation+"-help: "+((HelpfulNode)node).getHelpText()
                    +"\n"+indentation+"-tool: "+((HelpfulNode)node).getTooltip();
        }
        for(CommandNode<McmeCommandSender> child: node.getChildren()) {
            message = printNode(child,message+"\n",indentation+"    ");
        }
        return message;
    }

    public McmePlugin getPlugin() {
        return plugin;
    }
}
