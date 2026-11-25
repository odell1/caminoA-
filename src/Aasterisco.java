import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Aasterisco {
    public static void main(String[] args) throws Exception {
      // Creamos un Nodo por cada vértice
      Nodo a=new Nodo(0,0,null,"A");
      Nodo b=new Nodo(0,1,null,"B");
      Nodo c=new Nodo(1,0,null,"C");
      Nodo d=new Nodo(1,1,null,"D");
      Nodo e=new Nodo(2,0,null,"E");
      Nodo f=new Nodo(2,1,null,"F");
      
     Nodo[] nodos ={a,b,c,d,e,f};// Array de nodos

     //Lista de adyacencia-- Para cada nodo, una lista con sus vecinos
     Map<Nodo,ArrayList<Nodo>> lista=new LinkedHashMap<>();
    
     //Metemos todos los nodos
     for (Nodo n:nodos){
        lista.put(n, new ArrayList<>());
     }//for 

     agregarArista(lista,a,b);
     agregarArista(lista,a,c);
     agregarArista(lista,b,d);
     agregarArista(lista,c,d);
     agregarArista(lista,c,e);
     agregarArista(lista,d,f);
     agregarArista(lista,e,f);
    
     //Llamamos al método de búscar A* 
     List<Nodo> camino=aEstrella(lista,a,f); //Nodo inicial a, nodo final f

     //Impresión del camino
        for(Nodo n:camino){
            System.out.println(" "+ n.dato + " ");
        }

    }//main

    private static void agregarArista(Map<Nodo, ArrayList<Nodo>> lista, Nodo origen, Nodo destino) {
        lista.get(origen).add(destino);
        lista.get(destino).add(origen);
    }//agregarArista


    private static List<Nodo> aEstrella(Map<Nodo, ArrayList<Nodo>> lista, Nodo inicio, Nodo meta) {
       PriorityQueue<Nodo> abiertos=new PriorityQueue<>(); // --->Ojo! ordena por el método compareTo
       Set<Nodo> cerrados=new HashSet<>();
       //Nodo inicio lo actualizamos
       inicio.setCoste(0);
       inicio.calculaHeuristica(meta);
       inicio.funcionTotal();
       abiertos.add(inicio);

       while (!abiertos.isEmpty()) {
            Nodo actual=abiertos.poll(); //---> Es el menor coste de ditancia + heurística
            
            if(actual == meta){ 
                System.out.println("Eureka hemos encontrado el peine");
                return reconstruirCamino(actual);//Eureka hemos encontrado el peine
            }    //if
            
            cerrados.add(actual);
           // Ahora cogemos los sucesores de actual. Que es una lista!!!
           for (Nodo vecinete :lista.get(actual)){
                //Calculo el nuevo coste
                int nuevoCoste=actual.getCoste()+1;//costeArista(actual,vecinete);
                boolean enAbiertos=abiertos.contains(vecinete); //Si esta en abiertos o no

                if(cerrados.contains(vecinete)) continue;

                if(!enAbiertos || nuevoCoste<vecinete.getCoste()){
                    //Si ya estaba lo sacamos antes.
                    // Ojo la priorityQueu no se modifica al moficar un nodo
                    if(enAbiertos) abiertos.remove(vecinete);

                    vecinete.padre=actual;
                    vecinete.setCoste(nuevoCoste);
                    vecinete.calculaHeuristica(meta);
                    vecinete.funcionTotal();
                    
                    
                    abiertos.add(vecinete);//--> Se vuelve a meter.

                }//if

           }//for

       }//while
       
        return null;// Abiertos es vacía y no encuentra nada devolvemos null
    }//aEstrella

    private static List<Nodo> reconstruirCamino(Nodo actual) {
        List<Nodo> camino=new ArrayList<>();
        for(Nodo n=actual;n!=null;n=n.padre){
            camino.addFirst(n);
        }
        //camino.reversed();
        return camino;
        
    }//reconstruirCamino
    






}//Aasterisco
