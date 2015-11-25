package com.eugeneStewart;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Eugene on 11/11/2015.
 */
public class CSnake {

    final int DIRECTION_UP = 0;
    final int DIRECTION_DOWN = 1;
    final int DIRECTION_LEFT = 2;
    final int DIRECTION_RIGHT = 3;  //These are completely arbitrary numbers.

    private boolean hitWall = false;
    private boolean ateTail = false;

    private int snakeSquares[][];  //represents all of the squares on the screen
    //NOT pixels!
    //A 0 means there is no part of the snake in this square
    //A non-zero number means part of the snake is in the square
    //The head of the snake is 1, rest of segments are numbered in order

    private int currentHeading;  //Direction snake is going in, ot direction user is telling snake to go
    private int lastHeading;    //Last confirmed movement of snake. See moveSnake method

    private int snakeSize;   //size of snake - how many segments?

    private int growthIncrement = 2; //how many squares the snake grows after it eats a kibble

    private int justAteMustGrowThisMuch = 0;

    private int maxX, maxY, squareSize;
    private int snakeHeadX, snakeHeadY; //store coordinates of head - first segment

    int xx=0;
    int yy=0;



    public CSnake(int maxX, int maxY, int squareSize){
        this.maxX = maxX;
        this.maxY = maxY;
        this.squareSize = squareSize;
        //Create and fill snakeSquares with 0s
        snakeSquares = new int[maxX][maxY];
        fillSnakeSquaresWithZeros();
        createStartSnake();
    }

    protected void createStartSnake(){
        //snake starts as 3 horizontal squares in the center of the screen, moving left
        int screenXCenter = (int) maxX/2;  //Cast just in case we have an odd number
        int screenYCenter = (int) maxY/3;  //Cast just in case we have an odd number

        snakeSquares[screenXCenter][screenYCenter] = 1;
        snakeSquares[screenXCenter+1][screenYCenter] = 2;
        snakeSquares[screenXCenter+2][screenYCenter] = 3;

        snakeHeadX = screenXCenter;
        snakeHeadY = screenYCenter;

        snakeSize = 3;

        currentHeading = DIRECTION_LEFT;
        lastHeading = DIRECTION_LEFT;

        justAteMustGrowThisMuch = 0;
    }

    private void fillSnakeSquaresWithZeros() {
        for (int x = 0; x < this.maxX; x++){
            for (int y = 0 ; y < this.maxY ; y++) {
                snakeSquares[x][y] = 0;
            }
        }
    }

    public LinkedList<Point> segmentsToDraw(){
        //Return a list of the actual x and y coordinates of the top left of each snake segment
        //Useful for the Panel class to draw the snake
        LinkedList<Point> segmentCoordinates = new LinkedList<Point>();
        for (int segment = 1 ; segment <= snakeSize ; segment++ ) {
            //search array for each segment number
            for (int x = 0 ; x < maxX ; x++) {
                for (int y = 0 ; y < maxY ; y++) {
                    if (snakeSquares[x][y] == segment){
                        //make a Point for this segment's coordinates and add to list
                        Point p = new Point(x * squareSize , y * squareSize);
                        segmentCoordinates.add(p);
                    }
                }
            }
        }
        return segmentCoordinates;

    }



    public void    eatKibble(){
    //record how much snake needs to grow after eating food
    justAteMustGrowThisMuch += growthIncrement;
    }

    protected void moveSnake(){
        //Called every clock tick

        //Must check that the direction snake is being sent in is not contrary to current heading
        //So if current heading is down, and snake is being sent up, then should ignore.
        //Without this code, if the snake is heading up, and the user presses left then down quickly, the snake will back into itself.
        //int xx =kibble.getKibbleX();
        //int yy = kibble.getKibbleY();
        System.out.println(Score.score);


        int c[]=new int[maxX-1];
        int cy[]= new int[maxY-1];
        for(int i =0;i <c.length;i++ ){c[i]=i;}
        for(int i =0;i <cy.length;i++ ){cy[i]=i;}

        for(int i =0;i <c.length;i++ ){
            if (snakeHeadX==c[i]&& snakeHeadY==0||snakeHeadX==c[i]&& snakeHeadY==maxY-1){System.out.println(snakeHeadX==c[i]&& snakeHeadY==0||snakeHeadX==c[i]&& snakeHeadY==maxY-1);
                if (i>=maxX/2)System.out.println( i>maxX/2);{currentHeading=DIRECTION_RIGHT;System.out.println("1r");}if(i<maxX/2){currentHeading=DIRECTION_LEFT;System.out.println("1L");}
                if(snakeHeadX==0&& snakeHeadY==0){
              if (lastHeading==DIRECTION_UP){currentHeading=DIRECTION_RIGHT;System.out.println("2r");}else{currentHeading=DIRECTION_DOWN;System.out.println("2d");}}
                       if(snakeHeadX==0&&snakeHeadY==maxY-1){
                           if (lastHeading==DIRECTION_DOWN){currentHeading=DIRECTION_RIGHT;System.out.println("3r");}else{currentHeading=DIRECTION_UP;System.out.println("3u");}}}}
               if(lastHeading==currentHeading){
               for (int i =0;i <cy.length;i++){
                   if(snakeHeadX==0&&snakeHeadY==cy[i]||snakeHeadX==maxX-1&&snakeHeadY==cy[i]){if(i>=maxY/2){currentHeading = DIRECTION_DOWN;}else{currentHeading=DIRECTION_UP;}}
                   if(snakeHeadX==maxX-1&& snakeHeadY==0){if (lastHeading==DIRECTION_UP){currentHeading=DIRECTION_LEFT;System.out.println("4l");}else{currentHeading=DIRECTION_DOWN;System.out.println("2d");}}if(snakeHeadX==maxX-1&&snakeHeadY==maxY-1){if (lastHeading==DIRECTION_UP){currentHeading=DIRECTION_LEFT;}else {currentHeading=DIRECTION_UP;}}}}


        if(snakeHeadX==xx){if (snakeHeadY>yy){currentHeading=DIRECTION_UP;}else{currentHeading=DIRECTION_DOWN;} }
        if(snakeHeadY==yy){if(snakeHeadX>xx){ currentHeading=DIRECTION_LEFT;}else {currentHeading=DIRECTION_RIGHT;}}



        if (currentHeading == DIRECTION_DOWN && lastHeading == DIRECTION_UP) {
            currentHeading = DIRECTION_UP; //keep going the same way
        }
        if (currentHeading == DIRECTION_UP && lastHeading == DIRECTION_DOWN) {
            currentHeading = DIRECTION_DOWN; //keep going the same way
        }
        if (currentHeading == DIRECTION_LEFT && lastHeading == DIRECTION_RIGHT) {
            currentHeading = DIRECTION_RIGHT; //keep going the same way
        }
        if (currentHeading == DIRECTION_RIGHT && lastHeading == DIRECTION_LEFT) {
            currentHeading = DIRECTION_LEFT; //keep going the same way
        }

        //Did you hit the wall, snake?
        //Or eat your tail? Don't move.

        if (hitWall == true || ateTail == true) {
            SnakeGame.cSnake.reset();
            return;
        }

        //Use snakeSquares array, and current heading, to move snake

        //Put a 1 in new snake head square
        //increase all other snake segments by 1
        //set tail to 0 if snake did not just eat
        //Otherwise leave tail as is until snake has grown the correct amount

        //Find the head of the snake - snakeHeadX and snakeHeadY

        //Increase all snake segments by 1
        //All non-zero elements of array represent a snake segment

        for (int x = 0 ; x < maxX ; x++) {
            for (int y = 0 ; y < maxY ; y++){
                if (snakeSquares[x][y] != 0) {
                    snakeSquares[x][y]++;
                }
            }
        }

        //now identify where to add new snake head
        if (currentHeading == DIRECTION_UP) {
            //Subtract 1 from Y coordinate so head is one square up
            snakeHeadY-- ;
        }
        if (currentHeading == DIRECTION_DOWN) {
            //Add 1 to Y coordinate so head is 1 square down
            snakeHeadY++ ;
        }
        if (currentHeading == DIRECTION_LEFT) {
            //Subtract 1 from X coordinate so head is 1 square to the left
            snakeHeadX -- ;
        }
        if (currentHeading == DIRECTION_RIGHT) {
            //Add 1 to X coordinate so head is 1 square to the right
            snakeHeadX ++ ;
        }

        //Does this make snake hit the wall?
        if (snakeHeadX >= maxX || snakeHeadX < 0 || snakeHeadY >= maxY || snakeHeadY < 0 ) {
            if(snakeHeadX>=maxX-1){snakeHeadX=0;}
            if (snakeHeadX<0){snakeHeadX=maxX-1;}
            if (snakeHeadY>=maxY-1){snakeHeadY=0;}
            if (snakeHeadY<0){snakeHeadY=maxY-1;}
        }

        //Does this make the snake eat its tail?

        if (snakeSquares[snakeHeadX][snakeHeadY] != 0) {

            ateTail = true;
            Score.score+=2;
            SnakeGame.cSnake.reset();
            return;
        }

        //Otherwise, game is still on. Add new head
        snakeSquares[snakeHeadX][snakeHeadY] = 1;

        //If snake did not just eat, then remove tail segment
        //to keep snake the same length.
        //find highest number, which should now be the same as snakeSize+1, and set to 0

        if (justAteMustGrowThisMuch == 0) {
            for (int x = 0 ; x < maxX ; x++) {
                for (int y = 0 ; y < maxY ; y++){
                    if (snakeSquares[x][y] == snakeSize+1) {
                        snakeSquares[x][y] = 0;
                    }
                }
            }
        }
        else {
            //Snake has just eaten. leave tail as is.  Decrease justAte... variable by 1.
            justAteMustGrowThisMuch -- ;
            snakeSize ++;
        }

        lastHeading = currentHeading; //Update last confirmed heading
        wonGame();

    }

    protected boolean didHitWall(){
        return hitWall;

    }

    protected boolean didEatTail(){
        return ateTail;
    }

    public boolean isSnakeSegment(int kibbleX, int kibbleY) {
        if (snakeSquares[kibbleX][kibbleY] == 0) {
            return false;
        }
        return true;
    }



    public String toString(){
        String textsnake = "";
        //This looks the wrong way around. Actually need to do it this way or snake is drawn flipped 90 degrees.
        for (int y = 0 ; y < maxY ; y++) {
            for (int x = 0 ; x < maxX ; x++){
                textsnake = textsnake + snakeSquares[x][y];
            }
            textsnake += "\n";
        }
        return textsnake;
    }

    public boolean wonGame() {

        //If all of the squares have snake segments in, the snake has eaten so much kibble
        //that it has filled the screen. Win!

        //But if we get here, the snake has filled the screen. win!
        if (Score.score==-10){
        SnakeGame.setGameStage(SnakeGame.CGAME_WON);

        return true;}else { return false;}
    }

    public void reset() {
        hitWall = false;
        ateTail = false;
        fillSnakeSquaresWithZeros();
        createStartSnake();

    }


    public boolean didEatKibble(Kibble kibble) {

        xx=kibble.getKibbleX();
        yy=kibble.getKibbleY();

        if (kibble.getKibbleX() == snakeHeadX && kibble.getKibbleY() == snakeHeadY){
            justAteMustGrowThisMuch += growthIncrement;
            return true;
        }
        return false;
    }
}
