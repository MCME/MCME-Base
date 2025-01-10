package com.mcmiddleearth.base.bungee.command;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.adventure.AdventureUtils;
import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.message.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.Arrays;
import java.util.logging.Logger;

public abstract class BungeeMcmeCommandSender implements McmeCommandSender {

    @Override
    public boolean hasPermission(String permissionNode) {
        CommandSender sender = getBungeeCommandSender();
        return sender!=null && sender.hasPermission(permissionNode);
    }

    @Override
    public String getName() {
        CommandSender sender = getBungeeCommandSender();
        return sender != null? sender.getName() : "NULL";
    }

    @Override
    public void sendMessage(Message message) {
        Component component = ((AdventureMessage)message).getComponent();
        TextColor baseColor = component.color() != null ? component.color() : NamedTextColor.WHITE;
        BaseComponent[] baseComponents = BungeeComponentSerializer.get()
                .serialize(AdventureUtils.addBaseColor(baseColor, component));
        JSONComponentSerializer.json().serialize(AdventureUtils.addBaseColor(baseColor, component));
        //commandSender.
//Logger.getGlobal().info("Sender: "+commandSender.toString());
//Arrays.stream(baseComponents).forEach(comp -> Logger.getGlobal().info(comp.toString()));
//Arrays.stream(baseComponents).forEach(comp -> Logger.getGlobal().info(comp.toLegacyText()));
        if(getBungeeCommandSender() != null) {
            Arrays.stream(baseComponents).forEach(comp -> getBungeeCommandSender().sendMessage(comp.toLegacyText()));
        } else {
            Logger.getGlobal().info("Null command sender!!!");
        }
        //commandSender.sendMessage(baseComponents);
    }

    protected abstract CommandSender getBungeeCommandSender();
}
