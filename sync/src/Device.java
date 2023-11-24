import javax.naming.event.ObjectChangeListener;

public class Device extends Thread {
    private Router router;
    private String deviceName; //add

    public Device(Router r, String name) {
        router = r;
        deviceName = name; //add
    }

    public void run() {
        try {
            router.login(deviceName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
