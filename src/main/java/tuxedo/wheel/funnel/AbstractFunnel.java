package tuxedo.wheel.funnel;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractFunnel implements Funnel {
    private final boolean concurrent;

    @Override
    public final boolean canDrip() {
        if (concurrent) {
            synchronized (this) {
                return __canDrip();
            }
        } else {
            return __canDrip();
        }
    }

    protected abstract boolean __canDrip();
}
