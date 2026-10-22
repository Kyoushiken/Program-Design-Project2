/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

import com.mycompany.project2.controller.GameController;
import com.mycompany.project2.model.GameResult;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author poloj
 * @author nicka
 */
public class ResultsPanel extends JPanel {

    private static final String[] COLUMNS = {"Player", "Outcome", "Played at"};
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final GameController controller;
    private final DefaultTableModel tableModel = new DefaultTableModel(COLUMNS, 0);
    private final JTextField searchField = new JTextField(12);
    private final JLabel summaryLabel = new JLabel(" ");

    public ResultsPanel(GameController controller, Runnable onBack) {
        this.controller = controller;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTable table = new JTable(tableModel);

        // Results can only be viewed
        table.setEnabled(false);

        add(buildSearchBar(), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buildBottomBar(onBack), BorderLayout.SOUTH);
    }

    // Loads all saved game results
    public void showAll() {
        searchField.setText("");

        try {
            fillTable(controller.getAllResults());
            summaryLabel.setText(describeCounts(controller.getOutcomeCounts()));
        } catch (RuntimeException e) {
            showError(e);
        }
    }

    // Creates the search controls
    private JPanel buildSearchBar() {
        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show all");

        searchButton.addActionListener(e -> search());
        showAllButton.addActionListener(e -> showAll());

        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bar.add(new JLabel("Player:"));
        bar.add(searchField);
        bar.add(searchButton);
        bar.add(showAllButton);

        return bar;
    }

    // Creates the bottom action bar
    private JPanel buildBottomBar(Runnable onBack) {
        JButton clearButton = new JButton("Clear history");
        JButton backButton = new JButton("Back");

        clearButton.addActionListener(e -> clearHistory());
        backButton.addActionListener(e -> onBack.run());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(clearButton);
        buttons.add(backButton);

        JPanel bar = new JPanel(new BorderLayout());
        bar.add(summaryLabel, BorderLayout.CENTER);
        bar.add(buttons, BorderLayout.EAST);

        return bar;
    }

    // Searches for results by player name
    private void search() {
        String name = searchField.getText().trim();

        if (name.isEmpty()) {
            showAll();
            return;
        }

        try {
            List<GameResult> found = controller.getResultsFor(name);

            fillTable(found);
            summaryLabel.setText(found.size() + " game(s) found for " + name);

        } catch (RuntimeException e) {
            showError(e);
        }
    }

    // Deletes all saved game results
    private void clearHistory() {
        int answer = JOptionPane.showConfirmDialog(
                this,
                "Delete all saved results?",
                "Clear history",
                JOptionPane.YES_NO_OPTION);

        if (answer != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            controller.clearResults();
            showAll();
        } catch (RuntimeException e) {
            showError(e);
        }
    }

    // Displays results in the table
    private void fillTable(List<GameResult> results) {
        tableModel.setRowCount(0);

        for (GameResult result : results) {
            tableModel.addRow(new Object[]{
                result.getPlayerName(),
                result.getOutcome(),
                result.getPlayedAt().format(DATE_FORMAT)
            });
        }
    }

    // Creates a summary of game outcomes
    private String describeCounts(Map<String, Integer> counts) {

        if (counts.isEmpty()) {
            return "No games played yet.";
        }

        StringBuilder text = new StringBuilder();

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            text.append(entry.getKey())
                .append(": ")
                .append(entry.getValue())
                .append("   ");
        }

        return text.toString().trim();
    }

    // Shows database error messages
    private void showError(RuntimeException e) {
        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Database error",
                JOptionPane.ERROR_MESSAGE);
    }
}