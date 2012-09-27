package Pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;

import TroysCode.Constants;
import TroysCode.RenderableObject;
import TroysCode.hub;
import TroysCode.T.TButton;
import TroysCode.T.TMenu;
import TroysCode.T.TScrollEvent;

public class PongGame extends RenderableObject implements Constants
	{
		private static final long serialVersionUID = 1L;

		private boolean paused = true;

		private final Paddle leftPaddle = new Paddle(72, 518, 28, 270);
		private final Paddle rightPaddle = new Paddle(72, 518, 758, 270);

		private final Ball ball = new Ball(393, 290);

		private float leftScore = 1.0009f;
		private float rightScore = 1.0009f;

		private float resetTime = 0;

		private final TMenu buttonMenu = new TMenu(150, 0, 650, 50, TMenu.HORIZONTAL);
		private final TButton leftAISwitch = new TButton(0, 0, "Toggle AI ~ AI_MEDIUM");
		private final TButton rightAISwitch = new TButton(0, 0, "Toggle AI ~ AI_MEDIUM");
		private final TButton scoreResetSwitch = new TButton(0, 0, "RESET SCORES");

		@Override
		protected void initiate()
			{
				addTComponent(buttonMenu);

				buttonMenu.setButtonSpacing(50);

				buttonMenu.addTButton(leftAISwitch, true);
				buttonMenu.addTButton(scoreResetSwitch, true);
				buttonMenu.addTButton(rightAISwitch, true);
			}

		@Override
		protected void refresh()
			{
				resetTime = 90;
			}

		@Override
		protected void tick(double secondsPassed)
			{

				if (!paused)
					{
						leftPaddle.tick(secondsPassed, ball.getY(), ball.getX());
						rightPaddle.tick(secondsPassed, ball.getY(), ball.getX());

						if (resetTime < 0)
							ball.tick(secondsPassed);
						else
							resetTime -= 0.6f;

						if (leftPaddle.getAI().getDifficulty() == AI_OFF)
							{
								if (hub.input.getKeyState(KEY_W) == true)
									leftPaddle.moveUp(secondsPassed);
								if (hub.input.getKeyState(KEY_S) == true)
									leftPaddle.moveDown(secondsPassed);
							}
						if (rightPaddle.getAI().getDifficulty() == AI_OFF)
							{

								if (hub.input.getKeyState(KEY_UP) == true)
									rightPaddle.moveUp(secondsPassed);
								if (hub.input.getKeyState(KEY_DOWN) == true)
									rightPaddle.moveDown(secondsPassed);
							}

						if (ball.intersects(leftPaddle))
							{
								ball.reverseXVelocity();
								if (ball.getX() < 38)
									{
										ball.reverseYVelocity();
										ball.reverseXVelocity();
										// ball.getX() = 43;
									}
							}
						else if (ball.intersects(rightPaddle))
							{
								ball.reverseXVelocity();
								if (ball.getX() > 749)
									{
										ball.reverseYVelocity();
										ball.reverseXVelocity();
										// ball.getX() = 744;
									}
							}

						if (ball.getX() < -70)
							{
								rightScore += 0.001f;
								ball.reset();
								resetTime = 80;
							}
						else if (ball.getX() > 880)
							{
								leftScore += 0.001f;
								ball.reset();
								resetTime = 80;
							}
					}
			}

		@Override
		protected void renderObject(Graphics g)
			{
				g.setFont(new Font(g.getFont().toString(), g.getFont().getStyle(), 35));

				g.setColor(new Color(0, 0, 0, 70));
				g.fillRect(43, 0, 800, 600);
				g.setColor(new Color(0, 0, 0, 90));
				g.fillRect(0, 0, 43, 600);
				g.fillRect(758, 0, 42, 600);

				g.setColor(Color.GRAY);
				g.fillRect(0, 50, 800, 15);
				g.fillRect(0, 585, 800, 15);

				leftPaddle.render(g);
				rightPaddle.render(g);
				ball.render(g);

				g.drawString("" + leftScore, -25, 40);
				g.drawString("" + rightScore, 713, 40);

				g.setColor(Color.BLACK);
				g.fillRect(61, 0, 680, 50);

				g.setFont(new Font(g.getFont().toString(), g.getFont().getStyle(), 600));
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect((int) (ball.getX() - (resetTime / 2) + 7), (int) (ball.getY() - 115), (int) resetTime, 20);

				if (paused)
					{
						g.setColor(new Color(0, 0, 0, 150));
						g.fillRect(0, 50, 800, 550);
					}
			}

		@Override
		protected void mousePressed(MouseEvent event)
			{
				if (event.getY() > 50)
					paused = false;
			}

		@Override
		protected void keyPressed(KeyEvent event)
			{
				switch (event.getKeyCode())
					{
					case (KEY_P):
						paused = !paused;
						break;
					}
			}

		@Override
		protected void actionPerformed(ActionEvent event)
			{
				if (event.getSource() == leftAISwitch)
					{
						switch (leftPaddle.getAI().getDifficulty())
							{
							case (AI_OFF):
								leftPaddle.getAI().setDifficulty(AI_EASY);
								leftAISwitch.setLabel("Toggle AI ~ AI_EASY");
								break;
							case (AI_EASY):
								leftPaddle.getAI().setDifficulty(AI_MEDIUM);
								leftAISwitch.setLabel("Toggle AI ~ AI_MEDIUM");
								break;
							case (AI_MEDIUM):
								leftPaddle.getAI().setDifficulty(AI_HARD);
								leftAISwitch.setLabel("Toggle AI ~ AI_HARD");
								break;
							case (AI_HARD):
								leftPaddle.getAI().setDifficulty(AI_OFF);
								leftAISwitch.setLabel("Toggle AI ~ AI_OFF");
								break;
							}
					}
				else if (event.getSource() == rightAISwitch)
					{
						switch (rightPaddle.getAI().getDifficulty())
							{
							case (AI_OFF):
								rightPaddle.getAI().setDifficulty(AI_EASY);
								rightAISwitch.setLabel("Toggle AI ~ AI_EASY");
								break;
							case (AI_EASY):
								rightPaddle.getAI().setDifficulty(AI_MEDIUM);
								rightAISwitch.setLabel("Toggle AI ~ AI_MEDIUM");
								break;
							case (AI_MEDIUM):
								rightPaddle.getAI().setDifficulty(AI_HARD);
								rightAISwitch.setLabel("Toggle AI ~ AI_HARD");
								break;
							case (AI_HARD):
								rightPaddle.getAI().setDifficulty(AI_OFF);
								rightAISwitch.setLabel("Toggle AI ~ AI_OFF");
								break;
							}
					}
				else if (event.getSource() == scoreResetSwitch)
					{
						leftScore = 1.0009f;
						rightScore = 1.0009f;
					}
			}

		@Override
		protected void mouseReleased(MouseEvent event)
			{
			}

		@Override
		protected void mouseDragged(MouseEvent event)
			{
			}

		@Override
		protected void mouseMoved(MouseEvent event)
			{
			}

		@Override
		protected void mouseWheelScrolled(MouseWheelEvent event)
			{
			}

		@Override
		protected void keyReleased(KeyEvent event)
			{
			}

		@Override
		protected void keyTyped(KeyEvent event)
			{
			}

		@Override
		protected void mouseClicked(MouseEvent event)
			{
			}

		@Override
		protected void mouseEntered(MouseEvent event)
			{
			}

		@Override
		protected void mouseExited(MouseEvent event)
			{
			}

		@Override
		protected void programGainedFocus(WindowEvent event)
			{
			}

		@Override
		protected void programLostFocus(WindowEvent event)
			{
				paused = true;
			}

		@Override
		protected void frameResized(ComponentEvent event)
			{
			}

		@Override
		public void tScrollBarScrolled(TScrollEvent event)
			{
			}
	}
