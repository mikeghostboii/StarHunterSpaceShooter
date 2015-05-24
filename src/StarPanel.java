import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class StarPanel extends JPanel implements Runnable {

	private int score = 0;
	@SuppressWarnings("unused")
	private int x, y;
	@SuppressWarnings("unused")
	private double dx = 2, dy = 0;
	private BattleShip ship;
	private EnemyShip enemy, enemy1, enemy2, enemy3, enemy4, enemy5;
	private Ammo bullet;
	private boolean isTouching = false;
	private boolean shoot = false;
	private Image i;
	private Graphics graphics;

	/*
	 * Game functionality and logic
	 */
	public StarPanel() {

		ship = new BattleShip(400, 400);
		bullet = new Ammo(411, 403);
		enemy = new EnemyShip(50, 100);
		enemy1 = new EnemyShip(50, 140);
		enemy2 = new EnemyShip(50, 180);

		enemy3 = new EnemyShip(100, 100);
		enemy4 = new EnemyShip(100, 140);
		enemy5 = new EnemyShip(100, 180);

		Thread thread = new Thread(this);
		thread.start();

		controls();

		if (shoot == true) {
			System.out.println("TRUEE");
		}
		class MyListener extends MouseAdapter {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("MousePressed@[" + x + ", " + y + "]");
			}
		}
		addMouseListener(new MyListener());
	}

	public void run() {
		while (true) {

			moveEnemy(enemy);
			moveEnemy(enemy1);
			moveEnemy(enemy2);
			moveEnemy(enemy3);
			moveEnemy(enemy4);
			moveEnemy(enemy5);

			enemy.setX(enemy.getX() + dx);
			enemy1.setX(enemy1.getX() + dx);
			enemy2.setX(enemy2.getX() + dx);
			enemy3.setX(enemy3.getX() + dx);
			enemy4.setX(enemy4.getX() + dx);
			enemy5.setX(enemy5.getX() + dx);

			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Paints all the objects onto the frame
	 * 
	 * @param Graphics g
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		ship.draw(g2);
		repaint();
		scoreFont(g2);
		drawEnemies(g2);
		bullet.draw(g2);
	}

	public void drawEnemies(Graphics2D g2) {
		enemy.draw(g2);
		enemy1.draw(g2);
		enemy2.draw(g2);
		enemy3.draw(g2);
		enemy4.draw(g2);
		enemy5.draw(g2);
	}

	public void moveEnemy(EnemyShip e) {
		if (e.getX() + dx > 770) {
			e.setX(770);
			dx = -dx;
		} else if (e.getX() < 1) {
			e.setX(1);
			dx = -dx;
		} else {
			x += dx;
		}
	}

	/*
	 * Checks if the ship is going to go past the borders of the frame
	 */
	public void check() {
		if ((ship.getX() == 0 || ship.getX() == 770)) {
			isTouching = true;
		} else if (ship.getY() == 0 || ship.getY() == 600) {
			isTouching = true;
		}
	}

	/*
	 * Method that prints a Scoring system with a special font
	 * 
	 * @param Graphics g
	 */
	public void scoreFont(Graphics g) {
		String s = Integer.toString(score);
		Font font = new Font("Serif", Font.BOLD, 32);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(s, getWidth() - 150 + 2, 50 + 2);

		g.setColor(Color.BLUE.darker());
		g.drawString(s, getWidth() - 150, 50);
	}

	public void update(Graphics g) {
		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			graphics = i.getGraphics();
		}

		graphics.setColor(getBackground());
		graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);

		graphics.setColor(getForeground());
		paint(graphics);

		g.drawImage(i, 0, 0, this);
		controls();
	}

	/*
	 * All the key controls
	 */
	public void controls() {
		class KeyListenerPress extends KeyAdapter {
			public void keyPressed(KeyEvent e) {

				check();

				if (isTouching == false) {
					int keys = e.getKeyCode();

					// Key to move object to the left (x coordinate)
					if (keys == KeyEvent.VK_A || keys == KeyEvent.VK_LEFT) {
						check();
						ship.setX(ship.getX() - 10);
						repaint();
						System.out.println("A is being pressed");
					}

					// Key to move object to the right
					else if (keys == KeyEvent.VK_D || keys == KeyEvent.VK_RIGHT) {
						check();
						ship.setX(ship.getX() + 10);
						repaint();
						System.out.println("D is being pressed");
					}

					// Key to move object upwards
					else if (keys == KeyEvent.VK_W || keys == KeyEvent.VK_UP) {
						check();
						ship.setY(ship.getY() - 10);
						repaint();
						System.out.println("W is being pressed");
					}

					// Key to move object to the downwards
					else if (keys == KeyEvent.VK_S || keys == KeyEvent.VK_DOWN) {
						check();
						ship.setY(ship.getY() + 10);
						repaint();
						System.out.println("Space is being pressed");
					}

					else if (keys == KeyEvent.VK_Q) {
						check();
						ship.setPosition(ship.getX() - 10, ship.getY() - 10);
						repaint();
						System.out.println("Q is being pressed");
					}

					else if (keys == KeyEvent.VK_E) {
						check();
						ship.setPosition(ship.getX() + 10, ship.getY() - 10);
						repaint();
						System.out.println("E is being pressed");
					} else if (keys == KeyEvent.VK_SPACE) {
						System.out.println("Shoot");
						shoot = true;
					}

				}
			}
		}
		addKeyListener(new KeyListenerPress());
		setFocusable(true);
	}
}
