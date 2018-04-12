package maratonia;

import java.awt.Color;
import java.util.ArrayList;

public class UIAplicacion extends javax.swing.JFrame {

    public static javax.swing.JButton campos[][];
    ArrayList<String> datos;
    private int tablero[][];
    public static int tam=0;

    public UIAplicacion() {
        initComponents();
        menuSolucionar.setVisible(false);
    }

    public void iniciarCampos(int tam) {
        campos = new javax.swing.JButton[4][tam];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < tam; j++) {
                campos[i][j] = new javax.swing.JButton();
                panelCampos.add(campos[i][j]);
                campos[i][j].setActionCommand(i + "" + j);
                //campos[i][j].setEnabled(false);
                campos[i][j].setBackground(Color.white);
                campos[i][j].setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

            }
            validate();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCampos = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        menuCargar = new javax.swing.JMenu();
        menuSolucionar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maraton de robots");

        panelCampos.setMinimumSize(new java.awt.Dimension(0, 600));
        panelCampos.setPreferredSize(new java.awt.Dimension(699, 0));

        javax.swing.GroupLayout panelCamposLayout = new javax.swing.GroupLayout(panelCampos);
        panelCampos.setLayout(panelCamposLayout);
        panelCamposLayout.setHorizontalGroup(
            panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        panelCamposLayout.setVerticalGroup(
            panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        menuCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/upload.png"))); // NOI18N
        menuCargar.setText("Cargar Archivo");
        menuCargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCargarMouseClicked(evt);
            }
        });
        menuBar.add(menuCargar);

        menuSolucionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check.png"))); // NOI18N
        menuSolucionar.setText("Solucionar");
        menuSolucionar.setActionCommand("Resolver");
        menuSolucionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuSolucionarMousePressed(evt);
            }
        });
        menuBar.add(menuSolucionar);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuCargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCargarMouseClicked
        CargarArchivo ca = new CargarArchivo();
        datos = ca.CargarArchivo();

        if (datos.size() > 0) {
            for (int i = 0; i < datos.size(); i++) {
                System.out.println(datos.get(i));
            }

            menuSolucionar.setVisible(true);

            tam = datos.get(0).length();
            int x = 0, y = 1;
            tablero = new int[4][tam];

            for (int i = 0; i < 4; i++) {
                x = 0;
                y = 1;
                for (int j = 0; j < tam; j++) {
                    tablero[i][j] = Integer.parseInt(datos.get(i).substring(x, y));
                    x++;
                    y++;
                }
            }

            panelCampos.removeAll();
            panelCampos.repaint();
            panelCampos.setLayout(new java.awt.GridLayout(4, tam));
            campos = new javax.swing.JButton[4][tam];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < tam; j++) {
                    campos[i][j] = new javax.swing.JButton();
                    panelCampos.add(campos[i][j]);
                    campos[i][j].setActionCommand(i + "" + j);
                    //campos[i][j].setEnabled(false);
                    campos[i][j].setBackground(Color.white);
                    campos[i][j].setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

                }
                validate();
            }
            System.out.println(campos[0][0].getWidth() + " " + campos[0][0].getHeight());
            mostrarTablero(tablero);
        }
    }//GEN-LAST:event_menuCargarMouseClicked

    private void menuSolucionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSolucionarMousePressed
        /*Logica l = new Logica(tablero);
        l.solucionProblema();*/
        AdministrarMaraton at=new AdministrarMaraton(tablero);
        at.animacion();
        
    }//GEN-LAST:event_menuSolucionarMousePressed
    public void mostrarTablero(int tablero[][]) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCargar;
    private javax.swing.JMenu menuSolucionar;
    private javax.swing.JPanel panelCampos;
    // End of variables declaration//GEN-END:variables
}
