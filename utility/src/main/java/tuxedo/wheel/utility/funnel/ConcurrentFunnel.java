package tuxedo.wheel.utility.funnel;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcurrentFunnel implements Funnel {
    @NonNull
    private final Funnel impl;

    @Override
    public final boolean canDrip() {
        synchronized (this) {
            return impl.canDrip();
        }
    }
}
