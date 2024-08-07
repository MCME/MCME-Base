package com.mcmiddleearth.base.core.permission;

import java.util.HashMap;
import java.util.Map;

public class McmePermissionData {

    private String permissionNode;

    private String descripion;

    private Map<String, Boolean> children;

    private McmePermissionDefault permissionDefault;

    public McmePermissionData(String permissionNode, String description, McmePermissionDefault permissionDefault) {
        this.permissionNode = permissionNode;
        this.permissionDefault = permissionDefault;
        children = new HashMap<>();
    }

    public McmePermissionData addChild(String node, boolean inheritUnchanged) {
        children.put(node, inheritUnchanged);
        return this;
    }

    public McmePermissionData addChild(McmePermissionData permissionData, boolean inheritUnchanged) {
        children.put(permissionData.permissionNode, inheritUnchanged);
        return this;
    }

    public String getPermissionNode() {
        return permissionNode;
    }

    public String getDescription() {
        return descripion;
    }

    public Map<String, Boolean> getChildren() {
        return children;
    }

    public McmePermissionDefault getPermissionDefault() {
        return permissionDefault;
    }
}
