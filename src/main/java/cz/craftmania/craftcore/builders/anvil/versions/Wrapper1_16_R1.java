package cz.craftmania.craftcore.builders.anvil.versions;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.chat.ChatMessage;
import net.minecraft.network.protocol.game.PacketPlayOutCloseWindow;
import net.minecraft.network.protocol.game.PacketPlayOutOpenWindow;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.inventory.Container;
import net.minecraft.world.inventory.ContainerAccess;
import net.minecraft.world.inventory.ContainerAnvil;
import net.minecraft.world.inventory.Containers;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
/*

public class Wrapper1_16_R1 implements VersionWrapper {

    private final boolean IS_ONE_FIFTEEN = Bukkit.getBukkitVersion().contains("1.16");

    private int getRealNextContainerId(Player player) {
        return toNMS(player).nextContainerCounter();
    }

    */
/**
     * {@inheritDoc}
     *//*

    @Override
    public int getNextContainerId(Player player, Object container) {
        if(IS_ONE_FIFTEEN) {
            return ((AnvilContainer1_16_R1) container).w.getContainerId();
        } else {
            return ((AnvilContainer) container).getContainerId();
        }
    }

    */
/**
     * {@inheritDoc}
     *//*

    @Override
    public void handleInventoryCloseEvent(Player player) {
        CraftEventFactory.handleInventoryCloseEvent(toNMS(player));
    }

    */
/**
     * {@inheritDoc}
     *//*

    @Override
    public void sendPacketOpenWindow(Player player, int containerId) {
        toNMS(player).b.sendPacket(new PacketPlayOutOpenWindow(containerId, Containers.h, new ChatMessage("Repair & Name")));
    }

    */
/**
     * {@inheritDoc}
     *//*

    @Override
    public void sendPacketCloseWindow(Player player, int containerId) {
        toNMS(player).b.sendPacket(new PacketPlayOutCloseWindow(containerId));
    }

    */
/**
     * {@inheritDoc}
     *//*

    @Override
    public void setActiveContainerDefault(Player player) {
        toNMS(player).activeContainer = toNMS(player).defaultContainer;
    }

    */
/**
     * {@inheritDoc}
     *//*

    @Override
    public void setActiveContainer(Player player, Object container) {
        toNMS(player).activeContainer = (Container) container;
    }

    */
/**
     * {@inheritDoc}
     *//*

    @Override
    public void setActiveContainerId(Object container, int containerId) {
        //noop
    }

    */
/**
     * {@inheritDoc}
     *//*

    @Override
    public void addActiveContainerSlotListener(Object container, Player player) {
        ((Container) container).addSlotListener(toNMS(player));
    }

    */
/**
     * {@inheritDoc}
     *//*

    @Override
    public Inventory toBukkitInventory(Object container) {
        return ((Container) container).getBukkitView().getTopInventory();
    }

    */
/**
     * {@inheritDoc}
     *//*

    @Override
    public Object newContainerAnvil(Player player) {
        if (IS_ONE_FIFTEEN) {
            return new AnvilContainer1_16_R1(player, getRealNextContainerId(player));
        } else {
            return new Wrapper1_16_R1.AnvilContainer(player);
        }
    }

    */
/**
     * Turns a {@link Player} into an NMS one
     *
     * @param player The player to be converted
     * @return the NMS EntityPlayer
     *//*

    private EntityPlayer toNMS(Player player) {
        return ((CraftPlayer) player).getHandle();
    }

    */
/**
     * Modifications to ContainerAnvil that makes it so you don't have to have xp to use this anvil
     *//*

    private class AnvilContainer extends ContainerAnvil {

        public AnvilContainer(Player player) {
            super(getRealNextContainerId(player), ((CraftPlayer) player).getHandle().inventory,
                    ContainerAccess.at(((CraftWorld) player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
            this.checkReachable = false;
            setTitle(new ChatMessage("Repair & Name"));
        }

        @Override
        public void e() {
            super.e();
        }

        public int getContainerId() {
            return windowId;
        }

    }

}
*/
