package tuxedo.wheel.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.NonNull;
import tuxedo.wheel.array.ArrayUtil;

public class HashingExecutor implements Executor {
    private final Executor[] workers;

    public HashingExecutor(int nThreads) {
        workers = ArrayUtil.arrayFilledBySupplier(Executor.class, nThreads, Executors::newSingleThreadExecutor);
    }

    @Override
    public void execute(@NonNull Runnable command) {
        selectWorker(command).execute(command);
    }

    private Executor selectWorker(Runnable command) {
        return workers[workers.length == 1 ? 0 : Math.abs(command.hashCode()) % workers.length];
    }
}
