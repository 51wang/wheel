package tuxedo.wheel.funnel;

public class TimeBasedFunnel extends AbstractFunnel {
    private final long intervalMillis;
    private long next = 0;

    public TimeBasedFunnel(long intervalMillis, boolean concurrent) {
        super(concurrent);
        if (intervalMillis <= 0) {
            throw new IllegalArgumentException("IntervalMillis should be positive!");
        }
        this.intervalMillis = intervalMillis;
    }

    @Override
    protected boolean __canDrip() {
        long now = System.currentTimeMillis();
        if (now >= next) {
            next = now + intervalMillis;
            return true;
        } else {
            return false;
        }
    }
}
