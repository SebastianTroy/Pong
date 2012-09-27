package Pong;

import TroysCode.Constants;
import TroysCode.Tools;

public class AI implements Constants
	{
		private int difficulty = AI_MEDIUM;

		private boolean moveUp = false;
		private boolean moveDown = false;

		private int aboveLead = 2;
		private int belowLead = 50;

		private double timedHold = 0;
		private int buttonHeld = KEY_NONE;

		Paddle pad;

		public AI(Paddle owner)
			{
				pad = owner;
			}

		public final void tick(double secondsPassed, int ballY, int ballX)
			{
				moveUp = false;
				moveDown = false;

				if (difficulty != AI_OFF)
					{
						if (Math.abs(ballX - pad.getX()) < 350)
							{
								if (ballY < pad.getY() + aboveLead)
									moveUp = true;

								if (ballY > pad.getY() + belowLead)
									moveDown = true;
							}

						else if (difficulty == AI_HARD)
							{
								if (pad.getY() < 300)
									pad.moveDown(secondsPassed);
								else if (pad.getY() > 300)
									pad.moveUp(secondsPassed);
							}
						else if (difficulty != AI_HARD)
							{
								if (timedHold > 0)
									{
										timedHold -= secondsPassed;

										switch (buttonHeld)
											{
											case (KEY_W):
												moveUp = true;
												moveDown = false;
												break;
											case (KEY_S):
												moveUp = false;
												moveDown = true;
												break;
											}
									}
								else
									{
										moveUp = false;
										moveDown = false;

										timedHold = Tools.randDouble(0.15, 0.73);
										buttonHeld = Tools.randBool() ? KEY_W : KEY_S;
									}
							}

						if (moveUp)
							pad.moveUp(secondsPassed);
						if (moveDown)
							pad.moveDown(secondsPassed);

						if (Tools.randPercent() > 99 - difficulty)
							{
								belowLead += Tools.randInt(-difficulty, difficulty);
								aboveLead += Tools.randInt(-difficulty, difficulty);
								if (Tools.randPercent() > 95 + difficulty)
									{
										aboveLead = 2;
										belowLead = 50;
									}
							}
					}
			}

		public final void setDifficulty(int difficulty)
			{
				this.difficulty = difficulty;
			}

		public int getDifficulty()
			{
				return difficulty;
			}
	}
