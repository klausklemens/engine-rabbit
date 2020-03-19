package ninja.klausklemens.adventure;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame {
	public Board background;

	public ClientGui() {
		background = new Board();
		add(background);

		Dimension size = background.getPreferredSize();
		size.height = size.height + 37;
		setSize(size);

		setTitle("Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setVisible(true);
	}
}
