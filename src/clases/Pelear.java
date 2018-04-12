package clases;

public class Pelear extends Accion {
    private int fuerza;
    //Tiempo total
    private int tiempo;
    private int enemigos[];
    private int posenemigo;
    //Tiempo por el enemigo que pase
    private int tiempoenemigo;

    public Pelear(int fuerza, int costo,int tiempo,int posenemigo,int tiempoenemigo) {
        super(costo);
        this.fuerza = fuerza;
        this.tiempo=tiempo;
        this.posenemigo=posenemigo;
        this.tiempoenemigo=tiempoenemigo;
    }

    public int[] getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(int[] enemigos) {
        this.enemigos = enemigos;
    }
    

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getPosenemigo() {
        return posenemigo;
    }

    public void setPosenemigo(int posenemigo) {
        this.posenemigo = posenemigo;
    }

    public int getTiempoenemigo() {
        return tiempoenemigo;
    }

    public void setTiempoenemigo(int tiempoenemigo) {
        this.tiempoenemigo = tiempoenemigo;
    }
    
    
    
    
    public int tiempoPelea(int enemigos[]){
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] == fuerza){ 
            double cost=super.getCosto()/2; 
            int reduccion=(int)(Math.ceil(cost));
            tiempo+=reduccion;
            
        }else{
            tiempo+=super.getCosto();
        }
            
        }
        return tiempo;
    }
    
}
