package database;

import model.Estacionamiento;
import model.Tarifa;
import model.Tipo;
import model.Vehiculo;

import java.sql.*;

public class ManejadorDatos {

    private Connection conexion;

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Vehiculo buscarVehiculo(String placa) {
        try{
            String query = "SELECT * FROM VEHICULO v WHERE PLACA = ?";
            PreparedStatement prepareStatement = conexion.prepareStatement(query);
            prepareStatement.setString(1, placa);
            ResultSet resultado = prepareStatement.executeQuery();

            if (resultado.next()) {

                int idVehiculo = resultado.getInt("IDVEHICULO");
                String color = resultado.getString("COLOR");
                int idTipo=  resultado.getInt("IDTIPO");

                Vehiculo vehiculo= new Vehiculo(idVehiculo,placa,color,null);

                String query2 = "SELECT * FROM TIPO t WHERE IDTIPO = ?";
                PreparedStatement prepareStatement2 = conexion.prepareStatement(query2);
                prepareStatement2.setInt(1, idTipo);
                ResultSet resultado2 = prepareStatement2.executeQuery();

                if (resultado2.next()) {
                    String tipoS = resultado2.getString("TIPO");
                    Tipo tipo= new Tipo(idTipo,tipoS);
                    vehiculo.setTipo(tipo);

                }

                return vehiculo;

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Tarifa buscarTarifa(Tipo tipo) {
        try{
            String query = "SELECT * FROM TARIFAS t WHERE IDTIPO = ?";
            PreparedStatement prepareStatement = conexion.prepareStatement(query);
            prepareStatement.setInt(1, tipo.getIdTipo());
            ResultSet resultado = prepareStatement.executeQuery();

            if (resultado.next()) {

                int idTarifa = resultado.getInt("IDTARIFA");
                int valor = resultado.getInt("VALOR");

                return new Tarifa(idTarifa,tipo,valor);

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public Estacionamiento buscarEstacionamiento(Vehiculo vehiculo) {
        try{
            String query = "SELECT * FROM ESTACIONAMIENTO e WHERE IDVEHICULO = ? and TIEMPO_SALIDA IS NULL";
            PreparedStatement prepareStatement = conexion.prepareStatement(query);
            prepareStatement.setInt(1, vehiculo.getIdVehiculo());
            ResultSet resultado = prepareStatement.executeQuery();

            if (resultado.next()) {

                int idEstacionamiento = resultado.getInt("IDESTACIONAMIENTO");
                Timestamp tiempo_entrada= resultado.getTimestamp("TIEMPO_ENTRADA");


                return new Estacionamiento(idEstacionamiento,vehiculo,tiempo_entrada,null,0);

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void actualizarEstacionamiento(Estacionamiento estacionamiento) {
        try{
            String updateEstacionamiento = "UPDATE ESTACIONAMIENTO SET TIEMPO_SALIDA=?, TOTAL=? WHERE IDESTACIONAMIENTO=?";
            PreparedStatement prepareStatement = conexion.prepareStatement(updateEstacionamiento);
            prepareStatement.setTimestamp(1, estacionamiento.getTiempo_salida());
            prepareStatement.setInt(2,estacionamiento.getTotal());
            prepareStatement.setInt(3,estacionamiento.getIdEstacionamiento());
            prepareStatement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
