package tuxedo.wheel.utility.funnel;

public class FrequencyBasedFunnel implements Funnel {
    private final long frequency;
    private long counter = 0;

    public FrequencyBasedFunnel(long frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Frequency should be positive!");
        }
        this.frequency = frequency;
    }

    @Override
    public boolean canDrip() {
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
