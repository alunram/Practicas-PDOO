/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import controller.Controller;
import deepspace.GameState;
import deepspace.ShieldToUI;
import deepspace.SpaceStationToUI;
import deepspace.WeaponToUI;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author alvaro
 */
public class SpaceStationView extends javax.swing.JPanel {

    HangarView hangarView;
    DamageView damageView;

    /**
     * Creates new form SpaceStationView
     */
    public SpaceStationView() {
        initComponents();
        repaint();
        revalidate();
    }

    void setSpaceStation(SpaceStationToUI station) {
        if (station == null) {
            setVisible(false);
        } else {
            boolean hangarAvailable = (station.getHangar() != null);
            boolean shields = false;
            boolean weapons = false; //hangar
            boolean elements = false;
            boolean weaponsMounted = station.getWeapons().size() != 0;
            boolean shieldsMounted = station.getShieldBoosters().size() != 0;

            LabelName.setText(station.getName());
            LabelName.setOpaque(true);

            valor_ammopower.setText(Float.toString(station.getAmmoPower()));
            valor_shieldpower.setText(Float.toString(station.getShieldPower()));
            valor_fuel.setText(Float.toString(station.getFuelUnits()));
            valor_nmedals.setText(Integer.toString(station.getnMedals()));

            ArrayList<WeaponToUI> weaponsarray = station.getWeapons();
            ArrayList<ShieldToUI> shieldBoostersarray = station.getShieldBoosters();

            PanelWeapons.removeAll();
            PanelShields.removeAll();
            PanelHangar.removeAll();
            PanelDamage.removeAll();

            hangarView = new HangarView();
            damageView = new DamageView();

            WeaponView weaponView;
            ShieldView shieldView;
            for (WeaponToUI w : weaponsarray) {
                weaponView = new WeaponView();
                weaponView.setWeapon(w);
                PanelWeapons.add(weaponView);
            }

            for (ShieldToUI s : shieldBoostersarray) {
                shieldView = new ShieldView();
                shieldView.setShield(s);
                PanelShields.add(shieldView);
            }

            damageView.setDamage(station.getPendingDamage());
            PanelDamage.add(damageView);
            hangarView.setHangar(station.getHangar());
            PanelHangar.add(hangarView);

            if (hangarAvailable) {
                weapons = (station.getHangar().getWeapons().size() != 0);
                shields = (station.getHangar().getShieldBoosters().size() != 0);

                elements = weapons || shields;
            }

            GameState gameState = Controller.getInstance().getState();
            boolean init = gameState == GameState.INIT;
            boolean aftercombat = gameState == GameState.AFTERCOMBAT;

            discardHangarButt.setEnabled(hangarAvailable && (init || aftercombat));
            mountButt.setEnabled(hangarAvailable && elements && (init || aftercombat));
            discardButt.setEnabled((elements || weaponsMounted || shieldsMounted) && (init || aftercombat));

            repaint();

            revalidate();
        }
    }

    ArrayList<Integer> getSelectedWeapons() {
        ArrayList<Integer> result = new ArrayList<>();
        int contador = 0;

        for (Component c : PanelWeapons.getComponents()) {
            if (((CombatElementView) c).isSelected()) {
                result.add(contador);
            }
            contador++;
        }

        return result;
    }

    ArrayList<Integer> getSelectedShieldBoosters() {
        ArrayList<Integer> result = new ArrayList<>();
        int contador = 0;

        for (Component c : PanelShields.getComponents()) {
            if (((CombatElementView) c).isSelected()) {
                result.add(contador);
            }
            contador++;
        }

        return result;
    }

    void enabledButtons(SpaceStationToUI station) {
        boolean hangarAvailable = station.getHangar() != null;
        boolean shieldsInHangar = false;
        boolean weaponsInHangar = false;
        boolean elementsInHangar = false;
        if (hangarAvailable) {
            weaponsInHangar = (station.getHangar().getWeapons().size() != 0);
            shieldsInHangar = (station.getHangar().getShieldBoosters().size() != 0);

            elementsInHangar = weaponsInHangar || shieldsInHangar;
        }
        boolean weaponsMounted = station.getWeapons().size() != 0;
        boolean shieldsMounted = station.getShieldBoosters().size() != 0;

        GameState gameState = Controller.getInstance().getState();
        boolean init = gameState == GameState.INIT;
        boolean aftercombat = gameState == GameState.AFTERCOMBAT;

        discardButt.setEnabled(hangarAvailable && (init || aftercombat));
        mountButt.setEnabled(hangarAvailable && elementsInHangar && (init || aftercombat));
        discardButt.setEnabled((elementsInHangar || weaponsMounted || shieldsMounted) && (init || aftercombat));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelName = new javax.swing.JLabel();
        LabelAmmoPower = new javax.swing.JLabel();
        LabelShieldPower = new javax.swing.JLabel();
        LabelFuel = new javax.swing.JLabel();
        LabelnMedals = new javax.swing.JLabel();
        valor_ammopower = new javax.swing.JLabel();
        valor_shieldpower = new javax.swing.JLabel();
        valor_fuel = new javax.swing.JLabel();
        valor_nmedals = new javax.swing.JLabel();
        mountButt = new javax.swing.JButton();
        discardButt = new javax.swing.JButton();
        discardHangarButt = new javax.swing.JButton();
        ScrollPanelShields = new javax.swing.JScrollPane();
        PanelShields = new javax.swing.JPanel();
        ScrollPanelWeapons = new javax.swing.JScrollPane();
        PanelWeapons = new javax.swing.JPanel();
        PanelHangar = new javax.swing.JPanel();
        PanelDamage = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("SpaceStation"));

        LabelName.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 24)); // NOI18N
        LabelName.setText("NAME");

        LabelAmmoPower.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 13)); // NOI18N
        LabelAmmoPower.setText("-Potencia Ataque:");

        LabelShieldPower.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 13)); // NOI18N
        LabelShieldPower.setText("-Potencia Defensa");

        LabelFuel.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 13)); // NOI18N
        LabelFuel.setText("-Fuel:");

        LabelnMedals.setFont(new java.awt.Font("Fira Sans Semi-Light", 1, 13)); // NOI18N
        LabelnMedals.setText("-Medallas");

        valor_ammopower.setText("ammoPower");

        valor_shieldpower.setText("shieldPower");

        valor_fuel.setText("fuel");

        valor_nmedals.setText("nMedals");

        mountButt.setText("Equipar");
        mountButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mountButtActionPerformed(evt);
            }
        });

        discardButt.setText("Descartar");
        discardButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtActionPerformed(evt);
            }
        });

        discardHangarButt.setText("Descartar Hangar");
        discardHangarButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardHangarButtActionPerformed(evt);
            }
        });

        PanelShields.setBorder(javax.swing.BorderFactory.createTitledBorder("Shields"));
        ScrollPanelShields.setViewportView(PanelShields);

        PanelWeapons.setBorder(javax.swing.BorderFactory.createTitledBorder("Weapons"));
        ScrollPanelWeapons.setViewportView(PanelWeapons);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 305, Short.MAX_VALUE)
                        .addComponent(PanelDamage, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(mountButt)
                                .addGap(27, 27, 27)
                                .addComponent(discardButt)
                                .addGap(29, 29, 29)
                                .addComponent(discardHangarButt))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelName)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(LabelShieldPower)
                                                    .addComponent(LabelFuel))
                                                .addGap(23, 23, 23))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(LabelnMedals)
                                                .addGap(87, 87, 87)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(valor_nmedals)
                                            .addComponent(valor_fuel)
                                            .addComponent(valor_shieldpower)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(LabelAmmoPower)
                                        .addGap(26, 26, 26)
                                        .addComponent(valor_ammopower)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ScrollPanelShields)
                            .addComponent(ScrollPanelWeapons)
                            .addComponent(PanelHangar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelAmmoPower)
                            .addComponent(valor_ammopower))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelShieldPower)
                            .addComponent(valor_shieldpower))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelFuel)
                            .addComponent(valor_fuel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelnMedals)
                            .addComponent(valor_nmedals)))
                    .addComponent(PanelDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPanelShields, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPanelWeapons, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelHangar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mountButt)
                    .addComponent(discardButt)
                    .addComponent(discardHangarButt))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mountButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mountButtActionPerformed
        // TODO add your handling code here:
        ArrayList<Integer> weaponsSel = new ArrayList<>();
        ArrayList<Integer> shieldsSel = new ArrayList<>();
        
        hangarView.getSelectedInHangar(weaponsSel, shieldsSel);
        
        Controller.getInstance().mount(weaponsSel, shieldsSel);
        
        MainWindow.getInstance().updateView();
    }//GEN-LAST:event_mountButtActionPerformed

    private void discardButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtActionPerformed
        // TODO add your handling code here:
        ArrayList<Integer> weaponsSel = getSelectedWeapons();
        ArrayList<Integer> shieldsSel = getSelectedShieldBoosters();

        ArrayList<Integer> weaponsHangar = new ArrayList<>();

        ArrayList<Integer> shieldsHangar = new ArrayList<>();

        Controller.getInstance().discard(Controller.WEAPON, weaponsSel, shieldsSel);

        Controller.getInstance().discard(Controller.SHIELD, weaponsSel, shieldsSel);

        if (Controller.getInstance().getUIversion().getCurrentStation().getHangar() != null) {
            hangarView.getSelectedInHangar(weaponsHangar, shieldsHangar);
            Controller.getInstance().discard(Controller.HANGAR, weaponsHangar, shieldsHangar);
        }

        MainWindow.getInstance().updateView();
    }//GEN-LAST:event_discardButtActionPerformed

    private void discardHangarButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardHangarButtActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Hangar descartado.", MainWindow.getInstance().getAppName(), JOptionPane.INFORMATION_MESSAGE);

        Controller.getInstance().discardHangar();

        MainWindow.getInstance().updateView();
    }//GEN-LAST:event_discardHangarButtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelAmmoPower;
    private javax.swing.JLabel LabelFuel;
    private javax.swing.JLabel LabelName;
    private javax.swing.JLabel LabelShieldPower;
    private javax.swing.JLabel LabelnMedals;
    private javax.swing.JPanel PanelDamage;
    private javax.swing.JPanel PanelHangar;
    private javax.swing.JPanel PanelShields;
    private javax.swing.JPanel PanelWeapons;
    private javax.swing.JScrollPane ScrollPanelShields;
    private javax.swing.JScrollPane ScrollPanelWeapons;
    private javax.swing.JButton discardButt;
    private javax.swing.JButton discardHangarButt;
    private javax.swing.JButton mountButt;
    private javax.swing.JLabel valor_ammopower;
    private javax.swing.JLabel valor_fuel;
    private javax.swing.JLabel valor_nmedals;
    private javax.swing.JLabel valor_shieldpower;
    // End of variables declaration//GEN-END:variables
}
