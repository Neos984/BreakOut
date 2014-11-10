import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;


public class BreakOut  extends GraphicsProgram 
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of the paddle */
	private static int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static int NBRICK_ROWS = 5;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	private static final int NUMBER_BRICKS = NBRICKS_PER_ROW*NBRICK_ROWS ;
	/** Width of a brick */
	private static final int BRICK_WIDTH =
		(APPLICATION_WIDTH-(2*BRICK_SEP) - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 10;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
	/** LoL! I didn't even use this variable*/
	private static final int BALL2_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Delay between ball updates */
	private static final int ANIMATION_PAUSE = 10;
	
	/** The Red Ball */
	private Ball ball;
	
	/** The Blue Ball (ball and ball 2 are TWO DIFFERENT OBJECT TYPES */
	private Ball2 ball2;
	
	/** The Paddle */
	private GRect paddle;
	
	// The Text
	private GLabel messageLabel;
	private GLabel scoreLabel;
	private GLabel bricksLeft;
	private GLabel levelCounter;
	private GLabel livesCounter;
	
	/**Font for the text.*/
	Font start = new Font("Comic Sans", Font.BOLD, 15);
	
	/** Used in advancing to the next level.*/
	private boolean isWinner = false;
	
	// Cheats
	private boolean infiniteLives = false;
	private boolean doubleScore = false;
	private boolean trueScore = true;
<<<<<<< HEAD
	private boolean suicideMode = true;
=======
	private boolean suicideMode = false;
>>>>>>> FETCH_HEAD
	private boolean started = false;
	
	// All of the intergers
	private int level = 1;
	private int score = 0;
	private int brick = 50;
	private int multiplier = level;
	private int numBricksLeft = NUMBER_BRICKS;
	private int addScore = 1;
	private int lives = 3;
	
	/**Used in generating the color of the bricks*/
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	// The Background Colors
	Color set2 = new Color(130, 148, 53);
	Color set4 = new Color(9,130,210);
	Color set3 = new Color(240,100,32);
	Color set5 = new Color(255,255,200);
	
	
	public static void main(String[] args) {
		new BreakOut().start(args);
	}
	
		
	
	
// The main method
	public void run()
	{
		
		addMouseListeners();
		// For if level is changed in the code.
				if (level >= 13)
				{
					PADDLE_WIDTH = 50;
				}
		cheats();
<<<<<<< HEAD
		setup();
=======
>>>>>>> FETCH_HEAD
		variables();
		setup();
		
		// Defines the starting text.
		levelCounter = new GLabel("Level: " + level, 330, 15);
		levelCounter.setFont(start);
		if(level <= 15)
		{
		levelCounter.setColor(Color.white);
		}
		if(level > 15)
		{
		levelCounter.setColor(Color.black);
		}
		add(levelCounter);
		messageLabel = new GLabel("Click to start", 150, 400);
			if(lives == 2 || lives == 1)
			{
				messageLabel.setLabel("Lives: " + lives);
			}
			if(lives == 0)
			{
				ending();
			}
		
		messageLabel.setFont(start);
		if(level <= 15)
		{
		messageLabel.setColor(Color.white);
		}
		if(level > 15)
		{
		messageLabel.setColor(Color.black);
		}
		add(messageLabel);
		
		// Gameplay starts here.
		
		waitForClick();
		started = true;
		
		remove(messageLabel);
		
		// Defines more text (This text is used during gameplay).
		scoreLabel = new GLabel("Score: " + score, 10, 15); 
		scoreLabel.setFont(start);
		if(level <= 15)
		{
		scoreLabel.setColor(Color.white);
		}
		if(level > 15)
		{
		scoreLabel.setColor(Color.black);
		}
		add(scoreLabel);
		
		bricksLeft = new GLabel("Bricks Left: " + brick, 10, 30);
		bricksLeft.setFont(start);
		if(level <= 15)
		{
		bricksLeft.setColor(Color.white);
		}
		if(level > 15)
		{
		bricksLeft.setColor(Color.black);
		}
		add(bricksLeft);
		
		livesCounter = new GLabel("Lives: " + lives, 330, 30);
		livesCounter.setFont(start);
		if(level <= 15)
		{
		livesCounter.setColor(Color.white);
		}
		if(level > 15)
		{
		livesCounter.setColor(Color.black);
		}
		add(livesCounter);
		levelCounter = new GLabel("Level: " + level, 330, 15);
		levelCounter.setFont(start);
		if(level <= 15)
		{
		levelCounter.setColor(Color.white);
		}
		if(level > 15)
		{
		levelCounter.setColor(Color.black);
		}
		
		// Variables that are constantly being checked.
		while(true)
		{
			if(numBricksLeft == 0)
			{
				messageLabel.setLabel("You Win! :)");
				isWinner = true;
				break;
			}
			
			// If the red ball goes below the paddle.
			if(ball.getY() > APPLICATION_HEIGHT - PADDLE_Y_OFFSET + (PADDLE_HEIGHT / 2))
			{
				lives -= 1;
				messageLabel.setLabel("Lives: " + lives);
				if (level <= 3)
				{
					brick = 50;
					numBricksLeft = 50;
				}
				if (level >= 4)
				{
					brick = 100;
					numBricksLeft = 100;
				}
				removeAll();
				run();
				
				
			}
			
			// If the blue ball goes below the paddle.
			if (level >= 7)
			{
				if(ball2.getY() > APPLICATION_HEIGHT - PADDLE_Y_OFFSET + (PADDLE_HEIGHT / 2))
					{
					lives -= 1;
					messageLabel.setLabel("Lives: " + lives);
					if (level <= 3)
					{
						brick = 50;
						numBricksLeft = 50;
					}
					if (level >= 4)
					{
						brick = 100;
						numBricksLeft = 100;
					}
					removeAll();
					run();
				}
			}
			
			// If the player loses the game.
			if(lives == 0)
			{
				messageLabel.setLabel("You Lose... :(");
				ending();
			}
		
			// Moves Ball...
			ball.move();
			collide();
			// Moves Ball2
			if (level >= 7)
			{
				ball2.move2();
				collide2();
			}
			pause(ANIMATION_PAUSE);
			
			/* If this is not here the score nor bricks 
			  left will not update.*/
			 
			scoreLabel.setLabel("Score: " + score);
			bricksLeft.setLabel("Bricks Left: " + brick);
		}
		add(messageLabel);
		
		// Advances levels.
		if (isWinner)
		{
			messageLabel.setLabel("Click to advance to next level");
			waitForClick();
			level += 1;
			multiplier +=1;
			
			// For 50 Bricks
			if (level <= 3)
			{
				brick = 50;
				numBricksLeft = 50;
			}
			/*For 100 Bricks (Does not affect placed bricks, 
			  only the text)*/
			 
			if (level >= 4)
			{
				brick = 100;
				numBricksLeft = 100;
			}
			if (level == 10)
			{
				lives += 1;
			}
			// I doubt the player will get here.
			if (level == 20)
			{
				lives += 1;
			}
			if (level == 30)
			{
				lives += 1;
			}
			if (level == 40)
			{
				lives += 1;
			}
			if (level == 50)
			{
				lives += 1;
			}
			if (level == 60)
			{
				lives += 1;
			}
			if (level == 70)
			{
				lives += 1;
			}
			removeAll();
			isWinner = false;
			run();
		}
	}
	
	
	private void addBricks()
	{
		if (level >= 4)
		{
			NBRICK_ROWS = 10;
		}
		for(int row = 0; row < NBRICK_ROWS;row++)
		{
			//Generates colors
			Color c = rgen.nextColor();
			
			//Loops for 5 or 10 rows
			for(int col = 0; col < NBRICKS_PER_ROW;col++)
			{
				int x = BRICK_SEP + col*(BRICK_WIDTH+BRICK_SEP);
				int y = BRICK_Y_OFFSET + row*(BRICK_HEIGHT + BRICK_SEP);
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setFillColor(c);
				add(brick);
			}
		}
	}
	
	private void addPaddle()
	{
		paddle = new GRect(getWidth() / 2 - PADDLE_WIDTH / 2,
				 getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.green);
		add(paddle);
	}
	
	private void addBall()
	{
		// The ball's side to side movement speed.
		double vx = rgen.nextDouble(2.0,4.0);
		if(rgen.nextBoolean())
		{
			vx *= -1;
		}
		
		// The ball's up and down movement speed.
		int vy = 3;
		
		if (level >= 10 && level <= 12)
		{
			vy = 5;
		}
		
		if (level > 15)
		{
			vy = 5;
		}
		
		if(suicideMode)
		{
			vy = 7;
		}
		
		// Make sure it is Ball without a 2
		ball = new Ball(100, 400,
				2 * BALL_RADIUS, 2 * BALL_RADIUS, vx, vy);
		ball.setFilled(true);
		ball.setFillColor(Color.red);
		add(ball);	
	}
	
	private void addBall2()
	{
		// ditto
		double vx2 = rgen.nextDouble(2.0,4.0);
		if(rgen.nextBoolean())
		{
			vx2 *= -1;
		}
		
		int vy2 = 3;
		
		if (level >= 10 && level <= 12)
		{
			vy2 = 5;
		}
		
		if (level > 15)
		{
			vy2 = 5;
		}
		
		if(suicideMode)
		{
			vy2 = 7;
		}
			
		ball2 = new Ball2(300,220,
				2 * BALL2_RADIUS, 2 * BALL2_RADIUS, vx2, vy2);
		ball2.setFilled(true);
		ball2.setFillColor(Color.blue);
		add(ball2);
	}
	
	public void cheats()
	{
		if(level > 1 && started == false)
		{
			trueScore = false;
		}
		if(suicideMode)
		{
			level = 9990000;
			PADDLE_WIDTH = 50;
			if(started = false)
			{
				multiplier = 1;
			}
			multiplier = 1;
			trueScore = true;
		}
		if(doubleScore)
		{
			multiplier *= 2;
			trueScore = false;
		}
		if(infiniteLives)
		{
			lives = 999999;
			trueScore = false;
		}
	}
	
	public void setup()
	{
		// um... nothing to say
		addBricks();
		addPaddle();
		addBall();
		if (level >= 7)
		{
			addBall2();
		}
	}
	public void variables()
	{	
		// Background and Brick modifiers.
		if (level <= 3)
		{
			brick = 50;
			numBricksLeft = 50;
		}
		if (level >= 4)
		{  
			brick = 100;
			numBricksLeft = 100;
		}
		if (level >= 13)
		{
			PADDLE_WIDTH = 50;
		}
		if (level <= 3)
		{
			setBackground(Color.black);
		}
		if (level > 3 && level <=6)
		{
			setBackground(set2);
		}
		if (level > 6 && level <=9)
		{
			setBackground(set3);
		}
		if (level > 9 && level <=12)
		{
			setBackground(set4);
		}
		if (level > 12 && level <=15)
		{
			setBackground(set5);
		}
		if (level > 15)
		{
			setBackground(Color.white);
		}
	}
	
	// Gets the paddle to follow the mouse
	public void mouseMoved(MouseEvent me)
	{
		int x = me.getX();
		
		if(paddle != null)
		{
			if(x > PADDLE_WIDTH / 2 && x < getWidth() - PADDLE_WIDTH / 2)
			{
				paddle.setLocation(x - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET);
			}
		}
	}
	
	private void collide()
	{
		GObject collider = getCollidingObject();
		
		if(collider == paddle)
		{
			ball.setVY(-Math.abs(ball.getVY()));
		}
		
		/* These are very important. If these are not here
		   The game will glitch when the ball hits text*/
		else if(collider == ball2)
		{
		}
		
		else if(collider == livesCounter)
		{
		}
		
		else if(collider == levelCounter)
		{
		}
		
		else if(collider == bricksLeft)
		{
		}
		
		else if(collider == scoreLabel)
		{
		}
		
		
		else if(collider != null)
		{
			remove(collider);
			ball.setVY(-ball.getVY());
			numBricksLeft -= 1;
			score += (addScore*multiplier);
			brick -= 1;
		}
			
		else{}
			
	
		}
	
	private void collide2()
	{
		GObject collider2 = getCollidingObject2();
		
		// ditto
		if(collider2 == paddle)
		{
			ball2.setVY2(-Math.abs(ball2.getVY2()));
		}
		
		
		else if(collider2 == livesCounter)
		{
		}
		
		else if(collider2 == levelCounter)
		{
		}

		else if(collider2 == bricksLeft)
		{
		}
		
		else if(collider2 == scoreLabel)
		{
		}
		
		else if(collider2 == ball)
		{
		}
			
		else if(collider2 != null)
		{
			remove(collider2);
			ball2.setVY2(-ball2.getVY2());
			numBricksLeft -= 1;
			score += (addScore*multiplier);
			brick -= 1;
		}
		else{}
			
	
		}
	public void ending()
	{
		messageLabel.setLabel("You Lost... :(");
		messageLabel.setFont(start);
			// Red screen of death.
			setBackground(Color.RED);
			removeAll();
			add(messageLabel);
			add(scoreLabel);
			add(bricksLeft);
			add(levelCounter);
			
			if (level <= 3)
			{
				brick = 50;
				numBricksLeft = 50;
			}
			NBRICK_ROWS = 5;
			level = 1;
			// Multiplier will stay the same without this.
			multiplier = level;
			lives = 3;
			PADDLE_WIDTH = 60;
			System.out.println("Legit Score: " + trueScore);
<<<<<<< HEAD
			if(trueScore)
			{
				System.out.println("Score: " + score);
			}
			score = 0;
=======
>>>>>>> FETCH_HEAD
			started = false;
			waitForClick();
			removeAll();
			run();
	}
	
	
	// Collision Borders
	private GObject getCollidingObject() 
	{
		//Finds collider, if the ball collided on the top left corner 
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			return getElementAt(ball.getX(), ball.getY()) ; 
		}
		//Finds collider, if the ball collided on the top right corner 
		else if (getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()) != null){
			return getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()) ;
		}
		//Finds collider, if the ball collided on the bottom left corner 
		else if (getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS) != null) {
			return getElementAt(ball.getX(), ball.getY() + 2* BALL_RADIUS) ;
		}
		//Finds collider, if the ball collided on the bottom right corner 
		else if (getElementAt(ball.getX()+ 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS) != null) {
			return getElementAt(ball.getX()+ 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS) ;
		}
		return null;
	}
		private GObject getCollidingObject2()
		{
			//Finds collider, if the ball collided on the top left corner 
			if (getElementAt(ball2.getX(), ball2.getY()) != null) {
				return getElementAt(ball2.getX(), ball2.getY()) ; 
			}
			//Finds collider, if the ball collided on the top right corner 
			else if (getElementAt(ball2.getX()+2*BALL_RADIUS, ball2.getY()) != null){
				return getElementAt(ball2.getX()+2*BALL_RADIUS, ball2.getY()) ;
			}
			//Finds collider, if the ball collided on the bottom left corner 
			else if (getElementAt(ball2.getX(), ball2.getY() + 2*BALL_RADIUS) != null) {
				return getElementAt(ball2.getX(), ball2.getY() + 2* BALL_RADIUS) ;
			}
			//Finds collider, if the ball collided on the bottom right corner 
			else if (getElementAt(ball2.getX()+ 2*BALL_RADIUS, ball2.getY() + 2*BALL_RADIUS) != null) {
				return getElementAt(ball2.getX()+ 2*BALL_RADIUS, ball2.getY() + 2*BALL_RADIUS);
			}
		return null; 
		}
}

/* A very sad high score list. THIS IS MY LEGIT HIGH SCORE :D
 * no cheatsy doodles.
 */
/* High Scores:
 * 1: TAB: 3,454
 * 2: 
 * 3: 
 * 4: 
 * 5:
 * 6:
 * 7:
 * 8:
 * 9:
 * 10:
 */
 
 
