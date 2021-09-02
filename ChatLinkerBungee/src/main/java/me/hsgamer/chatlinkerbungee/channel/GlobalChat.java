package me.hsgamer.chatlinkerbungee.channel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hsgamer.chatlinkerbungee.event.GlobalChatEvent;
import me.hsgamer.hscore.bungeecord.channel.Channel;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Plugin;

public class GlobalChat extends Channel {
    public GlobalChat(Plugin plugin) {
        super("chatlinker:global", plugin);
    }

    @Override
    public void handleMessage(PluginMessageEvent event) {
        ByteArrayDataInput input = ByteStreams.newDataInput(event.getData());
        String serverName = input.readUTF();
        String playerName = input.readUTF();
        String message = input.readUTF();

        GlobalChatEvent globalChatEvent = new GlobalChatEvent(serverName, playerName, message);
        getPlugin().getProxy().getPluginManager().callEvent(globalChatEvent);
        if (globalChatEvent.isCancelled()) {
            return;
        }

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF(globalChatEvent.getServerName());
        output.writeUTF(globalChatEvent.getPlayerName());
        output.writeUTF(globalChatEvent.getMessage());

        sendAll(output.toByteArray(), true);
    }
}
