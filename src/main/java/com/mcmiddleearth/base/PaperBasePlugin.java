package com.mcmiddleearth.base;

import com.mcmiddleearth.base.bukkit.AbstractPaperPlugin;
import com.mcmiddleearth.base.core.message.Message;
import net.kyori.adventure.text.Component;

public class PaperBasePlugin extends AbstractPaperPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        getMcmeBackend().getConsole().sendMessage(createInfoMessage().add("Enabled on Paper server!"));
    }

    @Override
    public Message getMessagePrefix() {
        return createMessage().add("[MCME-Base] ");
    }

}
