package me.hsgamer.chatlinkerbukkit.command;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hsgamer.chatlinkerbukkit.ChatLinkerBukkit;
import me.hsgamer.chatlinkerbukkit.MainConfig;
import me.hsgamer.chatlinkerbukkit.Permissions;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class GlobalChatCommand extends Command {
    private final ChatLinkerBukkit instance;

    public GlobalChatCommand(ChatLinkerBukkit instance) {
        super("globalchat", "Global chat", "/privatechat <message>", Arrays.asList("gc", "chat"));
        this.instance = instance;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(Permissions.GLOBAL_CHAT)) {
            MessageUtils.sendMessage(sender, MainConfig.NO_PERMISSION.getValue());
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
