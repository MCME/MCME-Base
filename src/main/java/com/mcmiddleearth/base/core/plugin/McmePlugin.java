package com.mcmiddleearth.base.core.plugin;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import net.kyori.adventure.text.Component;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface McmePlugin {

    Component getMessagePrefix();

    Task getTask(Runnable runnable);

    McmeCommandSender getConsole();

    File getDataFolder();
    default void saveDefaultConfig(File configFile) {
        if(!configFile.getParentFile().exists()) {
            if(!configFile.getParentFile().mkdir()) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Creation of plugin data folder failed!");
            }
        }
        InputStream inputStream = this.getClass().getResourceAsStream(configFile.getName());
        if(!configFile.exists()) {
            if(inputStream==null) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "resource "+configFile.getName()+" not found in plugin jar");
            } else {
                try {
                    if (configFile.createNewFile()) {
                        try (InputStreamReader in = new InputStreamReader(inputStream);
                             FileWriter fw = new FileWriter(configFile)) {
                            char[] buf = new char[1024];
                            int read = 1;
                            while (read > 0) {
                                read = in.read(buf);
                                if (read > 0)
                                    fw.write(buf, 0, read);
                            }
                            fw.flush();
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }


}
