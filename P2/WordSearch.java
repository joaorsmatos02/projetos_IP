import java.util.Scanner;

/**
* The WordSearch class is a classic game of word search. Upon execution, a
* board with seemingly random letters appears with several hidden words in it.
* The player must find them all in order to win.
*
* Compile - javac WordSearch.java
* Run - java WordSearch Puzzle.txt
*
* @author - Daniel Luis fc56362
* @author - Joao Matos fc56292
*/
public class WordSearch{
  public static void main(String[] args){
    PuzzleReader reader = new PuzzleReader(args[0]);
    char[][] board = reader.getPuzzle();
    String[] hiddenWords = reader.getHiddenWords();

    int rows = 20;
    int cols = 15;

    Scanner sc = new Scanner(System.in);
    int remainingWords = hiddenWords.length;
    int count = 0;
    String wordsFound = "";


    if(isValidGame(board, hiddenWords)){
      printPuzzle(board, hiddenWords);
      System.out.println();
      do{
        int[] move = readMove(sc, rows, cols);
        String foundWord = findWord(board, move, hiddenWords);
        if(contains(hiddenWords, foundWord)){
          wordsFound = wordsFound.concat(foundWord) + " ";
          System.out.println("Found words: " + wordsFound);
          remainingWords--;
          count++;
        }
        System.out.println("Hidden words: " + remainingWords);
        System.out.println();
      }while(remainingWords > 0);

      System.out.println("Good job. All hidden words were found!");

    }else{
      System.out.println("The game cannot start due to an error in the board or the given hidden words.");
    }
  }

  /**
  * Checks if any of the hidden words was found in the move made by the player.
  *
  * @param board - The board.
  * @param move - The move made by the player.
  * @param hiddenWords - The array of words hidden in the board.
  * @requires {@code board} is a matrix && {@code hiddenWords != null}
  *
  * @return - The found word if it is contained in the hiddenWords array or
  * null if it is not contained in hiddenWords.
  */
  public static String findWord(char[][] board, int[] move, String[] hiddenWords){
    String word = "";
    int len = hiddenWords.length;
    StringBuilder sb = new StringBuilder("");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder reverse = new StringBuilder();
    String[] hiddenWordsReverse = new String[4];


    for(int l = 0; l < len; l++){
      reverse.append(hiddenWords[l]);
      reverse = reverse.reverse();
      String reverseWords = reverse.toString();
      hiddenWordsReverse[l] = reverseWords;
      reverse.delete(0, reverse.length());
    }

    if(move[1] == move[3]){
      for(int i = move[0]; i <= move[2]; i++){
        sb.append(board[i][move[1]]);
        word = sb.toString();
        for(int k = 0; k < len; k++){
          if(word.equals(hiddenWords[k])){
            i = move[2] + 1;
            k = len;
          }else if(word.equals(hiddenWordsReverse[k])){
            sb.delete(0, sb.length());
            sb.append(word);
            sb = sb.reverse();
            word = sb.toString();
            i = move[2] + 1;
            k = len;
          }
        }
      }
    }

    if(move[0] == move[2]){
      for(int m = move[1]; m <= move[3]; m++){
        sb1.append(board[move[0]][m]);
        word = sb1.toString();
        for(int n = 0; n < len; n++){
          if(word.equals(hiddenWords[n])){
            m = move[3] + 1;
            n = len;
          }else if(word.equals(hiddenWordsReverse[n])){
            sb1.delete(0, sb1.length());
            sb1.append(word);
            sb1 = sb1.reverse();
            word = sb1.toString();
            m = move[3] + 1;
            n = len;
           }
         }
       }
     }
    return word;
  }

  /**
  * Checks if all the elements of a given string array are contained in the given
  * matrix.
  *
  * @param board - The board.
  * @param hiddenWords - The array of words hidden in the board.
  * @requires {@code != null} && {@board} is a matrix
  *
  * @return True if all the elements of the array are contained in the matrix
  * or False if at least one element is not contained.
  */
  public static boolean isValidGame(char[] [] board, String[] hiddenWords){
    boolean result = true;
    int len = hiddenWords.length;
    for(int i = 0; i < len; i++){
      if(!isHidden(board, hiddenWords[i])){
        result = false;
        i = len +1;
      }
    }
    return result;
  }


  /**
  * Checks if a given word is hidden in the given matrix.
  * @param board - The matrix.
  * @param word - The word.
  * @requires - {@board} is a matrix && {@code word != null}
  *
  * @return True if the word is hidden in the matrix or False if
  * the word is not hidden in the matrix
  */
  public static boolean isHidden(char[][] board, String word){
    boolean hidden = false;
    StringBuilder hsb = new StringBuilder();
    StringBuilder hsb2 = new StringBuilder();
    StringBuilder sb = new StringBuilder();
    sb.append(word);
    sb = sb.reverse();
    String wordReverse = sb.toString();
    for(int i = 0; i < 20; i++){
      for(int j = 0; j < 15; j++){
        hsb.append(board[i][j]);
        String str = hsb.toString();
        if(str.contains(word) || str.contains(wordReverse)){
          hidden = true;
          i = 20;
          j = 15;
        }
      }
    for(int k = 0; k < 15; k++){
      for(int l = 0; l < 20; l++){
        hsb2.append(board[l][k]);
        String str1 = hsb2.toString();
        if(str1.contains(word) || str1.contains(wordReverse)){
          hidden = true;
          k = 15;
          l = 20;
        }
      }
    }
  }
 return hidden;
}

  /**
  * Prints an array of characters along with the number of words hidden in it.
  *
  * @param board - The array we want to print
  * @param hiddenWords - An array that contains the words hidden in the given array
  * @requires {@code board} is a matrix && {@code hiddenWords != null}
  *
  */
  public static void printPuzzle(char[][] board, String[] hiddenWords){
    int numWords = hiddenWords.length;
    System.out.println("   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15");

    System.out.print("1  ");
    for(int i = 0; i < 20; i++){
      for(int j = 0; j < 15; j++){
        System.out.print(board[i][j] +"   ");
      }
      System.out.println();
      if((i + 2) <= 9){
        System.out.print(i + 2 + "  ");
      }else if((i + 2) <= 20){
        System.out.print(i + 2 + " ");
      }
    }
    System.out.println();
    System.out.println("Hidden words: " + hiddenWords.length);
  }
  /**
  * Checks if a move is valid in a range of rows x columns of a certain board.
  *
  * @param move - Your move
  * @param rows - The number of rows in the board
  * @param columns - The number of columns in the boards
  * @requires {@code move != null}
  *
  * @return Returns true if the move is valid in the given range or returns false
  * if the move is invalid in the given range.
  */
  public static boolean isValidMove(int[] move, int rows, int columns){
    int len = move.length;
    boolean movesRange = move[0] <= 20 && move[0] >= 0 && move[2] <= 20 && move[2] >= 0 && move[1] <= 15 && move[1] >= 0 && move[3] <= 15 && move[3] >= 0;
    boolean sameRowOrCol = move[0] == move[2] || move[1] == move[3];
    boolean rightToLeft = move[0] <= move[2];
    boolean upDown = move[1] <= move[3];
    boolean valid = len == 4 && movesRange && sameRowOrCol && rightToLeft && upDown;

    return valid;
  }
  /**
  * Reads an array to be used as a move in the game.
  * Elements of the array are separated by spaces and are submited
  * by pressing the Enter key. If the array is not within the range of rows and
  * columns and/or does note meet the requirements to be considered valid, the
  * function will print an error message and ask for another input until a valid
  * input is given.
  *
  * @param sc - The scanner that will read user input.
  * @param rows - Number of rows of the playing board.
  * @param columns - Number of columns of the playing board.
  * @requires {@code sc != null} && rows > 0 && columns > 0
  *
  * @return - Returns the array given by the user.
  */
  public static int[] readMove(Scanner sc, int rows, int columns){
    System.out.print("Give your move: ");
    int r1 = 0;
    int c1 = 0;
    int r2 = 0;
    int c2 = 0;
    int[] move = new int[4];
    do{
      r1 = sc.nextInt();
      c1 = sc.nextInt();
      r2 = sc.nextInt();
      c2 = sc.nextInt();
      move[0] = r1 - 1;
      move[1] = c1 - 1;
      move[2] = r2 - 1;
      move[3] = c2 - 1;
      if(!isValidMove(move, rows, columns)){
        System.out.println("Your move is invalid.");
        System.out.println();
        System.out.print("Give your move: ");
      }
    }while(!isValidMove(move, rows, columns));

    return move;

  }
 /**
 * Check if a string is an element of an array.
 *
 * @param hiddenWords - The array.
 * @param word - The string.
 * @requires - {@code hiddenWords != null} && {@code word != null}
 *
 * @return - True if the word is contained in the array or False if the word
 * is not contained in the array.
 */
  public static boolean contains(String[] hiddenWords, String word){
    boolean contains = false;
    int len = hiddenWords.length;
    for(int i = 0; i < len; i++){
      if(word.equals(hiddenWords[i])){
        contains = true;
        i = len;
      }
    }
    return contains;
  }
}
