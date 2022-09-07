package multithreading;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecuteJob {

    public static  void main (String[] args) throws InterruptedException {

        Stopwatch stopwatch = Stopwatch.createStarted();
        int number = 100;
        System.out.println("Total # of task submitted: " + number);
        executeJob(number);
        System.out.println("Total Time taken executeJob : " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " seconds");
        stopwatch.reset();
        executeJobMultiThreaded(number);
        System.out.println("Total Time taken executeJobMultiThreaded : " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " seconds");

    }

    /**
     * print Numbers in sequential order
     */
    public static void executeJob(int number){

        for(int i = 0; i<number;i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * print Numbers in multithreaing
     */
    public static void executeJobMultiThreaded(int number){

        try (TaskExecutor taskExecutor = new TaskExecutor()) {

            List<CallableTask> todo = new ArrayList<>();
            List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
            int total = 0;
            for (int i = 0; i < number; i++) {
                CallableTask task = new CallableTask(10 - i, i);
                taskExecutor.submit(task);
                todo.add(task);
                total++;
            }
         //  System.out.println("Total # of task submitted: " + total);
           /* while (taskExecutor.hasNext()) {
                taskExecutor.next();

            }*/
           // ExecutorService executorService = Executors.newFixedThreadPool(10);


            //executorService.invokeAll(todo);
        }
    }
}
