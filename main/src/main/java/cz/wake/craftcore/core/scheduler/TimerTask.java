package cz.wake.craftcore.core.scheduler;

public class TimerTask extends TaskScheduler {

    private double period;
    private double delay;
    private double duration;
    private double endTime;
    private double current;

    /**
     * Creates a timer scheduler
     * @param runnable the runnable for executing
     * @param delay the delay time (in seconds) before the first execution time
     * @param period the period time (in seconds) between execution times
     */
    public TimerTask(Runnable runnable, double delay, double period) {
        super(runnable);
        this.period = period;
        this.delay = delay;
        this.duration = -1;
        this.current = 0;
    }

    /**
     * Creates a timer scheduler
     * @param runnable the runnable for executing
     * @param delay the delay time (in seconds) before the first execution time
     * @param period the period time (in seconds) between execution times
     * @param duration the living-duration of this task (in seconds)
     */
    public TimerTask(Runnable runnable, double delay, double period, double duration) {
        super(runnable);
        this.period = period;
        this.delay = delay;
        this.duration = duration < 0 ? 0 : duration;
        this.current = 0;
    }

    @Override
    public void run() {
        endTime = System.currentTimeMillis() + duration * 1000;
        this.thread = new Thread(() -> {
            try {
                Thread.sleep((long) (delay*1000));
                while(true) {
                    if(stopped || (duration != -1 && endTime <= System.currentTimeMillis())){
                        thread.interrupt();
                        break;
                    }
                    current++;
                    runnable.run();
                    Thread.sleep((long) (period*1000));
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        });
        this.thread.start();
    }

    public double getCurrent() {
        return current;
    }
}
