package com.mcmiddleearth.base.core.message;

public record MessageClickEvent(MessageClickEvent.Action action, String content) {

    public static enum Action {

        OPEN_URL,
        COPY_TO_CLIPBOARD,
        SUGGEST_COMMAND,
        RUN_COMMAND;
    }
}
