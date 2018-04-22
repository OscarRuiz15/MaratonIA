package maratonia;

import clases.Arbol;
import clases.Mover;
import clases.Pelear;
import clases.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
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
    private int segundos = -1;
    private int minutos = 0;
    private int horas = 0;
    private JLabel lblHora, lblMinuto, lblSegundo, contpiedra, contpapel, conttijera, contpistola;

    public AdministrarMaraton(int tab[][], JLabel lblHora, JLabel lblMinuto, JLabel lblSegundo, JLabel contpiedra, JLabel contpapel, JLabel conttijera, JLabel contpistola) {
        this.tablero = tab;
        this.lblHora = lblHora;
        this.lblMinuto = lblMinuto;
        this.lblSegundo = lblSegundo;
        this.contpapel = contpapel;
        this.contpiedra = contpiedra;
        this.conttijera = conttijera;
        this.contpistola = contpistola;

        Mover mover = new Mover(1, 1);
        Pelear pelear = new Pelear(6, 2, 0, 0, 0);
        piedra = new Robot(1, 0, mover, pelear, false);

        mover = new Mover(1, 1);
        pelear = new Pelear(7, 2, 0, 0, 0);
        papel = new Robot(2, 0, mover, pelear, false);

        mover = new Mover(1, 1);
        pelear = new Pelear(5, 2, 0, 0, 0);
        tijera = new Robot(3, 0, mover, pelear, false);

        mover = new Mover(1, 1);
        pelear = new Pelear(8, 2, 0, 0, 0);
        pistola = new Robot(4, 0, mover, pelear, false);

        arbol = new Arbol(0, tablero.clone(), 0, 0, 0, 0, false);

        AdministrarArbol ada = new AdministrarArbol();
        Robot robots[] = {piedra, papel, tijera, pistola};

        arbol.setRobots(robots);
        creados.add(arbol);

        hiloPrincipal = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    segundos++;
                    if (segundos == 59) {
                        segundos = 0;
                        minutos++;
                        lblMinuto.setText("" + minutos);
                        if (minutos == 59) {
                            horas++;
                            minutos = 0;
                            lblMinuto.setText("" + minutos);
                            lblHora.setText("" + horas);
                        }
                    }
                    if (segundos < 10) {
                        lblSegundo.setText("0" + segundos);
                    } else {
                        lblSegundo.setText("" + segundos);
                    }

                    //////////////////////////////////////////////////////////////////////////////
                    /////   CONDICIONAL PARA ELEGIR EL NODO CON MENOR COSTO+HEURISTICA       /////
                    /////               Y USARLO COMO RAIZ PARA EXPANDIR DE EL               /////
                    //////////////////////////////////////////////////////////////////////////////
                    if (decision) {

                        int seleccionado = 100;
                        int comparador = 100000;
                        for (int i = 0; i < creados.size(); i++) {
                            if (!creados.get(i).isExpandido()) {
                                if (creados.get(i).getSuma() < comparador) {
                                    comparador = creados.get(i).getSuma();
                                    seleccionado = i;
                                }
                            }
                        }
                        arbol = creados.get(seleccionado);
                        tablero = arbol.getNodo();
                        piedra = arbol.getRobots()[0];
                        papel = arbol.getRobots()[1];
                        tijera = arbol.getRobots()[2];
                        pistola = arbol.getRobots()[3];
                    }
                    decision = false;
                    mostrarTablero();
                    int tamtablero = tablero[0].length - 1;

                    contpiedra.setText(""+piedra.getPelear().getTiempo());
                    contpapel.setText(""+papel.getPelear().getTiempo());
                    conttijera.setText(""+tijera.getPelear().getTiempo());
                    contpistola.setText(""+pistola.getPelear().getTiempo());
                    
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
                                arbol.setExpandido(true);
                                int enemigos[] = new int[4];
                                for (int i = 0; i < enemigos.length; i++) {
                                    enemigos[i] = tablero[i][piedra.getPosicion() + 1];
                                }

                                int robotsavanzan[] = {1, 2, 3};
                                Arbol nodo1 = null;
                                Arbol nodo2 = null;
                                Arbol nodo3 = null;
                                Arbol nodo4 = null;

                                try {
                                    nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), null, piedra.getId() - 1);
                                    creados.add(nodo1);

                                    robotsavanzan[0] = 0;
                                    nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), null, papel.getId() - 1);
                                    creados.add(nodo2);

                                    robotsavanzan[1] = 1;
                                    nodo3 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), null, tijera.getId() - 1);
                                    creados.add(nodo3);

                                    robotsavanzan[2] = 2;
                                    nodo4 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), null, pistola.getId() - 1);
                                    creados.add(nodo4);
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                Arbol hijos[] = {nodo1, nodo2, nodo3, nodo4};
                                arbol.setHijos(hijos);
                                decision = true;
                            } ///////////////////////////////////////////////////
                            //Fin para decidir entre 4                       //
                            ///////////////////////////////////////////////////
                            ///////////////////////////////////////////////////
                            //Condicionales para decidir entre 3             //
                            ///////////////////////////////////////////////////
                            //Si se encuentra la piedra, papel y tijera en la misma posicion
                            else if (piedra.getPosicion() == papel.getPosicion() && piedra.getPosicion() == tijera.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                arbol.setExpandido(true);
                                int enemigos[] = new int[4];
                                for (int i = 0; i < enemigos.length; i++) {
                                    enemigos[i] = tablero[i][piedra.getPosicion() + 1];
                                }
                                int robotsavanzan[] = {1, 2};
                                int robotsres[] = {3};
                                Arbol nodo1 = null;
                                Arbol nodo2 = null;
                                Arbol nodo3 = null;

                                try {
                                    nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), piedra.getId() - 1);
                                    creados.add(nodo1);

                                    robotsavanzan[0] = 0;
                                    nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), papel.getId() - 1);
                                    creados.add(nodo2);

                                    robotsavanzan[1] = 1;
                                    nodo3 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), tijera.getId() - 1);
                                    creados.add(nodo3);
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                Arbol hijos[] = {nodo1, nodo2, nodo3};
                                arbol.setHijos(hijos);
                                decision = true;
                            } //Si se encuentra la piedra, papel y pistola en la misma posicion
                            else if (piedra.getPosicion() == papel.getPosicion() && piedra.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                arbol.setExpandido(true);
                                int enemigos[] = new int[4];
                                for (int i = 0; i < enemigos.length; i++) {
                                    enemigos[i] = tablero[i][piedra.getPosicion() + 1];
                                }
                                int robotsavanzan[] = {1, 3};
                                int robotsres[] = {2};
                                Arbol nodo1 = null;
                                Arbol nodo2 = null;
                                Arbol nodo3 = null;

                                try {
                                    nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), piedra.getId() - 1);
                                    creados.add(nodo1);

                                    robotsavanzan[0] = 0;
                                    nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), papel.getId() - 1);
                                    creados.add(nodo2);

                                    robotsavanzan[1] = 1;
                                    nodo3 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), pistola.getId() - 1);
                                    creados.add(nodo3);
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                Arbol hijos[] = {nodo1, nodo2, nodo3};
                                arbol.setHijos(hijos);
                                decision = true;
                            } //Si se encuentra la piedra, tijera y pistola en la misma posicion
                            else if (piedra.getPosicion() == tijera.getPosicion() && piedra.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                arbol.setExpandido(true);
                                int enemigos[] = new int[4];
                                for (int i = 0; i < enemigos.length; i++) {
                                    enemigos[i] = tablero[i][piedra.getPosicion() + 1];
                                }
                                int robotsavanzan[] = {2, 3};
                                int robotsres[] = {1};
                                Arbol nodo1 = null;
                                Arbol nodo2 = null;
                                Arbol nodo3 = null;

                                try {
                                    nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), piedra.getId() - 1);
                                    creados.add(nodo1);

                                    robotsavanzan[0] = 0;
                                    nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), pistola.getId() - 1);
                                    creados.add(nodo2);

                                    robotsavanzan[1] = 2;
                                    nodo3 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), pistola.getId() - 1);
                                    creados.add(nodo3);
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                Arbol hijos[] = {nodo1, nodo2, nodo3};
                                arbol.setHijos(hijos);
                                decision = true;
                            } //Si se encuentra el papel, tijera y pistola en la misma posicion
                            else if (papel.getPosicion() == tijera.getPosicion() && papel.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                arbol.setExpandido(true);
                                int enemigos[] = new int[4];
                                for (int i = 0; i < enemigos.length; i++) {
                                    enemigos[i] = tablero[i][pistola.getPosicion() + 1];
                                }
                                int robotsavanzan[] = {2, 3};
                                int robotsres[] = {0};
                                Arbol nodo1 = null;
                                Arbol nodo2 = null;
                                Arbol nodo3 = null;

                                try {
                                    nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), papel.getId() - 1);
                                    creados.add(nodo1);

                                    robotsavanzan[0] = 1;
                                    nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), tijera.getId() - 1);
                                    creados.add(nodo2);

                                    robotsavanzan[1] = 2;
                                    nodo3 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos.clone(), robotsavanzan.clone(), robotsres.clone(), pistola.getId() - 1);
                                    creados.add(nodo3);
                                } catch (CloneNotSupportedException ex) {
                                    Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                Arbol hijos[] = {nodo1, nodo2, nodo3};
                                arbol.setHijos(hijos);
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
                                    arbol.setExpandido(true);
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][piedra.getPosicion() + 1];
                                    }
                                    int robotsavanzan[] = {1};
                                    int robotsres[] = {2, 3};
                                    Arbol nodo1 = null;
                                    Arbol nodo2 = null;

                                    try {
                                        nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos,
                                                robotsavanzan, robotsres, piedra.getId() - 1);
                                        creados.add(nodo1);

                                        robotsavanzan[0] = 0;
                                        nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos,
                                                robotsavanzan, robotsres, papel.getId() - 1);
                                        creados.add(nodo2);
                                    } catch (CloneNotSupportedException ex) {
                                        Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    Arbol hijos[] = {nodo1, nodo2};
                                    arbol.setHijos(hijos);
                                    decision = true;
                                }
                                //Si se encuentra la piedra y la tijera en la misma posicion
                                if (piedra.getPosicion() == tijera.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                    arbol.setExpandido(true);
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][piedra.getPosicion() + 1];
                                    }
                                    int robotsavanzan[] = {2};
                                    int robotsres[] = {1, 3};
                                    Arbol nodo1 = null;
                                    Arbol nodo2 = null;

                                    try {
                                        nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos,
                                                robotsavanzan, robotsres, piedra.getId() - 1);
                                        creados.add(nodo1);

                                        robotsavanzan[0] = 0;
                                        nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos,
                                                robotsavanzan, robotsres, tijera.getId() - 1);
                                        creados.add(nodo2);
                                    } catch (CloneNotSupportedException ex) {
                                        Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    Arbol hijos[] = {nodo1, nodo2};
                                    arbol.setHijos(hijos);
                                    decision = true;
                                }
                                //Si se encuentra la piedra y pistola en la misma posicion
                                if (piedra.getPosicion() == pistola.getPosicion() && tablero[0][piedra.getPosicion() + 1] != 0) {
                                    arbol.setExpandido(true);
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][pistola.getPosicion() + 1];
                                    }
                                    int robotsavanzan[] = {3};
                                    int robotsres[] = {1, 2};
                                    Arbol nodo1 = null;
                                    Arbol nodo2 = null;

                                    try {
                                        nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos,
                                                robotsavanzan, robotsres, piedra.getId() - 1);
                                        creados.add(nodo1);

                                        robotsavanzan[0] = 0;
                                        nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos,
                                                robotsavanzan, robotsres, pistola.getId() - 1);
                                        creados.add(nodo2);
                                    } catch (CloneNotSupportedException ex) {
                                        Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    Arbol hijos[] = {nodo1, nodo2};
                                    arbol.setHijos(hijos);
                                    decision = true;
                                }
                                //Si se encuentra el papel y la tijera en la misma posicion
                                if (papel.getPosicion() == tijera.getPosicion() && tablero[0][papel.getPosicion() + 1] != 0) {
                                    arbol.setExpandido(true);
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][papel.getPosicion() + 1];
                                    }
                                    int robotsavanzan[] = {2};
                                    int robotsres[] = {0, 3};
                                    Arbol nodo1 = null;
                                    Arbol nodo2 = null;

                                    try {
                                        nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos,
                                                robotsavanzan, robotsres, papel.getId() - 1);
                                        creados.add(nodo1);

                                        robotsavanzan[0] = 1;
                                        nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos,
                                                robotsavanzan, robotsres, tijera.getId() - 1);
                                        creados.add(nodo2);
                                    } catch (CloneNotSupportedException ex) {
                                        Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    Arbol hijos[] = {nodo1, nodo2};
                                    arbol.setHijos(hijos);
                                    decision = true;
                                }
                                //Si se encuentra el papel y la pistola en la misma posicion
                                if (papel.getPosicion() == pistola.getPosicion() && tablero[0][papel.getPosicion() + 1] != 0) {
                                    arbol.setExpandido(true);
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][pistola.getPosicion() + 1];
                                    }
                                    int robotsavanzan[] = {3};
                                    int robotsres[] = {0, 2};
                                    Arbol nodo1 = null;
                                    Arbol nodo2 = null;

                                    try {
                                        nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos,
                                                robotsavanzan, robotsres, papel.getId() - 1);
                                        creados.add(nodo1);

                                        robotsavanzan[0] = 1;
                                        nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos, robotsavanzan,
                                                robotsres, pistola.getId() - 1);
                                        creados.add(nodo2);
                                    } catch (CloneNotSupportedException ex) {
                                        Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    Arbol hijos[] = {nodo1, nodo2};
                                    arbol.setHijos(hijos);
                                    decision = true;
                                }
                                //Si se encuentra la tijera y la pistola en la misma posicion
                                if (tijera.getPosicion() == pistola.getPosicion() && tablero[0][pistola.getPosicion() + 1] != 0) {
                                    arbol.setExpandido(true);
                                    int enemigos[] = new int[4];
                                    for (int i = 0; i < enemigos.length; i++) {
                                        enemigos[i] = tablero[i][pistola.getPosicion() + 1];
                                    }
                                    int robotsavanzan[] = {3};
                                    int robotsres[] = {0, 1};
                                    Arbol nodo1 = null;
                                    Arbol nodo2 = null;

                                    try {
                                        nodo1 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos,
                                                robotsavanzan, robotsres, tijera.getId() - 1);
                                        creados.add(nodo1);

                                        robotsavanzan[0] = 2;
                                        nodo2 = ada.crearNodo(creados.size(), (Arbol) arbol.clone(), enemigos, robotsavanzan, robotsres, pistola.getId() - 1);
                                        creados.add(nodo2);
                                    } catch (CloneNotSupportedException ex) {
                                        Logger.getLogger(AdministrarMaraton.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    Arbol hijos[] = {nodo1, nodo2};
                                    arbol.setHijos(hijos);
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
                                        arbol.setExpandido(true);
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][piedra.getPosicion() + 1];

                                        }
                                        Robot robots[] = {piedra};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);
                                        }
                                        elegirRobot(robots, enemigos);
//                                        decision = true;
                                    }
                                    //Si en la posicion siguiente a donde esta parada el papel es un enemigo (mayor a 4)
                                    //entonces el elegido es el papel
                                    if (tablero[0][papel.getPosicion() + 1] > 4 && papel.getPelear().getTiempo() == 0) {
                                        arbol.setExpandido(true);
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][papel.getPosicion() + 1];
                                        }
                                        Robot robots[] = {papel};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);

                                        }
                                        elegirRobot(robots, enemigos);
//                                        decision = true;
                                    }
                                    //Si en la posicion siguiente a donde esta parada la tijera es un enemigo (mayor a 4)
                                    //entonces el elegido es la tijera
                                    if (tablero[0][tijera.getPosicion() + 1] > 4 && tijera.getPelear().getTiempo() == 0) {
                                        arbol.setExpandido(true);
                                        int enemigos[] = new int[4];
                                        for (int i = 0; i < enemigos.length; i++) {
                                            enemigos[i] = tablero[i][tijera.getPosicion() + 1];
                                        }
                                        Robot robots[] = {tijera};
                                        for (int i = 0; i < robots.length; i++) {
                                            robots[i].getPelear().tiempoPelea(enemigos);
                                        }
                                        elegirRobot(robots, enemigos);
//                                        decision = true;
                                    }
                                    //Si en la posicion siguiente a donde esta parada la pistola es un enemigo (mayor a 4)
                                    //entonces el elegido es la pistola
                                    if (tablero[0][pistola.getPosicion() + 1] > 4 && pistola.getPelear().getTiempo() == 0) {
                                        arbol.setExpandido(true);
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
//                                        decision = true;
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
                            if (!decision) {
                                if (piedra.getPelear().getTiempo() != 0) {
                                    peleaRobot(piedra);
                                    if (((piedra.getPosicion()) == papel.getPosicion()) && piedra.getPelear().getTiempo() == 0) {
                                        papel.setOcupado(false);
                                    }
                                    if (((piedra.getPosicion()) == tijera.getPosicion()) && piedra.getPelear().getTiempo() == 0) {
                                        tijera.setOcupado(false);
                                    }
                                    if (((piedra.getPosicion()) == pistola.getPosicion()) && piedra.getPelear().getTiempo() == 0) {
                                        pistola.setOcupado(false);
                                    }
                                } else if (!piedra.isOcupado()) {
                                    moverRobot(piedra);
                                    if (((piedra.getPosicion()) == papel.getPosicion()) && papel.getPelear().getTiempo() != 0) {
                                        System.out.println("Piedra se venga de papel");
                                        piedra.setOcupado(true);
                                        papel.getPelear().setTiempo((papel.getPelear().getTiempo()) * 2);
                                        papel.getPelear().setCosto(papel.getPelear().getCosto() * 2);
                                    }
                                    if (((piedra.getPosicion()) == tijera.getPosicion()) && tijera.getPelear().getTiempo() != 0) {
                                        System.out.println("Piedra ayuda a tijera");
                                        piedra.setOcupado(true);
                                        tijera.getPelear().setTiempo((tijera.getPelear().getTiempo()) / 2);
                                        tijera.getPelear().setCosto(papel.getPelear().getCosto() / 2);
                                    }

                                }
                                if (papel.getPelear().getTiempo() != 0) {
                                    peleaRobot(papel);
                                    if (((piedra.getPosicion()) == papel.getPosicion()) && papel.getPelear().getTiempo() == 0) {
                                        piedra.setOcupado(false);
                                    }
                                    if (((tijera.getPosicion()) == papel.getPosicion()) && papel.getPelear().getTiempo() == 0) {
                                        tijera.setOcupado(false);
                                    }
                                    if (((pistola.getPosicion()) == papel.getPosicion()) && papel.getPelear().getTiempo() == 0) {
                                        pistola.setOcupado(false);
                                    }

                                } else if (!papel.isOcupado()) {
                                    moverRobot(papel);
                                    if (((papel.getPosicion()) == tijera.getPosicion()) && tijera.getPelear().getTiempo() != 0) {
                                        System.out.println("Papel se venga de tijera");
                                        papel.setOcupado(true);
                                        tijera.getPelear().setTiempo((tijera.getPelear().getTiempo()) * 2);
                                        tijera.getPelear().setCosto((tijera.getPelear().getCosto()) * 2);
                                    }
                                    if (((papel.getPosicion()) == piedra.getPosicion()) && piedra.getPelear().getTiempo() != 0) {
                                        System.out.println("Papel ayuda a piedra");
                                        papel.setOcupado(true);
                                        piedra.getPelear().setTiempo((piedra.getPelear().getTiempo()) / 2);
                                        piedra.getPelear().setCosto((piedra.getPelear().getCosto()) / 2);
                                    }
                                }
                                if (tijera.getPelear().getTiempo() != 0) {
                                    peleaRobot(tijera);
                                    if (((piedra.getPosicion()) == tijera.getPosicion()) && tijera.getPelear().getTiempo() == 0) {
                                        piedra.setOcupado(false);
                                    }
                                    if (((papel.getPosicion()) == tijera.getPosicion()) && tijera.getPelear().getTiempo() == 0) {
                                        papel.setOcupado(false);
                                    }
                                    if (((pistola.getPosicion()) == tijera.getPosicion()) && tijera.getPelear().getTiempo() == 0) {
                                        pistola.setOcupado(false);
                                    }
                                } else if (!tijera.isOcupado()) {
                                    moverRobot(tijera);
                                    if (((tijera.getPosicion()) == piedra.getPosicion()) && piedra.getPelear().getTiempo() != 0) {
                                        System.out.println("Tijera se venga de piedra");
                                        tijera.setOcupado(true);
                                        piedra.getPelear().setTiempo((piedra.getPelear().getTiempo()) * 2);
                                        piedra.getPelear().setCosto((piedra.getPelear().getCosto()) * 2);
                                    }
                                    if (((tijera.getPosicion()) == papel.getPosicion()) && papel.getPelear().getTiempo() != 0) {
                                        System.out.println("Tijera ayuda a papel");
                                        tijera.setOcupado(true);
                                        papel.getPelear().setTiempo((papel.getPelear().getTiempo()) / 2);
                                        papel.getPelear().setCosto((papel.getPelear().getCosto()) / 2);
                                    }

                                }
                                if (pistola.getPelear().getTiempo() != 0) {
                                    peleaRobot(pistola);
                                    if (((piedra.getPosicion()) == pistola.getPosicion()) && pistola.getPelear().getTiempo() == 0) {
                                        piedra.setOcupado(false);
                                    }
                                    if (((papel.getPosicion()) == pistola.getPosicion()) && pistola.getPelear().getTiempo() == 0) {
                                        papel.setOcupado(false);
                                    }
                                    if (((tijera.getPosicion()) == pistola.getPosicion()) && pistola.getPelear().getTiempo() == 0) {
                                        tijera.setOcupado(false);
                                    }
                                } else if (!pistola.isOcupado()) {
                                    moverRobot(pistola);
                                    if (((pistola.getPosicion()) == piedra.getPosicion()) && piedra.getPelear().getTiempo() != 0) {
                                        System.out.println("Pistola se venga de piedra");
                                        pistola.setOcupado(true);
                                        piedra.getPelear().setTiempo((piedra.getPelear().getTiempo()) * 2);
                                        piedra.getPelear().setCosto((piedra.getPelear().getCosto()) * 2);
                                    }
                                    if (((pistola.getPosicion()) == papel.getPosicion()) && papel.getPelear().getTiempo() != 0) {
                                        System.out.println("Pistola se venga de papel");
                                        pistola.setOcupado(true);
                                        papel.getPelear().setTiempo((papel.getPelear().getTiempo()) * 2);
                                        papel.getPelear().setCosto((papel.getPelear().getCosto()) * 2);
                                    }
                                    if (((pistola.getPosicion()) == tijera.getPosicion()) && tijera.getPelear().getTiempo() != 0) {
                                        System.out.println("Pistola se venga de tijera");
                                        pistola.setOcupado(true);
                                        tijera.getPelear().setTiempo((tijera.getPelear().getTiempo()) * 2);
                                        tijera.getPelear().setCosto((tijera.getPelear().getCosto()) * 2);
                                    }
                                }
                            }
                            ///////////////////////////////////////////////////
                            //FIN Condicionales para pelear                  //
                            ///////////////////////////////////////////////////
                            ////////////////////////
                            Thread.sleep(1000);
                        }

                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        );

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
//                array[posicion] = 0;
//                robot.getPelear().setEnemigos(array);
//                robot.getPelear()
//                robot.getPelear().setTiempo(robot.getPelear().getTiempo() - 1);
//                tablero[posicion][robot.getPosicion() + 1] = 0;
//                robot.getPelear().setPosenemigo(posicion + 1);
                robot.getPelear().setCosto(1);
                /////////////////////Cambio/////////////////////////////////////////////////////////    
                //Cambie esta logica porque solo funciona siempre y cuando todos los enemigos sean iguales
                //Ahora puse un contador de atributo para contar cuanto le falta al robot para acabar con cada enemigo
            } else {
                robot.getPelear().setCosto(2);
            }
            if (robot.getPelear().getTiempoenemigo() == 0) {
                robot.getPelear().setTiempoenemigo(robot.getPelear().getCosto());
            }

            robot.getPelear().setTiempo(robot.getPelear().getTiempo() - 1);
            robot.getPelear().setTiempoenemigo(robot.getPelear().getTiempoenemigo() - 1);
            if (robot.getPelear().getTiempoenemigo() == 0) {
                array[posicion] = 0;
                robot.getPelear().setEnemigos(array);
                tablero[posicion][robot.getPosicion() + 1] = 0;
                robot.getPelear().setPosenemigo(posicion + 1);
            }
            /////////////////////Fin Cambio/////////////////////////////////////////////////////////    
        }
        if (array[array.length - 1] == 0) {
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
