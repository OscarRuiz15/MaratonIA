package clases;



public class Robot implements Cloneable{
    
    private int id;
    private int posicion;
    private Mover mover;
    private Pelear pelear;
    private boolean ocupado;
    
    

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public Robot(int id, int posicion, Mover mover, Pelear pelear,boolean ocupado) {
        this.id=id;
        this.posicion = posicion;
        this.mover = mover;
        this.pelear = pelear;
        this.ocupado=ocupado;
        
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

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    
   
    
    
    
    
}
