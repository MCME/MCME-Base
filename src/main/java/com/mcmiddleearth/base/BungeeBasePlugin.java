package com.mcmiddleearth.base;

import com.mcmiddleearth.base.bungee.AbstractBungeePlugin;
import net.kyori.adventure.text.Component;

public class BungeeBasePlugin extends AbstractBungeePlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        getConsole().sendInfo(Component.text("Enabled on Bungee proxy!"));
    }

    @Override
    public Component getMessagePrefix() {
        return Component.text("[MCME-Base]");
    }
}
