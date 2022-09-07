package multithreading;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {
    int sleeptime = 1;
    Integer jobid;
    public CallableTask(int sleeptime, int jobid){
        this.sleeptime = sleeptime;
        this.jobid = jobid;
    }

    public Integer call() throws Exception {
       //TimeUnit.SECONDS.sleep(sleeptime);
        //Thread.sleep(1);

        System.out.println("Executing Job : "+ jobid + " -- "+ Thread.currentThread().getName());
        return jobid;
    }
}
