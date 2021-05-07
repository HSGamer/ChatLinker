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

public class PrivateChatCommand extends Command {
    private final ChatLinkerBukkit instance;

    public PrivateChatCommand(ChatLinkerBukkit instance) {
        super("privatechat", "Private chat", "/privatechat <player> <message>", Arrays.asList("pc", "whisper"));
        this.instance = instance;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(Permissions.PRIVATE_CHAT)) {
            MessageUtils.sendMessage(sender, MainConfig.NO_PERMISSION.getValue());
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
        MessageUtils.sendMessage(sender, MainConfig.PRIVATE_CHAT.getValue()
                .replace("{server}", MainConfig.SERVER_NAME.getValue())
                .replace("{from_player}", sender.getName())
                .replace("{to_player}", player)
                .replace("{message}", message)
        );
        return true;
    }
}
