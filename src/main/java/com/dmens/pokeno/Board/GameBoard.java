/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dmens.pokeno.Board;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Jarrett
 */
public class GameBoard extends javax.swing.JFrame {
    /**
     * Creates new form GameBoard
     */
    public GameBoard() {
        initComponents();

        PlayerHandPanel.setLayout(new FlowLayout());
        PlayerBenchPanel.setLayout(new FlowLayout());
        OpponentBenchPanel.setLayout(new FlowLayout());
        
        PlayerParalyzedLabel.setText("");
        PlayerAsleepLabel.setText("");
        PlayerStuckLabel.setText("");
        PlayerPoisonedLabel.setText("");
        OpponentParalyzedLabel.setText("");
        OpponentAsleepLabel.setText("");
        OpponentStuckLabel.setText("");
        OpponentPoisonedLabel.setText("");
    }
    
    //can be changed to take a Card object?
    public void addCardToHand(String cardName, boolean player)
    {
        JTextField newCard = new JTextField();
        newCard.setText(cardName);
        newCard.setEditable(false);
        
        MouseListener viewCard = new java.awt.event.MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent me) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //play card
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent me)
            {
                CardViewArea.setText(cardName);
            }

            @Override
            public void mouseExited(MouseEvent me)
            {
                CardViewArea.setText("");
            }
        };
        
        if (player)
        {
            PlayerHandPanel.add(newCard);
            newCard.addMouseListener(viewCard);
        }
        else
            OpponentHandPanel.add(newCard);
        //System.out.println("Drawing " + cardName);
    }
    
    //TODO - how do i get it to remove the right card?
    public void removeCardFromHand(int index, boolean player)
    {
        if (player)
        {
            //PlayerHandPanel.remove(this);
        }
    }
    
    public void addStatus(int type, boolean player)
    {
        if (player)
            switch(type)
            {
                case 0: PlayerParalyzedLabel.setText("Paralyzed"); break;
                case 1: PlayerAsleepLabel.setText("Asleep"); break;
                case 2: PlayerStuckLabel.setText("Stuck"); break;
                case 3: PlayerPoisonedLabel.setText("Poisoned"); break;
            }
        else
            switch(type)
            {
                case 0: OpponentParalyzedLabel.setText("Paralyzed"); break;
                case 1: OpponentAsleepLabel.setText("Asleep"); break;
                case 2: OpponentStuckLabel.setText("Stuck"); break;
                case 3: OpponentPoisonedLabel.setText("Poisoned"); break;
            }
    }
    
    public void removeStatus(int type, boolean player)
    {
        if (player)
            switch(type)
            {
                case 0: PlayerParalyzedLabel.setText(""); break;
                case 1: PlayerAsleepLabel.setText(""); break;
                case 2: PlayerStuckLabel.setText(""); break;
                case 3: PlayerPoisonedLabel.setText(""); break;
            }
        else
            switch(type)
            {
                case 0: OpponentParalyzedLabel.setText(""); break;
                case 1: OpponentAsleepLabel.setText(""); break;
                case 2: OpponentStuckLabel.setText(""); break;
                case 3: OpponentPoisonedLabel.setText(""); break;
            }
    }
    
    public void addCardToBench(String cardName, boolean player)
    {
        JTextField newCard = new JTextField();
        newCard.setText(cardName);
        newCard.setEditable(false);
        
        MouseListener viewCard = new java.awt.event.MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent me) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //play card
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent me)
            {
                CardViewArea.setText(cardName);
            }

            @Override
            public void mouseExited(MouseEvent me)
            {
                CardViewArea.setText("");
            }
        };
        
        if (player)
            PlayerBenchPanel.add(newCard);
        else
            OpponentBenchPanel.add(newCard);
        
        newCard.addMouseListener(viewCard);
    }
    
    public void setActivePokemon(String type, String name, String attack1, String attack2, int damageTaken, boolean player)
    {
        JTextArea activePokemonField;
        if (player)
        {
            activePokemonField = PlayersActivePokemonArea;
            PlayerDamageField.setText(Integer.toString(damageTaken));
        }
        else
        {
            activePokemonField = OpponentsActivePokemonArea;
            OpponentDamageField.setText(Integer.toString(damageTaken));
        }
        
        activePokemonField.setText(type + "\n" + name + "\n\n" + attack1 + "\n\n" + attack2);
        if(player && (attack2 == null || attack2.equals("")))
        {
            PlayerAttack2Btn.setEnabled(false);
        }
        else if(player)
        {
            PlayerAttack2Btn.setEnabled(true);
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

        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        CardViewArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        OpponentsActivePokemonArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        PlayersActivePokemonArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        OpponentLightningEnergyField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        OpponentWaterEnergyField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        OpponentPsychicEnergyField = new javax.swing.JTextField();
        OpponentFightingEnergyField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        PlayerWaterEnergyField = new javax.swing.JTextField();
        PlayerLightningEnergyField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        PlayerFightingEnergyField = new javax.swing.JTextField();
        PlayerPsychicEnergyField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ViewLightningEnergyField = new javax.swing.JTextField();
        ViewWaterEnergyField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        ViewPsychicEnergyField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        ViewFightingEnergyField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        PlayerRewardCardLabel = new javax.swing.JLabel();
        OpponentRewardCardLabel = new javax.swing.JLabel();
        PlayerHandPanel = new javax.swing.JPanel();
        PlayerBenchPanel = new javax.swing.JPanel();
        OpponentDamageField = new javax.swing.JTextField();
        OpponentRewardCardLabel1 = new javax.swing.JLabel();
        OpponentRewardCardLabel2 = new javax.swing.JLabel();
        PlayerDamageField = new javax.swing.JTextField();
        OpponentRewardCardLabel3 = new javax.swing.JLabel();
        ViewDamageField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        OpponentHandPanel = new javax.swing.JPanel();
        OpponentBenchPanel = new javax.swing.JPanel();
        PlayerAttack1Btn = new javax.swing.JButton();
        PlayerAttack2Btn = new javax.swing.JButton();
        PlayerRetreatBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        PlayerParalyzedLabel = new javax.swing.JLabel();
        PlayerAsleepLabel = new javax.swing.JLabel();
        PlayerStuckLabel = new javax.swing.JLabel();
        PlayerPoisonedLabel = new javax.swing.JLabel();
        OpponentParalyzedLabel = new javax.swing.JLabel();
        OpponentAsleepLabel = new javax.swing.JLabel();
        OpponentStuckLabel = new javax.swing.JLabel();
        OpponentPoisonedLabel = new javax.swing.JLabel();

        jLabel4.setText("Lightning");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CardViewArea.setEditable(false);
        CardViewArea.setColumns(20);
        CardViewArea.setRows(5);
        jScrollPane2.setViewportView(CardViewArea);

        OpponentsActivePokemonArea.setEditable(false);
        OpponentsActivePokemonArea.setColumns(20);
        OpponentsActivePokemonArea.setRows(5);
        OpponentsActivePokemonArea.setText("Card Type\nCard Name\n\nDescription/abilities");
        jScrollPane3.setViewportView(OpponentsActivePokemonArea);

        PlayersActivePokemonArea.setEditable(false);
        PlayersActivePokemonArea.setColumns(20);
        PlayersActivePokemonArea.setRows(5);
        PlayersActivePokemonArea.setText("Card Type\nCard Name\n\nDescription/abilities");
        jScrollPane4.setViewportView(PlayersActivePokemonArea);

        OpponentLightningEnergyField.setEditable(false);
        OpponentLightningEnergyField.setText("0");
        OpponentLightningEnergyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpponentLightningEnergyFieldActionPerformed(evt);
            }
        });

        jLabel7.setText("Water");

        OpponentWaterEnergyField.setEditable(false);
        OpponentWaterEnergyField.setText("0");

        jLabel8.setText("Psychic");

        OpponentPsychicEnergyField.setEditable(false);
        OpponentPsychicEnergyField.setText("0");

        OpponentFightingEnergyField.setEditable(false);
        OpponentFightingEnergyField.setText("0");

        jLabel9.setText("Fighting");

        jLabel10.setText("Lightning");

        PlayerWaterEnergyField.setEditable(false);
        PlayerWaterEnergyField.setText("0");

        PlayerLightningEnergyField.setEditable(false);
        PlayerLightningEnergyField.setText("0");
        PlayerLightningEnergyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayerLightningEnergyFieldActionPerformed(evt);
            }
        });

        jLabel15.setText("Water");

        jLabel16.setText("Fighting");

        PlayerFightingEnergyField.setEditable(false);
        PlayerFightingEnergyField.setText("0");

        PlayerPsychicEnergyField.setEditable(false);
        PlayerPsychicEnergyField.setText("0");

        jLabel17.setText("Psychic");

        jLabel18.setText("Lightning");

        jLabel19.setText("Water");

        ViewLightningEnergyField.setEditable(false);
        ViewLightningEnergyField.setText("0");
        ViewLightningEnergyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewLightningEnergyFieldActionPerformed(evt);
            }
        });

        ViewWaterEnergyField.setEditable(false);
        ViewWaterEnergyField.setText("0");

        jLabel20.setText("Lightning");

        ViewPsychicEnergyField.setEditable(false);
        ViewPsychicEnergyField.setText("0");

        jLabel21.setText("Psychic");

        jLabel22.setText("Fighting");

        ViewFightingEnergyField.setEditable(false);
        ViewFightingEnergyField.setText("0");

        jLabel2.setText("Hand");

        jLabel3.setText("Bench");

        PlayerRewardCardLabel.setText("Remaining Reward Cards: 6");

        OpponentRewardCardLabel.setText("Remaining Reward Cards: 6");

        javax.swing.GroupLayout PlayerHandPanelLayout = new javax.swing.GroupLayout(PlayerHandPanel);
        PlayerHandPanel.setLayout(PlayerHandPanelLayout);
        PlayerHandPanelLayout.setHorizontalGroup(
            PlayerHandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        PlayerHandPanelLayout.setVerticalGroup(
            PlayerHandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PlayerBenchPanelLayout = new javax.swing.GroupLayout(PlayerBenchPanel);
        PlayerBenchPanel.setLayout(PlayerBenchPanelLayout);
        PlayerBenchPanelLayout.setHorizontalGroup(
            PlayerBenchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        PlayerBenchPanelLayout.setVerticalGroup(
            PlayerBenchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        OpponentDamageField.setEditable(false);
        OpponentDamageField.setText("0");

        OpponentRewardCardLabel1.setText("DamageTaken");

        OpponentRewardCardLabel2.setText("DamageTaken");

        PlayerDamageField.setEditable(false);
        PlayerDamageField.setText("0");

        OpponentRewardCardLabel3.setText("DamageTaken");

        ViewDamageField.setEditable(false);
        ViewDamageField.setText("0");

        jLabel5.setText("Bench");

        jLabel6.setText("Hand");

        javax.swing.GroupLayout OpponentHandPanelLayout = new javax.swing.GroupLayout(OpponentHandPanel);
        OpponentHandPanel.setLayout(OpponentHandPanelLayout);
        OpponentHandPanelLayout.setHorizontalGroup(
            OpponentHandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        OpponentHandPanelLayout.setVerticalGroup(
            OpponentHandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout OpponentBenchPanelLayout = new javax.swing.GroupLayout(OpponentBenchPanel);
        OpponentBenchPanel.setLayout(OpponentBenchPanelLayout);
        OpponentBenchPanelLayout.setHorizontalGroup(
            OpponentBenchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        OpponentBenchPanelLayout.setVerticalGroup(
            OpponentBenchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        PlayerAttack1Btn.setText("Attack1");

        PlayerAttack2Btn.setText("Attack2");

        PlayerRetreatBtn.setText("Retreat");

        PlayerParalyzedLabel.setText("Paralyzed");

        PlayerAsleepLabel.setText("Asleep");

        PlayerStuckLabel.setText("Stuck");

        PlayerPoisonedLabel.setText("Poisoned");

        OpponentParalyzedLabel.setText("Paralyzed");

        OpponentAsleepLabel.setText("Asleep");

        OpponentStuckLabel.setText("Stuck");

        OpponentPoisonedLabel.setText("Poisoned");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(PlayerRewardCardLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(OpponentRewardCardLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(OpponentRewardCardLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(OpponentDamageField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(OpponentRewardCardLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PlayerDamageField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PlayerParalyzedLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PlayerAsleepLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PlayerStuckLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PlayerPoisonedLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(OpponentParalyzedLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(OpponentAsleepLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(OpponentStuckLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(OpponentPoisonedLabel, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(PlayerLightningEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel16)
                                            .addGap(15, 15, 15)
                                            .addComponent(PlayerFightingEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(PlayerPsychicEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(PlayerWaterEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(PlayerAttack1Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PlayerAttack2Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PlayerRetreatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(124, 124, 124))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(OpponentRewardCardLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ViewDamageField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(OpponentLightningEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(15, 15, 15)
                                            .addComponent(OpponentFightingEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(OpponentPsychicEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(OpponentWaterEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ViewLightningEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(15, 15, 15)
                                .addComponent(ViewFightingEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ViewPsychicEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ViewWaterEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PlayerBenchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PlayerHandPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(OpponentBenchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(8, 8, 8)
                                .addComponent(OpponentHandPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(OpponentHandPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(OpponentBenchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(OpponentRewardCardLabel)
                        .addGap(290, 290, 290)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(PlayerRewardCardLabel))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PlayerDamageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OpponentRewardCardLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PlayerParalyzedLabel)
                        .addGap(13, 13, 13)
                        .addComponent(PlayerAsleepLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(9, 9, 9)
                        .addComponent(PlayerStuckLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PlayerPoisonedLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(OpponentDamageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(OpponentRewardCardLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(OpponentParalyzedLabel)
                                        .addGap(13, 13, 13)
                                        .addComponent(OpponentAsleepLabel)
                                        .addGap(15, 15, 15)
                                        .addComponent(OpponentStuckLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(OpponentPoisonedLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(OpponentFightingEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(OpponentLightningEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(OpponentPsychicEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(OpponentWaterEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addGap(73, 73, 73)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(ViewDamageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(OpponentRewardCardLabel3))))
                                .addGap(98, 98, 98))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PlayerFightingEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PlayerLightningEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PlayerPsychicEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(PlayerWaterEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PlayerAttack1Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PlayerAttack2Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PlayerRetreatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ViewFightingEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ViewLightningEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ViewPsychicEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ViewWaterEnergyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(PlayerBenchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PlayerHandPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OpponentLightningEnergyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpponentLightningEnergyFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OpponentLightningEnergyFieldActionPerformed

    private void PlayerLightningEnergyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayerLightningEnergyFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PlayerLightningEnergyFieldActionPerformed

    private void ViewLightningEnergyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewLightningEnergyFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewLightningEnergyFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    //public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameBoard().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea CardViewArea;
    private javax.swing.JLabel OpponentAsleepLabel;
    private javax.swing.JPanel OpponentBenchPanel;
    private javax.swing.JTextField OpponentDamageField;
    private javax.swing.JTextField OpponentFightingEnergyField;
    private javax.swing.JPanel OpponentHandPanel;
    private javax.swing.JTextField OpponentLightningEnergyField;
    private javax.swing.JLabel OpponentParalyzedLabel;
    private javax.swing.JLabel OpponentPoisonedLabel;
    private javax.swing.JTextField OpponentPsychicEnergyField;
    private javax.swing.JLabel OpponentRewardCardLabel;
    private javax.swing.JLabel OpponentRewardCardLabel1;
    private javax.swing.JLabel OpponentRewardCardLabel2;
    private javax.swing.JLabel OpponentRewardCardLabel3;
    private javax.swing.JLabel OpponentStuckLabel;
    private javax.swing.JTextField OpponentWaterEnergyField;
    private javax.swing.JTextArea OpponentsActivePokemonArea;
    private javax.swing.JLabel PlayerAsleepLabel;
    private javax.swing.JButton PlayerAttack1Btn;
    private javax.swing.JButton PlayerAttack2Btn;
    private javax.swing.JPanel PlayerBenchPanel;
    private javax.swing.JTextField PlayerDamageField;
    private javax.swing.JTextField PlayerFightingEnergyField;
    private javax.swing.JPanel PlayerHandPanel;
    private javax.swing.JTextField PlayerLightningEnergyField;
    private javax.swing.JLabel PlayerParalyzedLabel;
    private javax.swing.JLabel PlayerPoisonedLabel;
    private javax.swing.JTextField PlayerPsychicEnergyField;
    private javax.swing.JButton PlayerRetreatBtn;
    private javax.swing.JLabel PlayerRewardCardLabel;
    private javax.swing.JLabel PlayerStuckLabel;
    private javax.swing.JTextField PlayerWaterEnergyField;
    private javax.swing.JTextArea PlayersActivePokemonArea;
    private javax.swing.JTextField ViewDamageField;
    private javax.swing.JTextField ViewFightingEnergyField;
    private javax.swing.JTextField ViewLightningEnergyField;
    private javax.swing.JTextField ViewPsychicEnergyField;
    private javax.swing.JTextField ViewWaterEnergyField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
