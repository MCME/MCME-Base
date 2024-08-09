package com.mcmiddleearth.base.core.plugin;

import com.mcmiddleearth.base.core.command.McmeCommandSender;
import com.mcmiddleearth.base.core.logger.McmeLogger;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import net.kyori.adventure.text.Component;

import java.io.*;

public interface McmePlugin {

    Component getMessagePrefix();

    Task getTask(Runnable runnable);

    McmeCommandSender getConsole();

    File getDataFolder();

    McmeLogger getMcmeLogger();

    default void saveResourceToFile(String resource, File file) {
        if(!file.getParentFile().exists()) {
            if(!file.getParentFile().mkdir()) {
                getMcmeLogger().error("Creation of plugin data folder failed!");
            }
        }
        InputStream inputStream = this.getClass().getResourceAsStream(resource);
        if(!file.exists()) {
            if(inputStream==null) {
                getMcmeLogger().error("resource "+resource+" not found in plugin jar");
            } else {
                try {
                    if (file.createNewFile()) {
                        try (InputStreamReader in = new InputStreamReader(inputStream);
                             FileWriter fw = new FileWriter(file)) {
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
                    getMcmeLogger().error("IOException: "+ ex.getLocalizedMessage());
                }
            }
        }
    }


}
