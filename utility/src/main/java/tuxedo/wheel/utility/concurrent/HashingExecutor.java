package tuxedo.wheel.utility.concurrent;

import lombok.NonNull;
import tuxedo.wheel.utility.array.ArrayUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
