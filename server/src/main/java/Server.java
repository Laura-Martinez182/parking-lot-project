import com.zeroc.Ice.ConnectionRefusedException;

public class Server {

    public static void main(String[] args)
    {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "server.cfg"))
        {
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("SimpleParkingAdapter");
            com.zeroc.Ice.Object object = new ParkingI();
            adapter.add(object, com.zeroc.Ice.Util.stringToIdentity("SimpleParking"));
            adapter.activate();
            communicator.waitForShutdown();
        } catch (ConnectionRefusedException e){
        System.out.println("No se pudo establecer la conexion con el cliente");
    }
    }
}
