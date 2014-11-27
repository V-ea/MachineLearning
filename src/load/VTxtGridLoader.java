 package load;
 
 import data.VDataLoader;
 import data.VTrainSet;
 import data.VTrainSet.Row;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 
 public class VTxtGridLoader
   implements VDataLoader<File>
 {
   public VTrainSet Load(File file)
   {
     VTrainSet tSet = new VTrainSet();
     try
     {
       InputStreamReader read = new InputStreamReader(
         new FileInputStream(file), "UTF-8");
       BufferedReader bufferedReader = new BufferedReader(read);
       String lineTxt = null;
       String string = bufferedReader.readLine();
       String[] strsStrings = string.split(" ");
       if (strsStrings.length != 2) {
         throw new Exception("FormatError");
       }
 
       int i = Integer.parseInt(strsStrings[0]);
       int j = Integer.parseInt(strsStrings[1]);
 
       VTrainSet.Row[] rows = new VTrainSet.Row[i];
       for (int n = 0; n < i; n++)
       {
         rows[n] = tSet.new Row();
       }
       int index = 0;
       while (((lineTxt = bufferedReader.readLine()) != null) && (index < i)) {
         System.out.println(lineTxt);
         lineTxt = lineTxt.replace('\t', ' ').replace(',', ' ')
           .replace(';', ' ');
         String[] columnStrings = lineTxt.split(" ");
         if (columnStrings.length != j) {
           throw new Exception("FormatErrorInData");
         }
         Object[] array = new Float[j];
         for (int k = 0; k < j; k++) {
           array[k] = Float.valueOf(Float.parseFloat(columnStrings[k].trim()));
         }
         rows[index].setArray(array);
         index++;
       }
       read.close();
       tSet.setValue(rows);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return tSet;
   }
 
   public static void main(String[] args) {
     new VTxtGridLoader().Load(new File("E:\\1.txt"));
   }
 }

/* Location:           E:\EAMath.jar
 * Qualified Name:     load.VTxtGridLoader
 * JD-Core Version:    0.6.0
 */