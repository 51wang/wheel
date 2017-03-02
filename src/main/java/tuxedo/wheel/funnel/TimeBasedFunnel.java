package tuxedo.wheel.funnel;

public class TimeBasedFunnel implements Funnel {
    private final long intervalMillis;
    private long next = 0;

    public TimeBasedFunnel(long intervalMillis) {
        if (intervalMillis <= 0) {
            throw new IllegalArgumentException("IntervalMillis should be positive!");
        }
        this.intervalMillis = intervalMillis;
    }

    @Override
    public boolean canDrip() {
        long now = System.currentTimeMillis();
        if (now >= next) {
            next = now + intervalMillis;
            return true;
        } else {
            return false;
        }
    }
}
