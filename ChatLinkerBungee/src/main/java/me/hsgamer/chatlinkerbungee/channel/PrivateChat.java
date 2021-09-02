package me.hsgamer.chatlinkerbungee.channel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hsgamer.chatlinkerbungee.event.PrivateChatEvent;
import me.hsgamer.hscore.bungeecord.channel.Channel;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Optional;

public class PrivateChat extends Channel {
    public PrivateChat(Plugin plugin) {
        super("chatlinker:private", plugin);
    }

    @Override
    public void handleMessage(PluginMessageEvent event) {
        ByteArrayDataInput input = ByteStreams.newDataInput(event.getData());
        String serverName = input.readUTF();
        String fromPlayer = input.readUTF();
        String toPlayer = input.readUTF();
        String message = input.readUTF();

        PrivateChatEvent privateChatEvent = new PrivateChatEvent(serverName, fromPlayer, toPlayer, message);
        getPlugin().getProxy().getPluginManager().callEvent(privateChatEvent);
        if (privateChatEvent.isCancelled()) {
            return;
        }

        Optional.ofNullable(getPlugin().getProxy().getPlayer(toPlayer))
                .ifPresent(proxiedPlayer -> {
                    ByteArrayDataOutput output = ByteStreams.newDataOutput();
                    output.writeUTF(privateChatEvent.getServerName());
                    output.writeUTF(privateChatEvent.getFromPlayer());
                    output.writeUTF(privateChatEvent.getToPlayer());
                    output.writeUTF(privateChatEvent.getMessage());
                    send(proxiedPlayer.getServer().getInfo(), output.toByteArray());
                });
    }
}
