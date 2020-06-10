import java.util.Arrays;
import java.util.List;


public class StringCalc{

    public String input= "4,2,1";

    public static void main(String[] args){
        
        int total= getInputString("");
        System.out.println(total);

    }
    
   
    //simple String calculator with a method signature:
    public static int getInputString(String numbers){
      if(numbers.length() > 0){
        List<String> extractIntegers= Arrays.asList(numbers.split(","));
        int val= extractIntegers.stream().map(num->Integer.parseInt(num)).reduce((total, order)-> total+order).get();
        return val;
      }
      return 0;
    }
    


}