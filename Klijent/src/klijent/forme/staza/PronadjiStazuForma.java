/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.forme.staza;

import domen.OpstiDomenskiObjekat;
import domen.SkiCentar;
import domen.Staza;
import klijent.forme.OpstaEkranskaForma;
import klijent.kontrolerKi.KontrolerKIPronadjiStazu;

/**
 *
 * @author UrosVesic
 */
public class PronadjiStazuForma extends OpstaEkranskaForma {

    /**
     * Creates new form PronadjiStazuForma1
     */
    Staza staza;
    private final KontrolerKIPronadjiStazu kkiPronadjiStazu;

    public PronadjiStazuForma() {
        initComponents();
        kkiPronadjiStazu = new KontrolerKIPronadjiStazu(this);
        prepare();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblStaze = new javax.swing.JTable();
        btnPronadji = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtBrojStaze = new javax.swing.JTextField();
        btnPromeni = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblStaze.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblStaze);

        btnPronadji.setText("Pronadji");
        btnPronadji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPronadjiActionPerformed(evt);
            }
        });

        jLabel1.setText("Pretrazi stazu po broju: ");

        btnPromeni.setText("Promeni");
        btnPromeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromeniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtBrojStaze, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnPronadji))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPromeni, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBrojStaze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPronadji))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(btnPromeni)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPronadjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPronadjiActionPerformed
        // TODO add your handling code here:
        kkiPronadjiStazu.SOPretraziStaze();
    }//GEN-LAST:event_btnPronadjiActionPerformed

    private void btnPromeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromeniActionPerformed
        // TODO add your handling code here:

        new PromeniStazuForma((Staza) kkiPronadjiStazu.getOdo()).setVisible(true);
        /*ModelTabeleStaza model = (ModelTabeleStaza) tblStaze.getModel();
        List<Staza> staze = model.getStaze();
        for (Staza staza1 : staze) {
            try {
                Kontroler.getInstanca().zapamtiStazu(staza1);
                JOptionPane.showMessageDialog(this, "Sistem je zapamtio stazu: " + staza1.getNazivStaze());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti stazu: " + staza1.getNazivStaze());
            }
        }*/

    }//GEN-LAST:event_btnPromeniActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPromeni;
    private javax.swing.JButton btnPronadji;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStaze;
    private javax.swing.JTextField txtBrojStaze;
    // End of variables declaration//GEN-END:variables

    private void prepare()  {
        kkiPronadjiStazu.pripremiTabelu();

    }

    public javax.swing.JTable getTblStaze() {
        return tblStaze;
    }

    public javax.swing.JTextField getTxtBrojStaze() {
        return txtBrojStaze;
    }

    @Override
    public OpstiDomenskiObjekat kreirajObjekat() {
        return new Staza();
    }

}
