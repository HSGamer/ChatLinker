package me.hsgamer.chatlinkerbungee.channel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hsgamer.chatlinkerbungee.Channel;
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

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF(serverName);
        output.writeUTF(playerName);
        output.writeUTF(message);

        sendAll(output.toByteArray());
    }
}
