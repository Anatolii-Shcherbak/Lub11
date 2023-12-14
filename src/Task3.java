public class Task3 implements Runnable {
    int count; Thread thread; static boolean stop = false; static  String currentName;

    Task3(String name){
        thread = new Thread(this, name);
        count = 0;
        currentName = name;
    }

    public void run(){
        System.out.println(thread.getName() + " starts to operate");
        do {count++;
            if(currentName.compareTo(thread.getName()) !=0){
                currentName = thread.getName();
                System.out.println(currentName + " is executed");
            }
        }while (stop == false && count < 10_000_000);
        stop = true;
        System.out.println("\n" + thread.getName() + "finishes running");
    }}
class PriorityDemo{
    public static void main(String[] args) {
        Task3 mt1 = new Task3("High Priority thread");
        Task3 mt2 = new Task3("Low Priority thread");
        Task3 mt3 = new Task3("Thread #1 with normal priority");
        Task3 mt4 = new Task3("Thread #2 with normal priority");
        Task3 mt5 = new Task3("Thread #3 with normal priority");

        mt1.thread.setPriority(Thread.MAX_PRIORITY);
        mt2.thread.setPriority(Thread.MIN_PRIORITY);

        mt1.thread.start(); mt2.thread.start(); mt3.thread.start(); mt4.thread.start(); mt5.thread.start();
        try {
            mt1.thread.join();   mt2.thread.join();   mt3.thread.join();   mt4.thread.join();   mt5.thread.join();
        }catch (InterruptedException e){
            System.out.println("The main Threads running");
        }
        System.out.println("\nHigh priority thread counted to " + mt1.count);
        System.out.println("The low priority thread counted to " + mt2.count);
        System.out.println("#1 Normal priority thread counted to " + mt3.count);
        System.out.println("#2 Normal priority thread counted to " + mt4.count);
        System.out.println("#3 Normal priority thread counted to " + mt5.count);
    }
}