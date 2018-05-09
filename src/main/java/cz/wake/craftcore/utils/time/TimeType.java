package cz.wake.craftcore.utils.time;

public enum TimeType {
    MONTH, WEEK, DAY;

    public static TimeType getTimeType(String str) {
        for (TimeType time : TimeType.values()) {
            if (time.toString().equalsIgnoreCase(str)) {
                return time;
            }
        }
        return null;
    }
}
