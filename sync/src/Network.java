import java.util.Scanner;

public class Network {

    public int N,TC;
    String[] TC_lines = new String[5];
    public void SetData()
    {
        System.out.println("What is the number of WI-FI Connections?");
        Scanner myObj = new Scanner(System.in);
        N = myObj.nextInt();
        System.out.println("What is the number of devices Clients want to connect?");
        TC = myObj.nextInt();
        
        for (int i = 0; i < TC; i++) 
        {
            Scanner myObj2 = new Scanner(System.in);
            String line = myObj2.nextLine();
            TC_lines[i] = line;
        }
    }

    public String[] getTotalDevicesNames()
    {
        return TC_lines;
    }

    public int getTotalDevicesNum()
    {
        return TC;
    }
    public void setTotalDevicesNum(int s)
    {
        TC = s;
    }
    public int getMaxConn()
    {
        return N;
    }
    public static void main(String[] args) throws Exception {
        Network a = new Network();

        a.SetData();
        int numThreads = a.getTotalDevicesNum();
        Device[] threadArray = new Device[numThreads];
        Router router = new Router(a.getMaxConn());

        String[] deviceNames = a.getTotalDevicesNames(); //  device names

        for (int i = 0; i < numThreads; i++) {
            threadArray[i] = new Device(router, deviceNames[i]); // Pass device name
        }

        for (Thread thread : threadArray) {
            thread.start();
        }
    }
}
