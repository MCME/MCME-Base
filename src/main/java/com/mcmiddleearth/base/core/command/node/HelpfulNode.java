package com.mcmiddleearth.base.core.command.node;

import com.mcmiddleearth.base.core.message.Message;

public interface HelpfulNode {

    Message getTooltip();

    String getHelpText();

    void setHelpText(String helpText);

}
