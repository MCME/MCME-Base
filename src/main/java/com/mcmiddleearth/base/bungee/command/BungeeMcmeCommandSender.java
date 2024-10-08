package com.mcmiddleearth.base.bungee.command;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.adventure.AdventureUtils;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;

public class BungeeMcmeCommandSender implements McmeCommandSender {

    private final CommandSender commandSender;

    public BungeeMcmeCommandSender(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public boolean hasPermission(String permissionNode) {
        return commandSender.hasPermission(permissionNode);
    }

    @Override
    public String getName() {
        return commandSender.getName();
    }

    @Override
    public void sendMessage(Message message) {
        Component component = ((AdventureMessage)message).getComponent();
        TextColor baseColor = component.color() != null ? component.color() : NamedTextColor.WHITE;
        BaseComponent[] baseComponents = BungeeComponentSerializer.get()
                .serialize(AdventureUtils.addBaseColor(baseColor, component));
        //Logger.getGlobal().info("Sender: "+commandSender.toString());
        //Arrays.stream(baseComponents).forEach(component -> Logger.getGlobal().info(component.toLegacyText()));
        commandSender.sendMessage(baseComponents);
    }
}
