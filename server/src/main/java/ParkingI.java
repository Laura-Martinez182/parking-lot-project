import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;
import database.ConexionBD;
import database.ManejadorDatos;
import model.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingI implements Demo.Parking  { //VentasManager

    private Communicator communicator;

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public boolean validatePlaca(String placa, Current current) {
        return placa.matches("[a-zA-Z]{3}[0-9]{3}") || placa.matches("[a-zA-Z]{3}[0-9]{2}[a-zA-Z]");
    }

    @Override
    public String calculateParking(String placa, Current current) {
           return calculatePrice(placa);
    }

    private String calculatePrice(String placa) {
        ConexionBD conexionBD = new ConexionBD(communicator);
        conexionBD.conectarBaseDatos();

        ManejadorDatos manejadorDatos = new ManejadorDatos();
        manejadorDatos.setConexion(conexionBD.getConnection());

        Vehiculo vehiculo= manejadorDatos.buscarVehiculo(placa);
        if(vehiculo==null){
            throw new RuntimeException("El vehículo no se encuentra en el sistema");
        }

        Estacionamiento estacionamiento= manejadorDatos.buscarEstacionamiento(vehiculo);

        if(estacionamiento==null){
            throw new RuntimeException("El vehiculo no ha ingresado al estacionamiento");
        }

        Tipo tipo= vehiculo.getTipo();
        Tarifa tarifa = manejadorDatos.buscarTarifa(tipo);

        estacionamiento=calculateRate(tarifa,estacionamiento);
        //manejadorDatos.actualizarEstacionamiento(estacionamiento);//SUPUESTO: YA PAGA


        System.out.println("Precio calculado correctamente");
        System.out.println("------------------------------------");

        Date date1 = new Date();
        date1.setTime(estacionamiento.getTiempo_entrada().getTime());
        String tiempo_entrada = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date1);

        Date date2 = new Date();
        date2.setTime(estacionamiento.getTiempo_salida().getTime());
        String tiempo_salida = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date2);

        return "------------------------------\nPlaca:"+placa+"\nLa tarifa es: $"+tarifa.getValor()+" la hora\nEntrada: "+tiempo_entrada+"\nSalida: "+tiempo_salida+"\nSu total es: $"+estacionamiento.getTotal()+"\n------------------------------";
    }

    public Estacionamiento calculateRate(Tarifa tarifa, Estacionamiento estacionamiento){
        Timestamp tiempo_salida = new Timestamp(System.currentTimeMillis());
        Timestamp tiempo_entrada= estacionamiento.getTiempo_entrada();

        // get time difference in seconds
        long milliseconds = tiempo_salida.getTime() - tiempo_entrada.getTime();
        int seconds = (int) milliseconds / 1000;

        // calculate hours minutes and seconds
        int hours = seconds / 3600;

        System.out.println("---------CALCULANDO TARIFA--------");
        System.out.println("Entrada: " + tiempo_entrada);
        System.out.println("Salida: " + tiempo_salida);

        System.out.println("Horas de permanencia: " + hours);

        int total= hours*tarifa.getValor();
        System.out.println("Total: $" + total);
        estacionamiento.setTiempo_salida(tiempo_salida);
        estacionamiento.setTotal(total);

        return estacionamiento;

    }
}
