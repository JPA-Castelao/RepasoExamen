import java.io.Serializable;

public class vehiculo implements Serializable {

    int id;
    String marca;
    String modelo;
    int ano;
    String descripcion;

    public vehiculo(int id, String marca, String modelo, int ano, String descripcion) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.descripcion = descripcion;
    }


}
