package com.mcmiddleearth.base.bungee;

import com.mcmiddleearth.base.bungee.logger.BungeeMcmeLogger;
import com.mcmiddleearth.base.bungee.server.BungeeMcmeProxy;
import com.mcmiddleearth.base.bungee.taskScheduling.BungeeTask;
import com.mcmiddleearth.base.core.logger.McmeLogger;
import com.mcmiddleearth.base.core.plugin.McmeProxyPlugin;
import com.mcmiddleearth.base.core.server.McmeProxy;
import com.mcmiddleearth.base.core.taskScheduling.Task;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Subclasses MUST call onEnable() as first statement of their own onEnable() method.
 */
public abstract class AbstractBungeePlugin extends Plugin implements McmeProxyPlugin {

    private BungeeAudiences adventure;
    private McmeLogger mcmeLogger;

    private final BungeeMcmeProxy mcmeProxy = new BungeeMcmeProxy(this);

    @Override
    public final void onEnable() {
        adventure = BungeeAudiences.create(this);
        mcmeLogger = new BungeeMcmeLogger(getLogger());
        mcmeLogger.info("Enabling plugin on Bungee proxy!");
        enable();
        mcmeLogger.info("Plugin enabled!");
    }

    @Override
    public final void onDisable() {
        mcmeLogger.info("Disabling plugin!");
        disable();
        mcmeLogger.info("Plugin disabled!");
        adventure.close();
    }

    public abstract void enable();
    public abstract void disable();

    public final Task getTask(Runnable task) {
        return new BungeeTask(this, task);
    }


    public BungeeAudiences getAdventure() {
            return adventure;
    }

    @Override
    public final McmeProxy getMcmeProxy() {
        return mcmeProxy;
    }

    @Override
    public final McmeLogger getMcmeLogger() {
        return mcmeLogger;
    }

    /*public static McmeCommandSender wrapCommandSender(CommandSender sender){
        if(sender instanceof ProxiedPlayer player) {
            BungeeMcmePlayer mcmePlayer = new BungeePluginPlayer(player);
            if(players.contains(mcmePlayer)) {
                //todo faster with hashing
                return players.stream().filter(search -> search.equals(mcmePlayer))
                        .findAny().orElse(null);
                //todo removing players who left or listener
            } else {
                players.add(mcmePlayer);
                return mcmePlayer;
            }
        } else {
            return new BungeeMcmeCommandSender(sender);
        }
    }

    private static class BungeePluginPlayer extends BungeeMcmePlayer {

        public BungeePluginPlayer(ProxiedPlayer player) {
            super(player);
        }
    }*/

}
