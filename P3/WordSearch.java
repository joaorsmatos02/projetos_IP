import java.lang.System;

/**
* The objects in this class represent a classic game of word search that contains
* a score, a time limit and a counter for the words that have been found.
*
* Compile - javac WordSearch.java
* Run - java WordSearch Puzzle.txt
*
* @author - Daniel Luis fc56362
* @author - Joao Matos fc56292
*/

public class WordSearch{

  private long startTime = System.currentTimeMillis();
  private long t;
  private int meanTime;
  private int maxTime;
  private Puzzle puzzle;
  private int numHiddenWords;
  private int numberFoundWords;
  private int score;
  private int wordPoints;
  private String[] hiddenWords;
  private String[] foundWords;

  /**
  * Builds the game according to the puzzle and time limit given.
  *
  * @requires puzzle != null && durationInSeconds > 0 && durationInSeconds/puzzle.numberHiddenWords()>5
  *
  * @param puzzle the puzzle
  * @param durationInSeconds the time limit of the game
  *
  */

  public WordSearch(Puzzle puzzle, int durationInSeconds){
    this.t = (System.currentTimeMillis() - this.startTime)/1000;
    this.puzzle = puzzle;
    this.maxTime = durationInSeconds;
    this.meanTime = durationInSeconds/puzzle.numberHiddenWords();
    this.numHiddenWords = puzzle.numberHiddenWords();
    this.wordPoints = (puzzle.rows()*puzzle.columns())/10;
    this.hiddenWords = puzzle.getHiddenWords();
    foundWords = new String[numHiddenWords];
    for(int j = 0; j < numHiddenWords; j++){
      foundWords[j] = "";
    }
  }

  /**
  * Returns the playing puzzle
  *
  * @return palying puzzle
  */

  public Puzzle puzzle(){
    return this.puzzle;
  }

  /**
  * Returns the time limit of the game
  *
  * @return time limit of the game
  */

  public int duration(){
    return this.maxTime;
  }

  /**
  * Returns the number of words that have been found so far
  *
  * @return the number of found words so far
  */

  public int howManyFoundWords(){
    return this.numberFoundWords;
  }

  /**
  * Returns all the words that have been found so far
  *
  * @return an array containing all the found words so far
  */
  public String[] foundWords(){
    return this.foundWords;
  }

  /**
  * Returns the current score
  *
  * @return the current score
  */

  public int score(){
    return this.score;
  }

  /**
  * Returns wether or not the game is over
  *
  * @return true if the game has finished or false if the game has not finished
  */

  public boolean isFinished(){
    long timeElapsed = (System.currentTimeMillis() - this.startTime)/1000;
    return timeElapsed-3 >= maxTime || this.numHiddenWords == 0;
  }

  /**
  *  If the game is not finished yet, registers the current move and indicates
  *  if a hidden word has been found, if the word is found it adds to the score
  *  according to the time that it took to find that word. If the time is up it
  *  finishes the match. It will also end the game if all words have been found
  *
  * @requires move != null && move.rows()==puzzle().rows() && move.columns()==puzzle().columns() && !isFinished()
  *
  * @param move the latest move done by the player
  *
  * @return true if a word has been found on the latest move or false if no words were found
  */

  public boolean play(Move move){
     this.t = ((System.currentTimeMillis() - this.startTime)/1000 - t);
     boolean bool = false;
     String word = puzzle.getWord(move);
     if(word != null){
     for(int i = 0; i < this.hiddenWords.length; i++){
       if(word.equals(this.hiddenWords[i]) && !((word.equals(this.foundWords[i])))){
         bool = true;
         if(this.t >= this.meanTime){
           this.score += this.wordPoints;
           numberFoundWords++;
           numHiddenWords--;
           this.foundWords[numberFoundWords - 1] = this.hiddenWords[i];
         }else if(this.t < this.meanTime){
           this.score += (1 + this.meanTime - this.t)*this.wordPoints;
           numberFoundWords++;
           numHiddenWords--;
           this.foundWords[numberFoundWords - 1] = this.hiddenWords[i];
         }
       }
     }
   }
     return bool;
    }

   /**
   * Gives a textual representation of the current state of the match
   *
   * @return textual representation of the current state of the match
   */

    public String toString(){
      long timeLeft = this.maxTime - (System.currentTimeMillis() - this.startTime)/1000;
      String status = "Score: " + this.score + "\n" + "Hidden words: " + this.numHiddenWords + "\n" + "Found Words: " + this.numberFoundWords + "\n" + "Is finished: " + isFinished() + "\n" + "Time left: " + timeLeft;
      return status;
    }
   }
