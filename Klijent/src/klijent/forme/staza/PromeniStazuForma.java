/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent.forme.staza;

import domen.OpstiDomenskiObjekat;
import domen.Staza;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import klijent.forme.OpstaEkranskaForma;
import klijent.kontrolerKi.KontrolerKIPromeniStazu;

/**
 *
 * @author draskovesic
 */
public class PromeniStazuForma extends OpstaEkranskaForma {

    private final KontrolerKIPromeniStazu kkiPromeniStazu;
    PronadjiStazeForma psf;

    public PromeniStazuForma(Staza staza, PronadjiStazeForma psf) {
        initComponents();
        kkiPromeniStazu = new KontrolerKIPromeniStazu(this, staza);
        this.psf = psf;
        pripremiKomboBox();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNazivStaze = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbTipStaze = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbSkiCentar = new javax.swing.JComboBox();
        txtBrojStaze = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnZapamtiPromene = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Promeni stazu");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel3.setText("Tezina staze:");

        cmbTipStaze.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laka", "Srednja", "Teska" }));

        jLabel4.setText("Ski Centar: ");

        cmbSkiCentar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtBrojStaze.setEditable(false);

        jLabel1.setText("Broj staze: ");

        jLabel2.setText("Naziv staze: ");

        btnZapamtiPromene.setText("Zapamti promene");
        btnZapamtiPromene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZapamtiPromeneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnZapamtiPromene)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNazivStaze)
                                .addComponent(cmbTipStaze, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbSkiCentar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtBrojStaze, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBrojStaze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNazivStaze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbTipStaze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbSkiCentar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnZapamtiPromene)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnZapamtiPromeneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZapamtiPromeneActionPerformed
        // TODO add your handling code here:
        kkiPromeniStazu.SOZapamtiStazu();
        psf.azurirajTabelu((Staza) kkiPromeniStazu.getOdo());
    }//GEN-LAST:event_btnZapamtiPromeneActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        kkiPromeniStazu.SOUcitajStazu();
    }//GEN-LAST:event_formWindowOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnZapamtiPromene;
    private javax.swing.JComboBox cmbSkiCentar;
    private javax.swing.JComboBox<String> cmbTipStaze;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtBrojStaze;
    private javax.swing.JTextField txtNazivStaze;
    // End of variables declaration//GEN-END:variables

    @Override
    public OpstiDomenskiObjekat kreirajObjekat() {
        return new Staza();
    }

    public JComboBox getCmbSkiCentar() {
        return cmbSkiCentar;
    }

    public JComboBox<String> getCmbTipStaze() {
        return cmbTipStaze;
    }

    public JTextField getTxtBrojStaze() {
        return txtBrojStaze;
    }

    public JTextField getTxtNazivStaze() {
        return txtNazivStaze;
    }

    private void pripremiKomboBox() {
        kkiPromeniStazu.pripremiKomboBox();
    }

}
