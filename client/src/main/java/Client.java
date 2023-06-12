import Demo.ParkingPrx;
import com.zeroc.Ice.ConnectionRefusedException;
import com.zeroc.Ice.ObjectPrx;

public class Client {

    public static void main(String[] args)
    {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"client.cfg"))
        {
            ObjectPrx base = communicator.propertyToProxy("Service.Proxy");
            Demo.ParkingPrx parkingPrx = Demo.ParkingPrx.checkedCast(base);
            if(parkingPrx == null)
            {
                throw new Error("Invalid proxy");
            }
           boolean valid =validateArgs(args,parkingPrx);
            if(valid){
                calculatePrice(args[0], parkingPrx);
            }

        }catch (ConnectionRefusedException e){
            System.out.println("No se pudo establecer la conexion con el servidor");
        }
    }

    public static boolean validateArgs(String[] args, ParkingPrx parkingPrx){
        if(args.length == 0) {
            noArgs();
            return false;
        }

        if(!parkingPrx.validatePlaca(args[0])){
            invalidPlaca();
            return false;
        }
        return true;
    }

    public static void noArgs(){
        System.out.println("Debe ingresar la placa del vehiculo" );

    }

    public static void invalidPlaca(){
        System.out.println("La placa ingresada no es valida" );

    }

    public static void calculatePrice(String placa, ParkingPrx parkingPrx){
        String message=parkingPrx.calculateParking(placa);
        System.out.println(message);
    }

}
