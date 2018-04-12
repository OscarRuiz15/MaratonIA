package clases;



public class Robot {
    
    private int id;
    private int posicion;
    private Mover mover;
    private Pelear pelear;
    
    

    public Robot(int id, int posicion, Mover mover, Pelear pelear) {
        this.id=id;
        this.posicion = posicion;
        this.mover = mover;
        this.pelear = pelear;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Mover getMover() {
        return mover;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }

    public Pelear getPelear() {
        return pelear;
    }

    public void setPelear(Pelear pelear) {
        this.pelear = pelear;
    }
    
    
   
    
    
    
    
}
