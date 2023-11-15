import javax.naming.event.ObjectChangeListener;

public class Device extends Thread
{
    Router router;
    public Device(Router r)
    {
        router = r;
    }
    public void run() 
    {
        router.login();

    }
}