package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import TroysCode.Tools;

public class Ball
	{
		private final Rectangle collisionRect;
		private double x, y = 0;

		private int ballXVelocity = 300;
		private int ballYVelocity = 300;

		public Ball(int startX, int startY)
			{
				collisionRect = new Rectangle(startX, startY, 14, 14);
				x = startX;
				y = startY;
			}

		public final void tick(double secondsPassed)
			{
				x += ballXVelocity * secondsPassed;
				y += ballYVelocity * secondsPassed;
				collisionRect.x = (int) x;
				collisionRect.y = (int) y;

				if (y < 66 && ballYVelocity < 0)
					reverseYVelocity();
				else if (y > 570 && ballYVelocity > 0)
					reverseYVelocity();
			}

		public final void render(Graphics g)
			{
				g.setColor(Color.WHITE);
				g.fillRect(collisionRect.x, collisionRect.y, collisionRect.width, collisionRect.height);
			}

		public final boolean intersects(Paddle pad)
			{
				if (collisionRect.intersects(pad.getCollisionRect()))
					return true;

				return false;
			}

		public final void reset()
			{
				x = 393;
				y = 290;
				collisionRect.x = 393;
				collisionRect.y = 290;

				reverseXVelocity();

				if (Tools.randBool())
					reverseYVelocity();
			}

		public final int getX()
			{
				return collisionRect.x;
			}

		public final int getY()
			{
				return collisionRect.y;
			}

		public final void setX(int newX)
			{
				collisionRect.x = newX;
			}

		public final void setY(int newY)
			{
				collisionRect.y = newY;
			}

		public final void setXVelocity(int newVelocity)
			{
				ballXVelocity = newVelocity;
			}

		public final void setYVelocity(int newVelocity)
			{
				ballYVelocity = newVelocity;
			}

		public final void reverseXVelocity()
			{
				ballXVelocity *= -1;
			}

		public final void reverseYVelocity()
			{
				ballYVelocity *= -1;
			}
	}
