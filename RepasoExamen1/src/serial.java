import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class serial {

    public static void llenarTabla() {
        Connection conn = conexion.conectar();

        String consulta = " INSERT INTO vehiculo(Modelo,Marca,Ano,Descripcion)VALUES" + "('Mustang','Ford','2021','Deportivos americanos icónicos')," + "('Model S','Tesla','2023','Sedán eléctrico de luxo con gran autonomía')," + "('Civic','Honda','2020','Compacto de gran fiabilidade')," + "('Corvette','Chevrolet','2022','Deportivo americano con motor V8')," + "('Prius','Toyota','2022','Híbrido de baixo consumo e ecolóxico.');";
        try {
            PreparedStatement query = conn.prepareStatement(consulta);
            query.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }


    public void crearFicheiro(ArrayList<vehiculo> listaCoches) {

        String ruta = "/home/dam/ad/RepasoExamen1/SerialCoches.ser";
        File serialCoches = new File(ruta);
        ArrayList<vehiculo> lsitaDeserializada = new ArrayList<vehiculo>();

        try {
            FileOutputStream FOS = new FileOutputStream(serialCoches);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);

            for (vehiculo coche : listaCoches) {

                OOS.writeObject(coche);
            }

            serialCoches.createNewFile();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public void serializar() {
        Connection conn = conexion.conectar();
        String consulta = "SELECT * FROM vehiculo";
        ArrayList<vehiculo> listaVehiculos = new ArrayList<vehiculo>();

        int id;
        String marca;
        String modelo;
        int ano;
        String descripcion;


        try {
            PreparedStatement consultarVehiculos = conn.prepareStatement(consulta);
            ResultSet resultado = consultarVehiculos.executeQuery();

            while (resultado.next()) {
                id = resultado.getInt(1);
                marca = resultado.getString("marca");
                modelo = resultado.getString("modelo");
                ano = resultado.getInt("ano");
                descripcion = resultado.getString("descripcion");

                vehiculo coche = new vehiculo(id, marca, modelo, ano, descripcion);
                listaVehiculos.add(coche);
            }

            crearFicheiro(listaVehiculos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void deserializar(File archivoSerial) {
        File archivo = archivoSerial;
        vehiculo objeto;
        try {
            FileInputStream FIS = new FileInputStream(archivo);
            ObjectInputStream OIS = new ObjectInputStream(FIS);

            try {
                objeto = (vehiculo) OIS.readObject();
                System.out.println(objeto.modelo);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        serial ser = new serial();
        ser.serializar();
        File archivo = new File("/home/dam/ad/RepasoExamen1/SerialCoches.ser");
        ser.deserializar(archivo);
    }
}
