package tuxedo.wheel.funnel;

public class AlwaysDenyFunnel implements Funnel {
    @Override
    public boolean canDrip() {
        return false;
    }
}
