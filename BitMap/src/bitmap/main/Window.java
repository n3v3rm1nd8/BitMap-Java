/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bitmap.main;

import bitmap.menu.MyMenuBuilder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;

/**
 *
 * @author n3v3rm1nd
 */
public class Window extends javax.swing.JFrame {

    private DefaultListModel modelOpenPorts;
    private String outputLimpio = "";
    private int threads = 0;
    private int timeout = 0;
    private int startRange = 0;
    private int finishRange = 0;
    
    /**
     * Creates new form Window
     */
    public Window() {
        GlassPanePopup.install(this);
        MyMenuBuilder drawerBuilder = new MyMenuBuilder();
        Drawer.getInstance().setDrawerBuilder(drawerBuilder);
        initComponents();
        //Drawer.getInstance().showDrawer();
        modelOpenPorts = new DefaultListModel();
        txt_ip.setFocusable(true);
        btn_scan.setFocusable(false);
        btn_menu.setFocusable(false);
        readFileThreads();
        readFileTimeout();
        readFileRangePorts();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_scan = new javax.swing.JButton();
        txt_ip = new javax.swing.JTextField();
        btn_menu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_scan.setText("Scan");
        btn_scan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_scan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_scanActionPerformed(evt);
            }
        });

        btn_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bitmap/images/engranajes2.png"))); // NOI18N
        btn_menu.setContentAreaFilled(false);
        btn_menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_menuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_menuMouseExited(evt);
            }
        });
        btn_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(367, 367, 367)
                        .addComponent(txt_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(btn_scan))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btn_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(401, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btn_menu)
                .addGap(68, 68, 68)
                .addComponent(txt_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btn_scan)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_scanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scanActionPerformed
        // TODO add your handling code here:
        String ip = txt_ip.getText();
        Scanner s = new Scanner();
        s.setIp(ip);
        
        readFileThreads();
        readFileTimeout();
        readFileRangePorts();
        
//        startRange = 1000;
//        finishRange = 6000;
        System.out.println(startRange + " " + finishRange);
        
        s.portScan(threads, timeout, startRange, finishRange);
        //createTemporalWindowScan(s);
//        modelOpenPorts.addElement(s.getOpenPortList());
//        lst_OpenPorts.setModel(modelOpenPorts);
        //JOptionPane.showMessageDialog(null, s.getOpenPortList());
    }//GEN-LAST:event_btn_scanActionPerformed

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }
    
    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    
    public int getStartRange() {
        return startRange;
    }

    public void setStartRange(int startRange) {
        this.startRange = startRange;
    }
    
    public int getFinishRange() {
        return finishRange;
    }

    public void setFinishRange(int finishRange) {
        this.finishRange = finishRange;
    }

    private void readFileThreads(){
        try {
            FileInputStream fis = new FileInputStream("threads.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            threads = (int)ois.readObject();
            fis.close();
            ois.close();
        } 
        catch (Exception ex) {
            threads = 500;
        }
    }
    
    private void readFileTimeout(){
        try {
            FileInputStream fis = new FileInputStream("timeout.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            timeout = (int)ois.readObject();
            fis.close();
            ois.close();
        } 
        catch (Exception ex) {
            timeout = 200;
        }
    }
    
    private void readFileRangePorts(){
        try {
            FileInputStream fis = new FileInputStream("rangePorts.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            startRange = (int)ois.readObject();
            finishRange = (int)ois.readObject();
            fis.close();
            ois.close();
        } 
        catch (Exception ex) {
            startRange = 0;
            finishRange = 65535;
        }
    }
    
    private void btn_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menuActionPerformed
        // TODO add your handling code here:
        Drawer.getInstance().showDrawer();
    }//GEN-LAST:event_btn_menuActionPerformed

    private void btn_menuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_menuMouseEntered
        // TODO add your handling code here:
        btn_menu.setIcon(new ImageIcon(getClass().getResource("/bitmap/images/engranajes3.png")));
    }//GEN-LAST:event_btn_menuMouseEntered

    private void btn_menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_menuMouseExited
        // TODO add your handling code here:
        btn_menu.setIcon(new ImageIcon(getClass().getResource("/bitmap/images/engranajes2.png")));
    }//GEN-LAST:event_btn_menuMouseExited

    /*private void createTemporalWindowScan(Scanner s){
        JFrame frame = new JFrame();
        JPanel buttonPanel = new JPanel();
        JButton buttonCopiar = new JButton("Copiar");
        JButton buttonSalir = new JButton("Salir");
        
        frame.setUndecorated(true);
        buttonCopiar.setFocusable(false);
        buttonSalir.setFocusable(false);
        buttonPanel.setLayout(new FlowLayout());
        
        buttonPanel.add(buttonCopiar);
        buttonPanel.add(buttonSalir);
        
        // Crear un componente de texto para mostrar el contenido
        String ports = s.getOpenPortList().toString();
        outputLimpio = ports.substring(1, ports.length() - 1);
        outputLimpio = outputLimpio.replace(" ", "");
        
        JLabel label = new JLabel("<html><body><center><u>PORTS OPEN</u></center><br><br>" + outputLimpio + "</body></html>");
        label.setPreferredSize(label.getPreferredSize());
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        buttonCopiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar cuando se hace clic en el botón
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection selection = new StringSelection(outputLimpio);
                clipboard.setContents(selection, null);
                JOptionPane.showMessageDialog(null, "Puertos copiados al portapapeles");
            }
        });
        
        buttonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar cuando se hace clic en el botón
                frame.setVisible(false);
            }
        });
        
        // Establecer el tamaño de la ventana
        frame.setSize(300, 100);
        
        // Centrar la ventana en la pantalla y hacer que la pantalla sea dinamica en tamaño en funcion del tamaño del output que reciba
        frame.setLocationRelativeTo(null);
        frame.pack();
        
        // Hacer visible la ventana
        frame.setVisible(true);
    }*/
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_menu;
    private javax.swing.JButton btn_scan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_ip;
    // End of variables declaration//GEN-END:variables
}
