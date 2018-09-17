package cz.wake.craftcore.protocol;

import cz.wake.craftcore.utils.Group;
import cz.wake.craftcore.utils.GameVersion;
import cz.wake.craftcore.utils.reflections.ReflectionUtils;
import org.bukkit.entity.HumanEntity;

public class NamedEntitySpawn {
    public static PacketSender create(Object nmsEntityHuman) {
        String v = GameVersion.getVersion().toString();
        try {
            Class<?> nmsEntityHumanClass = Class.forName("net.minecraft.server." + v + ".EntityHuman");
            Class<?> packetPlayOutNamedEntitySpawnClass = Class.forName("net.minecraft.server." + v + ".PacketPlayOutNamedEntitySpawn");
            return new PacketSender(ReflectionUtils.getConstructor(packetPlayOutNamedEntitySpawnClass, new Group<>(
                    new Class<?>[]{nmsEntityHumanClass},
                    new Object[]{nmsEntityHuman}
            )));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PacketSender create(HumanEntity humanEntity) {
        String v = GameVersion.getVersion().toString();
        try {
            Class<?> craftHumanEntityClass = Class.forName("org.bukkit.craftbukkit." + v + ".entity.CraftHumanEntity");
            Class<?> nmsEntityHumanClass = Class.forName("net.minecraft.server." + v + ".EntityHuman");
            Class<?> packetPlayOutNamedEntitySpawnClass = Class.forName("net.minecraft.server." + v + ".PacketPlayOutNamedEntitySpawn");
            Object craftEntityHuman = ReflectionUtils.cast(craftHumanEntityClass, humanEntity);
            Object nmsEntityHuman = ReflectionUtils.getMethod("getHandle", craftHumanEntityClass, craftEntityHuman);
            return new PacketSender(ReflectionUtils.getConstructor(packetPlayOutNamedEntitySpawnClass, new Group<>(
                    new Class<?>[]{nmsEntityHumanClass},
                    new Object[]{nmsEntityHuman}
            )));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
