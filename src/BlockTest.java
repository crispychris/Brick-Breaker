import static org.junit.Assert.*;

import org.junit.Test;


public class BlockTest {
	
	Block testBlock = new Block(2,2,2,2,1);

	//tests to make sure the collisions are recorded 
	//correctly and that the block recognizes when
	//it has been destroyed
	@Test
	public void testRecordCollision() {
		
		testBlock.recordCollision();
		assertTrue(testBlock.isDetroyed());
		
	}

}
