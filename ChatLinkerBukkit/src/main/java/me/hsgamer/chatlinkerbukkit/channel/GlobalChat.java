package me.hsgamer.chatlinkerbukkit.channel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.hsgamer.chatlinkerbukkit.Channel;
import me.hsgamer.chatlinkerbukkit.MainConfig;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GlobalChat extends Channel {
    public GlobalChat(Plugin plugin) {
        super("chatlinker:global", plugin);
    }

    @Override
    public void handleMessage(Player player, byte[] data) {
        ByteArrayDataInput input = ByteStreams.newDataInput(data);
        String serverName = input.readUTF();
        String playerName = input.readUTF();
        String message = input.readUTF();
        String formattedMessage = MessageUtils.colorize(MainConfig.GLOBAL_CHAT.getValue()
                .replace("{server}", serverName)
                .replace("{player}", playerName)
                .replace("{message}", message));
        Bukkit.getOnlinePlayers().forEach(player1 -> MessageUtils.sendMessage(player1, formattedMessage));
    }
}
