package tuxedo.wheel.funnel;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcurrentFunnel implements Funnel {
    private final @NonNull Funnel impl;

    @Override
    public final boolean canDrip() {
        synchronized (this) {
            return impl.canDrip();
        }
    }
}
