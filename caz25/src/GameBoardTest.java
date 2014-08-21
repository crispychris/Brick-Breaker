import static org.junit.Assert.*;

import org.junit.Test;


public class GameBoardTest {

	
		//creates a test GameBoard
		GameBoard myGame = new GameBoard();
		
	@Test
	public void testCreateBlockList(){
		
		//tests to make sure the blockList is created properly
		
		assertEquals(40, myGame.getBlockList().size(),0);
		
	}

}
