package clases;


public class Mover extends Accion implements Cloneable{
    
    private int casillas;

    public Mover(int costo,int casillas) {
        super(costo);
        this.casillas=casillas;
        
    }
    

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCasillas() {
        return casillas;
    }

    public void setCasillas(int casillas) {
        this.casillas = casillas;
    }
    
    public int moverA(int posicion){
        return posicion+casillas;
        
    }

    
    
    
    
    
}
