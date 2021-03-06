/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.util.ArrayList;
import java.util.Random;
import modelos.Instancias;
import modelos.Patron;

/**
 *
 * @author Roberto Cruz Leija
 */
public class GeneradorInstancias {
    
    private Instancias instanciaOriginal;

    public GeneradorInstancias(Instancias instanciaOriginal) {
        this.instanciaOriginal = instanciaOriginal;
    }
    
    public ArrayList<Patron> generaInstancia (int[] caracteristicas,double porcentaje,FactorSeleccion factor){
       // filtramos las caracteristicas
       ArrayList<Patron> nuevaInstancia = generarInstancia(caracteristicas);
       
       // en base al factor de seleccion seleccionamos los patrones de la 
       // instancia original 
       switch(factor){
           case RANDOM:{
               // seleccionamos con factor aleatorio en base a un porcentaje
               seleccionaPorcentajeAleatorio(porcentaje,nuevaInstancia);
               break;
           }
           case PRIMEROS:{
           break;
           }
           case ULTIMOS:{
           break;
           }
           default:{System.out.println("No se reconocer el factor de selección");}
       }
       return nuevaInstancia;
      
    }
    
    public ArrayList<Patron> generarInstancia(int[] caracteristicas){
        // será las nuevas instancias
        ArrayList<Patron> nuevaInstancia = new ArrayList<>();
        
        // recorrer mis instancia original para generar el filtrado (a las nuevas)
        for (Patron patron: this.instanciaOriginal.getPatrones()){
            // procesar cada uno de los patrones
             String nombreClase = patron.getClase();
             double[] vector = patron.getVectorCa();
        
             double[] vectorNuevo = new double[caracteristicas.length];
            // recorrer los indices de la caracteristicas de interes 
            for(int x=0; x < caracteristicas.length;x+=1){
               vectorNuevo[x]=vector[caracteristicas[x]];
            } 
            nuevaInstancia.add(new Patron(vectorNuevo, nombreClase));
        }
     return nuevaInstancia;
    }

    private void seleccionaPorcentajeAleatorio(double porcentaje, ArrayList<Patron> nuevaInstancia) {
        if (porcentaje==100){
        // no se modifica la referencia
        }else{
            Random ran = new Random();
         // calcular la cantidad de elementos por clase 
         for (int x=0; x < this.instanciaOriginal.getClases().size();x++){
            int cantidadEliminar = calculaCantidad(x,porcentaje);
            int auxRan = instanciaOriginal.getCantidades().get(x);
            for(int y=0; y < cantidadEliminar;y++){
              int pos = ran.nextInt(auxRan);
              nuevaInstancia.remove(pos);
              auxRan--;
            }
         }
       }
    }

    public int calculaCantidad(int id_Clase, double porcentaje) {
        int elementos = this.instanciaOriginal.getCantidades().get(id_Clase);
        return (int)((elementos*porcentaje)/100);
    }
    
    
    
}
