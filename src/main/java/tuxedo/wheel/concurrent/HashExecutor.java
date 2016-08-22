package tuxedo.wheel.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import lombok.NonNull;

public class HashExecutor implements Executor {
    private final Executor[] workers;

    public HashExecutor(int nThreads) {
        if (nThreads <= 0) {
            throw new IllegalArgumentException("nThreads should be greater than 0!");
        }

        this.workers = new Executor[nThreads];
        IntStream.range(0, nThreads).forEach(i -> workers[i] = Executors.newSingleThreadExecutor());
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
