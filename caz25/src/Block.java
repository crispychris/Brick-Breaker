
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;;


//this class extends Rectangle and is the back end data holder for the 
//positions of all the blocks that need to be drawn on the screen
public class Block extends Rectangle{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	int numberOfHitsTillGone = 1;//number of hits till block is destroyed
	int numberOfTimesHit = 0;//number of times the ball has been  hit
	int hitsDifference = getNumberOfHitsTillGone() - getNumberTimesHit();
	//the hitsDifference is how many hits are left till the block is destroyed
	//it is used when painting the blocks
	boolean destroyed = false;//set to true when the block is hit
	private int scoreValue = 1;//the score that each block adds to total score when hit
	
	
	//default constructor
	public Block()
	{
		
		setXCoor(0);
		setYCoor(0);
		setWidth(60);
		setHeight(40);
		setNumberTimesToHitTillGone(1);
		
	}
	
	//constructor with variables
	
	public Block(int x, int y, int width, int height, int numberOfHitsTillGone){
		
		setXCoor(x);
		setYCoor(y);
		setWidth(width);
		setHeight(height);
		setNumberTimesToHitTillGone(numberOfHitsTillGone);
		
		calculateHitsDifference();
		
		
		
	}
	
	
	//the setters and getters
	public int getScoreValue(){
		
		return scoreValue;
		
	}
	
	public boolean isDetroyed(){
		
		return destroyed;
		
	}
	
	public int getXCoor(){
		return this.x;
	}
	public int getYCoor(){
		return this.y;
	}
	public int getHeightBlock(){
		return this.height;
	}
	public int getWidthBlock(){
		return this.width;
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
	
	public int getNumberTimesHit(){
		
		return numberOfTimesHit;
	}
	
	public int getNumberOfHitsTillGone(){
		
		return numberOfHitsTillGone;
	}
	
	public int getHitDifference(){
		
		return hitsDifference;
	}
	
	public void setNumberTimesToHitTillGone(int numberOfTimesToHit){
		
		numberOfHitsTillGone = numberOfTimesToHit;
	}
	
	//increases the numberOfTimesHit
	//sets destroyed to true if the ball has been 
	// hit enough times
	public void recordCollision(){
		
		//add a hit
		numberOfTimesHit++;
		
		//recalculate hit difference
		calculateHitsDifference();
		
		//if hit sufficient times then the ball is destroyed
		if(numberOfTimesHit>= numberOfHitsTillGone){
			
			destroyed = true;
			
		}
		
	}
	
	
	//calculates the number of times the ball has been hit
	//versus the number of hits needed to destroy it
	//this matters when painting the block color
	public void calculateHitsDifference(){
		
		hitsDifference = getNumberOfHitsTillGone() - getNumberTimesHit();
	}
	
	
	//paints the blocks different colors depending 
	//on how many hits are need 
	//still to destroy the block
	public void paintThisBlock(Graphics g){
	
		
		switch(getHitDifference()){
			case 1:
				g.setColor(Color.red);
				break;
			case 2:
				g.setColor(Color.white);
				break;
			case 3:
				g.setColor(Color.yellow);
				break;
			case 4:
				g.setColor(Color.blue);
				break;
			default:
				g.setColor(Color.red);
				break;
		}
	
		g.fillRect(getXCoor(), getYCoor(), getWidthBlock(), getHeightBlock());
		g.setColor(Color.green);
		g.drawRect(getXCoor(), getYCoor(), getWidthBlock(), getHeightBlock());
	
	}

	//called when the block is going to be painted
	//but seen to be already destroyed
	public void doNotPaintBlock() {
		
		destroyBlock();
		
	}
	
	//moves the block from the screen by assigning 
	//0 to all values
	private void destroyBlock(){
		
		setXCoor(0);
		setYCoor(0);
		setWidth(0);
		setHeight(0);
		
	}
	
	
	
}
