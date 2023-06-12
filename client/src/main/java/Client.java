public class Client {

    public static void main(String[] args)
    {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args))
        {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimpleParking:default -p 10000");
            Demo.ParkingPrx parkingPrx = Demo.ParkingPrx.checkedCast(base);
            if(parkingPrx == null)
            {
                throw new Error("Invalid proxy");
            }
           // parkingPrx.printString("Hello World!");
        }
    }
}
