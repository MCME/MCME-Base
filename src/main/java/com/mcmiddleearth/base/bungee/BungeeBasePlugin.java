package com.mcmiddleearth.base.bungee;

import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeBasePlugin extends Plugin {

    BungeeAudiences adventure;

    @Override
    public void onEnable() {
        adventure = BungeeAudiences.create(this);
        adventure.console().sendMessage(Component.text("MCME-Base enabled!!!"));
    }
}
