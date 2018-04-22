package maratonia;

import java.awt.Color;
import java.util.ArrayList;

public class UIAplicacion extends javax.swing.JFrame {

    public static javax.swing.JButton campos[][];
    ArrayList<String> datos;
    private int tablero[][];
    public static int tam = 0;

    public UIAplicacion() {
        initComponents();
        menuSolucionar.setVisible(false);
        lblHora.setVisible(false);
        lblMinuto.setVisible(false);
        lblSegundo.setVisible(false);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        lblContPapel.setVisible(false);
        lblContPiedra.setVisible(false);
        lblContTijera.setVisible(false);
        lblContPistola.setVisible(false);
        lblPiedra.setVisible(false);
        lblPapel.setVisible(false);
        lblTijera.setVisible(false);
        lblPistola.setVisible(false);
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
        lblHora = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblMinuto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblSegundo = new javax.swing.JLabel();
        lblPiedra = new javax.swing.JLabel();
        lblPapel = new javax.swing.JLabel();
        lblTijera = new javax.swing.JLabel();
        lblPistola = new javax.swing.JLabel();
        lblContPiedra = new javax.swing.JLabel();
        lblContPapel = new javax.swing.JLabel();
        lblContTijera = new javax.swing.JLabel();
        lblContPistola = new javax.swing.JLabel();
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

        lblHora.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblHora.setText("00");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText(":");

        lblMinuto.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblMinuto.setText("00");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText(":");

        lblSegundo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblSegundo.setText("00");

        lblPiedra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/piedra.png"))); // NOI18N

        lblPapel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/papel.png"))); // NOI18N

        lblTijera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tijera.png"))); // NOI18N

        lblPistola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pistola.png"))); // NOI18N

        lblContPiedra.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblContPiedra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContPiedra.setText("0");
        lblContPiedra.setMaximumSize(new java.awt.Dimension(64, 64));
        lblContPiedra.setMinimumSize(new java.awt.Dimension(64, 64));
        lblContPiedra.setPreferredSize(new java.awt.Dimension(64, 64));
        lblContPiedra.setVerifyInputWhenFocusTarget(false);

        lblContPapel.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblContPapel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContPapel.setText("0");
        lblContPapel.setPreferredSize(new java.awt.Dimension(64, 64));

        lblContTijera.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblContTijera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContTijera.setText("0");
        lblContTijera.setPreferredSize(new java.awt.Dimension(64, 64));

        lblContPistola.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        lblContPistola.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContPistola.setText("0");
        lblContPistola.setPreferredSize(new java.awt.Dimension(64, 64));

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPistola)
                                .addGap(18, 18, 18)
                                .addComponent(lblContPistola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTijera)
                                .addGap(18, 18, 18)
                                .addComponent(lblContTijera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPapel)
                                .addGap(18, 18, 18)
                                .addComponent(lblContPapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPiedra)
                                .addGap(18, 18, 18)
                                .addComponent(lblContPiedra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMinuto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSegundo)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHora)
                            .addComponent(jLabel1)
                            .addComponent(lblMinuto)
                            .addComponent(jLabel2)
                            .addComponent(lblSegundo)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPiedra)
                            .addComponent(lblContPiedra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPapel)
                            .addComponent(lblContPapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTijera)
                            .addComponent(lblContTijera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPistola)
                            .addComponent(lblContPistola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuCargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCargarMouseClicked
        CargarArchivo ca = new CargarArchivo();
        datos = ca.CargarArchivo();

        if (datos.size() > 0) {
            /*for (int i = 0; i < datos.size(); i++) {
             System.out.println(datos.get(i));
             }*/

            menuSolucionar.setVisible(true);
            lblHora.setVisible(true);
            lblMinuto.setVisible(true);
            lblSegundo.setVisible(true);
            jLabel1.setVisible(true);
            jLabel2.setVisible(true);
            lblContPapel.setVisible(true);
            lblContPiedra.setVisible(true);
            lblContTijera.setVisible(true);
            lblContPistola.setVisible(true);
            lblPiedra.setVisible(true);
            lblPapel.setVisible(true);
            lblTijera.setVisible(true);
            lblPistola.setVisible(true);

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
            //System.out.println(campos[0][0].getWidth() + " " + campos[0][0].getHeight());
            mostrarTablero(tablero);
        }
    }//GEN-LAST:event_menuCargarMouseClicked

    private void menuSolucionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSolucionarMousePressed
        /*Logica l = new Logica(tablero);
         l.solucionProblema();*/
        AdministrarMaraton at = new AdministrarMaraton(tablero, lblHora, lblMinuto, lblSegundo, lblContPapel, lblContPiedra, lblContTijera, lblContPistola);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel lblContPapel;
    public static javax.swing.JLabel lblContPiedra;
    public static javax.swing.JLabel lblContPistola;
    public static javax.swing.JLabel lblContTijera;
    public javax.swing.JLabel lblHora;
    public javax.swing.JLabel lblMinuto;
    private javax.swing.JLabel lblPapel;
    private javax.swing.JLabel lblPiedra;
    private javax.swing.JLabel lblPistola;
    public static javax.swing.JLabel lblSegundo;
    private javax.swing.JLabel lblTijera;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCargar;
    private javax.swing.JMenu menuSolucionar;
    private javax.swing.JPanel panelCampos;
    // End of variables declaration//GEN-END:variables
}
