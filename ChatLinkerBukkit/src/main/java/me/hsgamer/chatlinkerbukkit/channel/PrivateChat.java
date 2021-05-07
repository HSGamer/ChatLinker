package me.hsgamer.chatlinkerbukkit.channel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.hsgamer.chatlinkerbukkit.Channel;
import me.hsgamer.chatlinkerbukkit.MainConfig;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

public class PrivateChat extends Channel {
    public PrivateChat(Plugin plugin) {
        super("chatlinker:private", plugin);
    }

    @Override
    public void handleMessage(Player player, byte[] data) {
        ByteArrayDataInput input = ByteStreams.newDataInput(data);
        String serverName = input.readUTF();
        String fromPlayer = input.readUTF();
        String toPlayer = input.readUTF();
        String message = input.readUTF();

        Optional.ofNullable(Bukkit.getPlayer(toPlayer))
                .ifPresent(player1 -> MessageUtils.sendMessage(player1, MainConfig.PRIVATE_CHAT.getValue()
                        .replace("{server}", serverName)
                        .replace("{from_player}", fromPlayer)
                        .replace("{to_player}", toPlayer)
                        .replace("{message}", message)
                ));
    }
}
