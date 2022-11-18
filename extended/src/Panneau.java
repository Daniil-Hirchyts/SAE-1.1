
import javax.swing.JPanel;
import java.awt.Graphics;

/* Auteur principal : Loic Wisniewski ; date de creation : decembre 2014 ; mise a jour : 1 decembre 2014  */

public class Panneau extends JPanel{
	
    public void paintComponent(Graphics g) {
	for(int i = 0; i < Point.numberInstances(); i++){
	    Point p = Point.getInstance(i);
	    g.setColor(p.getColor());
	    g.fillOval(p.getPosx(), p.getPosy(), 20, 20);
	}
    }
}
