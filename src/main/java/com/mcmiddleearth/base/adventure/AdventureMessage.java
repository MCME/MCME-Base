package com.mcmiddleearth.base.adventure;

import com.mcmiddleearth.base.core.message.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

public class AdventureMessage implements Message {

    private Component component;

    public AdventureMessage() {
        component = Component.empty();
    }

    public AdventureMessage(MessageStyle defaultStyle) {
        component = Component.empty().style(AdventureUtils.buildStyle(defaultStyle));
    }

    public AdventureMessage(Component component) {
        this.component = component;
    }

    public Message setDefaultStyle(MessageStyle defaultStyle) {
        component = component.style(AdventureUtils.buildStyle(defaultStyle));
        return this;
    }

    @Override
    public Message add(String message) {
        component = component.append(Component.text(message));//.style(AdventureUtils.buildStyle(defaultStyle)));
        return this;
    }

    @Override
    public Message add(String message, MessageStyle style) {
        component = component.append(Component.text(message).style(AdventureUtils.buildStyle(style)));
        return this;
    }

    @Override
    public Message add(String message, MessageColor color) {
        component = component.append(Component.text(message)
                                    .color(AdventureUtils.buildColor(color)));
                                  //.decorate(AdventureUtils.buildDecorations(defaultStyle.getDecorations())));
        return this;
    }

    @Override
    public Message add(String message, MessageDecoration... decorations) {
        component = component.append(Component.text(message)
                                    //.color(AdventureUtils.buildColor(defaultStyle.getColor()))
                                    .decorate(AdventureUtils.buildDecorations(decorations)));
        return this;
    }

    @Override
    public Message add(String message, MessageColor color, MessageDecoration... decorations) {
        component = component.append(Component.text(message)
                                    .color(AdventureUtils.buildColor(color))
                                    .decorate(AdventureUtils.buildDecorations(decorations)));
        return this;
    }

    @Override
    public Message add(Message message) {
        component = component.append(((AdventureMessage)message).getComponent());
        return this;
    }

    @Override
    public Message addHover(MessageHoverEvent hoverEvent) {
        if(hoverEvent.action().equals(MessageHoverEvent.Action.TEXT)) {
            component = component.hoverEvent(HoverEvent.showText(((AdventureMessage)hoverEvent.message()).getComponent()));
        }
        return this;
    }

    @Override
    public Message addClick(MessageClickEvent messageClickEvent) {
        switch(messageClickEvent.action()) {
            case OPEN_URL -> component = component.clickEvent(ClickEvent.openUrl(messageClickEvent.content()));
            case COPY_TO_CLIPBOARD -> component = component.clickEvent(ClickEvent.copyToClipboard(messageClickEvent.content()));
            case RUN_COMMAND -> component = component.clickEvent(ClickEvent.runCommand(messageClickEvent.content()));
            case SUGGEST_COMMAND -> component = component.clickEvent(ClickEvent.suggestCommand(messageClickEvent.content()));
        }
        return this;
    }

    public Component getComponent() {
        return component;
    }

    @Override
    public boolean isEmpty() {
        return component.equals(Component.empty());
    }
}
