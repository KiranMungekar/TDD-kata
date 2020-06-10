import java.util.*; 
import java.util.Arrays;
import java.util.List;

public class StringCalc{

  //  public String input= "4,2,1";

    public static List<Integer> negativeNumber= new ArrayList<>();
    public static void main(final String[] args){
        
        final int total= getInputString("//;\n1;2;3;4");
        System.out.println(total);

    }
   
    
    public static int add(int total, int order){
      if(order > 0){
        return total+order;
      }else{
        negativeNumber.add(order);
        return total;
      }
    }


    public static int findSum(final List<String> extractIntegers) throws ArithmeticException{
      if(extractIntegers.size() > 0){
        final int val= extractIntegers.stream().map(num->Integer.parseInt(num.trim())).reduce((total, order)-> add(total,order)).get();
        if(negativeNumber != null && negativeNumber.size() > 0){
          throw new ArithmeticException("negatives not allowed " + negativeNumber.toString());
        }else{
          return val;
        }
        
      }
      return 0;
    }


    public static Map<String ,String> getDelimiter(final String inputString){
     // JSONObject outputObj=new JSONObject();
      final Map<String ,String> outputs=new HashMap<>();
      String defaultDelimiters=",\n";
      String defaultString=inputString;
      if(inputString.startsWith("//")){
        final String delimiter= inputString.substring(0, 4);
        defaultDelimiters= defaultDelimiters.concat(delimiter);
        final String transformedString= inputString.substring(4, inputString.length());
        defaultString= transformedString;
      }
      outputs.put("delimiter","["+defaultDelimiters+"]");
      outputs.put("inputString", defaultString);
      return outputs;
    }


    //simple String calculator with a method signature:
    public static int getInputString(final String numbers){
      if(numbers.length() > 0){
        final Map<String,String> inputs=getDelimiter(numbers);
        System.out.println(inputs);

        final List<String> extractIntegers= Arrays.asList(inputs.get("inputString").split(inputs.get("delimiter")));
      //   System.out.println(extractIntegers);
        final int total= findSum(extractIntegers);
        return total;
      }
      return 0;
    }


}