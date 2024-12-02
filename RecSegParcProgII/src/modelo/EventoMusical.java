
package modelo;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class EventoMusical extends Evento {
    private String artista;
    private GeneroMusical generomusical;

    public EventoMusical(int id, String artista, LocalDate fecha, String nombre, GeneroMusical generomusical) {
        super(id, nombre, fecha);
        this.artista = artista;
        this.generomusical = generomusical;
    }

    public GeneroMusical getGeneromusical() {
        return generomusical;
    }

    public String getArtista() {
        return artista;
    }

    
    @Override
    public String toString() {
        return "EventoMusical{" + "artista=" + artista + ", generomusical=" + generomusical + '}';
    }
    
    @Override
    public String toCSV(){
        return getId() + "," + getNombre() + "," + getFecha() + "," + artista + "," + generomusical.toString(); 
    }
    
}
