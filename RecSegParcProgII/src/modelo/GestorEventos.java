
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import servicio.Inventariable;


public class GestorEventos<T extends Evento> implements Inventariable<T> {

    List<T> lista = new ArrayList<>();
    
    @Override
    public void agregar(T item) {
        if(item == null){
            System.out.println("Error, no se puede agregar null");
        }else 
            lista.add(item);

    }

    @Override
    public T obtener(int indice) {
        if (lista.size()<= indice){
            System.out.println("Indice fuera de rango");
        }
        return lista.get(indice);
    }    

    @Override
    public void eliminar(int indice) {
        lista.remove(indice);
    }

    @Override
    public List<T> filtrar(Predicate<T> predicado) {
        List<T> toReturn = new ArrayList<>();
        for(T item : lista){
            if(predicado.test(item)){
                toReturn.add(item);
            }
        }
        return toReturn;    
    }

    @Override
    public void ordenarNatural() {
        ordenar((Comparator<T>) Comparator.naturalOrder());
    }

    @Override
    public void ordenar(Comparator<T> comparador) {
        lista.sort(comparador);
    }

    @Override
    public void guardarEnBinario(String path) {
        
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(path))){
            
            salida.writeObject(lista);
            
        } catch (IOException ex ){
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public void cargarDesdeBinario(String path) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path))){
            
            lista = (List <T> ) input.readObject();
        } catch (IOException | ClassNotFoundException ex ){
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public void guardarEnCSV(String path) {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
                bw.write("id,nombre,especie,alimentacion\n");
                for(T item: lista){
                    bw.write(item.toCSV()+ "\n");
                }
            }   catch(IOException ex){
                System.out.println(ex.getMessage());
                throw new RuntimeException("KKKKKK");
            }
    }
    

    @Override
    public void cargarDesdeCSV(String path) {
            
            try(BufferedReader bf = new BufferedReader(new FileReader(path))){
                String linea;
                bf.readLine();
                while((linea = bf.readLine()) != null){
                    String[] valores = linea.split(",");
                    Evento eventoActual = new EventoMusical(Integer.parseInt(valores[0]),
                        valores[1],
                        LocalDate.parse(valores[2]),
                        valores[3],
                        GeneroMusical.valueOf(valores[4]));
                    lista.add((T) eventoActual);
                }
            }   catch(IOException ex){
                System.out.println(ex.getMessage());
                throw new RuntimeException("Error en el archivo");
            }    
    }

    @Override
    public void mostrarTodos() {
        for (Evento evento : lista) {
            System.out.println(evento);
        }
    }

    @Override
    public List<T> buscarPorRango(LocalDate fecha1, LocalDate fecha2) {
        List<T> resultado = new ArrayList<>();

        for (Evento evento : lista) {
            LocalDate fecha = evento.getFecha();
            if ((fecha.isEqual(fecha1) || fecha.isAfter(fecha1)) && (fecha.isEqual(fecha2) || fecha.isBefore(fecha2))) {
                resultado.add((T) evento);
            }
        }
        return resultado;
    }

    public void limpiar(){
        lista.clear();
    }

    
}
