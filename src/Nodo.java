
public class Nodo implements Comparable<Nodo> { // Comparable para poder hacer la ordenación de la lista de abiertos
 
    public String dato;
    public int x,y; // Fila y la columna
    public Nodo padre;//Referencia al padre
 
    // Para El algoritmo A*
    // f(n)=g(n)+h(n)
    public int Coste;  //Coste para llegar a este nodo g(n)
    public int Heuristica; //Función heurística h(n)
    public int distancia; // Distancia total f(n)
 
 
    public Nodo(int x, int y, String dato) {
        this.x = x;
        this.y = y;
        this.padre=null;
        this.dato=dato;
    }
    public Nodo(int x, int y, Nodo padre, String dato) {
        this.x = x;
        this.y = y;
        this.padre=padre;
        this.dato=dato;
    }
    //////
    /// Método para calcular heurística de un nodo
    ///
    public int calculaHeuristica(Nodo meta){
        Heuristica= Math.abs(meta.getX()-x)+Math.abs(meta.getY()-y);
        return Heuristica;
    }//calculaHeuristica
 
 
    /// Método para devolver la función total
    public int funcionTotal(){
        this.distancia=this.Coste+this.Heuristica;
        return this.distancia;
    }//funcionTotal
 
 
    @Override
    public int compareTo(Nodo o) {
        return Integer.compare(this.distancia, o.distancia);
    }//compareTo
 
    public int getX() {
        return x;
    }
 
    public void setX(int x) {
        this.x = x;
    }
 
    public int getY() {
        return y;
    }
 
    public void setY(int y) {
        this.y = y;
    }
 
    public int getCoste() {
        return Coste;
    }
 
    public void setCoste(int Coste) {
        this.Coste = Coste;
    }
 
    public int getHeuristica() {
        return Heuristica;
    }
 
    public void setHeuristica(int Heuristica) { // antes no recibía parámetro y se asignaba a sí misma
        this.Heuristica = Heuristica;
    }
 
    public int getDistancia() {
        return distancia;
    }
 
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
 
}//Nodo