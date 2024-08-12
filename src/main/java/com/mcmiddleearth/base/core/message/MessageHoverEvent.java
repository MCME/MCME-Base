package com.mcmiddleearth.base.core.message;

public record MessageHoverEvent(MessageHoverEvent.Action action,
                                Message message) {

    public static enum Action {

        TEXT;

    }
}
