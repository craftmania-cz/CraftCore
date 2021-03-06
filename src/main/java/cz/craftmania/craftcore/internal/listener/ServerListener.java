package cz.craftmania.craftcore.internal.listener;

import cz.craftmania.craftcore.events.ServerReloadEvent;
import cz.craftmania.craftcore.events.ServerStopEvent;
import cz.craftmania.craftcore.nms.NMSManager;
import cz.craftmania.craftcore.utils.reflections.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

public class ServerListener implements Listener {
    private static int reloadCountTracker;
    private static boolean isStopping = false;

    public ServerListener() {
        try {
            Class<?> craftServerClass = Class.forName("org.bukkit.craftbukkit." + NMSManager.getVersion() + ".CraftServer");
            Object craftServer = ReflectionUtils.cast(craftServerClass, Bukkit.getServer());
            reloadCountTracker = (int) ReflectionUtils.getField("reloadCount", craftServerClass, craftServer);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void stop(PluginDisableEvent event) {
        if (isStopping) {
            return;
        }
        try {
            Class<?> craftServerClass = Class.forName("org.bukkit.craftbukkit." + NMSManager.getVersion() + ".CraftServer");
            Class<?> nmsServerClass = Class.forName("net.minecraft.server." + NMSManager.getVersion() + ".MinecraftServer");
            Object craftServer = ReflectionUtils.cast(craftServerClass, Bukkit.getServer());
            int reloadCount = (int) ReflectionUtils.getField("reloadCount", craftServerClass, craftServer);
            Object nmsServer = ReflectionUtils.getMethod("getServer", craftServerClass, craftServer);
            boolean isRunning = (boolean) ReflectionUtils.getMethod("isRunning", nmsServerClass, nmsServer);
            if (isRunning) {
                if (reloadCountTracker < reloadCount) {
                    ServerReloadEvent ev = new ServerReloadEvent();
                    Bukkit.getServer().getPluginManager().callEvent(ev);
                    reloadCountTracker = reloadCount;
                }
            } else {
                ServerStopEvent ev = new ServerStopEvent();
                Bukkit.getServer().getPluginManager().callEvent(ev);
                isStopping = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
