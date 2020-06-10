import java.util.*; 
import java.util.Arrays;
import java.util.List;

public class StringCalc{

    public String input= "4,2,1";

    public static void main(String[] args){
        
        int total= getInputString("//;\n1;2;3;4");
        System.out.println(total);

    }
    
   
    //simple String calculator with a method signature:
    public static int getInputString(String numbers){
      if(numbers.length() > 0){
        Map<String,String> inputs=getDelimiter(numbers);
        System.out.println(inputs);
        List<String> extractIntegers= Arrays.asList(inputs.get("inputString").split(inputs.get("delimiter")));
        System.out.println(extractIntegers);
        int val= extractIntegers.stream().map(num->Integer.parseInt(num.trim())).reduce((total, order)-> total+order).get();
        return val;
      }
      return 0;
    }
    

    public static Map<String ,String> getDelimiter(String inputString){
     // JSONObject outputObj=new JSONObject();
      Map<String ,String> outputs=new HashMap<>();
      String defaultDelimiters=",\n";
      String defaultString=inputString;
      if(inputString.startsWith("//")){
        String delimiter= inputString.substring(0, 4);
        defaultDelimiters= defaultDelimiters.concat(delimiter);
        String transformedString= inputString.substring(4, inputString.length());
        defaultString= transformedString;
      }
      outputs.put("delimiter","["+defaultDelimiters+"]");
      outputs.put("inputString", defaultString);
      return outputs;
    }


}