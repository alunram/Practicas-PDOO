/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import deepspace.WeaponToUI;
import java.awt.Color;

/**
 *
 * @author alvaro
 */
public class WeaponView extends javax.swing.JPanel implements CombatElementView {

    private boolean selected = false;

    @Override
    public boolean isSelected() {
        return selected;
    }

    /**
     * Creates new form WeaponView
     */
    public WeaponView() {
        initComponents();
        selected = false;
        setOpaque(selected);
    }

    void setWeapon(WeaponToUI weapon) {
        if (weapon == null) {
            setVisible(false);
        } else {
            valor_tipo.setText(weapon.getType().toString());
            valor_potencia.setText(Float.toString(weapon.getPower()));
            valor_usos.setText(Integer.toString(weapon.getUses()));

            this.setOpaque(false);
            this.setBackground(Color.green);

            repaint();
            revalidate();

            setVisible(true);
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

        LabelUses = new javax.swing.JLabel();
        LabelPower = new javax.swing.JLabel();
        LabelType = new javax.swing.JLabel();
        valor_usos = new javax.swing.JLabel();
        valor_potencia = new javax.swing.JLabel();
        valor_tipo = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("weapon"));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        LabelUses.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 13)); // NOI18N
        LabelUses.setText("Uses:");

        LabelPower.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 13)); // NOI18N
        LabelPower.setText("Power: ");

        LabelType.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 13)); // NOI18N
        LabelType.setText("Type:");

        valor_usos.setText("usos");

        valor_potencia.setText("potencia");

        valor_tipo.setText("tipo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelUses)
                    .addComponent(LabelPower)
                    .addComponent(LabelType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valor_tipo)
                    .addComponent(valor_potencia)
                    .addComponent(valor_usos))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelUses)
                    .addComponent(valor_usos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPower)
                    .addComponent(valor_potencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelType)
                    .addComponent(valor_tipo))
                .addGap(0, 8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        selected = !selected;
        setOpaque(selected);
        repaint();
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelPower;
    private javax.swing.JLabel LabelType;
    private javax.swing.JLabel LabelUses;
    private javax.swing.JLabel valor_potencia;
    private javax.swing.JLabel valor_tipo;
    private javax.swing.JLabel valor_usos;
    // End of variables declaration//GEN-END:variables
}
