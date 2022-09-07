package multithreading;

import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskExecutor<V> implements Iterator<V>, AutoCloseable {

    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger completedTask = new AtomicInteger(0);

    private CompletionService<V> completer;

    private ExecutorService executor = Executors.newWorkStealingPool(10);

    public TaskExecutor() {
        this.completer = new ExecutorCompletionService<>(executor);
    }

    public Future<V> submit(Callable<V> task) {
        Future<V> future = completer.submit(task);
        count.incrementAndGet();
        return future;
    }

    @Override
    public boolean hasNext() {
        return count.decrementAndGet() >= 0;
    }

    @Override
    public V next() {
        try {
            completedTask.decrementAndGet();
            return completer.take().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            executor = null;
            completer = null;
            count = null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
