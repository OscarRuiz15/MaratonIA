package clases;

public class Arbol {
    private int nodo[][];
    private Arbol padre;
    private Arbol hijos[];
    private int heuristica;
    private int costo;
    private int suma;
    private boolean expandido;
    private Robot robots[];

    public Arbol(int[][] nodo, Arbol padre, int heuristica, int costo, int suma, boolean expandido) {
        this.nodo = nodo;
        this.padre = padre;
       
        this.heuristica = heuristica;
        this.costo = costo;
        this.suma = suma;
        this.expandido=expandido;
        
    }

    public int[][] getNodo() {
        return nodo;
    }

    public void setNodo(int[][] nodo) {
        this.nodo = nodo;
    }

    public Arbol getPadre() {
        return padre;
    }

    public void setPadre(Arbol padre) {
        this.padre = padre;
    }

    

    public Arbol[] getHijos() {
        return hijos;
    }

    public void setHijos(Arbol[] hijos) {
        this.hijos = hijos;
    }

    public int getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public Robot[] getRobots() {
        return robots;
    }

    public void setRobots(Robot[] robots) {
        this.robots = robots;
    }

    public boolean isExpandido() {
        return expandido;
    }

    public void setExpandido(boolean expandido) {
        this.expandido = expandido;
    }
    
    
    
    
}
