package maratonia;

import clases.Arbol;
import clases.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministrarArbol {

    static int xd = 1;

    public AdministrarArbol() {
    }

    public int calcularHeuristica(Arbol nodo) {
        int tab[][] = nodo.getNodo();
        int acumulador = 0;
        Robot robots[] = nodo.getRobots();
        for (int i = 0; i < robots.length; i++) {
            //Calcula la posicion del robot con respecto a la meta
            acumulador += tab[0].length - robots[i].getPosicion();

        }
        acumulador /= 4;

        return acumulador;
    }
//Ahora sirve pero toca probarlo bien         

    public int calcularCosto(Arbol nodo) {

        int acumulador = 0;
        Robot robots[] = nodo.getRobots();
        for (int i = 0; i < robots.length; i++) {
            //Calcula la posicion del robot con respecto a la meta
            acumulador += robots[i].getPelear().getTiempo();

        }

        return acumulador;
    }

    ////////////////////////////////////////////////////////////         
    public Arbol crearNodo(int id,Arbol padre, int enemigos[], int robotspelea[], int robotsres[], int idrobot) {
        Arbol p = null;

        try {
            p = (Arbol) padre.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AdministrarArbol.class.getName()).log(Level.SEVERE, null, ex);
        }

        int idPadre = p.getPadre(); //Deberia ser getId(?
        int tab[][] = new int[4][p.getNodo()[0].length];
        tab[0] = p.getNodo()[0].clone();
        tab[1] = p.getNodo()[1].clone();
        tab[2] = p.getNodo()[2].clone();
        tab[3] = p.getNodo()[3].clone();

        Arbol nodo = new Arbol(id,tab, idPadre, 0, 0, 0, false);
        Robot robots[] = new Robot[4];
        try {
            robots[0] = (Robot) p.getRobots()[0].clone();
            robots[1] = (Robot) p.getRobots()[1].clone();
            robots[2] = (Robot) p.getRobots()[2].clone();
            robots[3] = (Robot) p.getRobots()[3].clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(AdministrarArbol.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < robotspelea.length; i++) {

            robots[robotspelea[i]].getMover().setCasillas(2);
            moverRobot(robots[robotspelea[i]], nodo.getNodo());
            //Estas dos lineas son necesarias para que no se contabilice los tiempos calculados anteriormente
            robots[robotspelea[i]].getPelear().setTiempo(0);
            robots[robotspelea[i]].getPelear().setEnemigos(null);
        }
        robots[idrobot].getPelear().tiempoPelea(enemigos);
        robots[idrobot].getPelear().setEnemigos(enemigos);

        peleaRobot(robots[idrobot], nodo.getNodo());
        if (robotsres != null) {

            if (robots[robotsres[0]].getPosicion() != robots[robotsres[0]].getPosicion()) {

                for (int i = 0; i < robotsres.length; i++) {

                    if (robots[robotspelea[i]].getPelear().getTiempo() != 0) {
                        peleaRobot(robots[robotsres[i]], nodo.getNodo());
                    } else {
                        moverRobot(robots[robotsres[i]], nodo.getNodo());
                    }

                }
            }
        }
        nodo.setRobots(robots);

        int heuristica = calcularHeuristica(nodo);
        nodo.setHeuristica(heuristica);
        int costo = calcularCosto(nodo);
        nodo.setCosto(costo);
        nodo.setSuma(costo + heuristica);

        verNodos(nodo);
        return nodo;
    }

    public Arbol inicializarNodo(int id,int padre, int tab[][], Robot robots[]) {
        //nodo.setExpandido(true);

        Arbol nodo = new Arbol(id,tab, padre, 0, 0, 0, false);

        nodo.setRobots(robots);
        int heuristica = calcularHeuristica(nodo);
        nodo.setHeuristica(heuristica);
        int costo = calcularCosto(nodo);
        nodo.setCosto(costo);
        nodo.setSuma(costo + heuristica);
        return nodo;
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

    //Metodo para calcular la heurisca de cada nodo
    public void verNodos(Arbol nodo) {
        System.out.println(" Nodo " + nodo.getId() + ":");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < nodo.getNodo()[0].length; j++) {
                System.out.print(nodo.getNodo()[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("\n");
        xd++;
    }
}
