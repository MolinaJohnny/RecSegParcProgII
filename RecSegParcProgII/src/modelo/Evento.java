
package modelo;

import java.io.Serializable;
import java.time.LocalDate;


public class Evento implements Comparable<Evento>, Serializable {
    private static final long SerialVersionUID = 1l;    
    private int id;
    private String nombre;
    private LocalDate fecha;

    public Evento(int id, String nombre, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Evento{" + "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + '}';
    }

    @Override
    public int compareTo(Evento o) {
        return fecha.compareTo(o.getFecha());
    
    }
    public String toCSV(){
        return id + "," + nombre + "," + fecha + ","; 
    }
        
        
        
        
}
