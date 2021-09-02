package me.hsgamer.chatlinkerbungee;

import com.google.common.collect.ImmutableList;
import me.hsgamer.chatlinkerbungee.channel.GlobalChat;
import me.hsgamer.chatlinkerbungee.channel.PrivateChat;
import me.hsgamer.hscore.bungeecord.channel.Channel;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.List;

public final class ChatLinkerBungee extends Plugin {
    private final List<Channel> channels = ImmutableList.of(
            new GlobalChat(this),
            new PrivateChat(this)
    );

    @Override
    public void onEnable() {
        channels.forEach(Channel::register);
    }

    @Override
    public void onDisable() {
        channels.forEach(Channel::unregister);
    }
}
