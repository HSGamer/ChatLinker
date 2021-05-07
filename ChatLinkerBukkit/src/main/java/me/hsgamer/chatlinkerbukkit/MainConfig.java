package me.hsgamer.chatlinkerbukkit;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.StringConfigPath;
import org.bukkit.plugin.Plugin;

public class MainConfig extends PathableConfig {
    public static final StringConfigPath SERVER_NAME = new StringConfigPath("setting.server-name", "SERVERNAME");

    public static final StringConfigPath PREFIX = new StringConfigPath("message.prefix", "&f[&6ChatLinker&f] &r");
    public static final StringConfigPath GLOBAL_CHAT = new StringConfigPath("message.global-chat", "&7[&a{server}&7] &e{player}&f: &r{message}");
    public static final StringConfigPath PRIVATE_CHAT = new StringConfigPath("message.private-chat", "&7[&a{server}&7] &e{from_player} &e-> &e{to_player}&f: &r{message}");
    public static final StringConfigPath NO_PERMISSION = new StringConfigPath("message.no-permission", "&cYou don't have permission to do that");

    public MainConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "config.yml"));
    }
}
