package com.hewid.alpheus.GameEngine;

import com.hewid.alpheus.GameEngine.InteractionEvent;

public interface InteractionEventHandler {
    boolean handleInteractionEvent(InteractionEvent event);
}
