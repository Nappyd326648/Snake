package com.eugeneStewart;

import java.util.TimerTask;

public class GameClock extends TimerTask {

	Snake snake;
	Kibble kibble;
	Score score;
	CSnake cSnake;
	DrawSnakeGamePanel gamePanel;
		
	public GameClock(Snake snake, CSnake cSnake, Kibble kibble, Score score, DrawSnakeGamePanel gamePanel){
		this.snake = snake;
		this.cSnake= cSnake;
		this.kibble = kibble;
		this.score = score;
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void run() {
		// This method will be called every clock tick
						
		int stage = SnakeGame.getGameStage();

		switch (stage) {
			case SnakeGame.BEFORE_GAME: {
				//don't do anything, waiting for user to press a key to start
				break;
			}
			case SnakeGame.DURING_GAME: {
				//
				snake.moveSnake();
				if (snake.didEatKibble(kibble) == true) {		
					//tell kibble to update
					kibble.moveKibble(snake);
					Score.increaseScore();
				}
				cSnake.moveSnake(kibble);
				if (cSnake.didEatKibble(kibble)==true){
					kibble.moveKibble(snake);
					Score.increaseScore();
				}
				break;
			}
			case SnakeGame.GAME_OVER: {
				this.cancel();		//Stop the Timer	
				break;	
			}
			case SnakeGame.GAME_WON: {
				this.cancel();   //stop timer
				break;
			}
			
		
		}
				
		gamePanel.repaint();		//In every circumstance, must update screen
		
	}
}
