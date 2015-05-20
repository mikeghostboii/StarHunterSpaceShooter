import javax.swing.JFrame;

@SuppressWarnings("serial")
public class StarFrame extends JFrame {

	private StarPanel panel;

	public StarFrame() {
		setSize(800, 650);
		panel = new StarPanel();
		this.add(panel);
	}
}

