package multithreading;

import java.util.concurrent.Callable;

public class FileWriterTask implements Callable<Integer> {
String fileName;

    public FileWriterTask(String fileName) {
        this.fileName = fileName;
    }

    public Integer call() throws Exception {
        //TimeUnit.SECONDS.sleep(sleeptime);
        Thread.sleep(1);

        //System.out.println(" Executing Job : "+ jobid);
        return null;
    }
}
