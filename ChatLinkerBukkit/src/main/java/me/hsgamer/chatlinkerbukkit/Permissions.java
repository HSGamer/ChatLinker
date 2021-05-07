package me.hsgamer.chatlinkerbukkit;

import me.hsgamer.hscore.bukkit.utils.PermissionUtils;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions {
    public static final Permission GLOBAL_CHAT = PermissionUtils.createPermission("chatlinker.globalchat", null, PermissionDefault.TRUE);
    public static final Permission PRIVATE_CHAT = PermissionUtils.createPermission("chatlinker.privatechat", null, PermissionDefault.TRUE);

    private Permissions() {
        // EMPTY
    }
}
