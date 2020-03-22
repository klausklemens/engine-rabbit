package ninja.klausklemens.adventure;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame {
	public Board board;
	public Sprite dude;

	public ClientGui() {
		board = new Board();
		add(board);

		Dimension size = board.getPreferredSize();
		size.height = size.height + 37;
		setSize(size);

		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Test");

		setVisible(true);
	}
}
