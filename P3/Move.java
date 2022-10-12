/**
* The objects in this class represent a valid play in matrixes of a given dimension.
* A play is defined by 2 sets of contiguous coordinates, one being the start position
* and the other being the end position.
*
* Compile - javac Move.java
*
* @author Daniel Luis fc56362
* @author Joao Matos fc56292
*
*/
public class Move{

  private int row1;
  private int row2;
  private int col1;
  private int col2;
  private int rows;
  private int columns;

  /**
  * Checks if a Move is valid within a range of rows x columns and if the start
  * position and end position are contiguous
  *
  * @param row1 - Row of the start position
  * @param col1 - Column of the start position
  * @param row2 - Row of the end position
  * @param col2 - Columns of the end position
  * @param rows - The number of rows on the playing board
  * @param columns - The number of columns on the playing board
  *
  * @ensures - The move is valid
  * @return - True if the move is valid or False if the move is not valid
  *
  */
  public static boolean definesMove(int row1, int col1, int row2, int col2, int rows, int columns){
    boolean rowsInRange = row1 <= rows && row2 <= rows && row1 >= 0 && row2 >= 0;
    boolean colsInRange = col1 <= columns && col2 <= columns && col1 >= 0 && col2 >= 0;
    boolean contiguous = (col1 <= col2 && row1 <= row2);
    boolean samePos = (row1 == row2) && (col1 == col2);
    boolean isMove = rowsInRange && colsInRange && contiguous && !samePos;

    return isMove;
  }

  /**
  * Builds a Move that represents a play in a game of Word Search
  *
  * @param row1 - Row of the start position
  * @param col1 - Column of the start position
  * @param row2 - Row of the end position
  * @param col2 - Columns of the end position
  * @param rows - The number of rows on the playing board
  * @param columns - The number of columns on the playing board
  *
  * @requires definesMove(row1, col1, row2, col2, rows, columns) == true
  */
  public Move(int row1, int col1, int row2, int col2, int rows, int columns){
      this.row1 = row1;
      this.row2 = row2;
      this.col1 = col1;
      this.col2 = col2;
      this.rows = rows;
      this.columns = columns;

  }

  /**
  * Returns the row of the start position of the play
  *
  * @return - The row of the start position of the play
  */
  public int startRow(){
    return this.row1;
  }

  /**
  * Returns the column of the start position of the play
  *
  * @return - The column of the start position of the play
  */
  public int startColumn(){
    return this.col1;
  }

  /**
  * Returns the row of the end position of the play
  *
  * @return - The row of the end position of the play
  */
  public int endRow(){
    return this.row2;
  }

  /**
  * Returns the column of the end position of the play
  *
  * @return - The column of the end position of the play
  */
  public int endColumn(){
    return this.col2;
  }

  /**
  * Returns the direction of the play
  *
  * @return - The direction of the play
  */
  public Direction direction(){
    Direction dir = null;

    if(this.row1 == this.row2 && this.col1 < this.col2){
      dir = Direction.HORIZONTAL;

    }else if(this.col1 == this.col2 && this.row1 < this.row2){
      dir = Direction.VERTICAL;

    }else if(this.col1 <= this.col2 && this.row1 <= this.row2){
      dir = Direction.DIAGONAL_RIGHT;

    }else if(this.col1 >= this.col2 && this.row1 <= this.row2){
      dir = Direction.DIAGONAL_LEFT;
    }

    return dir;
  }

  /**
  * Returns the number of rows on the playing board
  *
  * @return - The number of rows on the playing board
  */
  public int rows(){
    return this.rows;
  }

  /**
  * Returns the number of columns on the playing board
  *
  * @return - The number of columns on the playing board
  */
  public int columns(){
    return this.columns;
  }
}
