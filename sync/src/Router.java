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
