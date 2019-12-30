package tuxedo.wheel.utility.funnel;

public class AlwaysDenyFunnel implements Funnel {
    @Override
    public boolean canDrip() {
        return false;
    }
}
