package TroysCode;

/**
 * This interface contains variables with a constant value. These constants can
 * be reached in any Class, as long as they <code> implement Constants</code>.
 * 
 * @author Sebastian Troy
 */
public interface Constants
	{
		/*
		 * This class allows the declaration of constants. I often use constants
		 * to refer to objects in an array, for example: ""arrayOfColours[0]""
		 * refers to the first member of that array, but that could be any
		 * colour. If I have the constant ""public static final byte BLUE = 0""
		 * and call ""arrayOfColours[BLUE]"" I then have an idea of what it is
		 * I'm actually accesing.
		 */

		public final int BALL_SPEED = 5;

		public final int KEY_NONE = 0;
		public final int KEY_W = 87;
		public final int KEY_S = 83;
		public final int KEY_P = 80;
		public final int KEY_UP = 38;
		public final int KEY_DOWN = 40;
		public final int KEY_ESCAPE = 27;

		public final int AI_OFF = 3;
		public final int AI_EASY = 4;
		public final int AI_MEDIUM = 2;
		public final int AI_HARD = 1;
	}
