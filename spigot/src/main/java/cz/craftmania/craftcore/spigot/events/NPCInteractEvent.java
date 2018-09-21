package cz.craftmania.craftcore.spigot.events;

import cz.craftmania.craftcore.spigot.builders.npc.NPCBuilder;
import cz.craftmania.craftcore.spigot.inventory.EquipSlot;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * An event triggers when a player interact (left or right click) with an NPC which is created by this library. From 1.9 versions, this event will trigger twice, the first time for the main hand and the second time is for the off hand.
 */
public class NPCInteractEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Action action;
    private EquipSlot slot;
    private Player player;
    private NPCBuilder npc;

    public NPCInteractEvent(Player player, NPCBuilder npc, Action action, EquipSlot slot) {
        this.player = player;
        this.action = action;
        this.slot = slot;
        this.npc = npc;
    }

    public NPCBuilder getNPC() {
        return this.npc;
    }

    public EquipSlot getSlot() {
        return this.slot;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Action getAction() {
        return action;
    }

    public enum Action {
        INTERACT, ATTACK, INTERACT_AT;
    }
}
