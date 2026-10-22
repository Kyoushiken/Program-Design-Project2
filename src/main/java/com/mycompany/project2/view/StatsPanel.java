package com.mycompany.project2.view;

import com.mycompany.project2.model.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class StatsPanel extends JPanel {

    private static final Color HP_GOOD = new Color(0x4CAF50);
    private static final Color HP_LOW = new Color(0xD9534F);

    private final JLabel nameLabel = GameStyle.label(" ");
    private final JProgressBar hpBar = new JProgressBar();
    private final JLabel weaponLabel = GameStyle.label(" ");
    private final JLabel itemsLabel = GameStyle.label(" ");

    public StatsPanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        hpBar.setStringPainted(true);
        hpBar.setPreferredSize(new Dimension(160, 22));

        add(nameLabel);
        add(hpBar);
        add(weaponLabel);
        add(itemsLabel);
        refresh(null);
    }

    public void refresh(Player player) {
        if (player == null) {
            setVisible(false);
            return;
        }
        setVisible(true);
        nameLabel.setText(player.getName());
        hpBar.setMaximum(player.getMaxHp());
        hpBar.setValue(player.getHp());
        hpBar.setString("HP " + player.getHp() + "/" + player.getMaxHp());
        hpBar.setForeground(player.getHp() <= player.getMaxHp() / 3 ? HP_LOW : HP_GOOD);
        weaponLabel.setText("Weapon: " + player.getWeapon().getName());
        itemsLabel.setText("Items: " + String.join(", ", player.getInventory().getItems()));
    }
}
