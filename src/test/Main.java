 package test;
 
 import data.VClass;
 import data.VTrainSet;
 import data.VTrainSet.Row;
 import impl.VK_Means4PointMethod;
 import util.VMethod;
 
 public class Main
 {
   public static void main(String[] args)
   {
     VTrainSet tsSet = new VTrainSet();
     VTrainSet.Row[] rows = new VTrainSet.Row[9];
     rows[0] = tsSet.new Row(new Float[] { Float.valueOf(1.0F), Float.valueOf(2.0F) });
     rows[1] = tsSet.new Row(new Float[] { Float.valueOf(2.0F), Float.valueOf(3.0F) });
     rows[2] = tsSet.new Row(new Float[] { Float.valueOf(4.0F), Float.valueOf(5.0F) });
     rows[3] = tsSet.new Row(new Float[] { Float.valueOf(3.0F), Float.valueOf(4.0F) });
     rows[4] = tsSet.new Row(new Float[] { Float.valueOf(102.0F), Float.valueOf(103.0F) });
     rows[5] = tsSet.new Row(new Float[] { Float.valueOf(105.0F), Float.valueOf(106.0F) });
     rows[6] = tsSet.new Row(new Float[] { Float.valueOf(128.0F), Float.valueOf(130.0F) });
     rows[7] = tsSet.new Row(new Float[] { Float.valueOf(140.0F), Float.valueOf(150.0F) });
     rows[8] = tsSet.new Row(new Float[] { Float.valueOf(1000.0F), Float.valueOf(1700.0F) });
     VClass class1 = new VClass();
     class1.valueObjects = new Integer[9];
     for (int j = 0; j < 9; j++)
     {
       class1.valueObjects[j] = VClass.NOT_GIVEN_CLASS;
     }
     tsSet.setValue(rows);
     VMethod method = new VK_Means4PointMethod(3);
     try {
       method.Accept(tsSet, class1);
       method.PreCalculate();
       method.Calculate();
       method.PostCalculate();
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 }

/* Location:           E:\EAMath.jar
 * Qualified Name:     test.Main
 * JD-Core Version:    0.6.0
 */