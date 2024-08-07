package com.mcmiddleearth.base.core.plugin;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import net.kyori.adventure.text.Component;

import java.io.File;

public interface McmePlugin {

    Component getMessagePrefix();

    Task getTask(Runnable runnable);

    McmeCommandSender getConsole();

    File getDataFolder();

}
