
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import java.util.ArrayList;

//this class is the Jpanel on which everything happens. 
//It implements runnable, the run function keeps the program
//going, and keylistener, which is used to move the slider.
public class GameBoard extends JPanel implements Runnable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//dimensions for the board, constants
	private final int GAMEBOARD_WIDTH = 1000;
    private final int GAMEBOARD_HEIGHT = 600;
    private final int BLOCK_WIDTH = 60;
    private final int BLOCK_HEIGHT = 40;
   
    //private data members
    
    private int delay = 10;//used to make the images move smoothly
    //blockList holds all the blocks on the screen
    private ArrayList<Block> blockList = new ArrayList<Block>();
    private Ball theBall = new Ball();
    private Slider theSlider= new Slider();
    //the thread the game runs on
    private Thread animator;

    //printed out on the screen
    private String instructUp = "Press up to go faster!";
    private String instructDown ="Press down to go slower";
    private String instructLeft = "Move left";
    private String instructRight = "Move right";
    
    //score the during the game
    private int score = 0;
    
    private String scoreText = "Score:";
    
    //String version of score used when 
    //printing it out
    private String scoreString;
    
    //booleans to indicate the end of the game
    private boolean gameOver = false;
    private boolean gameWin = false;
    
    
    private String timeText = "Time:";
    //keeps track of the duration of the game
    private int currentTime;
    private int minutes = 0;
    private int seconds = 0;
    //gets time in String form to print out
    private String currentTimeString;
    
    //how much the slider moves each time
    //the arrow keys are pressed
    private int moveSliderDistance = 50;
    
    //constructor for GameBoard
	public GameBoard(){
		
		//creates the back end data objects for the blocks
		//that are drawn the screen
		createBlockList();
		
		//sets up the size, and background color
		initializeGameBoard();
		
		//makes the GameBoard visible
		setVisible(true);
        setFocusable(true);
        requestFocusInWindow();
		
        //adds Key listeners for moving the slider
		addKeyListener(this);
		
	}
    /// Override the addNotify() method - this makes the the component
	//displayable. We want it displayable on a new thread. 
	//We do not call this method - swing does.
    @Override
    public void addNotify() {
        super.addNotify();

        //starts the thread for the game
        animator = new Thread(this);
        animator.start();
    } 
    
    //called in the constructor
	private void initializeGameBoard(){
		
		// Set background color (method of a JPanel)
        setBackground(Color.BLACK);
        
        // Set JPanel size
        setPreferredSize(new Dimension(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT));
        
        setDoubleBuffered(true);
        
    } // end initializeGameBoard
	
	//overridden paintComponent that paints all the objects on the board
    @Override
    public void paintComponent(Graphics g) {
       
    	super.paintComponent(g);
       
       //paints the instructions and score and timer
       paintText(g);
      
       
       //paints the blocks    
       paintBlocks(g);
            	
       //paints the slider
  	   theSlider.paintSlider(g);
  	    	
  	   //paints the ball
  	   theBall.paintBall(g);
  	    	
  	    	
    }//end paintComponent;
        
    //this function goes through the arraylist of Blocks
    // and paints them all. If they have been hit by the ball
    //already it recognizes that and does not paint them
    private void paintBlocks(Graphics g){
      try{
    		//cycles through block list
    	for(int i =0; i< blockList.size();i++){
    	
    		//checks if block has been destroyed
    		if(blockList.get(i).isDetroyed() == false){
            		
    			//if not it paints the block
    			blockList.get(i).paintThisBlock(g);
    			
    		
    		}//end  if	
    		else{//when block has been destroyed
    			
    			blockList.get(i).doNotPaintBlock();
    		}
    	}//end for	
    	
      }//end try
      catch(ArrayIndexOutOfBoundsException e){
    	  System.out.println("Array index exception in paintBlocks()");
    	  e.getStackTrace();
    	  
      }//end catch
    }//end paintBlocks
    
    
    
    //paints the instructions and score and timer on screen
    private void paintText(Graphics g){
    	
    	//casts the timer and score to strings 
    	//so they can be printed
    	setTimerString();
    	setScoreString();
    
    	//print 
    	g.setColor(Color.CYAN);
    	g.setFont(new Font("default", Font.TRUETYPE_FONT, 16));
    	g.drawString(instructUp, 415, 400);
    	g.drawString(instructDown, 405, 530);
    	g.drawString(instructLeft, 290, 470);
    	g.drawString(instructRight, 630, 470);
    	g.drawString(scoreText, 440 ,470);
    	g.drawString(scoreString, 530 ,470);
    	g.drawString(timeText, 440, 490);
    	g.drawString(currentTimeString, 530, 490);
    	
    	//special cases for the game endings
    	//when game is lost
    	if(gameOver == true){
    	
    		String lose ="YOU LOSE!";
    		g.drawString(lose, 450 ,230);
    		
    	
    	}
    	//when game is won
    	if(gameWin == true){
    		
    		String win ="WINNER WINNER CHICKER DINNER";
    		g.drawString(win, 360 ,230);
    		
    		
    	}//end if
    
    }//end function
    
   
    //calls paint one last time to paint lose message
    private void endGame(){
    	
    	repaint();
    }
    
    //calls paint one last time to paint win message
    private void winner(){
    	
    	repaint();
    }
    
    //checks if all the blocks have been hit once,
    //if they have, then gameWin is set to true
    private void checkGameWin(){
      try{	
    	int allBlocksGone = 0;
    	
    	//runs through the arrayList and increases
    	//allBlocksGone if a block is destroyed
    	for(int i = 0; i< blockList.size();i++){
    		
    		if( blockList.get(i).isDetroyed() == true){
    		
    			
    			allBlocksGone++;
    		
    		}//end if
    		
    	}//end for	
    	
    	//if all the blocks are gone then the game is won
    	if(allBlocksGone == 40){
    			
    		gameWin = true;
    	}//end if
      }//end try
      catch(ArrayIndexOutOfBoundsException e){
    	  System.out.println("Array index exception in checkGameWin()");
    	  e.getStackTrace();  
      }
    }
    
    //when a block is hit, this is called to add 
    //the block's score value to the total score
    private void updateScore(Block blockObj){
    	
    	score += blockObj.getScoreValue();
    	
    }
    
    //gets the score integer and casts it as a string
    //so it can be printed on the screen
    private void setScoreString(){
    	scoreString = String.valueOf(score);
    }
    
    //populates the blockList arrayList with 40 blocks
    //at the beginning of the game
  	public void createBlockList(){
  	
  		
  		int xCoordinate;
  	    int yCoordinate;
  	    int xCoordinateOffset;
        int numberOfHitsTillGone;  
  	    
       
        //outer for loop creates each row
  	    for(int j = 0; j<= 3;j++){
            	 
  	    		
        	    yCoordinate = j*80;
        	    xCoordinateOffset = 0;
        	    //each lower block row takes one
        	    //hit to destroy
        	    numberOfHitsTillGone = 4-j;
        	    
        	    //used to offset the block rows
            	if(j==0||j==2){
            		xCoordinateOffset = 39;
            	}
            	
            	
            	//inner for loop creates all block in one row
            	for(int i = 0; i< (GAMEBOARD_WIDTH/100);i++)
            	{
            		xCoordinate = xCoordinateOffset+(100*i);
            		Block newBlock = new Block(xCoordinate, yCoordinate, BLOCK_WIDTH,
            				BLOCK_HEIGHT, numberOfHitsTillGone);
            		
            		blockList.add(newBlock);
            		
            		
            	}//end inner for
            	
  	   
  	    }//end outer for
  	  
  	}//end createBlockList
  	
  	//cycle keeps the ball moving while checking if the ball 
  	//has hit the walls, the slider, or the blocks
  	//special cases are when the ball has fallen too far
  	private void cycle() {
  		
  		//moves the ball when it has not fallen off 
  		//the bottom
  		if(theBall.getBallStatus() != false){
  			
			theBall.keepTheBallRolling();
			
			//checks if the ball has collided with
			//the ball or the slider
			//or if the game has been won
			checkBallCollidesWithBlock();
			checkBallCollidesWithSlider();
	  		checkGameWin();
		}
  		else{//when the ball status is true
  			//it means the ball has fallen off the bottom 
  			// and the player has lost
  			
  			gameOver = true;
  			
  		}
  		
    }//end cycle
  	
  	
  	//changes the time into minute:second format String
  	public void setTimerString(){
  		
  		//gets seconds from currentTime
  		seconds = currentTime/60;
  		
  		//gets minutes from seconds
  		minutes = seconds/60;

  		//turn minutes and seconds into strings to print them
  		//also take the modulus of them to get them in 
  		//common time format
  		currentTimeString = String.valueOf(minutes%60);
 		currentTimeString += ":";
  		currentTimeString += String.valueOf(seconds%60);
  		currentTimeString += ":";
  		currentTimeString += String.valueOf(currentTime%60);
  	}//end setTimerString
  
  	//run is where the game is running
  	//it is constantly repainting the objects and moving the ball
    @Override
    public void run() {

    	//variables used to ensure the graphic motions 
    	//are smooth
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        // Keep updating GUI
        while (true) {

  
        	//special cases for game win and loss
        	if(gameOver == true){
        		
        		endGame();
        		break;
        	}
        	if(gameWin == true){
        		
        		winner();
        		break;
        		
        	}
        	
        	// Move x y coordinates
            cycle();
            
            //increase time with every iteration of the loop
            currentTime++;
            
            // Repaint the GUI
            repaint();
            
            

            // See if the methods are running at the
            // same rate. If not, sleep.
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = delay - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }

            beforeTime = System.currentTimeMillis();
        }
    }//end run
    
    //checks if the ball collides with any of the blocks by looping
    //through the arraylist of blocks and using the inherited intersects method belonging 
    //to the ball class
    public void checkBallCollidesWithBlock(){
      try{	
    	
    	//checks all the blocks
    	for(int i = 0; i< blockList.size();i++){
    		boolean collision = false;
    		
    		//only checks collision if the block
    		//has not already been hit
    		if( blockList.get(i).isDetroyed() != true){
    		
    			collision = theBall.intersects(blockList.get(i));
    		
    		
    		}
    		//if a collision is found
    		if(collision==true){
    			
    			//the score is updated
    			updateScore(blockList.get(i));
    			//the block records it was destroyed
    			blockList.get(i).recordCollision();
    			//and ball checks which way it has to move now
    			theBall.checkBlockSideHit(blockList.get(i));
    			
    			
    		}//end if
    	
    	}//end for
    	
      }//end try
      catch(ArrayIndexOutOfBoundsException e){
    	  System.out.println("Array index exception in checkBallCollidesWithBlock()");
    	  e.getStackTrace();
      }//end catch
    		
    }//end checkBallCollidesWithBlock
    
   
    //the same this as checkBallCollidesWithBlock
    //except for the slider
    public void checkBallCollidesWithSlider(){
      	
    	boolean collision = false;
    	
    		
    	collision = theBall.intersects(theSlider);
    	
    	//if a collision is found
    	if(collision==true ){
    			
    		//the ball bounces
    		theBall.checkSliderSideHit(theSlider);
    			
    	}//end if
    	
    	
    }//end checkBallCollidesWithSlider
    
    
    //keylisteners for the arrows keys
    @Override
    public void keyPressed(KeyEvent e) 
    {
    	//when the left key is pressed
    	//the slider moves left
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
        	//using a for loop prevents an error of the slider
        	//moving into the ball and the ball becoming trapped in
        	//slider
        	for(int i =0; i<moveSliderDistance; i++){
        		
        		if(theBall.intersects(theSlider)==false)
        			theSlider.moveSliderLeft();
        	}
        }
        
      //when the right key is pressed
    	//the slider moves left
        if(e.getKeyCode()== KeyEvent.VK_RIGHT)
        {
        	//using a for loop prevents an error of the slider
        	//moving into the ball and the ball becoming trapped in
        	//slider
        	for(int i =0; i<moveSliderDistance; i++){
        		
        		if(theBall.intersects(theSlider)==false)
        			theSlider.moveSliderRight();
        	}
        }
        
        //when the up or down keys are pressed the ball
        //and slider move faster or slower
        if(e.getKeyCode()== KeyEvent.VK_UP){
        	//decreases the delay making the ball move faster and 
        	//increases the slider moving speed
        	if(delay > 2){
        	delay--;
        	moveSliderDistance+=4;
        	}
        }
        if(e.getKeyCode()== KeyEvent.VK_DOWN){
        	//increases the delay making the ball move slower and 
        	//decreases the slider moving speed
        	if(delay < 20){
        	delay++;
        	moveSliderDistance-=4;
        	}
        }
        
    }

    @Override
	public void keyReleased(KeyEvent e) {
			
			//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		//do nothing
			
	}
	
	//returns the blockList
	//used for testing
	public ArrayList<Block> getBlockList(){
		
		return blockList;
	}
    
}//end GameBoard

