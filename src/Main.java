public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		// Threads
		Thread thread = new Thread(game);
		thread.start();
	}

}
