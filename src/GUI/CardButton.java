package GUI;

import Core.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CardButton extends JButton {
    private Card card;
    private BufferedImage image;
    private String label;

    public CardButton(Card card) {
        this.card = card;
        processImage();
        processLabel();
    }

    private void processLabel() {
        switch (this.card.value) {
            case 14:
                this.label = "A";
                break;
            case 13:
                this.label = "K";
                break;
            case 12:
                this.label = "Q";
                break;
            case 11:
                this.label = "J";
                break;
            default:
                this.label = "" + this.card.value;
        }
    }

    private void processImage() {
        switch (this.card.suit) {
            case SPADES:
                this.image = SuitImages.getSpade();
                break;
            case HEARTS:
                this.image = SuitImages.getHeart();
                break;
            case CLUBS:
                this.image = SuitImages.getClub();
                break;
            case DIAMONDS:
                this.image = SuitImages.getDiamond();
                break;
            default:
                this.image = null;
        }
    }

    @Override
    protected void paintComponent(Graphics gOld) {
        Graphics2D g = (Graphics2D) gOld;
        paintCardBody(g);
    }

    @Override
    protected void paintBorder(Graphics gOld) {
        Graphics2D g = (Graphics2D) gOld;

        Point p = (this.getMousePosition());
        if (p != null) {
            if (this.contains(p)) {
                g.setColor(Color.white);
                g.drawRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
            }
        }
    }

    private void paintCardBody(Graphics2D g) {
        GradientPaint bodyGradient = new GradientPaint(200 - this.getWidth() / 2, 0, Color.white,
                200 - this.getWidth() / 2, this.getHeight() + 250, Color.yellow);
        g.setPaint(bodyGradient);
        g.fillRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 10, 10);

        g.setColor(Color.gray);
        g.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 10, 10);

        double factor = 75 / 100.0;
        int imgDim;
        if (this.getWidth() < this.getHeight()) {
            imgDim = (int) (this.getWidth() * factor);
        } else {
            imgDim = (int) (this.getHeight() * factor);
        }

        int xPos = 1 + (this.getWidth() - imgDim) / 2;
        int yPos = 1 + (this.getHeight() - imgDim) / 2;
        g.drawImage(this.image, xPos, yPos, imgDim, imgDim, this);

        int sxPos1 = 5;
        int syPos1 = 17;
        int sxPos2 = this.getWidth() - (17 + ((this.label.length() - 1) * 5));
        int syPos2 = this.getHeight() - 5;
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.black);
        g.drawString(this.label, sxPos1, syPos1);
        g.drawString(this.label, sxPos2, syPos2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(42, 60);
    }
}
