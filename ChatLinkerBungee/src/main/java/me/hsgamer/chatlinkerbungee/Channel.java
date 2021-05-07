package me.hsgamer.chatlinkerbungee;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Plugin;

public abstract class Channel {
    private final String name;
    private final Plugin plugin;

    protected Channel(String name, Plugin plugin) {
        this.name = name;
        this.plugin = plugin;
    }

    public void register() {
        this.plugin.getProxy().registerChannel(this.name);
    }

    public void unregister() {
        this.plugin.getProxy().unregisterChannel(this.name);
    }

    public abstract void handleMessage(PluginMessageEvent event);

    public void sendAll(byte[] data) {
        this.plugin.getProxy().getServers().values().forEach(serverInfo -> send(serverInfo, data));
    }

    public void send(ServerInfo server, byte[] data) {
        server.sendData(this.name, data);
    }

    public String getName() {
        return name;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
