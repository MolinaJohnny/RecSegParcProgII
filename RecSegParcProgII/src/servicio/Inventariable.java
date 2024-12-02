
package servicio;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import modelo.Evento;

/**
 *
 * @author User
 */
public interface Inventariable<T extends Evento> {
     
    void agregar(T item);
    
    T obtener(int indice);
    
    void eliminar(int indice);
    
    List<T> buscarPorRango(LocalDate fecha1, LocalDate fecha2);
    
    List<T> filtrar(Predicate<T> predicado);
    
    void ordenarNatural();
    
    void ordenar(Comparator<T> comparador);
    
    void guardarEnBinario(String path);
    
    void cargarDesdeBinario(String path);
    
    void guardarEnCSV(String path);
    
    void cargarDesdeCSV(String path);
    
    void mostrarTodos();
    
}
