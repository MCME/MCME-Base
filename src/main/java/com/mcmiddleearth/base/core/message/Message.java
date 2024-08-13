package com.mcmiddleearth.base.core.message;

public interface Message {

    Message setDefaultStyle(MessageStyle defaultStyle);

    Message add(Message message);

    Message add(String message);
    Message add(String message, MessageStyle style);
    Message add(String message, MessageColor color);
    Message add(String message, MessageDecoration... decorations);
    Message add(String message, MessageColor color, MessageDecoration... decorations);

    Message addHover(MessageHoverEvent messageHoverEvent);
    Message addClick(MessageClickEvent messageClickEvent);

    boolean isEmpty();
}
