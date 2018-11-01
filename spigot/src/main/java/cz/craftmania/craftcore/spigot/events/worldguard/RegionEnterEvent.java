package cz.craftmania.craftcore.spigot.events.worldguard;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerEvent;

public class RegionEnterEvent extends RegionEvent implements Cancellable {

    private boolean cancelled;
    private boolean cancellable;

    /**
     * Fired when a player entered into registred WorldGuard region.
     *
     * @param region   Name entered region
     * @param player   The player that enter
     * @param movement Type of movement
     * @param parent   Event of entering
     */
    public RegionEnterEvent(final ProtectedRegion region, final Player player, final MovementWay movement, final PlayerEvent parent) {
        super(region, player, movement, parent);
        this.cancelled = false;
        this.cancellable = true;
        if (movement == MovementWay.SPAWN || movement == MovementWay.DISCONNECT) {
            this.cancellable = false;
        }
    }

    public void setCancelled(final boolean cancelled) {
        if (!this.cancellable) {
            return;
        }
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public boolean isCancellable() {
        return this.cancellable;
    }

    protected void setCancellable(final boolean cancellable) {
        if (!(this.cancellable = cancellable)) {
            this.cancelled = false;
        }
    }
}