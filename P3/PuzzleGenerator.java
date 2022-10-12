/**
* The Generator class creates a new puzzle with a randomized board and hidden words in it.
*
* compile - javac PuzzleGenerator.java
*
* @author Joao Matos fc56292
* @author Daniel Luis fc56362
*/

import java.util.Random;
public class PuzzleGenerator{

private char[][] randomBoard;
/**
* Creates a random 20x15 board with 4 hidden words in varying positions.
*
*
* @ensures words are randomly hidden in the board, either vertically or
* horizontally, whithout overlapping
*/

  public PuzzleGenerator(){
    randomBoard = new char [20][15];
    for (int i = 0; i < 20; i++){
      for (int j = 0; j < 15; j++){
         int rand = (int) (Math.random() * 26) + 65;
         randomBoard[i][j] = (char) rand;
      }
    }
   char[] engenharia = {'E', 'N', 'G', 'E', 'N', 'H', 'A', 'R', 'I', 'A'};
   char[] java = {'J', 'A', 'V', 'A'};
   char[] programacao = {'P', 'R', 'O', 'G', 'R', 'A', 'M', 'A', 'C', 'A', 'O'};
   char[] informatica = {'I', 'N', 'F', 'O', 'R', 'M', 'A', 'T', 'I', 'C', 'A'};
   double[] taken = new double[36];
   int  randomCol1 = (int) (Math.random() * 19);
   int  randomLine1 = (int) (Math.random() * 14);

   boolean vertical = ((int)(Math.random()*2)) == 1;
    if(vertical == true && randomCol1 <= 10){
     for(int i = 0; i < engenharia.length; i++){
       randomBoard[randomCol1 + i][randomLine1] = engenharia[i];
       double decimal = randomLine1 * 0.01;
       taken[i] = decimal + randomCol1 + i;
     }
    }
    if(vertical == true && randomCol1 > 10){
      for(int j = 0; j < engenharia.length; j++){
        randomBoard[randomCol1 - j][randomLine1] = engenharia[j];
        double decimal = randomLine1 * 0.01;
        taken[j] = decimal + randomCol1 - j;
      }
    }
    if(vertical == false && randomLine1 <= 5){
     for(int k = 0; k < engenharia.length; k++){
       randomBoard[randomCol1][randomLine1 + k] = engenharia[k];
       double decimal = (randomLine1 + k) * 0.01;
       taken[k] = decimal + randomCol1;
     }
    }
    if(vertical == false && randomLine1 > 5){
      for(int l = 0; l < engenharia.length; l++){
        randomLine1 = (14-((int)Math.random()*4));
        randomBoard[randomCol1][randomLine1 - l] = engenharia[l];
        double decimal = (randomLine1 - l) * 0.01;
        taken[l] = decimal + randomCol1;
      }
    }

   boolean existe = false;
  do{
   int  randomCol2 = (int) (Math.random() * 19);
   int  randomLine2 = (int) (Math.random() * 14);
   existe = false;
   vertical = ((int)(Math.random()*2)) == 1;
    if(vertical == true && randomCol2 <= 16){
      for(int i = 0; i < java.length; i++){
        double decimal = randomLine2 * 0.01;
        taken[i + engenharia.length] = decimal + randomCol2 + i;
        int m = 0;
        while(m < 11 && existe == false){
          int n = 0;
          while(n < taken.length && existe == false){
            if(m != n && existe == false){
            existe = taken[m] == taken [n];}
            n++;
          }
            m++;
        }
       if(existe == false){
       randomBoard[randomCol2 + i][randomLine2] = java[i];}
      }
    }
    if(vertical == true && randomCol2 > 16){
      for(int j = 0; j < java.length; j++){
        double decimal = randomLine2 * 0.01;
        taken[j + engenharia.length] = decimal + randomCol2 - j;
        int m = 0;
        while(m < 11 && existe == false){
          int n = 0;
          while(n < taken.length && existe == false){
            if(m != n && existe == false){
            existe = taken[m] == taken [n];}
            n++;}
            m++;
        }
       if(existe == false){
       randomBoard[randomCol2 - j][randomLine2] = java[j];}
      }
    }
    if(vertical == false && randomLine2 <= 11){
     for(int k = 0; k < java.length; k++){
       double decimal = (randomLine2 + k) * 0.01;
       taken[k + engenharia.length] = decimal + randomCol2;
       int m = 0;
       while(m < 11 && existe == false){
         int n = 0;
         while(n < taken.length && existe == false){
           if(m != n && existe == false){
           existe = taken[m] == taken [n];}
           n++;}
           m++;
       }
      if(existe == false){
      randomBoard[randomCol2][randomLine2 + k] = java[k];}
     }
    }
    if(vertical == false && randomLine2 > 11){
      for(int l = 0; l < java.length; l++){
        randomLine2 = (14-((int)Math.random()*5));
        double decimal = (randomLine2 - l) * 0.01;
        taken[l + engenharia.length] = decimal + randomCol2;
        int m = 0;
        while(m < 11 && existe == false){
          int n = 0;
          while(n < taken.length && existe == false){
            if(m != n && existe == false){
            existe = taken[m] == taken [n];}
            n++;}
            m++;
        }
       if(existe == false){
       randomBoard[randomCol2][randomLine2 - l] = java[l];}
      }
    }
  }while(existe == true);

   do{
   int  randomCol3 = (int) (Math.random() * 19);
   int  randomLine3 = (int) (Math.random() * 14);
   existe = false;
   vertical = ((int)(Math.random()*2)) == 1;
    if(vertical == true && randomCol3 <= 9){
     for(int i = 0; i < (programacao.length); i++){
       double decimal = randomLine3 * 0.01;
       taken[i + engenharia.length + java.length] = decimal + randomCol3 + i;
       int m = 0;
       while(m < 15 && existe == false){
         int n = 0;
         while(n < taken.length && existe == false){
           if(m != n && existe == false){
           existe = taken[m] == taken [n];}
           n++;}
           m++;
       }
      if(existe == false){
      randomBoard[randomCol3 + i][randomLine3] = programacao[i];}
     }
    }
    if(vertical == true && randomCol3 > 9){
      for(int j = 0; j < (programacao.length); j++){
        double decimal = randomLine3 * 0.01;
        taken[j + engenharia.length + java.length] = decimal + randomCol3 - j;
        int m = 0;
        while(m < 15 && existe == false){
          int n = 0;
          while(n < taken.length && existe == false){
            if(m != n && existe == false){
            existe = taken[m] == taken [n];}
            n++;}
            m++;
        }
      if(existe == false){
      randomBoard[randomCol3 - j][randomLine3] = programacao[j];}
      }
    }
    if(vertical == false && randomLine3 <= 4){
     for(int k = 0; k < (programacao.length); k++){
       double decimal = (randomLine3 + k) * 0.01;
       taken[k + engenharia.length + java.length] = decimal + randomCol3;
       int m = 0;
       while(m < 15 && existe == false){
         int n = 0;
         while(n < taken.length && existe == false){
           if(m != n && existe == false){
           existe = taken[m] == taken [n];}
           n++;}
           m++;
       }
      if(existe == false){
      randomBoard[randomCol3][randomLine3 + k] = programacao[k];}
     }
    }
    if(vertical == false && randomLine3 > 4){
      for(int l = 0; l < programacao.length; l++){
        randomLine3 = (14-((int)Math.random()*5));
        double decimal = (randomLine3 - l) * 0.01;
        taken[l + engenharia.length + java.length] = decimal + randomCol3;
        int m = 0;
        while(m < 15 && existe == false){
          int n = 0;
          while(n < taken.length && existe == false){
            if(m != n && existe == false){
            existe = taken[m] == taken [n];}
            n++;}
            m++;
        }
      if(existe == false){
      randomBoard[randomCol3][randomLine3 - l] = programacao[l];}
      }
    }
  }while(existe == true);


   do{
   int  randomCol4 = (int) (Math.random() * 19);
   int  randomLine4 = (int) (Math.random() * 14);
   existe = false;
   vertical = ((int)(Math.random()*2)) == 1;
    if(vertical == true && randomCol4 <= 9){
     for(int i = 0; i < (informatica.length); i++){
       double decimal = randomLine4 * 0.01;
       taken[i + engenharia.length + java.length + programacao.length] = decimal + randomCol4 + i;
       int m = 0;
       while(m < 26 && existe == false){
         int n = 0;
         while(n < taken.length && existe == false){
           if(m != n && existe == false){
           existe = taken[m] == taken [n];}
           n++;}
           m++;
       }
     if(existe == false){
     randomBoard[randomCol4 + i][randomLine4] = informatica[i];}
     }
    }
    if(vertical == true && randomCol4 > 9){
      for(int j = 0; j < (informatica.length); j++){
        double decimal = randomLine4 * 0.01;
        taken[j + engenharia.length + java.length + programacao.length] = decimal + randomCol4 - j;
        int m = 0;
        while(m < 26 && existe == false){
          int n = 0;
          while(n < taken.length && existe == false){
            if(m != n && existe == false){
            existe = taken[m] == taken [n];}
            n++;}
            m++;
        }
      if(existe == false){
        randomBoard[randomCol4 - j][randomLine4] = informatica[j];}
      }
    }
    if(vertical == false && randomLine4 <= 4){
     for(int k = 0; k < (informatica.length); k++){
       double decimal = (randomLine4 + k) * 0.01;
       taken[k + engenharia.length + java.length + programacao.length] = decimal + randomCol4;
       int m = 0;
       while(m < 26 && existe == false){
         int n = 0;
         while(n < taken.length && existe == false){
           if(m != n && existe == false){
           existe = taken[m] == taken [n];}
           n++;}
           m++;
       }
     if(existe == false){
       randomBoard[randomCol4][randomLine4 + k] = informatica[k];}
     }
    }
    if(vertical == false && randomLine4 > 4){
      for(int l = 0; l < (informatica.length); l++){
        randomLine4 = (14-((int)Math.random()*5));
        double decimal = (randomLine4 - l) * 0.01;
        taken[l + engenharia.length + java.length + programacao.length] = decimal + randomCol4;
        int m = 0;
        while(m < 26 && existe == false){
          int n = 0;
          while(n < taken.length && existe == false){
            if(m != n && existe == false){
            existe = taken[m] == taken [n];}
            n++;}
            m++;
        }
      if(existe == false){
        randomBoard[randomCol4][randomLine4 - l] = informatica[l];}
      }
    }
  }while(existe == true);
 }

/**
* Generates a new puzzle.
*
* @return a new puzzle
*/
 public Puzzle nextPuzzle(){
    String[] hiddenWords = {"ENGENHARIA", "INFORMATICA", "JAVA", "PROGRAMACAO"};
    Puzzle puzzle = new Puzzle(randomBoard, hiddenWords);
    return puzzle;
  }
}
