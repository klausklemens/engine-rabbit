package ninja.klausklemens.adventure;

import javax.swing.*;
import java.awt.*;

public class Sprite implements Drawable {
	private final float SPEED = 0.2f;

	private Image texture;
	private float x = 100;
	private float y = 500;
	private int width;
	private int height;
	private boolean backwards = false;

	private float targetX = x;
	private float targetY = y;
	private Vector direction;

	public Sprite() {
		initSprite();
	}

	private void initSprite() {
		texture = new ImageIcon("module/assets/livingroom/character.png").getImage();
		width = texture.getWidth(null);
		height = texture.getHeight(null);
	}

	public void moveTo(int x, int y) {
		x -= width / 2;
		y -= height;

		targetX = x;
		targetY = y;

		Vector delta = new Vector(x - this.x, y - this.y);

		float stepDistanceFactor = delta.length();

		direction = new Vector((delta.getX() / stepDistanceFactor) * SPEED, (delta.getY() / stepDistanceFactor) * SPEED);

		if (direction.getX() > 0) {
			backwards = false;
		}
		if (direction.getX() < 0) {
			backwards = true;
		}
	}

	public void move() {
		if (new Vector(x - targetX, y - targetY).length() < SPEED) {
			x = targetX;
			y = targetY;
			direction = null;
		} else if (direction != null) {
			x += direction.getX();
			y += direction.getY();
		}
	}

	public void draw(Graphics g) {
		int drawWidth;
		int drawX;
		if (backwards) {
			drawWidth = -width;
			drawX = width / 2 + (int) x;
		} else {
			drawWidth = width;
			drawX = (int) x;
		}

		g.drawImage(texture, (int) drawX, (int) y, drawWidth, height, null);
		g.drawLine((int) x, (int) y, (int) targetX, (int) targetY);
		if (direction == null) { return; }
		g.drawLine((int) x + 1, (int) y + 1, (int) (x + direction.getX()) + 1, (int) (y + direction.getY()) + 1);
	}

	private static class Vector {
		private float x;
		private float y;

		public Vector(float x, float y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Vector{" +
					"x=" + x +
					", y=" + y +
					'}';
		}

		public float length() {
			return (float) Math.sqrt((x * x) + (y * y));
		}

		public float getX() {
			return x;
		}

		public void setX(float x) {
			this.x = x;
		}

		public float getY() {
			return y;
		}

		public void setY(float y) {
			this.y = y;
		}
	}
}
