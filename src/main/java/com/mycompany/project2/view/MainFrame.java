package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;
import com.mycompany.project2.controller.GameListener;
import com.mycompany.project2.controller.GameState;
import com.mycompany.project2.model.Player;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MainFrame extends JFrame implements GameListener {

    private final GameController controller = new GameController();
    private final ScenePanel scenePanel = new ScenePanel();
    private final StatsPanel statsPanel = new StatsPanel();
    private final JTextArea logArea = new JTextArea();
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);
    private static final String INTRO =
    "The gates of the town are shut, and the guard will only let in a traveller who carries "
    + "the town's lost silver ring. A monster took it and hides somewhere beyond the crossroad. "
    + "Defeat it, take the ring, and bring it to the guard.";

    public MainFrame() {
        super("Town's Lost Ring");
        controller.addListener(this);
        
        stateChanged(controller.getState());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeGame();
            }
        });

        scenePanel.add(buildTopBar(), BorderLayout.NORTH);
        scenePanel.add(buildBottomBox(), BorderLayout.SOUTH);
        logArea.setText(INTRO + "\n");
        setContentPane(scenePanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    
    private JPanel buildTopBar() {
        JButton newGameButton = GameStyle.button("New game");
        JButton resultsButton = GameStyle.button("Results");
        newGameButton.addActionListener(e -> onNewGame());
        resultsButton.addActionListener(e -> showResults());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        buttons.setOpaque(false);
        buttons.add(newGameButton);
        buttons.add(resultsButton);

        JPanel bar = GameStyle.shade(new BorderLayout());
        bar.add(statsPanel, BorderLayout.CENTER);
        bar.add(buttons, BorderLayout.EAST);
        return bar;
    }

    
    private JPanel buildBottomBox() {
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setOpaque(false);
        logArea.setForeground(Color.WHITE);
        logArea.setFont(GameStyle.FONT);

        JScrollPane scroll = new JScrollPane(logArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(900, 130));

        // One card per place
        cardPanel.setOpaque(false);
        cardPanel.add(new StartPanel(controller), GameState.START.name());
        cardPanel.add(new TownGatePanel(controller), GameState.TOWN_GATE.name());
        cardPanel.add(new CrossroadPanel(controller), GameState.CROSSROAD.name());
        cardPanel.add(new RiverPanel(controller), GameState.RIVER.name());
        cardPanel.add(new ForestPanel(controller), GameState.FOREST.name());
        cardPanel.add(new FightPanel(controller), GameState.FIGHT.name());
        cardPanel.add(new GameOverPanel(() -> onNewGame(), () -> showResults(), () -> closeGame()),
                GameState.GAME_OVER.name());

        JPanel box = GameStyle.shade(new BorderLayout(0, 8));
        box.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        box.add(scroll, BorderLayout.NORTH);
        box.add(cardPanel, BorderLayout.SOUTH);
        return box;
    }

    
    private void onNewGame() {
        GameState state = controller.getState();
        boolean gameRunning = state != GameState.START && state != GameState.GAME_OVER;
        if (gameRunning) {
            int answer = JOptionPane.showConfirmDialog(this,
                    "Abandon this game and start again?", "New game", JOptionPane.YES_NO_OPTION);
            if (answer != JOptionPane.YES_OPTION) {
                return;
            }
        }
        controller.restart();
        logArea.setText(INTRO + "\n");
    }

    
    private void showResults() {
        JDialog dialog = new JDialog(this, "Results", true);
        ResultsPanel panel = new ResultsPanel(controller, () -> dialog.dispose());
        panel.showAll();
        dialog.setContentPane(panel);
        dialog.setSize(640, 420);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void closeGame() {
        controller.shutdown();
        dispose();
        System.exit(0);
    }

    @Override
    public void stateChanged(GameState newState) {
        cardLayout.show(cardPanel, newState.name());
        scenePanel.setScene(newState);
        statsPanel.refresh(controller.getPlayer());
        if (newState == GameState.START) {
            logArea.setText(""); // fresh messages for a new game
        }
    }

    @Override
    public void messageLogged(String message) {
        logArea.append("\n" + message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    @Override
    public void playerChanged(Player player) {
        statsPanel.refresh(player);
    }

    @Override
    public void errorOccurred(String message) {
        JOptionPane.showMessageDialog(this, message, "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}
