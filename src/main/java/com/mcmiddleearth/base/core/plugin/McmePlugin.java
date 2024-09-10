package com.mcmiddleearth.base.core.plugin;

import com.mcmiddleearth.base.adventure.AdventureMessage;
import com.mcmiddleearth.base.core.logger.McmeLogger;
import com.mcmiddleearth.base.core.message.McmeColors;
import com.mcmiddleearth.base.core.message.Message;
import com.mcmiddleearth.base.core.message.MessageStyle;
import com.mcmiddleearth.base.core.scoreboard.ScoreboardManager;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

import java.io.*;

public interface McmePlugin {

    Message getMessagePrefix();

    Task getTask(Runnable runnable);

    File getDataFolder();

    McmeLogger getMcmeLogger();

    default Message createInfoMessage() {
        return createPrefixedMessage(new MessageStyle(McmeColors.INFO, MessageStyle.DEFAULT_DECORATION));
    }
    default Message createErrorMessage() {
        return createPrefixedMessage(new MessageStyle(McmeColors.ERROR, MessageStyle.DEFAULT_DECORATION));
    }
    default Message createPrefixedMessage(MessageStyle defaultStyle) {
        return getMessagePrefix().setDefaultStyle(defaultStyle);
    }
    default Message createMessage() {
        return createMessage(MessageStyle.DEFAULT);
    }

    default Message createMessage(MessageStyle defaultStyle) {
        return new AdventureMessage(defaultStyle);
    }

    default Message emptyMessage() { return new AdventureMessage(); }

    default Message deserializeMessage(String message) {
        return new AdventureMessage(GsonComponentSerializer.gson().deserialize(message));
    }

    default String serializeMessage(Message message) {
        return GsonComponentSerializer.gson().serialize(((AdventureMessage)message).getComponent());
    }

    default void saveResourceToFile(String resource, File file) {
        if(!file.getParentFile().exists()) {
            if(!file.getParentFile().mkdir()) {
                getMcmeLogger().error("Creation of plugin data folder failed!");
            }
        }
        InputStream inputStream = this.getClass().getResourceAsStream("/"+resource);

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
