package maratonia;

import clases.Arbol;
import clases.Mover;
import clases.Pelear;
import clases.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AdministrarMaraton extends UIAplicacion {

    private Robot piedra;
    private Robot papel;
    private Robot tijera;
    private Robot pistola;

    private Arbol arbol;

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

        arbol = new Arbol(tablero.clone(), 0, 0, 0, 0, false);
        AdministrarArbol ada = new AdministrarArbol();
        Robot robots[] = {piedra, papel, tijera, pistola};
        arbol.setRobots(robots);

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
                                
                                int robotspelea[] = {1, 2, 3};
                                //Arbol arb1 = arbol;
//                                Robot robots[] = {piedra, papel, tijera, pistola};
                                System.out.println("A1");
                                for (int i = 0; i < 4; i++) {
                                    for (int j = 0; j < arbol.getNodo()[0].length; j++) {
                                        System.out.print(arbol.getNodo()[i][j]+" ");                                        
                                    }
                                    System.out.println("");
                                }
//                                int tableroactual[][]=tablero.clone();
                                
//                                Robot robots[]={piedra,papel,tijera,pistola};
////                                Arbol nodo1 = ada.crearNodo((Arbol)arbol.clone(), enemigos, robotspelea, null, piedra.getId() - 1);
                                
                                
                                System.out.println("A2");
                                for (int i = 0; i < 4; i++) {
                                    for (int j = 0; j < arbol.getNodo()[0].length; j++) {
                                        System.out.print(arbol.getNodo()[i][j]+" ");                                        
                                    }
                                    System.out.println("");
                                }
                                
                                Arbol nodo1=null;
                                Arbol nodo2=null;
                                Arbol nodo3=null;
                                Arbol nodo4=null;
                                try {
                                    
                                    nodo1 = ada.crearNodo((Arbol)arbol.clone(), enemigos, robotspelea, null, piedra.getId() - 1);
                                    robotspelea[0] = 0;
                                    nodo2 = ada.crearNodo((Arbol)arbol.clone(), enemigos, robotspelea, null, piedra.getId() - 1);
                                    robotspelea[1] = 1;
                                    nodo3 = ada.crearNodo((Arbol)arbol.clone(), enemigos, robotspelea, null, piedra.getId() - 1);
                                    robotspelea[2] = 2;
                                    nodo4 = ada.crearNodo((Arbol)arbol.clone(), enemigos, robotspelea, null, piedra.getId() - 1);
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                robotspelea[1] = 1;
                                
//                                Arbol nodo3 = ada.crearNodo((Arbol)arbol.clone(), enemigos, robotspelea, null, piedra.getId() - 1);
                                
                                robotspelea[2] = 2;
                                
//                                Arbol nodo4 = ada.crearNodo((Arbol)arbol.clone(), enemigos, robotspelea, null, piedra.getId() - 1);
                                creados.add(nodo1);
                                creados.add(nodo2);
                                creados.add(nodo3);
                                creados.add(nodo4);
                                Arbol hijos[] = {nodo1, nodo2, nodo3, nodo4};
                                arbol.setHijos(hijos);
//                                nodo1.getRobots()[0].getPelear().tiempoPelea(enemigos);
//                                nodo1.getRobots()[0].getPelear().setEnemigos(enemigos);
//
//                                for (int i = 0; i < 4; i++) {
//                                    if (i != 0) {
//                                        nodo1.getRobots()[i].getPelear().setTiempo(0);
//                                        nodo1.getRobots()[i].getMover().setCasillas(2);
//                                    }
//                                }
//                                peleaRobot(nodo1.getRobots()[0]);
//                                for (int i = 0; i < 4; i++) {
//                                    if (i != 0) {
//                                        moverRobot(nodo1.getRobots()[i]);
//                                    }
//
//                                }
//
//                                for (int i = 0; i < robots.length; i++) {
//                                    robots[i].getPelear().tiempoPelea(enemigos);
//
//                                }
//                                elegirRobot(robots, enemigos);
//                                decision = true;
                            } ///////////////////////////////////////////////////
                            //Fin para decidir entre 4                       //
                            ///////////////////////////////////////////////////
                            ///////////////////////////////////////////////////
                            //Condicionales para decidir entre 3             //
                            ///////////////////////////////////////////////////
                            //Si se encuentra la piedra, papel y tijera en la misma posicion
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

                            } //Si se encuentra la piedra, papel y pistola en la misma posicion
                            else if (piedra.getPosicion() == papel.getPosicion() && piedra.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
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

                            } //Si se encuentra la piedra, tijera y pistola en la misma posicion
                            else if (piedra.getPosicion() == tijera.getPosicion() && piedra.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
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

                            } //Si se encuentra el papel, tijera y pistola en la misma posicion
                            else if (papel.getPosicion() == tijera.getPosicion() && papel.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
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
                                //Si se encuentra la piedra y el papel en la misma posicion
                                if (piedra.getPosicion() == papel.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][piedra.getPosicion() + 1];

                                    }
                                    Robot robots[] = {piedra, papel};
                                    for (int i = 0; i < robots.length; i++) {
                                        robots[i].getPelear().tiempoPelea(enemigos);

                                    }
                                    elegirRobot(robots, enemigos);
                                    decision = true;
                                }
                                //Si se encuentra la piedra y la tijera en la misma posicion
                                if (piedra.getPosicion() == tijera.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][piedra.getPosicion() + 1];

                                    }
                                    Robot robots[] = {piedra, tijera};
                                    for (int i = 0; i < robots.length; i++) {
                                        robots[i].getPelear().tiempoPelea(enemigos);

                                    }
                                    elegirRobot(robots, enemigos);
                                    decision = true;
                                }
                                //Si se encuentra la piedra y pistola en la misma posicion
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
                                //Si se encuentra el papel y la tijera en la misma posicion
                                if (papel.getPosicion() == tijera.getPosicion() && tablero[0][papel.getPosicion() + 1] != 0) {
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][papel.getPosicion() + 1];

                                    }
                                    Robot robots[] = {tijera, papel};
                                    for (int i = 0; i < robots.length; i++) {
                                        robots[i].getPelear().tiempoPelea(enemigos);

                                    }
                                    elegirRobot(robots, enemigos);
                                    decision = true;
                                }
                                //Si se encuentra el papel y la pistola en la misma posicion
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
                                //Si se encuentra la tijera y la pistola en la misma posicion
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
                            //Decidir entre 1                                //
                            ///////////////////////////////////////////////////
                            ///////////////////////////////////////////////////
                            //Oscar Estuvo aca                               //
                            ///////////////////////////////////////////////////
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
                                        //Calcula el tiempo en que acaba con los enemigos
                                        //Algo raro aca, pilas
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
                            //FIN para decidir entre 1                       //
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
        //Elige el robot que se demore menos tiempo en derrotarlos
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

    public void generarDesicion() {
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
        //FIN para decidir entre 1                       //
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

    public void moverRobot(Robot robot, int tablero[][]) {

        int mov = robot.getMover().moverA(robot.getPosicion());

        tablero[robot.getId() - 1][mov] = robot.getId();
        tablero[robot.getId() - 1][robot.getPosicion()] = 0;
        robot.setPosicion(mov);
        robot.getMover().setCasillas(1);

    }

    public void peleaRobot(Robot robot, int tablero[][]) {
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

    

    public void animacion() {
        hiloPrincipal.start();
    }

}
