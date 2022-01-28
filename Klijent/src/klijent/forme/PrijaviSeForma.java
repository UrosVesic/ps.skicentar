/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.forme;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import klijent.komunikacija.Komunikacija;
import klijent.kontrolerKi.KontrolerKIPrijaviSe;

/**
 *
 * @author draskovesic
 */
public class PrijaviSeForma extends OpstaEkranskaForma {

    private final KontrolerKIPrijaviSe kkips;

    public PrijaviSeForma() {
        initComponents();
        kkips = new KontrolerKIPrijaviSe(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtSifra = new javax.swing.JPasswordField();
        btnPrijaviSe = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmRegistracija = new javax.swing.JMenu();
        jmiRegistruj = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("E-mail:");

        jLabel2.setText("Sifra:");

        txtEmail.setText("urosvesic@gmail.com");

        txtSifra.setText("urosvesic");

        btnPrijaviSe.setText("Prijavi se");
        btnPrijaviSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrijaviSeActionPerformed(evt);
            }
        });

        jmRegistracija.setText(" Registracija");

        jmiRegistruj.setText(" Registruj novog korisnika");
        jmRegistracija.add(jmiRegistruj);

        jMenuBar1.add(jmRegistracija);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(279, Short.MAX_VALUE)
                .addComponent(btnPrijaviSe)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSifra)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSifra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnPrijaviSe)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrijaviSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrijaviSeActionPerformed
        // TODO add your handling code here:
        if (kkips.SOPrijaviSe()) {
            new GlavnaForma().setVisible(true);
        }
    }//GEN-LAST:event_btnPrijaviSeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrijaviSe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jmRegistracija;
    private javax.swing.JMenuItem jmiRegistruj;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSifra;
    // End of variables declaration//GEN-END:variables

    @Override
    public OpstiDomenskiObjekat kreirajObjekat() {
        return new Korisnik();
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JPasswordField getTxtSifra() {
        return txtSifra;
    }

}
