package cz.craftmania.craftcore.events.time;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MonthChangeEvent extends Event {

    /**
     * The Constant handlers.
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * Gets the handler list.
     *
     * @return the handler list
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    private boolean fake = false;

    /**
     * Instantiates a new month change event.
     */
    public MonthChangeEvent() {
        super(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.bukkit.event.Event#getHandlers()
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public boolean isFake() {
        return fake;
    }

    public void setFake(boolean fake) {
        this.fake = fake;
    }

}
