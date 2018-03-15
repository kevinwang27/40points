package GUI;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    public JPanel playPanel;

    public TablePanel() {
        setBackground(Color.green);
        setLayout(new BorderLayout());

        playPanel = new JPanel();
        playPanel.setBackground(Color.green);
        playPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        add(playPanel, BorderLayout.PAGE_END);
    }
}
