package me.hsgamer.chatlinkerbukkit.channel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hsgamer.hscore.bukkit.channel.BungeeSubChannel;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GlobalPlayerList extends BungeeSubChannel implements Runnable {
    private final Set<String> players = new HashSet<>();

    public GlobalPlayerList(Plugin plugin) {
        super("PlayerList", plugin);
    }

    @Override
    public void handleSubChannelMessage(Player player, ByteArrayDataInput dataInput) {
        String server = dataInput.readUTF();
        List<String> list = Arrays.asList(dataInput.readUTF().split(", "));
        if (server.equalsIgnoreCase("all")) {
            players.removeIf(s -> !list.contains(s));
        }
        players.addAll(list);
    }

    public Set<String> getPlayers() {
        return players;
    }

    @Override
    public void run() {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("ALL");
        send(output.toByteArray());
    }
}
