 package test;
 
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class PatternDemo1
 {
   public static void main(String[] args)
   {
//     method2(new String[] { "1", "12345", "12" }, "[0-9]");
     method1();
   }
 
   private static void method2(String[] a, String regex) {
     Pattern p = Pattern.compile(regex);
     for (int i = 0; i < a.length; i++)
       System.out.println(a[i] + "," + p.matcher(a[i]).matches());
   }
 
   private static void method1() {
//     String str = "2011-11-12";
//     String pat = "\\d{4}-\\d{2}-\\d{2}";
//     System.out.println(Pattern.compile(pat).matcher(str).matches());
// 
//     String str1 = "a1b22c333d4444e55555f";
// 
//     String[] str1Arr = Pattern.compile("\\d+").split(str1);
//     System.out.println(Arrays.toString(str1Arr));
// 
     Matcher matcher=Pattern.compile("([^=]*)(==|!=|>|>=|<=|<)([^=]*)").matcher("a <= b");
     if(matcher.find()){
    	 System.out.println(matcher.group(1));
    	 System.out.println(matcher.group(2));
    	 //System.out.println(matcher.group(3));
     }
   }
 }

/* Location:           E:\EAMath.jar
 * Qualified Name:     test.PatternDemo1
 * JD-Core Version:    0.6.0
 */