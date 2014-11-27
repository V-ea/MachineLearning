 package test;
 
 import data.VClass;
 import data.VTrainSet;
 import impl.VK_Means4PointMethod;
 import java.io.File;
 import load.VTxtGridLoader;
 import util.VMethod;
 
 public class TestTxtGridLoader
 {
   public static void main(String[] args)
   {
     VTrainSet tsSet = new VTxtGridLoader().Load(new File("E:\\test.txt"));
     VClass class1 = new VClass();
     class1.valueObjects = new Integer[12];
     for (int j = 0; j < 12; j++)
     {
       class1.valueObjects[j] = VClass.NOT_GIVEN_CLASS;
     }
     VMethod method = new VK_Means4PointMethod(5);
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
 * Qualified Name:     test.TestTxtGridLoader
 * JD-Core Version:    0.6.0
 */