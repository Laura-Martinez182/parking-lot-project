import java.sql.Timestamp;

public class Estacionamiento {




    private int idEstacionamiento;
    private Vehiculo  vehiculo;
    private Timestamp tiempo_entrada;
    private Timestamp tiempo_salida;
    private int total;

    public Estacionamiento(int idEstacionamiento, Vehiculo vehiculo, Timestamp tiempo_entrada, Timestamp tiempo_salida, int total) {
        this.idEstacionamiento = idEstacionamiento;
        this.vehiculo = vehiculo;
        this.tiempo_entrada = tiempo_entrada;
        this.tiempo_salida = tiempo_salida;
        this.total = total;
    }

    public Estacionamiento(){
        super();
    }

    public int getIdEstacionamiento() {
        return idEstacionamiento;
    }

    public void setIdEstacionamiento(int idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Timestamp getTiempo_entrada() {
        return tiempo_entrada;
    }

    public void setTiempo_entrada(Timestamp tiempo_entrada) {
        this.tiempo_entrada = tiempo_entrada;
    }

    public Timestamp getTiempo_salida() {
        return tiempo_salida;
    }

    public void setTiempo_salida(Timestamp tiempo_salida) {
        this.tiempo_salida = tiempo_salida;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
