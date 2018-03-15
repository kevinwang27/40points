package GUI;

import Core.Card;
import Core.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private JPanel cardsPanel;
    private TablePanel tablePanel;
    private Player player;

    public GamePanel(Player player) {
        this.player = player;
        setLayout(new BorderLayout());

        cardsPanel = new JPanel();
        cardsPanel.setBackground(Color.gray);
        cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        tablePanel = new TablePanel();

        updateCards();
        add(cardsPanel, BorderLayout.PAGE_END);
        add(tablePanel, BorderLayout.CENTER);
    }

    public void updateCards() {
        this.cardsPanel.removeAll();
        for (Card card : player.hand) {
            CardButton cardButton = new CardButton(card);
            cardButton.addActionListener(this);
            cardsPanel.add(cardButton);
        }
        this.revalidate();
    }

    public void display()
    {
        JFrame mainFrame = new JFrame("40 points");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(this);
        mainFrame.setPreferredSize(new Dimension(900, 550));

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        CardButton clickedCard = (CardButton) actionEvent.getSource();
        clickedCard.removeActionListener(this);
        this.cardsPanel.remove(clickedCard);
        this.cardsPanel.repaint();
        this.tablePanel.playPanel.add(clickedCard);
        this.tablePanel.playPanel.repaint();
        this.revalidate();
    }
}
