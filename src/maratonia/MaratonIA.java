package maratonia;

public class MaratonIA {
    
    public MaratonIA(){
        /**Declaramos el objeto*/
        UIAplicacion ui;
        /**Instanciamos el objeto*/
        ui=new UIAplicacion();
        /**Hacemos que se cargue la ventana*/
        ui.setVisible(true);
        ui.setDefaultCloseOperation(UIAplicacion.EXIT_ON_CLOSE);
        /**Hacemos que se abra al tamaño de la pantalla*/
        ui.setExtendedState(UIAplicacion.MAXIMIZED_BOTH);
    }
    public static void main(String args[]){
        /**Declaramos el objeto*/
        MaratonIA ia;
        /**Instanciamos el objeto*/
        ia = new MaratonIA();
    }
}
