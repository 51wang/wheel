package tuxedo.wheel.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.NonNull;
import tuxedo.wheel.array.ArrayUtil;

public class HashExecutor implements Executor {
    private final Executor[] workers;

    public HashExecutor(int nThreads) {
        if (nThreads <= 0) {
            throw new IllegalArgumentException("nThreads should be greater than 0!");
        }
        this.workers = ArrayUtil.arrayFilledBySupplier(Executor.class, nThreads, Executors::newSingleThreadExecutor);
    }

    @Override
    public void execute(Runnable command) {
        selectWorker(command).execute(command);
    }

    private Executor selectWorker(@NonNull Runnable command) {
        if (workers.length == 1) {
            return workers[0];
        } else {
            return workers[Math.abs(command.hashCode()) % workers.length];
        }
    }
}
