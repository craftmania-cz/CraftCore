package cz.craftmania.craftcore.events.time;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MinuteChangedEvent extends Event {

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
    private int minute;

    public MinuteChangedEvent(int minute) {
        super(true);
        this.minute = minute;
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

    public int getMinute() {
        return minute;
    }
}
