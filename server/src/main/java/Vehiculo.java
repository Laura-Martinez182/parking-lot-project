public class Vehiculo {

    private int idVehiculo;
    private String placa;
    private String color;
    private Tipo tipo;

    public Vehiculo(int idVehiculo, String placa, String color, Tipo tipo) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.color = color;
        this.tipo = tipo;
    }

    public Vehiculo() {
        super();
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
