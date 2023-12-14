public class Task4 {
    private int sum;
    int Task4(int nums[]){
        sum = 0;
        for(int i=0; i<nums.length; i++){
            sum+=nums[i];
            System.out.println(Thread.currentThread().getName() + " computed a partial sum equal to " + sum);
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                System.out.println("The thread has been interrupted");
            }}return  sum;

    }}

class MyThreadSync implements  Runnable{
    Thread thread;
    static Task4 sa = new Task4();
    int a[], answer;
    MyThreadSync(String name, int nums[]){
        thread = new Thread(this, name);
        a = nums;
    }
    public static MyThreadSync createAndStart(String name, int nums[]){
        MyThreadSync myThreadSync = new MyThreadSync(name, nums);
        myThreadSync.thread.start();
        return myThreadSync;
    }

    public  void run(){
        System.out.println(thread.getName() + " starts a new action");
        answer = sa.Task4(a);
        System.out.println(thread.getName() + " computed the sum equal to " + answer);
        System.out.println(thread.getName() + " finishes running");
    }}
class Sync {
    public static void main(String[] args) {
        int a[] = {48, 15, 33, 51, 53, 56, 99, 1, 22, 44};
        MyThreadSync myThreadSync1 = MyThreadSync.createAndStart("Child thread # 1", a);
        MyThreadSync myThreadSync2 = MyThreadSync.createAndStart("Child thread # 2", a);
        Thread myThreadSync3 = new Thread(new AvgCalculator(a));
        Thread myThreadSync4 = new Thread(new AvgCalculator(a));

        try {
            myThreadSync1.thread.join();
            myThreadSync2.thread.join();
            myThreadSync3.start();
            myThreadSync4.start();
        } catch (InterruptedException e) {
            System.out.println("THe main thread has been terminated");
        }
    }
}

    class AvgValArray {
         int sum; double average;

        public void calculateAverage(int[] array) {
            synchronized (this) {
                sum = 0;
                for (int num : array) {
                    sum += num;
                }
                average = (double) sum / array.length;
                  System.out.println(Thread.currentThread().getName() + " is runing ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("THe main thread has been terminated");
                }
            }
        }
    }

class AvgCalculator implements Runnable {
    private static final AvgValArray avgValArray = new AvgValArray();

    private int[] array;

    public AvgCalculator(int[] array) {
        this.array = array;
    }

    public void run() {

        avgValArray.calculateAverage(array);
        System.out.println(Thread.currentThread().getName() + " calculated average: " + avgValArray.average);
        }
    }




