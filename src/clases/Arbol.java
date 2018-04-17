package clases;

public class Arbol implements Cloneable {

    private int nodo[][];
    private int padre;
    private Arbol hijos[];
    private int heuristica;
    private int costo;
    private int suma;
    private boolean expandido;
    private Robot robots[];

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public Arbol(int[][] nodo, int padre, int heuristica, int costo, int suma, boolean expandido) {
        this.nodo = nodo;
        this.padre = padre;
        this.heuristica = heuristica;
        this.costo = costo;
        this.suma = suma;
        this.expandido = expandido;

    }

    public int[][] getNodo() {
        return nodo;
    }

    public void setNodo(int[][] nodo) {
        this.nodo = nodo;
    }

    public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
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
