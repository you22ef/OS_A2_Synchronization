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
    public synchronized void P() 
    {
        //System.out.println("value : "+value);
        value-- ;
        if (value < 0)
        {
            try 
            {
                Thread currentThread = Thread.currentThread();
                String threadName = currentThread.getName();
                String[] tokens = threadName.split(" ");
                System.out.println(tokens[0] + " " + "(" + tokens[1] + ")" + "arrived and waiting" );
                wait() ;
            } 
            catch( InterruptedException e ) {
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
