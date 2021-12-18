package com.kdyncs.dragonsong.common.event;

import org.springframework.context.ApplicationEvent;

public class ShutdownEvent extends ApplicationEvent {

    public ShutdownEvent(Object source) {
        super(source);
    }
}
