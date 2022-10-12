/**
 * Program that checks for a subsequence within a row and displays a certain sequence given a start position and an end position
 * 
 * Compile: javac NumberSearch.java
 * Run: java NumberSearch
 * 
 * @author Daniel Caetano Luis fc56362
 * @author João Ricardo Silva Matos fc56292
 * 
 */
public class NumberSearch {
    public static void main(String[] args) {
     int numberDigits = 9;
     int row1 = 198745334;
     int row2 = 19874533; 
     int sequence1 = 533;
     int sequence2 = 335;
     int sequence3 = 121;
     int sequence4 = 305;
     hiddenSequence(row1, sequence1, numberDigits);
     hiddenSequence(row1, sequence2, numberDigits);
     hiddenSequence(row1, sequence3, numberDigits);
     hiddenSequence(row2, sequence1, numberDigits);
     hiddenSequence(row1, sequence4, numberDigits);
     hiddenSequence(row2, sequence4, numberDigits);
     System.out.println();
     searchSequence(row1, 6, 8);
     searchSequence(row1, 1, 5);
     searchSequence(row1, 8, 6);
     searchSequence(row1, 6, 12);
     }
    /**
     * Runs the function subsequence if the condition for the function to run is met.
     * @param row - the row
     * @param from - start position
     * @param to - end position
     * @requires isValidRow(row) == true
     */
    public static void searchSequence(int row, int from, int to) {
        if(1 <= from && from <= to && to <= digits(row)){
               System.out.println("The sequence from position " + from + " to " + to + " in row " + row + " is " + subsequence(row, from, to) + ".");
        }else{
               System.out.println("The range from " + from + " to " + to + " is not valid in row " + row + ".");
        }
     
     }
    /**
     * Runs the function isSubsequence if the conditions for the function to run are met.
     * @param row - the row
     * @param sequence - the sequence we want to check wether or not it is contained in row
     * @param numberDigits - the number of digits in row
     */
    public static void hiddenSequence(int row, int sequence, int numberDigits) {
        if(isValidRow(row, numberDigits) && isValidSequence(sequence, numberDigits)) {
            if(isSubsequence(row, sequence) || isSubsequence(row, contrario(sequence))){
                System.out.println("The sequence " + sequence + " is hidden in row " + row + ".");
            }else if(!isSubsequence(row, sequence) && !isSubsequence(row, contrario(sequence))){
                System.out.println("The sequence " + sequence + " is not hidden in row " + row + ".");
            }  
        }else if(!isValidRow(row, numberDigits) && !isValidSequence(sequence, numberDigits)){
            System.out.println("The row " + row + " is not valid. The sequence " + sequence + " is not valid.");
        }else if(isValidRow(row, numberDigits) && !isValidSequence(sequence, numberDigits)){
            System.out.println("The sequence " + sequence + " is not valid.");
        }else if(!isValidRow(row, numberDigits) && isValidSequence(sequence, numberDigits)){
            System.out.println("The row " + row + " is not valid.");
        }
     }
    /**
     * Counts the number of digits in a given number
     * @param num - the number whose digits we want to count
     * @requires num > 0
     * @ensures \result > 0
     * @return the number of digits in num
     */
    public static int digits(int num) {
        int numDigits = 0;
        int base = 1;
        while(num >= base){
            base = base * 10;
            numDigits++;
        }
        return numDigits;
     }
    /**
     * Calculates de reverse of a given number
     * @param num - the number we want to reverse
     * @requires num > 0
     * @ensures \result > 0
     * @return the reverse of num
     */
    public static int contrario(int num){
        int contrario = 0;
        while(num > 0) {
            int digito = num % 10;
            contrario = digito + contrario * 10;
            num = num/10;
        }
      return contrario;
     }
    /**
     * Verifies if a given sequence is within a given row
     * @param num1 - the row
     * @param num2 - the sequence
     * @requieres num1 > 0 && num2 > 0
     * @return true or false
     */
    public static boolean isSubsequence(int num1, int num2) {
        int p = 1;
        while (p <= num2) {
            p *= 10;
        }
        boolean contem = num1 == num2;
        while (!contem && num1 >= num2) {
            contem = num1 % p == num2 || num1 == num2;
            num1 /= 10;
        }
        return contem;
     }
    /**
     * Verifies if a given row is valid
     * @param num - the row we want to verify
     * @param numberDigits - the number of digits in the row
     * @requires num > 0 && numberDigits > 0
     * @return true or false wether num is or isnt a valid row
     */
    public static boolean isValidRow(int num, int numberDigits){
        boolean validRow = num > 0 && digits(num) == numberDigits && entre1e9(num);
        return validRow;
     }
    /**
     * Auxiliary function that verifies if a given number is made up of digits between 1 and 9
     * @param num - the number we want to verify
     * @requires num > 0
     * @ensures each digit of num is between 1 and 9
     * @return true or false
     */
    public static boolean entre1e9(int num){
        int temp = 1;
        for(int i = 1; i <= digits(num); i++){
            if(num%10 != 0){
                num = num/10;
            }else if(num%10 == 0){
                temp = 0;
            }
          }
        
        boolean check = temp == 1;
        return check;
    }   
    /**
     * Verifies if a given sequence is valid given a maximum number of digits
     * @param num - the sequence we want to verify
     * @param numberDigits - the maximum number of digits of the sequence
     * @requires num > 0
     * @return true or false wether num is or isnt a valid sequence
     */
    public static boolean isValidSequence(int num, int numberDigits){
        boolean validSequence = num > 0 && digits(num) <= numberDigits && entre1e9(num);
        return validSequence;
     }
    /**
     * Auxiliary function that calcutares powers
     * @param base - the base of the power
     * @param exp - the exponent of the power
     * @requires base > 0 && exp > 0
     * @ensures \result > 0
     * @return base to the power of exp
     */
    public static int elevado(int base, int exp){
      int resultado =1;
       if(exp == 0) return 1;
       else{
         while (exp != 0){
           resultado *= base;
           --exp;}
         return resultado;
       }
     }
    /**
     * Calculates the sequence of digits in a given row given a start position and stop position
     * @param num - the row
     * @param from - start position
     * @param to - stop position
     * @requires isValidRow(num) && 1 <= from && from <= to && to <= digits(num)
     * @ensures \result > 0
     * @return the sequence between the start position and the end position
     */
    public static int subsequence(int num, int from, int to) {
      int power = 0;
      int sequence = 0;
      for(int i = 1; i <= 9 - to; i++){
          num /= 10;
      }
      for(int i = 1; i <= (to-from)+1; i++){
          sequence += (num%10)*elevado(10,power);
          num /= 10;
          power++;
      }
      return sequence;
     }
}