package recursion;

public class MyCounter {
    int counter;

    public MyCounter(int counter) {
        this.counter = counter;
    }

    public void increment() {
        counter = counter + 1;
    }

    public void decrement() {
        counter = counter - 1;
        if (counter < 0) {
            counter = 0;
        }
    }

    public int getCounter(){
        return this.counter;
    }
}
