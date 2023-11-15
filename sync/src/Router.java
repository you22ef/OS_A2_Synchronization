public class Router 
{
    public int size;
    public Router(int a)
    {
        size = a;
    }
    public int connections = 1;
    Semaphore loged_in = new Semaphore();
    Semaphore loged_out = new Semaphore();
    
    public void login()
    {
        loged_in.setValue(size);
        System.out.println(size);
        loged_in.P();
        Thread currentThread = Thread.currentThread();
        String threadName = currentThread.getName();
        String[] tokens = threadName.split(" ");
        System.out.println(tokens[0] + " " + "(" + tokens[1] + ")" + "arrived" );
        try 
        {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("connection"+connections+": "+ tokens[0] +" Occupied");
        try 
        {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("connection"+connections+": "+ tokens[0] +" login");
        try 
        {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        connections = (connections + 1);
        if(connections > 2)
        {
            connections = 1;
        }
        System.out.println(tokens[0]+" performs online activity");
        loged_out.V();
        logout();
    }
    public void logout() 
    {
        loged_out.P();
        Thread currentThread = Thread.currentThread();
        String threadName = currentThread.getName();
        String[] tokens = threadName.split(" ");
        System.out.println("connection"+connections+": "+ tokens[0] +" Logged out");
        loged_in.V();
        connections = (connections - 1);
        if(connections <= 0)
        {
            connections = 1;
        }
    }
}
