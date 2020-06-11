import java.util.*; 
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;
import java.lang.Character;

public class StringCalc{

  //  public String input= "4,2,1";

    public static List<Integer> negativeNumber= new ArrayList<>();
    public static void main(final String[] args){
        
        final int total= getInputString("//[***][%%]\n1001***2%%3");
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
        final int val= extractIntegers.stream().map(num->Integer.parseInt(num.trim())).filter(num-> num<=1000).reduce((total, order)-> add(total,order)).get();
        if(negativeNumber != null && negativeNumber.size() > 0){
          throw new ArithmeticException("negatives not allowed " + negativeNumber.toString());
        }else{
          return val;
        }
        
      }
      return 0;
    }


    public static Map<String ,String> getDelimiter(String inputString){
     // JSONObject outputObj=new JSONObject();
      final Map<String ,String> outputs=new HashMap<>();
      String defaultDelimiters=",";
      String defaultString=inputString;
      if(inputString.startsWith("//")){
        int firstOccN= inputString.indexOf("\n");
        final String delimiter= inputString.substring(0, firstOccN);
        defaultDelimiters= defaultDelimiters.concat(extractOnlyDelimiter(delimiter));
        final String transformedString= inputString.substring(firstOccN+1, inputString.length());
        defaultString= transformedString;
      }
      outputs.put("delimiter","["+defaultDelimiters+"]+");
      outputs.put("inputString", defaultString);
      return outputs;
    }


    public static String extractOnlyDelimiter(String delimiterString){
        String delimiters ="";
      if(delimiterString.contains("[")){
        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(delimiterString);
        while(m.find()) {
          String escapesChars="";
          System.out.println(m.group(1).toCharArray());
          List<char[]>ch1=Arrays.asList(m.group(1).toCharArray());
          for (int i = 0;i < m.group(1).length(); i++){
            escapesChars+="\\\\"+m.group(1).charAt(i);
          }
         
          // 
          // dlList.forEach(action->{
          //   escapesChars+="\\"+action;
          // });
          
          //dlList.stream().map(eachChar->);

          System.out.println("escaped "+escapesChars);
          delimiters= delimiters.concat("|"+"["+escapesChars+"]");
        }
      
      }
      return delimiters;
    }
    //[,|\\*\\*\\*]+

    //simple String calculator with a method signature:
    public static int getInputString(final String numbers){
      if(numbers.length() > 0){
         //patternMatching(numbers);
        final Map<String,String> inputs=getDelimiter(numbers);
        System.out.println(inputs);
      //  Pattern p = Pattern.compile("\\[(.*?)\\]");
        String pattern= inputs.get("delimiter");
        final List<String> extractIntegers= Arrays.asList(inputs.get("inputString").split(pattern));

        System.out.println("Before sum "+ extractIntegers);
        final int total= findSum(extractIntegers);
        return total;
      }
      return 0;
    }


}