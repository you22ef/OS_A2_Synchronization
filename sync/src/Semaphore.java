public class Semaphore {
    public int value ;
    public Semaphore() { value = 0 ;}
    public Semaphore(int initial) 
    {
        value = initial ;
    }
    public void setValue(int x){
        value =x ;
    }
    public synchronized void P() { //change
        value--;
        if (value < 0) {
            try {
                Thread currentThread = Thread.currentThread();
                String threadName = currentThread.getName();
                String[] tokens = threadName.split(" ");

                // Check if the array has at least two elements before accessing index 1
                if (tokens.length >= 2) {
                    System.out.println(tokens[0] + " " + "(" + tokens[1] + ")" + " arrived and waiting");
                } else {
                    System.out.println("Unexpected thread name format: " + threadName);
                }

                wait();
            } catch (InterruptedException e) {
                System.out.println("ERROR IN SEMAPHORE P()");
            }
        }
    }

    public synchronized void V() 
    {
        value++ ;
        if (value <= 0)
        {
            notify() ;
        }
    }
}
