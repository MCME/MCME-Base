package com.mcmiddleearth.base;

import com.mcmiddleearth.base.bungee.AbstractBungeePlugin;
import com.mcmiddleearth.base.core.message.Message;

public class BungeeBasePlugin extends AbstractBungeePlugin {

    /*@Override
    public void onEnable() {
        super.onEnable();

        getMcmeProxy().getConsole().sendMessage(createInfoMessage().add("Enabled on Bungee proxy!"));
    }*/

    @Override
    public void enable() {
        //nothing to do
    }

    @Override
    public void disable() {
        //nothing to do
    }


    @Override
    public Message getMessagePrefix() {
        return createMessage().add("[MCME-Base] ");
    }
}
