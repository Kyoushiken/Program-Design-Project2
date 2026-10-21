package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;
import com.mycompany.project2.controller.GameState;
import com.mycompany.project2.controller.DerbyGameResultDAO;
import com.mycompany.project2.db.DatabaseManager;
import com.mycompany.project2.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class MainFrame extends JFrame implements PropertyChangeListener {
    
    private static final String RESULTS_CARD = "RESULTS";
    private static final int GAP = 10;
    private static final Dimension WINDOW_SIZE = new Dimension(640, 640);
    private static final Dimension LOG_SIZE = new Dimension(560, 180);
    
    static {
        Theme.install();
    }
    
    private final GameController controller = new GameController();

    private final ScenePanel scenePanel = new ScenePanel();
    
    private final JLabel statusLabel = new JLabel(" ");
    private final JTextArea logArea = new JTextArea();
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    public MainFrame() {
        super("Text Adventure RPG");
        controller.addListener(this);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatabaseManager.getInstance().shutdown();
                dispose();
                System.exit(0);
            }
        });

        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.BOLD, 14f));
        add(statusLabel, BorderLayout.NORTH);

        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setPreferredSize(new Dimension(560, 250));
        add(scrollPane, BorderLayout.CENTER);

        cardPanel.add(new StartPanel(controller), GameState.START.name());
        cardPanel.add(new TownGatePanel(controller), GameState.TOWN_GATE.name());
        cardPanel.add(new CrossroadPanel(controller), GameState.CROSSROAD.name());
        cardPanel.add(new FightPanel(controller), GameState.FIGHT.name());
        cardPanel.add(new GameOverPanel(), GameState.GAME_OVER.name());
        add(cardPanel, BorderLayout.SOUTH);

        setSize(620, 480);
        setLocationRelativeTo(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case GameController.STATE_PROPERTY ->
                cardLayout.show(cardPanel, controller.getState().name());
            case GameController.LOG_PROPERTY -> {
                logArea.append(evt.getNewValue() + "\n\n");
                logArea.setCaretPosition(logArea.getDocument().getLength());
            }
            case GameController.PLAYER_PROPERTY -> updateStatus();
        }
    }

    private void updateStatus() {
        Player player = controller.getPlayer();
        if (player != null) {
            statusLabel.setText(player.toString());
        }
    }
}
