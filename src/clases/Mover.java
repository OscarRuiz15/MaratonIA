package clases;


public class Mover extends Accion{
    
    private int casillas;

    public Mover(int costo,int casillas) {
        super(costo);
        this.casillas=casillas;
        
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
