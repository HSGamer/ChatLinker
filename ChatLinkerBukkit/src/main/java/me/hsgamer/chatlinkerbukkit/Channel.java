package me.hsgamer.chatlinkerbukkit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public abstract class Channel implements PluginMessageListener {
    private final String name;
    private final Plugin plugin;

    protected Channel(String name, Plugin plugin) {
        this.name = name;
        this.plugin = plugin;
    }

    public void register() {
        this.plugin.getServer().getMessenger().registerIncomingPluginChannel(this.plugin, this.name, this);
        this.plugin.getServer().getMessenger().registerOutgoingPluginChannel(this.plugin, this.name);
    }

    public void unregister() {
        this.plugin.getServer().getMessenger().unregisterIncomingPluginChannel(this.plugin, this.name, this);
        this.plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(this.plugin, this.name);
    }

    public abstract void handleMessage(Player player, byte[] data);

    public void send(byte[] data) {
        this.plugin.getServer().sendPluginMessage(this.plugin, this.name, data);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] data) {
        if (channel.equalsIgnoreCase(this.name)) {
            handleMessage(player, data);
        }
    }

    public String getName() {
        return name;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
