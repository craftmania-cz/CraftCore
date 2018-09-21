package cz.craftmania.craftcore.spigot.utils.time;

import cz.craftmania.craftcore.spigot.Main;
import cz.craftmania.craftcore.spigot.events.time.*;
import cz.craftmania.craftcore.spigot.internal.ServerData;
import cz.craftmania.craftcore.spigot.utils.Log;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class TimeChecker {

    /**
     * The instance.
     */
    static TimeChecker instance = new TimeChecker();

    /**
     * Gets the single instance of TimeChecker.
     *
     * @return single instance of TimeChecker
     */
    public static TimeChecker getInstance() {
        return instance;
    }

    /**
     * Instantiates a new time checker.
     */
    private TimeChecker() {
    }

    public void forceChanged(TimeType time) {
        forceChanged(time, true);
    }

    public void forceChanged(TimeType time, boolean fake) {
        Log.withPrefix("Vyzadana zmena casu: " + time.toString());
        PreDateChangedEvent preDateChanged = new PreDateChangedEvent(time);
        preDateChanged.setFake(fake);
        Main.getInstance().getServer().getPluginManager().callEvent(preDateChanged);
        if (time.equals(TimeType.DAY)) {
            DayChangeEvent dayChange = new DayChangeEvent();
            dayChange.setFake(fake);
            Main.getInstance().getServer().getPluginManager().callEvent(dayChange);
        } else if (time.equals(TimeType.WEEK)) {
            WeekChangeEvent weekChange = new WeekChangeEvent();
            weekChange.setFake(fake);
            Main.getInstance().getServer().getPluginManager().callEvent(weekChange);
        } else if (time.equals(TimeType.MONTH)) {
            MonthChangeEvent monthChange = new MonthChangeEvent();
            monthChange.setFake(fake);
            Main.getInstance().getServer().getPluginManager().callEvent(monthChange);
        }

        DateChangedEvent dateChanged = new DateChangedEvent(time);
        dateChanged.setFake(fake);
        Main.getInstance().getServer().getPluginManager().callEvent(dateChanged);

        Log.withPrefix("Zmena casu byla dokoncena: " + time.toString());
    }

    public LocalDateTime getTime() {
        return LocalDateTime.now().plusHours(Main.getInstance().getTimeHourOffSet());
    }

    /**
     * Checks for day changed.
     *
     * @return true, if successful
     */
    public boolean hasDayChanged() {
        int prevDay = ServerData.getInstance().getPrevDay();
        int day = getTime().getDayOfMonth();
        ServerData.getInstance().setPrevDay(day);
        if (prevDay == -1) {
            return false;
        }
        if (prevDay != day) {
            return true;
        }
        return false;
    }

    /**
     * Checks for month changed.
     *
     * @return true, if successful
     */
    public boolean hasMonthChanged() {
        String prevMonth = ServerData.getInstance().getPrevMonth();
        String month = getTime().getMonth().toString();
        ServerData.getInstance().setPrevMonth(month);
        return !prevMonth.equals(month);

    }

    public boolean hasTimeOffSet() {
        return Main.getInstance().getTimeHourOffSet() != 0;
    }

    /**
     * Checks for week changed.
     *
     * @return true, if successful
     */
    public boolean hasWeekChanged() {
        int prevDate = ServerData.getInstance().getPrevWeekDay();
        LocalDateTime date = getTime();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = date.get(woy);
        ServerData.getInstance().setPrevWeekDay(weekNumber);
        if (prevDate == -1) {
            return false;
        }
        if (weekNumber != prevDate) {
            return true;
        }
        return false;
    }

    /**
     * Update.
     */
    public void update() {
        if (hasTimeOffSet()) {
            //plugin.extraDebug(getTime().getHour() + ":" + getTime().getMinute());
        }

        boolean dayChanged = false;
        boolean weekChanged = false;
        boolean monthChanged = false;
        if (hasDayChanged()) {
            //plugin.debug("Day changed");
            dayChanged = true;
        }
        if (hasWeekChanged()) {
            //plugin.debug("Week Changed");
            weekChanged = true;
        }
        if (hasMonthChanged()) {
            //plugin.debug("Month Changed");
            monthChanged = true;
        }

        if (dayChanged) {
            forceChanged(TimeType.DAY, false);
        }
        if (weekChanged) {
            forceChanged(TimeType.WEEK, false);
        }
        if (monthChanged) {
            forceChanged(TimeType.MONTH, false);
        }

    }
}
