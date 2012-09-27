package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle
	{
		private final int speed = 300;

		private Rectangle collisionRect;
		double y = 0;

		private final AI aI = new AI(this);

		public Paddle(int minRange, int maxRange, int xPos, int startY)
			{
				collisionRect = new Rectangle(xPos, startY, 15, 60);
				y = startY;
			}

		public final void tick(double secondsPassed, int ballY, int ballX)
			{
				aI.tick(secondsPassed, ballY, ballX);
			}

		public final void render(Graphics g)
			{
				g.setColor(Color.WHITE);
				g.fillRect(collisionRect.x, collisionRect.y, collisionRect.width, collisionRect.height);
			}

		public final void moveUp(double secondsPassed)
			{
				y -= speed * secondsPassed;
				if (y < 68)
					y = 68;
				collisionRect.y = (int) y;
			}

		public final void moveDown(double secondsPassed)
			{
				y += speed * secondsPassed;
				if (y > 520)
					y = 520;
				collisionRect.y = (int) y;
			}

		public final AI getAI()
			{
				return aI;
			}

		public final Rectangle getCollisionRect()
			{
				return collisionRect;
			}

		public final int getX()
			{
				return collisionRect.x;
			}

		public final int getY()
			{
				return collisionRect.y;
			}
	}
