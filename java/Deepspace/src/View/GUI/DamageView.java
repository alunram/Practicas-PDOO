/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import deepspace.DamageToUI;

/**
 *
 * @author alvaro
 */
public class DamageView extends javax.swing.JPanel {

    /**
     * Creates new form DamageView
     */
    public DamageView() {
        initComponents();
    }
    
    void setDamage(DamageToUI damage){
        if(damage == null){
            setVisible(false);
        }
        else{
            valor_armas.setText(damage.getWeaponInfo());
            valor_shields.setText(Integer.toString(damage.getNShields()));
            
            repaint();
            revalidate();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelShields = new javax.swing.JLabel();
        LabelWeapons = new javax.swing.JLabel();
        valor_shields = new javax.swing.JLabel();
        ScrollPanel = new javax.swing.JScrollPane();
        valor_armas = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Damage"));

        LabelShields.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 13)); // NOI18N
        LabelShields.setText("-Shields:");

        LabelWeapons.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 13)); // NOI18N
        LabelWeapons.setText("-Weapons:");

        valor_shields.setText("escudos");

        valor_armas.setEditable(false);
        valor_armas.setColumns(20);
        valor_armas.setRows(5);
        ScrollPanel.setViewportView(valor_armas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelShields)
                        .addGap(28, 28, 28)
                        .addComponent(valor_shields)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelWeapons)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrollPanel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelShields)
                    .addComponent(valor_shields))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(LabelWeapons)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelShields;
    private javax.swing.JLabel LabelWeapons;
    private javax.swing.JScrollPane ScrollPanel;
    private javax.swing.JTextArea valor_armas;
    private javax.swing.JLabel valor_shields;
    // End of variables declaration//GEN-END:variables
}
