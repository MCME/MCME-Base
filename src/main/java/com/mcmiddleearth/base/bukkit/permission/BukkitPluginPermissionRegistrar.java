package com.mcmiddleearth.base.bukkit.permission;

import com.mcmiddleearth.base.core.permission.McmePluginPermissionRegistrar;
import io.papermc.paper.plugin.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;

public abstract class BukkitPluginPermissionRegistrar implements McmePluginPermissionRegistrar {

    public void registerPermissions(Plugin plugin) {
        PermissionManager permissionManager = Bukkit.getPluginManager();
        getPermissionData().forEach(permissionData -> {
            PermissionDefault permissionDefault = switch (permissionData.getPermissionDefault()) {
                case TRUE -> PermissionDefault.TRUE;
                case FALSE -> PermissionDefault.FALSE;
                case OP -> PermissionDefault.OP;
                case NOT_OP -> PermissionDefault.NOT_OP;
            };
            Permission permission = new Permission(permissionData.getPermissionNode(),
                    permissionData.getDescription(),
                    permissionDefault,
                    permissionData.getChildren());
            permissionManager.addPermission(permission);
        });
    }
}
