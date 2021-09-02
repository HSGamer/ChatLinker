package me.hsgamer.chatlinkerbungee.event;

import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public class GlobalChatEvent extends Event implements Cancellable {
    private final String serverName;
    private final String playerName;
    private boolean cancelled = false;
    private String message;

    public GlobalChatEvent(String serverName, String playerName, String message) {
        this.serverName = serverName;
        this.playerName = playerName;
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

    public String getPlayerName() {
        return playerName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
