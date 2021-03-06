package com.eugeneStewart;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 * 
 * @author Clara
 *
 */
public class DrawSnakeGamePanel extends JPanel {
	
	private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint
	
	private Snake snake;
	private Kibble kibble;
	private Score score;
	private CSnake cSnake;
	
	DrawSnakeGamePanel(Snake s, Kibble k, Score sc,CSnake cs){
		this.snake = s;
		this.kibble = k;
		this.score = sc;
		this.cSnake=cs;
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        /* Where are we at in the game? 4 phases.. 
         * 1. Before game starts
         * 2. During game
         * 3. Game lost aka game over
         * 4. or, game won
         */

        gameStage = SnakeGame.getGameStage();
        
        switch (gameStage) {
        case 1: {
        	displayInstructions(g);
        	break;
        } 
        case 2 : {
        	displayGame(g);
        	break;
        }
        case 3: {
        	displayGameOver(g);
        	break;
        }
        case 4: {
        	displayGameWon(g);
        	break;
        }
			case 5: {
				displayGameCWon(g);
				break;
			}
        }
        
        
        
    }

	private void displayGameWon(Graphics g) {
		// TODO Replace this with something really special!

		g.clearRect(100, 100, 350, 350);
		g.setColor(Color.YELLOW	);
		g.setFont(new Font("Papyrus", Font.BOLD, 20));
		g.drawString("YOU WON SNAKE!!!", 150, 150);
		displaySnake(g);
		
	}
	private void displayGameCWon(Graphics g) {
		// TODO Replace this with something really special!
		g.clearRect(100, 100, 350, 350);
		g.setFont(new Font("Papyrus", Font.BOLD, 20));
		g.drawString("Jonny Walker WON SNAKE!!!", 150, 150);
		displayCSnake(g);

	}

	private void displayGameOver(Graphics g) {

		g.clearRect(100,100,350,350);
		g.drawString("GAME OVER", 150, 150);
		
		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();
		
		g.drawString("SCORE = " + textScore, 150, 250);
		
		g.drawString("HIGH SCORE = " + textHighScore, 150, 300);
		g.drawString(newHighScore, 125, 400);
		
		g.drawString("press Any key to play again", 150, 350);
		g.drawString("Press q to quit the game", 150, 400);
    			
	}

	private void displayGame(Graphics g) {
		//displayGameGrid(g);
		displayCSnake(g);
		displaySnake(g);
		displayKibble(g);


	}

	private void displayGameGrid(Graphics g) {

		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;
		
		g.clearRect(0, 0, maxX, maxY);

		g.setColor(Color.RED);

		//Draw grid - horizontal lines
		for (int y=0; y <= maxY ; y+= squareSize){			
			g.drawLine(0, y, maxX, y);
		}
		//Draw grid - vertical lines
		for (int x=0; x <= maxX ; x+= squareSize){			
			g.drawLine(x, 0, x, maxY);
		}
	}

	private void displayKibble(Graphics g) {

		//Draw the kibble in green
		g.setColor(Color.RED);

		int x = kibble.getKibbleX() * SnakeGame.squareSize;
		int y = kibble.getKibbleY() * SnakeGame.squareSize;

		g.fillOval(x + 1, y + 1, SnakeGame.squareSize - 2, SnakeGame.squareSize - 2);
		
	}

	private void displaySnake(Graphics g) {

		LinkedList<Point> coordinates = snake.segmentsToDraw();
		
		//Draw head in grey
		g.setColor(Color.LIGHT_GRAY);
		Point head = coordinates.pop();
		g.fillRect((int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		
		//Draw rest of snake in black
		g.setColor(Color.BLACK);
		for (Point p : coordinates) {
			g.fillOval((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
			g.setFont(new Font ("Stencil",Font.PLAIN,20));
			g.drawString(score.getStringScore(), 450, 40);
		}

	}
	private void displayCSnake(Graphics g) {

		LinkedList<Point> coordinates = cSnake.segmentsToDraw();

		//Draw head in grey
		g.setColor(Color.blue);
		Point head = coordinates.pop();
		g.fillOval((int) head.getX(), (int) head.getY(), SnakeGame.squareSize, SnakeGame.squareSize);

		//Draw rest of snake in black
		g.setColor(Color.cyan);
		for (Point p : coordinates) {
			g.fillRect((int)p.getX(), (int)p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

	}

	private void displayInstructions(Graphics g) {
		g.setFont(new Font("Papyrus", Font.BOLD, 20));
		g.drawString("The rules Are as follows", 200, 200);
		g.drawString("It is a Tug Of War race to Fifteen",200,230);
		g.drawString("Do not hit the Wall ",200,255);
		g.drawString("Do not hit You Tall ",200,280);
		g.drawString("When score is Greater than Three Warp walls are activated ",200,330);
		g.drawString("Press any key to begin!",200,360);
        g.drawString("Press q to quit the game",200,390);
    	}
	
    
}

