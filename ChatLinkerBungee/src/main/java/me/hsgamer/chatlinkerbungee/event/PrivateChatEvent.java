package me.hsgamer.chatlinkerbungee.event;

import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public class PrivateChatEvent extends Event implements Cancellable {
    private final String serverName;
    private final String fromPlayer;
    private final String toPlayer;
    private boolean cancelled = false;
    private String message;

    public PrivateChatEvent(String serverName, String fromPlayer, String toPlayer, String message) {
        this.serverName = serverName;
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
        this.message = message;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public String getServerName() {
        return serverName;
    }

    public String getFromPlayer() {
        return fromPlayer;
    }

    public String getToPlayer() {
        return toPlayer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
