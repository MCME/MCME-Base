package com.mcmiddleearth.base;

import com.mcmiddleearth.base.bukkit.AbstractPaperPlugin;
import net.kyori.adventure.text.Component;

public class PaperBasePlugin extends AbstractPaperPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        getConsole().sendInfo(Component.text("Enabled on Paper server!"));
    }

    @Override
    public Component getMessagePrefix() {
        return Component.text("[MCME-Base]");
    }

}
