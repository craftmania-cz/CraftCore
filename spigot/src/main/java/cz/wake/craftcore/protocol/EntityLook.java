package cz.wake.craftcore.protocol;

import cz.wake.craftcore.utils.Group;
import cz.wake.craftcore.utils.GameVersion;
import cz.wake.craftcore.utils.reflections.ReflectionUtils;

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
