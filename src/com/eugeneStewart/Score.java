package com.eugeneStewart;

/** Keeps track of, and display the user's score
 * 
 */


class Score {

	private static int score;
	private static int highScore = 0;
	private static int increment;
	
	public Score(){
		score = 0;
		increment = 1;  //how many points for eating a kibble
		//Possible TODO get more points for eating kibbles, the longer the snake gets?
	}
	
	public static void resetScore() {
		score = 0;	
	}
	
	public static void increaseScore() {
		
		score = score + increment;
		
	}
	
// --Commented out by Inspection START (11/17/2015 8:41 AM):
//	public int getScore(){
//		return score;
//	}
// --Commented out by Inspection STOP (11/17/2015 8:41 AM)

	//Checks if current score is greater than the current high score. 
	//updates high score and returns true if so.
	
// --Commented out by Inspection START (11/17/2015 8:41 AM):
//	public boolean gameOver(){
//
//		if (score > highScore) {
//			highScore = score;
//			return true;
//		}
//		return false;
//	}
// --Commented out by Inspection STOP (11/17/2015 8:41 AM)

	//These methods are useful for displaying score at the end of the game
	
	public String getStringScore() {
		return Integer.toString(score);
	}

	public String newHighScore() {
		
		if (score > highScore) {
			highScore = score;
			return "New High Score!!";
		} else {
			return "";
	}
	}

	public String getStringHighScore() {
		return Integer.toString(highScore);
	}

	public static void DecreaseScore() {
		score = score - increment;
	}
}

