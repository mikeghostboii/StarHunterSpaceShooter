import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author michaelwasihun
 */
@SuppressWarnings("serial")
public class Game extends JFrame implements Runnable {
 
	int x, xDirection, xCor, mousex, rektx, bulletx, x1, x1Direction, x2, x3;
	int y, yDirection, yCor, mousey, rekty, bullety, y1, y2, y3;
	int score;
	private Image dbImage;
	private Graphics dbg;
	Image ship, enemy, obstacle, enemy1;
	boolean mouseOnScreen = false;
	boolean mouseDragged = false;
	boolean collision = false;
	boolean readyToFire, shot = false;
	Random random = new Random();
	
	Rectangle bullet, bullet1, portal;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				move();
				shoot();
				Thread.sleep(7);
				
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	public void move() {
		x += xDirection;
		y += yDirection;

		if (x <= 0) {
			x = 0;
		}
		if (x >= getWidth() - 90) {
			x = getWidth() - 90;
		}
		if (y <= 25) {
			y = 25;
		}
		if (y >= getHeight() - 85) {
			y = getHeight() - 85;
		}
	}

	public void setXDir(int xdir) {
		xDirection = xdir;
	}

	public void setYDir(int ydir) {
		yDirection = ydir;
	}

	public class Mouse extends MouseAdapter implements MouseMotionListener {
		@Override
		public void mousePressed(MouseEvent e) {
			xCor = e.getX();
			yCor = e.getY();
			System.out.println("MousePressed@[" + xCor + ", " + yCor + "]");
			e.consume();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			mouseOnScreen = true;
			e.consume();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			mouseOnScreen = false;
			e.consume();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mousex = e.getX();
			mousey = e.getY();
			mouseDragged = true;
			e.consume();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mousex = e.getX();
			mousey = e.getY();
			mouseDragged = false;
			e.consume();
		}
	}

	public void shoot() {
		if (shot == true) {
			bullet.y--;
			bullet1.y--;
		}
	}

	public class KeyBoard extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_LEFT) {
				setXDir(-1);
			}
			if (keyCode == KeyEvent.VK_RIGHT) {
				setXDir(1);

			}
			if (keyCode == KeyEvent.VK_UP) {
				setYDir(-1);
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				setYDir(1);
			}
			if (keyCode == KeyEvent.VK_SPACE) {
				if (bullet == null) {
					readyToFire = true;
				}
				if (readyToFire = true) {
					bullety = y - 7;
					bulletx = x + 18;
					bullet = new Rectangle(bulletx, bullety, 3, 10);
					bullet1 = new Rectangle(bulletx + 52, bullety, 3, 10);
					shot = true;
				}
			}
		}

		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_LEFT) {
				setXDir(0);
			}
			if (keyCode == KeyEvent.VK_RIGHT) {
				setXDir(0);
			}
			if (keyCode == KeyEvent.VK_UP) {
				setYDir(0);
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				setYDir(0);
			}
			if (keyCode == KeyEvent.VK_SPACE) {
				readyToFire = false;
				if (bullet.y <= 200) {
					bullet = new Rectangle(0, 0, 0, 0);
					shot = false;
					readyToFire = true;
				}
			}
		}
	}

	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}

	public void paintComponent(Graphics g) {
		// draw ship
		x1 = 524;
		y1 = 74;
		x2 = 994;
		y2 = 291;
		x3 = 450;
		y3 = 74;
		g.drawImage(ship, x, y, this);
		g.drawImage(enemy, x1, y1, this);
		g.drawImage(obstacle, x2, y2, this);
		g.drawImage(enemy1, x3, y3, this);
		
		g.setColor(Color.BLACK);

		portal = new Rectangle(993, 459, 15, 15);
		g.fillRect(portal.x, portal.y, portal.width, portal.height);

		Rectangle r1 = new Rectangle(175, 175, 10, 10);
		g.setColor(Color.BLUE);
		g.fillRect(r1.x, r1.y, r1.width, r1.height);
		Rectangle r2 = new Rectangle(x, y, ship.getWidth(this),
				ship.getHeight(this));
		g.setColor(Color.GREEN);
		g.drawRect(r2.x, r2.y, r2.width, r2.height-10);
		g.setColor(Color.BLUE);
		g.drawRect(x2+2, y2+4, obstacle.getWidth(this)-4,  obstacle.getHeight(this)-14);
		g.setColor(Color.RED);
		g.drawRect(x1+5, y1, enemy.getWidth(this)-13,  enemy.getHeight(this)-2);
		g.drawRect(x3, y3+2, enemy.getWidth(this)-8,  enemy.getHeight(this)-1);

		// Collision
		if (r1.intersects(r2)) {
			g.drawString("Magic Portal", 984, 200);
			collision = true;
			x = random.nextInt(800);
			y = random.nextInt(250);
		}
		
		if (r2.intersects(portal)) {
			g.drawString("Magic Portal", 984, 200);
			x = random.nextInt(800);
			y = random.nextInt(250);
		}

		// mouse position
		g.setColor(Color.BLUE.darker());
		g.drawString("ShipPosition@[" + x + ", " + y + "]", 984, 170);
	

		if (mouseOnScreen == true) {
			g.setColor(Color.black);
			g.drawString("MousePressed@[" + xCor + ", " + yCor + "]", 984, 62);
		}
		if (mouseDragged == false && mouseOnScreen == true) {
			g.drawString("MouseDragged@[" + xCor + ", " + yCor + "]", 984, 100);

		} else if (mouseDragged == true & mouseOnScreen == true) {
			g.setColor(Color.GREEN);
			g.drawString("MouseDragged@[" + mousex + ", " + mousey + "]", 984,
					100);
		}
		if (mouseOnScreen == true) {
			g.setColor(Color.RED);
			g.drawString("MousePosition@[" + mousex + ", " + mousey + "]", 984,
					138);
		}

		if (shot == true) {
			g.setColor(Color.BLACK);
			g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
			g.fillRect(bullet1.x, bullet1.y, bullet1.width, bullet1.height);

		}
		repaint();
	}

	public Game() {
		// Load images
		ImageIcon i = new ImageIcon(
				"/Users/michaelwasihun/Documents/Eclipse/projects/Game/src/ship.png");
		ImageIcon i1 = new ImageIcon(
				"/Users/michaelwasihun/Documents/Eclipse/projects/Game/src/a2.png");
		ImageIcon i2 = new ImageIcon(
				"/Users/michaelwasihun/Documents/Eclipse/projects/Game/src/a1.png");
		ImageIcon i3 = new ImageIcon(
				"/Users/michaelwasihun/Documents/Eclipse/projects/Game/src/enemy1.png");

		
		ship = i.getImage();
		enemy = i1.getImage();
		obstacle = i2.getImage();
		enemy1 = i3.getImage();
		
		x = 220;
		y = 300;

		addKeyListener(new KeyBoard());
		addMouseListener(new Mouse());
		addMouseMotionListener(new Mouse());
		setSize(1200, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
