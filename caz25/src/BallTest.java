import static org.junit.Assert.*;

import org.junit.Test;


public class BallTest {

	//test objects
	Ball testBall1 = new Ball(2,2,2,2);
	Ball testBall2 = new Ball(5,26,20,20);
	Ball testBall3 = new Ball(0,620,20,20);
	Block testBlock = new Block(2,4,2,2,5);
	Slider testSlider = new Slider(5,30,40,20);

	//tests to makes sure the ball's direction
	//changes when it hits the slider
	@Test
	public void testCheckSliderSideHit() {
		
		
		int ballMoveSpeedBeforeHit = testBall2.getBallMoveSpeedY();
		

		testBall2.keepTheBallRolling();

		testBall2.checkSliderSideHit(testSlider);
	
		assertEquals((-1)*ballMoveSpeedBeforeHit, testBall2.getBallMoveSpeedY(),0);
		
	}
	
	//tests to make sure the ball's direction changes
	//when the ball hits a block
	
	@Test
	public void testCheckBlockSideHit(){
		
		int ballMoveSpeedBeforeHit = testBall1.getBallMoveSpeedY();
		

		testBall1.keepTheBallRolling();

		testBall1.checkBlockSideHit(testBlock);
		assertEquals((-1)*ballMoveSpeedBeforeHit, testBall1.getBallMoveSpeedY(),0);
		
	}
	
	//tests to make sure the ball recognizes when the ball
	//goes off the bottom of the screen
	@Test
	public void testCheckCollisionWithBottom(){
		
		boolean collided =testBall3.checkCollisionWithBottom();
		assertTrue(collided);
		
	}
	
	//tests to make sure the ball recognizes when the 
	//when it hits a side or top wall
	@Test
	public void testCheckCollisionWithWalls(){
		
		testBall3.checkCollisionWithWalls();
		assertEquals(-1, testBall3.getBallMoveSpeedX(),0);
		
	}

}
