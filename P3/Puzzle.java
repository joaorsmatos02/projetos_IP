/**
* The objetcs in this class represent a puzzle of a WordSearch game, composed of a
* board and an array of words hidden in it
*
* Compile - javac Puzzle.java
*
* @author - Daniel Luis fc56362
* @author - Joao Matos fc56292
*/

public class Puzzle{

  private char[][] board;
  private String[] hiddenWords;


  /**
  * Checks if a word is hidden in any line, column or diagonal in board
  *
  * @requires board to be a matrix && word != null
  *
  * @param board the board
  * @param word the word to be found
  *
  * @return true if the word is hidden in board or false if the word is not hidden in board
  */

  public static boolean isHidden(char[][] board, String word){
    boolean hidden = false;
    StringBuilder sb = new StringBuilder();

    StringBuilder reverseSb = new StringBuilder();
    reverseSb.append(word);
    reverseSb = reverseSb.reverse();
    String wordReverse = reverseSb.toString();


    int i = 0;
    while(i < board.length && hidden == false){
      for(int j = 0; j < board[0].length; j++){
        sb.append(board[i][j]);
        String str = sb.toString();
        if(str.contains(word) || str.contains(wordReverse)){
          hidden = true;
        }
      }
      i++;
    }
    sb.delete(0, sb.length());


    int k = 0;

    while(k < board[0].length && hidden == false){
      for(int l = 0; l < board.length; l++){
        sb.append(board[l][k]);
        String str1 = sb.toString();
        if(str1.contains(word) || str1.contains(wordReverse)){
          hidden = true;
        }
      }
      k++;
    }

    sb.delete(0, sb.length());


  for(int m = 0; m < board.length; m++){
      for(int n = 0; n < board[0].length; n++){
        for(int o = 0; o+n < board[0].length && o+m < board.length; o++){
          sb.append(board[m+o][n+o]);
          String str2 = sb.toString();
          if(str2.contains(word) || str2.contains(wordReverse)){
            hidden = true;
          }
        }
      }
    }

    sb.delete(0, sb.length());

    for(int p = 0; p < board.length; p++){
      for(int q = board[0].length - 1; q >= 0 ; q--){
        for(int r = 0; p+r < board.length  && q-r >= 0 ; r++){
          sb.append(board[p+r][q-r]);
          String str3 = sb.toString();
          if(str3.contains(word) || str3.contains(wordReverse)){
            hidden = true;
          }
        }
      }
    }
    return hidden;
  }

  /**
  * Checks if there is at least one hidden word, that there are no
  * duplicate hidden words and that every word is at least one character long
  * and is hidden in board
  *
  * @requires board to be a matrix && hiddenWords != null
  *
  * @param board the board
  * @param hiddenWords the array of hidden words
  *
  *
  * @return true if hiddenWords.lenght > 0, all words are different, have at least
  * one character and are hidden in board or false if at least one of the conditions is not met
  */

  public static boolean definesPuzzle(char[][] board, String[] hiddenWords){
    boolean validLen = hiddenWords.length > 0;
    boolean noRepeatedWords = true;
    boolean hidden = true;
    boolean atLeastOneChar = true;
    for(int i = 0; i < hiddenWords.length && i+1 < hiddenWords.length; i++){
      if(hiddenWords[i].length() < 1 || !isHidden(board, hiddenWords[i])){
        atLeastOneChar = false;
        hidden = false;
      }
      if(hiddenWords[i] == hiddenWords[i+1]){
        noRepeatedWords = false;
      }
    }
    boolean isPuzzle = validLen && noRepeatedWords && hidden && atLeastOneChar;
    return isPuzzle;
  }

  /**
  * Builds the puzzle in which the game is going to be played
  *
  * @param board the board in which the game is going to be played
  * @param hiddenWords the array of words hidden in {@code board}
  *
  * @requires {@code definesPuzzle(board, hiddenWords)}
  * @return the puzzle built with the given parameters
  */

  public Puzzle(char[][] board, String[] hiddenWords){
      this.board = board;
      this.hiddenWords = hiddenWords;
  }

  /**
  * Gives the number of rows in board
  *
  * @return number of rows in board
  */

  public int rows(){
    return board.length;
  }

  /**
  * Gives the number of columns in board
  *
  * @return number of columns in board
  */

  public int columns(){
    return board[0].length;
  }

  /**
  * Gives the number of hidden words
  *
  * @return number of hidden words
  */

  public int numberHiddenWords(){
    return hiddenWords.length;
  }

  /**
  * Gives a separate copy of board
  *
  * @return new copy of the game board
  */

  public char[][] board(){
    char[][] boardCopy = new char[this.board.length][this.board[0].length];
    for(int i = 0; i < this.board.length; i++){
      for(int j = 0; j < this.board[0].length; j++){
        boardCopy[i][j] = this.board[i][j];
      }
    }
    return boardCopy;
  }

  /**
  * Cives the word hidden in the move position if there is one,
  * otherwise returns null
  *
  * @requires move != null && move.rows()==rows() && move.columns()==columns()
  *
  * @return the word in the move location if there is one or null if there is no word hidden in the move location
  */

 public String getWord(Move move){
   int icol = move.startColumn() - 1;
   int fcol = move.endColumn() - 1;
   int irow = move.startRow() - 1;
   int frow = move.endRow() - 1;
   StringBuilder sb = new StringBuilder();
   char[][] board = this.board;
   String hiddenWord = "";
   String hiddenWordReverse = "";
   String result = null;
   int i = 0;
   int lenrow = (fcol - icol) + 1;
   int lencol = (frow - irow) + 1;
   if(move.direction() == Direction.HORIZONTAL){
     while(i < lenrow){
       sb.append(board[irow][icol + i]);
       i++;
     }
     hiddenWord = sb.toString();
     sb = sb.reverse();
     hiddenWordReverse = sb.toString();
     if(isHidden(board, hiddenWord)){
       for(int o = 0; o < this.hiddenWords.length; o++){
         if(hiddenWord.equals(this.hiddenWords[o])){
          result = hiddenWord;
        }else if(hiddenWordReverse.equals(this.hiddenWords[o])){
          result = hiddenWordReverse;
        }
      }
     }
   }
   int j = 0;
   if(move.direction() == Direction.VERTICAL){
     while(j < lencol){
       sb.append(board[irow + j][icol]);
       j++;
     }
     hiddenWord = sb.toString();
     sb = sb.reverse();
     hiddenWordReverse = sb.toString();
     if(isHidden(board, hiddenWord)){
       for(int p = 0; p < this.hiddenWords.length; p++){
         if(hiddenWord.equals(this.hiddenWords[p])){
          result = hiddenWord;
        }else if(hiddenWordReverse.equals(this.hiddenWords[p])){
          result = hiddenWordReverse;
        }
      }
     }
   }
   int k = 0;
   int l = 0;
   if(move.direction() == Direction.DIAGONAL_RIGHT){
     while(k < lenrow){
       while(l < lencol){
         sb.append(board[irow + k][icol + l]);
         l++;
         k++;
       }
     }
     hiddenWord = sb.toString();
     sb = sb.reverse();
     hiddenWordReverse = sb.toString();
     if(isHidden(board, hiddenWord)){
       for(int q = 0; q < this.hiddenWords.length; q++){
         if(hiddenWord.equals(this.hiddenWords[q])){
          result = hiddenWord;
        }else if(hiddenWordReverse.equals(this.hiddenWords[q])){
          result = hiddenWordReverse;
        }
       }
     }
   }
   int m = 0;
   int n = 0;
   if(move.direction() == Direction.DIAGONAL_LEFT){
     while(m > lenrow){
       while(n > lencol){
         sb.append(board[m][n]);
        n--;
        m--;
       }
     }
     hiddenWord = sb.toString();
     sb = sb.reverse();
     hiddenWordReverse = sb.toString();
     if(isHidden(board, hiddenWord)){
       for(int r = 0; r < this.hiddenWords.length; r++){
         if(hiddenWord.equals(this.hiddenWords[r])){
          result = hiddenWord;
        }else if(hiddenWordReverse.equals(this.hiddenWords[r])){
          result = hiddenWordReverse;
        }
       }
     }
   }
 return result;
}

/**
* Gives the array of hidden words
*
* @return the array of hidden words
*/
public String[] getHiddenWords(){
  return this.hiddenWords;
 }
}
