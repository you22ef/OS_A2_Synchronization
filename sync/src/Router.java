/*
public class Router
{
    public int size;
    public Router(int a)
    {                               //old ver
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
*/
import java.util.LinkedList;
import java.util.Queue;

public class Router {
    private int size;
    private int connections = 0;
    private Queue<String> deviceQueue = new LinkedList<>(); //add queue
    private Semaphore semaphore;


    public Router(int a) {
        size = a;
        semaphore = new Semaphore(a);
    }

    public void login(String deviceName) throws InterruptedException {
        semaphore.P();
        deviceQueue.add(deviceName);
        connections++;
        System.out.println("- (" + deviceName + ") arrived");

        if (connections <= size) {
            System.out.println("- Connection " + connections + ": " + deviceName + " Occupied");
        } else {
            System.out.println("- " + deviceName + " arrived and waiting");
        }

        if (connections == size) {
            while (!deviceQueue.isEmpty()) {
                String nextDevice = deviceQueue.poll();
                System.out.println("- Connection " + connections + ": " + nextDevice + " login");
                Thread.sleep(1000); // Simulating online activity
                System.out.println("- Connection " + connections + ": " + nextDevice + " Logged out");
                connections--;
                semaphore.V();
            }
        }
    }
}

// removw logout
