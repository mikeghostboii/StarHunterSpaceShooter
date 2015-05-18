import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Obstacles extends Ship {

	int x, y;
	private int width = 5;
	private int height = 5;
	
	Ellipse2D.Double circle;
	
	public Obstacles(){
		super();
	}
	public Obstacles(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2){
		int xCor = x;
		int yCor = y;			
    	circle = new Ellipse2D.Double(xCor, yCor, width, height);
		g2.setColor(Color.DARK_GRAY);
		g2.fill(circle);
		g2.draw(circle);
	}
	
}
 