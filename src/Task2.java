public class Task2 {
    String state;

    synchronized void tick(boolean running){
        if(!running){
            state = "ticked";
            notify();
            return;}
        System.out.print("tik ");

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("The thread has been interrupted");
        }

        state = "ticked";
        notify();

        try {
            while (!state.equals("tocked"))
                wait(500);
        }catch (InterruptedException e){
            System.out.println("The thread has been interrupted");
        }
    }

    synchronized void tack (boolean running){
        if(!running){
            state = "tocked";
            notify();
            return;}
        System.out.println("tak");

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("The thread has been interrupted");
        }
        state = "tocked";
        notify();
        try {
            while (!state.equals("ticked"))
                wait(500);
        }catch (InterruptedException e){
            System.out.println("The thread has been interrupted");
        }
    }
}

class  MyThreadclock implements Runnable {
    Thread thread;
    Task2 tt0b;

    MyThreadclock(String name, Task2 tt) {
        thread = new Thread(this, name);
        tt0b = tt;
    }

    public static MyThreadclock createAndStart(String name, Task2 tt) {
        MyThreadclock myThreadclock = new MyThreadclock(name, tt);
        myThreadclock.thread.start();
        return myThreadclock;
    }

    public void run() {
        if (thread.getName().compareTo("tik") == 0) {
            for (int i = 0; i < 20; i++) tt0b.tick(true);
            tt0b.tick(false);
        } else {
            for (int i = 0; i < 20; i++) tt0b.tack(true);
            tt0b.tack(false);
        }
    }
}

    class ThreadCon{
        public static void main(String[] args) {
            Task2 tt = new Task2();
            MyThreadclock myThreadclock1 = MyThreadclock.createAndStart("tik", tt);
            MyThreadclock myThreadclock2 = MyThreadclock.createAndStart("tak", tt);
            try {
                myThreadclock1.thread.join();
                myThreadclock2.thread.join();
            }catch (InterruptedException e){
                System.out.println("The main Thread has been terminated");
            }
        }
    }
