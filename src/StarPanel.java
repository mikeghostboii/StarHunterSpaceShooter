import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

@SuppressWarnings("serial")
public class StarPanel extends JPanel implements Runnable {

	int score = 0;
	int x, y;
	BattleShip ship;
	EnemyShip enemy, enemy1;
	Random rand;
	Obstacles o, o1, o2, o3;
	static ArrayList<Obstacles> list = new ArrayList<Obstacles>();
	boolean isTouching = false;

	/*
	 * Game functionality and logic
	 */
	public StarPanel() {

		setBackground(Color.cyan.darker());
		ship = new BattleShip(400, 400);
		enemy = new EnemyShip(400, 50);
		enemy1 = new EnemyShip(300, 35);
		
		rand = new Random();
		addObs();
		
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

					// Key to shoot bullets
					else if (keys == KeyEvent.VK_SPACE) {
						System.out.println("Shoot");
					}
				}
			}
		}
		addKeyListener(new KeyListenerPress());
		setFocusable(true);

		class MyListener extends MouseAdapter {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int x = e.getX();
				int y = e.getY();
				System.out.println("MousePressed@[" + x + ", " + y + "]");
			}
		}
		addMouseListener(new MyListener());

		@SuppressWarnings("unused")
		class ActionPressListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		}
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			score++;
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
		for(Obstacles x : list){
			x.draw(g2);
		}
		enemy.draw(g2);
		enemy1.draw(g2);
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

	public void addObs() {
		if (!list.contains(o)) { // checks if arraylist contains engine
			o = new Obstacles(x, y);
			list.add(o);
			return;
		} else if (!list.contains(o1)) {
			o1 = new Obstacles(x, y);
			list.add(o1);
			return;
		} else if (!list.contains(o2)) {
			o2 = new Obstacles(x, y);
			list.add(o2);
		} else if (!list.contains(o3)) {
			o3 = new Obstacles(x, y);
			list.add(o3);
		}
		return;
	}
}