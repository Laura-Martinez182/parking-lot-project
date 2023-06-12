import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

import java.sql.Timestamp;

public class ParkingI implements Demo.Parking  { //VentasManager

    private Communicator communicator;

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void calculateParking(String placa, Current current) {
            calculatePrice(placa);
    }

    private void calculatePrice(String placa) {
        ConexionBD conexionBD = new ConexionBD(communicator);
        conexionBD.conectarBaseDatos();

        ManejadorDatos manejadorDatos = new ManejadorDatos();
        manejadorDatos.setConexion(conexionBD.getConnection());

        Vehiculo vehiculo= manejadorDatos.buscarVehiculo(placa);

        Estacionamiento estacionamiento= manejadorDatos.buscarEstacionamiento(vehiculo);
        Tipo tipo= vehiculo.getTipo();
        Tarifa tarifa = manejadorDatos.buscarTarifa(tipo);

        estacionamiento=calculateRate(tarifa,estacionamiento);
        manejadorDatos.actualizarEstacionamiento(estacionamiento);


        System.out.println("Precio calculado correctamente");

    }

    public Estacionamiento calculateRate(Tarifa tarifa, Estacionamiento estacionamiento){
        Timestamp tiempo_salida = new Timestamp(System.currentTimeMillis());
        Timestamp tiempo_entrada= estacionamiento.getTiempo_entrada();

        // get time difference in seconds
        long milliseconds = tiempo_salida.getTime() - tiempo_entrada.getTime();
        int seconds = (int) milliseconds / 1000;

        // calculate hours minutes and seconds
        int hours = seconds / 3600;


        System.out.println("timestamp1: " + tiempo_entrada);
        System.out.println("timestamp2: " + tiempo_salida);

        System.out.println("Difference: ");
        System.out.println(" Hours: " + hours);

        int total= hours*tarifa.getValor();

        estacionamiento.setTiempo_salida(tiempo_salida);
        estacionamiento.setTotal(total);

        return estacionamiento;

    }
}
