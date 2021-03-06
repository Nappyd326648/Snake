package com.eugeneStewart;

import java.util.Random;

class Kibble {

	/** Identifies a random square to display a kibble
	 * Any square is ok, so long as it doesn't have any snake segments in it. 
	 * There is only one Kibble and when the snake eats it, then it moves. 
	 */
	
	private int kibbleX; //This is the square number (not pixel)
	private int kibbleY;  //This is the square number (not pixel)

	
	public Kibble(Snake s, CSnake cSnake){
		//Kibble needs to know where the snake is, so it does not create a kibble in the snake
		//Pick a random location for kibble, check if it is in the snake
		//If in snake, try again
		
		moveKibble(s,cSnake);
	}

	void moveKibble(Snake s, CSnake cSnake){
		
		Random rng = new Random();
		boolean kibbleInSnake = true;
		while (kibbleInSnake) {
			//Generate random kibble location
			kibbleX = rng.nextInt(SnakeGame.xSquares);
			kibbleY = rng.nextInt(SnakeGame.ySquares);

			kibbleInSnake = s.isSnakeSegment(kibbleX, kibbleY)||cSnake.isSnakeSegment(kibbleX,kibbleY);
		}
		
		
	}

	public int getKibbleX() {
		return kibbleX;
	}

	public int getKibbleY() {
		return kibbleY;
	}


	
}
