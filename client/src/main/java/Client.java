import Demo.ParkingPrx;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.SocketException;

import java.io.Console;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        Console console = System.console();
        boolean retry=false;
        int countRetry=0;
        String proxy="Service.Proxy";
        do {

            try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "client.cfg")) {

                ObjectPrx base = communicator.propertyToProxy(proxy);
                Demo.ParkingPrx parkingPrx = Demo.ParkingPrx.checkedCast(base);

                if (parkingPrx == null) {
                    throw new Error("Invalid proxy");
                }

                exitParkingLot(console,parkingPrx);


            } catch (SocketException  e) {
                System.err.println("Se ha presentado un error en la conexion del sistema, por favor espere");
                retry=true;
                if(countRetry<4){
                    countRetry++;
                }else{
                    proxy="Emergency.Proxy";
                    countRetry=0;
                }

                Thread.sleep(1000);
            }


        }while(retry);
    }

    public static void exitParkingLot(Console console, ParkingPrx parkingPrx){

         do {
                 String placa = console.readLine("Ingrese la placa: ");
                 boolean valid = validateInput(placa, parkingPrx);
                 if (valid) {
                     calculatePrice(placa, parkingPrx);
                 }

         }while(true);
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
