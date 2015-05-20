import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class StarPanel extends JPanel implements Runnable {

	private int score = 0;
	private int x, y;
	private double dx = 2, dy = 0;
	private BattleShip ship;
	private EnemyShip enemy, enemy1;
	private Obstacles o;
	private boolean isTouching = false;
	private Image i;
	private Graphics graphics;
	ArrayList<EnemyShip> list = new ArrayList<EnemyShip>();

	/*
	 * Game functionality and logic
	 */
	public StarPanel() {

		setBackground(new Color(165, 42, 0));
		ship = new BattleShip(400, 400);
		addEnemyShip();
		o = new Obstacles(x, y);

		Thread thread = new Thread(this);
		thread.start();

		controls();

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
			boundaries();

			list.get(0).setX(list.get(0).getX() + dx);
			// enemy.setY(enemy.getY() + dy);

			list.get(1).setX(list.get(1).getX() - dx);
			// enemy1.setY(enemy1.getY() - dy);
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
		for (EnemyShip x : list) {
			x.draw(g2);
		}
	}

	// Make it later so user can enter number of enemies
	public void addEnemyShip() {
		list.add(new EnemyShip(50, 50));
		list.add(new EnemyShip(80, 80));
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

	public void stop() {

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
	}

	/*
	 * All the key controls
	 */
	public void controls() {
		class KeyListenerPress extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				System.out.println("ship.getX() = " + ship.getX());
				System.out.println("ship.getY() = " + ship.getY());

				check();

				if (isTouching == false) {
					System.out.println("key pressed");
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
					}
					// Key to shoot bullets
					else if (keys == KeyEvent.VK_SPACE) {
						System.out.println("Shoot");
					}
				}
			}
		}
		addKeyListener(new KeyListenerPress());
		setFocusable(true);
	}

	/*
	 * Prevents enemy ship from passing the boundary of the frame
	 */
	public void boundaries() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getX() > 770) {
				list.get(i).setX(770);
				dx = -dx;
			} else if (list.get(i).getX() < 1) {
				list.get(i).setX(1);
				dx = -dx;
			} else {
				x += dx;
			}

			if (list.get(i).getY() > 600) {
				list.get(i).setY(600);
				dy = -dy;
			} else if (list.get(i).getY() < 1) {
				list.get(i).setY(1);
				dy = -dy;
			} else {
				y += dy;
			}
		}
	}
}
