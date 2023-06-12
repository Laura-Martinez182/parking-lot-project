public class Tarifa {

   private int idTarifa;
   private Tipo tipo;
   private int valor;

    public Tarifa(int idTarifa, Tipo tipo, int valor) {
        this.idTarifa = idTarifa;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Tarifa(){
       super();
   }

    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
