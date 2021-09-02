package me.hsgamer.chatlinkerbukkit.command;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hsgamer.chatlinkerbukkit.ChatLinkerBukkit;
import me.hsgamer.chatlinkerbukkit.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.Collections;

public class GlobalChatCommand extends Command {
    private static final Permission PERMISSION = new Permission("chatlinker.globalchat", PermissionDefault.TRUE);

    static {
        Bukkit.getPluginManager().addPermission(PERMISSION);
    }

    private final ChatLinkerBukkit instance;

    public GlobalChatCommand(ChatLinkerBukkit instance) {
        super("globalchat", "Global chat", "/globalchat <message>", Collections.singletonList("gc"));
        this.instance = instance;
        setPermission(PERMISSION.getName());
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        String message = String.join(" ", args);
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF(MainConfig.SERVER_NAME.getValue());
        output.writeUTF(sender.getName());
        output.writeUTF(message);
        instance.getGlobalChat().send(output.toByteArray());
        return true;
    }
}
