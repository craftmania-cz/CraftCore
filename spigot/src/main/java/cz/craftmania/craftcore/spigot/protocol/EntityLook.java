package cz.craftmania.craftcore.spigot.protocol;

import cz.craftmania.craftcore.core.utils.Group;
import cz.craftmania.craftcore.spigot.utils.GameVersion;
import cz.craftmania.craftcore.spigot.utils.reflections.ReflectionUtils;

public class EntityLook {

    public static PacketSender create(int id, byte yaw, byte pitch, boolean ground) {
        String v = GameVersion.getVersion().toString();
        try {
            Class<?> packetPlayOutEntityTeleportClass = Class.forName("net.minecraft.server." + v + (GameVersion.getVersion().equals(GameVersion.v1_8_R1) ? "." : ".PacketPlayOutEntity$") + "PacketPlayOutEntityLook");
            return new PacketSender(ReflectionUtils.getConstructor(packetPlayOutEntityTeleportClass, new Group<>(
                    new Class<?>[]{int.class, byte.class, byte.class, boolean.class},
                    new Object[]{id, yaw, pitch, ground}
            )));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
