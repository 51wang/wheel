package tuxedo.wheel.utility.funnel;

public class AlwaysAllowFunnel implements Funnel {
    @Override
    public boolean canDrip() {
        return true;
    }
}
