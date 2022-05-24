package folha2.ex2;

import javax.swing.*;
import java.awt.*;

public class DrawCircles extends JFrame {

    Circulo circulo1;
    Circulo circulo2;

    public DrawCircles(Circulo circulo1, Circulo circulo2){

        this.circulo1 = circulo1;
        this.circulo2 = circulo2;

        setTitle("Circulos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);
    }

    @Override
    public void paint(Graphics g) {

        // necessário para o setStroke
        Graphics2D g2d = (Graphics2D) g;

        Color cor1;
        switch (circulo1.getCor()) {
            case "azul":
                cor1 = Color.blue;
                break;
            case "verde":
                cor1 = Color.green;
                break;
            case "vermelho":
                cor1 = Color.red;
                break;
            case "preto":
                cor1 = Color.black;
                break;
            default:
                cor1 = Color.black;
                break;
        }

        Color cor2;
        switch (circulo2.getCor()) {
            case "azul":
                cor2 = Color.blue;
                break;
            case "verde":
                cor2 = Color.green;
                break;
            case "vermelho":
                cor2 = Color.red;
                break;
            case "preto":
                cor2 = Color.black;
                break;
            default:
                cor2 = Color.black;
                break;
        }

        g2d.setColor(cor1);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawOval((int)(getWidth()/2-(circulo1.getRaio()/2)), (int)(getHeight()/2-(circulo1.getRaio()/2)), (int)(circulo1.getRaio()), (int)(circulo1.getRaio()));

        g2d.setColor(cor2);
        g2d.drawOval((int)(getWidth()/2-(circulo2.getRaio()/2)), (int)(getHeight()/2-(circulo2.getRaio()/2)), (int)(circulo2.getRaio()), (int)(circulo2.getRaio()));

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("default", Font.BOLD, 20));
        g2d.drawString("Primeiro círculo:", 100, 100);
        g2d.drawString(" - Raio: " + circulo1.getRaio(), 100, 130);
        g2d.drawString(" - Área: " + circulo1.areaCirculo(), 100, 160);
        g2d.drawString("Segundo círculo:", 100, 200);
        g2d.drawString(" - Raio: " + circulo2.getRaio(), 100, 230);
        g2d.drawString(" - Área: " + circulo2.areaCirculo(), 100, 260);
    }
}