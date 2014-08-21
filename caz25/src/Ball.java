import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.lang.Math;


//This class extends Ellipse2D.Float which gives it the intersects 
//function which is used in detecting collisions
public class Ball extends Ellipse2D.Float{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int radius = (int) this.height/2;
	private int centerX = (int) this.getX() + radius;//center of ball x
	private int centerY = (int) this.getY() + radius;//center of ball y
	private boolean stillAlive = true;//set to false when
	//the ball falls out of the bottom of the screen
	
	//the rate at which the ball travels
	private int ballMoveSpeedX = 1;
	private int ballMoveSpeedY = -1;
	
	//initial starting points for the ball
	private int initX = 475;
	private int initY = 400;
	private int initWidth = 20;
	private int initHeight = 20;
	
	//default constructor
	public Ball(){
		
		//sets a random starting point for the ball
		setRandomX();
		
		//set ball coordinates
		this.setXCoor(initX);
		this.setYCoor(initY);
		this.setWidth(initWidth);
		this.setHeight(initHeight);
	}
	
	//created for testing purposes
	public Ball(int initX, int initY, int initWidth, int initHeight){
		
		//set ball coordinates
		this.setXCoor(initX);
		this.setYCoor(initY);
		this.setWidth(initWidth);
		this.setHeight(initHeight);
		
	}
	
	//uses Math.random to give the ball a few possible starting positions
	private void setRandomX(){
		
		//returns a random double
		double randomNum = Math.random();
		int randomInitX = 400;//default value
		
		
		if(randomNum >= 0.00 && randomNum < 0.25){
			
			randomInitX = 200;
		}
		if(randomNum >= 0.33 && randomNum < 0.50){
			
			randomInitX = 600;
		}
		if(randomNum >= 0.50 && randomNum < 0.75){
			
			randomInitX = 700;
		}
		if(randomNum >= 0.75 && randomNum < 1.00){
			
			randomInitX = 900;
		}
		
		
		initX = randomInitX;
		
	}
	
	//the setters and getters 
	public int getXCoor(){
		return (int) this.x;
	}
	public int getYCoor(){
		return (int) this.y;
	}
	public int getHeightBall(){
		return (int) this.height;
	}
	public int getWidthBall(){
		return (int) this.width;
	}
	
	public void setXCoor(int xToSet){
		x = xToSet;
	}
	public void setYCoor(int yToSet){
		y = yToSet;
	}
	public void setHeight(int howTall){
		height = howTall;
	}
	public void setWidth(int howWide){
		width = howWide;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public int getCenterBallX(){
		
		centerX = (int) this.getX() + radius;
		return centerX;
		
	}
	
	public int getCenterBallY(){
		
		centerY = (int) this.getY() + radius;
		return centerY;
	}
	
	public boolean getBallStatus(){
		
		return stillAlive;
		
	}
	
	//moves the ball 1 space each time
	
	public void keepTheBallRolling(){
		
		//makes sure the ball does not go off the screen
		
		checkCollisionWithWalls();
		
		
		this.x += ballMoveSpeedX;
		this.y += ballMoveSpeedY;
	
		//lets the game know if ball has fallen
		
		if(checkCollisionWithBottom() == true){
			
			stillAlive = false;
		}	
			
	}
	
	//reverses the ball direction y movement
	public void changeBallDirectionY(){
		
		ballMoveSpeedY = (-1)*ballMoveSpeedY;
		
	}
	//reverses the ball direction x movement
	public void changeBallDirectionX(){
		
		ballMoveSpeedX = (-1)*ballMoveSpeedX;
	}	
	
	//checks the ball's position with the boundaries
	//of the board to make it bounce off the walls or ceiling
	//if it needs to
	public void checkCollisionWithWalls(){
	
		//if ball hits ceiling
		if(getCenterBallY()- getRadius()<=0){
			
			changeBallDirectionY();
			
		}
		//if ball hits left wall
		else if(getX() == 0){
			
			changeBallDirectionX();
			
		}
		//if ball hits right wall
		else if(getX()+getWidth() == 1000){
			
			changeBallDirectionX();
			
		}
	}
	
	//stops the ball from moving if it has died
	//and returns true
	public boolean checkCollisionWithBottom(){
		
		boolean ballLost = false;
		
		//checks if ball has disappeared off the
		//bottom of the screen
		if(getCenterBallY()+ getRadius()>=620){
			
			ballLost = true;
			return ballLost;

		}//otherwise the ball is still  active
		else{
			
			ballLost = false;
			return ballLost;
		}
		
	}
	//paints the ball
	public void paintBall(Graphics g){
  		
  		g.setColor(Color.white);
  		g.fillOval(getXCoor(),getYCoor(),
	   			getWidthBall(), getHeightBall());
	   	g.setColor(Color.red);
	   	g.drawOval(getXCoor(),getYCoor(),
	   			getWidthBall(), getHeightBall());
  	}
	
	//checks which side of a block was hit and changes the balls 
	//direction accordingly
	public void checkBlockSideHit(Block blockObj){
    	
    	//returns the overlapping rectangle made by the ball moving into
		//the block
    	Rectangle intersectionBox = getBounds().intersection(blockObj.getBounds());
    	
    	//if the overlapping rectangle is taller rather than wider
    	//then the ball came in from the side and the x mover is reversed
    	if (intersectionBox.height > intersectionBox.width)
    	{
    	    changeBallDirectionX();
    	}
    	
    	//if the overlapping rectangle is wider rather than taller
    	//then the ball came in from the top of bottom and the y mover is reversed
    	else if (intersectionBox.width > intersectionBox.height)
    	{
    	    changeBallDirectionY();
    	}
    	
    	// a catch all in case something goes wrong
    	//for cases when the ball
    	//hits at a perfect corner
    	else{
    		
    		changeBallDirectionX();
    		changeBallDirectionY();
    	}

    	
    
    }
	
	public int getBallMoveSpeedX(){
		
		return ballMoveSpeedX;
		
	}
	
	public int getBallMoveSpeedY(){
		
		return ballMoveSpeedY;
	}
	
	//the same thing as checkBlockSideHit but for the Slider
	public void checkSliderSideHit(Slider slider){
    	
    	
    	Rectangle intersectionBox = getBounds().intersection(slider.getBounds());
    	
    	if (intersectionBox.height > intersectionBox.width)
    	{
    	   changeBallDirectionX();
    	}
    	
    	else if (intersectionBox.width > intersectionBox.height)
    	{
    	    changeBallDirectionY();
    	}
    	
    	else{
    		
    		changeBallDirectionX();
    		changeBallDirectionY();
    	}

    	
    	
   }
	
	
	
}//end ball

