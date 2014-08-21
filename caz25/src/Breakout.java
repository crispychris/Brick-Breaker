// 
// Project 2 
// Name: Chris Zawora 
// E-mail: caz25@georgetown.edu 
// Instructor: Singh 
// COSC 150 
// 
// In accordance with the class policies and Georgetown's Honor Code, 
// I certify that, with the exceptions of the lecture and Blackboard 
// notes and those items noted below, I have neither given nor received 
// any assistance on this project. 
// 
//		Help received from TA Andrew and Oracle.com 
//
// Description: This program is the game breakout using JSwing. It can be run on Eclipse
//When the program is run a new window appears on your screen 
//and the game begins with four rows of bricks,
//a ball, and a slider on the screen. The ball is moving and must be kept from 
// falling too low by moving the slider using the arrow keys. The game is lost if 
// the ball falls too low and it is won when all the blocks are destroyed after being hit
// by the ball.
// 




import java.awt.EventQueue;
import javax.swing.JFrame;

//this is the class that is the frame on which everything 
//happens. Nothing much happens in this class. The frame is
//initialized and the game is started in main.
public class Breakout extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Breakout Constructor
	 */
    public Breakout() {
    	
    	
    	initUI();
    	
    }
    
    /*
     * initUI()
     * 
     * Initialize the GUI
     * @param - none
     * @return - non
     * 
     */
    public void initUI() {
    	
    	// Add a Board object to your JFrame
        add(new GameBoard());

        // Set the title of the frame to 'Star
        setTitle("Breakout");               
        
        // Size the frame
        pack();
        
        // Do not allow it to be resized
        setResizable(false);
        
        // Set the location of the window relative to a 
        // specified component. If the component is not 
        // showing, or is null, the frame is centered 
        // on the screen.
        setLocationRelativeTo(null);
        
        // Exit with the frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    }
    
    public static void main(String[] args) {
    	
    	// Run the Breakout class on a 
    	// separate Event Dispatching Thread
    	// If an application wants to create an 
    	// instance of a thread, you need a way to execute
    	// the thread. That is what a Runnable does - it is
    	// one way to execute a thread. The Runnable object is
    	// passed to the thread constructor. Its only method is
    	// the thread method.
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {              
            	//start the new game!
                JFrame newGame = new Breakout();
                
                // Show the GUI
                newGame.setVisible(true);      
            }
        });
      
    }//end main
    
}