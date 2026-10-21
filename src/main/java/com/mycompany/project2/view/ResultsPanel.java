/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project2.view;

/**
 *
 * @author poloj
 */

import com.mycompany.project2.dao.GameResultDAO;
import com.mycompany.project2.model.GameResult;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ResultsPanel extends JPanel{
    
    private static final String[] COLUMNS = {"Player", "Outcome", "Played at" };
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final int NAME_FIELD_WIDTH = 12;
    
    private final GameResultDAO dao;
    
    private final DefaultTableModel tableModel = new DefaultTableModel(COLUMNS, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    /**
     * @param dao
     * @param onBack
     */
    public ResultsPanel(GameResultDAO dao, Runnable onBack) {
        this.dao = dao;
        setLayout
    }
            
}
