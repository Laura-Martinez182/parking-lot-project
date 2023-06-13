import Demo.ParkingPrx;
import com.zeroc.Ice.ConnectFailedException;
import com.zeroc.Ice.ConnectionRefusedException;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.SocketException;
import java.io.Console;

public class Client {

    public static void main(String[] args)
    {
        Console console = System.console();

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"client.cfg"))
        {
            String placa= console.readLine("Ingrese la placa: ");

            ObjectPrx base = communicator.propertyToProxy("Service.Proxy");
            Demo.ParkingPrx parkingPrx = Demo.ParkingPrx.checkedCast(base);
            if(parkingPrx == null)
            {
                throw new Error("Invalid proxy");
            }
           boolean valid =validateInput(placa,parkingPrx);
            if(valid){
                calculatePrice(placa, parkingPrx);
            }

        }catch (ConnectionRefusedException e) {
            System.err.println("La conexion fue rechazada por el servidor, no se pudo establecer una conexion en el puerto o IP especificada.");
        }catch (ConnectFailedException e) {
            System.err.println("Tiempo de conexion agotado. Verifique la direccion IP indicada para el cliente.");
        } catch (SocketException u) {
            System.out.println("No se pudo encontrar el puerto indicado, verifique que este disponible.");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean validateInput(String placa, ParkingPrx parkingPrx){
        if(placa.isEmpty()) {
            noPlaca();
            return false;
        }

        if(!parkingPrx.validatePlaca(placa)){
            invalidPlaca();
            return false;
        }
        return true;
    }

    public static void noPlaca(){
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
