import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Obstacles extends Ship {

	private int width = 5;
	private int height = 5;
	
	Ellipse2D.Double circle;
	
	public Obstacles(){
		super();
	}
	public Obstacles(int x, int y){
		super(x, y);
	}
	
	public void draw(Graphics2D g2){
    		circle = new Ellipse2D.Double(x, y, width, height);
		g2.setColor(Color.DARK_GRAY);
		g2.fill(circle);
		g2.draw(circle);
	}
	
}
 
 
