package com.mcmiddleearth.base.core.command.argument;

import com.mcmiddleearth.base.core.message.Message;

public interface HelpfulArgumentType {

    void setTooltip(Message tooltip);

    default Message getTooltip() {
        return null;
    }
}
