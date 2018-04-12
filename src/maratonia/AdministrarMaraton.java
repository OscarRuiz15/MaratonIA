package maratonia;

import clases.Arbol;
import clases.Mover;
import clases.Pelear;
import clases.Robot;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AdministrarMaraton extends UIAplicacion {

    private Robot piedra;
    private Robot papel;
    private Robot tijera;
    private Robot pistola;

    Thread hiloPrincipal;
    private List<Arbol> expandidos = new ArrayList<>();
    private List<Arbol> creados = new ArrayList<>();
    private int tablero[][];
    private boolean decision = false;

    public AdministrarMaraton(int tablero[][]) {
        this.tablero = tablero;
        Mover mover = new Mover(1, 1);
        Pelear pelear = new Pelear(6, 2, 0, 0, 0);
        piedra = new Robot(1, 0, mover, pelear);

        mover = new Mover(1, 1);
        pelear = new Pelear(7, 2, 0, 0, 0);
        papel = new Robot(2, 0, mover, pelear);

        mover = new Mover(1, 1);
        pelear = new Pelear(5, 2, 0, 0, 0);
        tijera = new Robot(3, 0, mover, pelear);

        mover = new Mover(1, 1);
        pelear = new Pelear(8, 2, 0, 0, 0);
        pistola = new Robot(4, 0, mover, pelear);

        hiloPrincipal = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    int tamtablero = tablero[0].length - 1;

                    //int papel.getPosicion() = papel.getPosicion();
                    try {

                        if (tamtablero - 1 < piedra.getPosicion() || tamtablero - 1 < papel.getPosicion() || tamtablero - 1 < tijera.getPosicion() || tamtablero - 1 < pistola.getPosicion()) {
                            JOptionPane.showMessageDialog(null, "Finalizo", "finish", JOptionPane.WARNING_MESSAGE);
                            break;
                        } else {

                            ///////////////////////////////////////////////////
                            //Condicionales para decidir entre 4             //
                            ///////////////////////////////////////////////////
                            if (piedra.getPosicion() == papel.getPosicion() && piedra.getPosicion() == tijera.getPosicion()
                                    && piedra.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                int enemigos[] = new int[4];
                                for (int i = 0; i < enemigos.length; i++) {
                                    enemigos[i] = tablero[i][piedra.getPosicion() + 1];

                                }
                                Robot robots[] = {piedra, papel, tijera, pistola};
                                for (int i = 0; i < robots.length; i++) {
                                    robots[i].getPelear().tiempoPelea(enemigos);

                                }
                                elegirRobot(robots, enemigos);
                                decision = true;
                            } ///////////////////////////////////////////////////
                            //Fin para decidir entre 4                       //
                            ///////////////////////////////////////////////////
                            ///////////////////////////////////////////////////
                            //Condicionales para decidir entre 3             //
                            ///////////////////////////////////////////////////
                            else if (piedra.getPosicion() == papel.getPosicion() && piedra.getPosicion() == tijera.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                int enemigos[] = new int[4];
                                for (int i = 0; i < enemigos.length; i++) {
                                    enemigos[i] = tablero[i][piedra.getPosicion() + 1];

                                }
                                Robot robots[] = {piedra, papel, tijera};
                                for (int i = 0; i < robots.length; i++) {
                                    robots[i].getPelear().tiempoPelea(enemigos);

                                }
                                elegirRobot(robots, enemigos);
                                decision = true;

                            } else if (piedra.getPosicion() == papel.getPosicion() && piedra.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                int enemigos[] = new int[4];
                                for (int i = 0; i < enemigos.length; i++) {
                                    enemigos[i] = tablero[i][piedra.getPosicion() + 1];

                                }
                                Robot robots[] = {piedra, papel, pistola};
                                for (int i = 0; i < robots.length; i++) {
                                    robots[i].getPelear().tiempoPelea(enemigos);

                                }
                                elegirRobot(robots, enemigos);
                                decision = true;

                            } else if (piedra.getPosicion() == tijera.getPosicion() && piedra.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                int enemigos[] = new int[4];
                                for (int i = 0; i < enemigos.length; i++) {
                                    enemigos[i] = tablero[i][piedra.getPosicion() + 1];

                                }
                                Robot robots[] = {piedra, pistola, tijera};
                                for (int i = 0; i < robots.length; i++) {
                                    robots[i].getPelear().tiempoPelea(enemigos);

                                }
                                elegirRobot(robots, enemigos);
                                decision = true;

                            } else if (papel.getPosicion() == tijera.getPosicion() && papel.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                int enemigos[] = new int[4];
                                for (int i = 0; i < enemigos.length; i++) {
                                    enemigos[i] = tablero[i][pistola.getPosicion() + 1];

                                }
                                Robot robots[] = {pistola, papel, tijera};
                                for (int i = 0; i < robots.length; i++) {
                                    robots[i].getPelear().tiempoPelea(enemigos);

                                }
                                elegirRobot(robots, enemigos);
                                decision = true;

                            }
                            ///////////////////////////////////////////////////
                            //FIN para decidir entre 3                       //
                            ///////////////////////////////////////////////////

                            ///////////////////////////////////////////////////
                            //Condicionales para decidir entre 2             //
                            ///////////////////////////////////////////////////
                            ///////////////////////////////////////////////////
                            if (!decision) {

                                if (piedra.getPosicion() == papel.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][pistola.getPosicion() + 1];

                                    }
                                    Robot robots[] = {piedra, papel};
                                    for (int i = 0; i < robots.length; i++) {
                                        robots[i].getPelear().tiempoPelea(enemigos);

                                    }
                                    elegirRobot(robots, enemigos);
                                    decision = true;
                                }
                                if (piedra.getPosicion() == tijera.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][pistola.getPosicion() + 1];

                                    }
                                    Robot robots[] = {piedra, tijera};
                                    for (int i = 0; i < robots.length; i++) {
                                        robots[i].getPelear().tiempoPelea(enemigos);

                                    }
                                    elegirRobot(robots, enemigos);
                                    decision = true;
                                }
                                if (piedra.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][pistola.getPosicion() + 1];

                                    }
                                    Robot robots[] = {piedra, pistola};
                                    for (int i = 0; i < robots.length; i++) {
                                        robots[i].getPelear().tiempoPelea(enemigos);

                                    }
                                    elegirRobot(robots, enemigos);
                                    decision = true;
                                }
                                if (papel.getPosicion() == tijera.getPosicion() && tablero[0][papel.getPosicion() + 1] != 0) {
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][pistola.getPosicion() + 1];

                                    }
                                    Robot robots[] = {tijera, papel};
                                    for (int i = 0; i < robots.length; i++) {
                                        robots[i].getPelear().tiempoPelea(enemigos);

                                    }
                                    elegirRobot(robots, enemigos);
                                    decision = true;
                                }
                                if (papel.getPosicion() == pistola.getPosicion() && tablero[0][papel.getPosicion() + 1] != 0) {
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][pistola.getPosicion() + 1];

                                    }
                                    Robot robots[] = {pistola, papel};
                                    for (int i = 0; i < robots.length; i++) {
                                        robots[i].getPelear().tiempoPelea(enemigos);

                                    }
                                    elegirRobot(robots, enemigos);
                                    decision = true;
                                }
                                if (tijera.getPosicion() == pistola.getPosicion() && tablero[0][pistola.getPosicion() + 1] != 0) {
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][pistola.getPosicion() + 1];

                                    }
                                    Robot robots[] = {tijera, pistola};
                                    for (int i = 0; i < robots.length; i++) {
                                        robots[i].getPelear().tiempoPelea(enemigos);

                                    }
                                    elegirRobot(robots, enemigos);
                                    decision = true;
                                }

                            }
                            ///////////////////////////////////////////////////
                            //FIN para decidir entre 2                       //
                            ///////////////////////////////////////////////////

                            ///////////////////////////////////////////////////
                            //Oscar Estuvo aca                               //
                            ///////////////////////////////////////////////////
                            // No se que hace ese condicional, pero sirvio
                            if (!decision) {
                                //Condicional si todos esten en una posicion diferente (alguno solo en una columna)
                                if (piedra.getPosicion() != papel.getPosicion() && piedra.getPosicion() != tijera.getPosicion()
                                        && piedra.getPosicion() != pistola.getPosicion()) {
                                    //Si en la posicion siguiente a donde esta parada la piedra es un enemigo (mayor a 4)
                                    //entonces el elegido es la piedra
                                    if (tablero[0][piedra.getPosicion() + 1] > 4 && piedra.getPelear().getTiempo() == 0) {
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][piedra.getPosicion() + 1];

                                        }
                                        Robot robots[] = {piedra};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);

                                        }
                                        elegirRobot(robots, enemigos);
                                        decision = true;
                                    }
                                    //Si en la posicion siguiente a donde esta parada el papel es un enemigo (mayor a 4)
                                    //entonces el elegido es el papel
                                    if (tablero[0][papel.getPosicion() + 1] > 4 && papel.getPelear().getTiempo() == 0) {
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][papel.getPosicion() + 1];

                                        }
                                        Robot robots[] = {papel};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);

                                        }
                                        elegirRobot(robots, enemigos);
                                        decision = true;
                                    }
                                    //Si en la posicion siguiente a donde esta parada la tijera es un enemigo (mayor a 4)
                                    //entonces el elegido es la tijera
                                    if (tablero[0][tijera.getPosicion() + 1] > 4 && tijera.getPelear().getTiempo() == 0) {
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][tijera.getPosicion() + 1];
                                        }
                                        Robot robots[] = {tijera};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);

                                        }
                                        elegirRobot(robots, enemigos);
                                        decision = true;
                                    }
                                    //Si en la posicion siguiente a donde esta parada la pistola es un enemigo (mayor a 4)
                                    //entonces el elegido es la pistola
                                    if (tablero[0][pistola.getPosicion() + 1] > 4 && pistola.getPelear().getTiempo() == 0) {
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][pistola.getPosicion() + 1];

                                        }
                                        Robot robots[] = {pistola};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);

                                        }
                                        elegirRobot(robots, enemigos);
                                        decision = true;
                                    }
                                    //Estos condicionales son diferentes a los demas (!=0) porque se daba el caso en el que
                                    //el siguiente no era un enemigo, si no otro robot, y lo tomaba como enemigo y se jodia
                                }
                            }
                            ///////////////////////////////////////////////////
                            //Hasta aca estuvo Oscar                         //
                            ///////////////////////////////////////////////////
                            ///////////////////////////////////////////////////
                            //FIN para decidir entre 2                       //
                            ///////////////////////////////////////////////////

                            ///////////////////////////////////////////////////
                            //Condicionales para pelear                      //
                            ///////////////////////////////////////////////////
                            //ESto lo hacen todos los nodos////////////////////
                            if (piedra.getPelear().getTiempo() != 0) {
                                peleaRobot(piedra);
                            } else {
                                moverRobot(piedra);
                            }
                            if (papel.getPelear().getTiempo() != 0) {
                                peleaRobot(papel);
                            } else {
                                moverRobot(papel);
                            }
                            if (tijera.getPelear().getTiempo() != 0) {
                                peleaRobot(tijera);
                            } else {
                                moverRobot(tijera);
                            }
                            if (pistola.getPelear().getTiempo() != 0) {
                                peleaRobot(pistola);
                            } else {
                                moverRobot(pistola);
                            }
                            ///////////////////////////////////////////////////
                            //FIN Condicionales para pelear                  //

                            decision = false;

                            mostrarTablero();

                            Thread.sleep(500);
                        }

                    } catch (InterruptedException e) {
                    }
                }
            }
        });

    }
    ///////////////////////////////////////////////////////////////
    //Cambios en el tablero como vector, pasar tablero de cada nodo como parametro
    ///////////////////////////////////////////////////77

    public void moverRobot(Robot robot) {

        int mov = robot.getMover().moverA(robot.getPosicion());

        tablero[robot.getId() - 1][mov] = robot.getId();
        tablero[robot.getId() - 1][robot.getPosicion()] = 0;
        robot.setPosicion(mov);
        robot.getMover().setCasillas(1);

    }

    public void peleaRobot(Robot robot) {
        int largo = robot.getPelear().getEnemigos().length;
        int posicion = robot.getPelear().getPosenemigo();
        int array[] = robot.getPelear().getEnemigos();

        int fuerza = robot.getPelear().getFuerza();

        if (array[posicion] != 0) {
            if (array[posicion] == fuerza) {
                array[posicion] = 0;
                robot.getPelear().setEnemigos(array);
                robot.getPelear().setTiempo(robot.getPelear().getTiempo() - 1);
                tablero[posicion][robot.getPosicion() + 1] = 0;
                robot.getPelear().setPosenemigo(posicion + 1);
                /////////////////////Cambio/////////////////////////////////////////////////////////    
                //Cambie esta logica porque solo funciona siempre y cuando todos los enemigos sean iguales
                //Ahora puse un contador de atributo para contar cuanto le falta al robot para acabar con cada enemigo
            } else {
                if (robot.getPelear().getTiempoenemigo() == 0) {
                    robot.getPelear().setTiempoenemigo(2);
                }

                robot.getPelear().setTiempo(robot.getPelear().getTiempo() - 1);
                robot.getPelear().setTiempoenemigo(robot.getPelear().getTiempoenemigo() - 1);
                if (robot.getPelear().getTiempoenemigo() == 0) {
                    array[posicion] = 0;
                    robot.getPelear().setEnemigos(array);
                    tablero[posicion][robot.getPosicion() + 1] = 0;
                    robot.getPelear().setPosenemigo(posicion + 1);
                }

            }
            /////////////////////Fin Cambio/////////////////////////////////////////////////////////    
        }
        if (array[array.length - 1] == 0) {
            moverRobot(robot);
            robot.getPelear().setPosenemigo(0);
        }

    }
////////////////////////////////////////////////////////////////////////////////////////////
    public void elegirRobot(Robot robot[], int enemigos[]) {
        Robot elegido = robot[0];
        int pos = 0;
        for (int i = 0; i < robot.length; i++) {
            if (elegido.getPelear().getTiempo() > robot[i].getPelear().getTiempo()) {
                elegido = robot[i];
                pos = i;

            }

        }
        //Pasar como elegido cada robot que tenga que pelear del tablero del nodo
        elegido.getPelear().setEnemigos(enemigos);

        for (int i = 0; i < robot.length; i++) {
            if (i != pos) {
                robot[i].getPelear().setTiempo(0);
                robot[i].getMover().setCasillas(2);
            }

        }

    }
    
    public void generarDesicion(){
        if (piedra.getPosicion() != papel.getPosicion() && piedra.getPosicion() != tijera.getPosicion()
                                        && piedra.getPosicion() != pistola.getPosicion()) {
                                    //Si en la posicion siguiente a donde esta parada la piedra es un enemigo (mayor a 4)
                                    //entonces el elegido es la piedra
                                    if (tablero[0][piedra.getPosicion() + 1] > 4 && piedra.getPelear().getTiempo() == 0) {
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][piedra.getPosicion() + 1];

                                        }
                                        Robot robots[] = {piedra};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);

                                        }
                                        elegirRobot(robots, enemigos);
                                        decision = true;
                                    }
                                    //Si en la posicion siguiente a donde esta parada el papel es un enemigo (mayor a 4)
                                    //entonces el elegido es el papel
                                    if (tablero[0][papel.getPosicion() + 1] > 4 && papel.getPelear().getTiempo() == 0) {
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][papel.getPosicion() + 1];

                                        }
                                        Robot robots[] = {papel};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);

                                        }
                                        elegirRobot(robots, enemigos);
                                        decision = true;
                                    }
                                    //Si en la posicion siguiente a donde esta parada la tijera es un enemigo (mayor a 4)
                                    //entonces el elegido es la tijera
                                    if (tablero[0][tijera.getPosicion() + 1] > 4 && tijera.getPelear().getTiempo() == 0) {
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][tijera.getPosicion() + 1];
                                        }
                                        Robot robots[] = {tijera};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);

                                        }
                                        elegirRobot(robots, enemigos);
                                        decision = true;
                                    }
                                    //Si en la posicion siguiente a donde esta parada la pistola es un enemigo (mayor a 4)
                                    //entonces el elegido es la pistola
                                    if (tablero[0][pistola.getPosicion() + 1] > 4 && pistola.getPelear().getTiempo() == 0) {
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][pistola.getPosicion() + 1];

                                        }
                                        Robot robots[] = {pistola};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);

                                        }
                                        elegirRobot(robots, enemigos);
                                        decision = true;
                                    }
                                    //Estos condicionales son diferentes a los demas (!=0) porque se daba el caso en el que
                                    //el siguiente no era un enemigo, si no otro robot, y lo tomaba como enemigo y se jodia
                                }
                            
                            ///////////////////////////////////////////////////
                            //Hasta aca estuvo Oscar                         //
                            ///////////////////////////////////////////////////
                            ///////////////////////////////////////////////////
                            //FIN para decidir entre 2                       //
                            ///////////////////////////////////////////////////

                            ///////////////////////////////////////////////////
                            //Condicionales para pelear                      //
                            ///////////////////////////////////////////////////
                            if (piedra.getPelear().getTiempo() != 0) {
                                peleaRobot(piedra);
                            } else {
                                moverRobot(piedra);
                            }
                            if (papel.getPelear().getTiempo() != 0) {
                                peleaRobot(papel);
                            } else {
                                moverRobot(papel);
                            }
                            if (tijera.getPelear().getTiempo() != 0) {
                                peleaRobot(tijera);
                            } else {
                                moverRobot(tijera);
                            }
                            if (pistola.getPelear().getTiempo() != 0) {
                                peleaRobot(pistola);
                            } else {
                                moverRobot(pistola);
                            }
    }

    public void mostrarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == 0) {
                    campos[i][j].setIcon(null);
                }
                if (tablero[i][j] == 1) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/piedra.png")));
                }
                if (tablero[i][j] == 2) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/papel.png")));
                }
                if (tablero[i][j] == 3) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tijera.png")));
                }
                if (tablero[i][j] == 4) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pistola.png")));
                }
                if (tablero[i][j] == 5) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vegetal.jpg")));
                    campos[i][j].setName("Vegetal");
                }
                if (tablero[i][j] == 6) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ladron2.png")));
                    campos[i][j].setName("Ladron");
                }
                if (tablero[i][j] == 7) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hilariante.png")));
                    campos[i][j].setName("Hilariante");
                }
                if (tablero[i][j] == 8) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blanco.png")));
                    campos[i][j].setName("Blanco");
                }
            }

        }
    }

    //Metodo para calcular la heurisca de cada nodo
    public int calcularHeuristica(Arbol nodo) {
        int tab[][] = nodo.getNodo();
        int acumulador = 0;
        Robot robots[] = nodo.getRobots();
        for (int i = 0; i < robots.length; i++) {
            //Calcula la posicion del robot con respecto a la meta
            acumulador += robots[i].getPosicion() - tab.length;

        }
        acumulador /= 4;

        return acumulador;
    }
//Esto no sirve pero se deja para inicializar el nodo         
    public int calcularCosto(Arbol nodo){
        int tab[][]=nodo.getNodo();
        int acumulador=0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j]==1 || tab[i][j]==2 || tab[i][j]==3 || tab[i][j]==4 ) {
                    acumulador+=tab.length-i;
                    
                }
                
            }
            
        }
        acumulador/=4;
        
        
        return acumulador;
    }
   ////////////////////////////////////////////////////////////         
    public Arbol crearNodo(Arbol padre) {
        int tab[][]=padre.getNodo();
        
        Arbol nodo=new Arbol(tab, padre,  0, 0, 0, false);
        Robot robots[]={piedra,papel,tijera,pistola};
        nodo.setRobots(robots);
        int heuristica=calcularHeuristica(nodo);
        nodo.setHeuristica(heuristica);
        int costo=calcularCosto(nodo);
        nodo.setCosto(costo);
        nodo.setSuma(costo+heuristica);
        return nodo;
    }
    
    public Arbol InicializarNodo(Arbol nodo) {
        nodo.setExpandido(true);
        int tab[][]=padre.getNodo();
        
        Arbol nodo=new Arbol(tab, padre,  0, 0, 0, false);
        Robot robots[]={piedra,papel,tijera,pistola};
        nodo.setRobots(robots);
        int heuristica=calcularHeuristica(nodo);
        nodo.setHeuristica(heuristica);
        int costo=calcularCosto(nodo);
        nodo.setCosto(costo);
        nodo.setSuma(costo+heuristica);
        return nodo;
    }

    public void animacion() {
        hiloPrincipal.start();

    }

}
