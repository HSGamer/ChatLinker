package me.hsgamer.chatlinkerbungee;

import com.google.common.collect.ImmutableList;
import me.hsgamer.chatlinkerbungee.channel.GlobalChat;
import me.hsgamer.chatlinkerbungee.channel.PrivateChat;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

public final class ChatLinkerBungee extends Plugin implements Listener {
    private final List<Channel> channels = ImmutableList.of(
            new GlobalChat(this),
            new PrivateChat(this)
    );

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, this);
        channels.forEach(Channel::register);
    }

    @Override
    public void onDisable() {
        channels.forEach(Channel::unregister);
    }

    @EventHandler
    public void onReceive(PluginMessageEvent event) {
        String tag = event.getTag();
        channels.stream()
                .filter(channel -> channel.getName().equalsIgnoreCase(tag))
                .forEach(channel -> channel.handleMessage(event));
    }
}
