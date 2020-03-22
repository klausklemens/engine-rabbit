package ninja.klausklemens.adventure;

import java.awt.*;
import java.util.Scanner;

public class Client implements Runnable {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Client());

		Scanner s = new Scanner(System.in);
		/*while (true) {
			if (s.hasNextLine()) {
				if (s.hasNextFloat()) {
					Sprite.SPEED = s.nextFloat();
				} else {
					System.out.println("\"" + s.nextLine() + "\" is no float");
				}
			}
		}*/
	}

	@Override
	public void run() {
		new ClientGui();
	}
}