
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


//extends the Rectangle class
public class Slider extends Rectangle {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//initial starting points
	int initX = 400;
	int initY = 550;
	int initWidth =200;
	int initHeight = 25;

	//default constructor
	public Slider(){
		
		setXCoor(initX);
		setYCoor(initY);
		setWidth(initWidth);
		setHeight(initHeight);
		
		
		
	}
	
	//constructor made for testing purposes
	public Slider(int x, int y, int width, int height){
		
		setXCoor(x);
		setYCoor(y);
		setWidth(width);
		setHeight(height);
		
		
	}
	
	
	
	//the setters and getters
	public int getXCoor(){
		return x;
	}
	public int getYCoor(){
		return y;
	}
	public int getHeightSlider(){
		return height;
	}
	public int getWidthSlider(){
		return width;
	}
	public void setXCoor(int xToSet){
		x = xToSet;
	}
	public void setYCoor(int yToSet){
		y = yToSet;
	}
	
	public void setSlider(int x , int y){
		setXCoor(x);
		setYCoor(y);
		
		
	}
	
	public void setHeight(int howTall){
		height = howTall;
	}
	public void setWidth(int howWide){
		width = howWide;
	}
	
	//unless it is at the edge of the screen
	//this moves the slider left by one
	public void moveSliderLeft(){
	
		if(x != 0){
			x=x-1;
		}
		
		
	}
	
	//unless it is at the edge of the screen this 
	//moves the slider to the right by 1
	public void moveSliderRight(){
		
		if(x + getWidth() != 1000){
			
			x=x+1;
		}
		
		
	}
	
	//paints the slider
	public void paintSlider(Graphics g){
  	
  		g.setColor(Color.orange);
  		g.fillRect(getXCoor() , getYCoor(), getWidthSlider(), getHeightSlider());
	    g.setColor(Color.red);
	    g.drawRect(getXCoor(), getYCoor(), getWidthSlider() , getHeightSlider());
  	}
	
}//end class Slider
