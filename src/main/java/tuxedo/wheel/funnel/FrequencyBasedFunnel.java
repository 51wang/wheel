package tuxedo.wheel.funnel;

public class FrequencyBasedFunnel extends AbstractFunnel {
    private final long frequency;
    private long counter = 0;

    public FrequencyBasedFunnel(long frequency, boolean concurrent) {
        super(concurrent);
        if (frequency <= 0) {
            throw new IllegalArgumentException("Frequency should be positive!");
        }
        this.frequency = frequency;
    }

    @Override
    protected boolean __canDrip() {
        boolean canDrip = false;
        if (counter++ == 0) {
            canDrip = true;
        }
        if (counter == frequency) {
            counter = 0;
        }
        return canDrip;
    }
}
