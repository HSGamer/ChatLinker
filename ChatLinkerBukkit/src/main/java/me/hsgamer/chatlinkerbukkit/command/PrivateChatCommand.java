package me.hsgamer.chatlinkerbukkit.command;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hsgamer.chatlinkerbukkit.ChatLinkerBukkit;
import me.hsgamer.chatlinkerbukkit.MainConfig;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.*;
import java.util.stream.Collectors;

public class PrivateChatCommand extends Command {
    private static final Permission PERMISSION = new Permission("chatlinker.privatechat", PermissionDefault.TRUE);

    static {
        Bukkit.getPluginManager().addPermission(PERMISSION);
    }

    private final ChatLinkerBukkit instance;

    public PrivateChatCommand(ChatLinkerBukkit instance) {
        super("privatechat", "Private chat", "/privatechat <player> <message>", Collections.singletonList("pc"));
        this.instance = instance;
        setPermission(PERMISSION.getName());
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        if (args.length < 2) {
            MessageUtils.sendMessage(sender, getUsage());
            return false;
        }
        String player = args[0];
        String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF(MainConfig.SERVER_NAME.getValue());
        output.writeUTF(sender.getName());
        output.writeUTF(player);
        output.writeUTF(message);
        instance.getPrivateChat().send(output.toByteArray());
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            String player = args[0].trim().toLowerCase(Locale.ROOT);
            Set<String> players = instance.getGlobalPlayerList().getPlayers();
            if (player.isEmpty()) {
                return new ArrayList<>(players);
            } else {
                return players.stream().filter(s -> s.toLowerCase(Locale.ROOT).startsWith(player)).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}
