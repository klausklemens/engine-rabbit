package ninja.klausklemens.adventure;

import javax.swing.JFrame;
import java.awt.*;

public class Client extends JFrame implements Runnable {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Client());
	}

	@Override
	public void run() {
		new ClientGui();
	}
}