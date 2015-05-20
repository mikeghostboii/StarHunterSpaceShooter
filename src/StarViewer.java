import javax.swing.JFrame;

public class StarViewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StarFrame frame = new StarFrame();
		StarPanel panel = new StarPanel();
		frame.add(panel);
		frame.setTitle("Ghost games presents: Star Hunter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

