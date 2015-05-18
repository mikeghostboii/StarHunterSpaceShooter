import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BattleShip extends Ship {

	private int width = 25;
	private int height = 25;

	Rectangle box, body, tip;

	public BattleShip() {
		super();
	}

	public BattleShip(int x, int y) {
		super(x, y);
	}

	public void draw(Graphics2D g2) {
		int xCor = x;
		int yCor = y;
		box = new Rectangle(xCor, yCor, width, height);
		g2.draw(box);
		g2.fill(box);
		g2.setColor(Color.YELLOW);
		body = new Rectangle(x, y, width - 15, height - 15);
		g2.fill(body);
	}

}
