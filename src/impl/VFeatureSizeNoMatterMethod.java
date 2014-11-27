 package impl;
 
 import data.VClass;
 import data.VTrainSet;
 import data.VTrainSet.Row;
 import exception.VNotSameSizeException;
 import java.util.Arrays;
 import util.VMethod;
 
 public abstract class VFeatureSizeNoMatterMethod
   implements VMethod
 {
   protected VTrainSet data = null;
   protected VClass class1 = null;
   private int rowSize = 0;
 
   public static Integer NOT_GIVEN_CLASS = Integer.valueOf(-1);
 
   public int getRowSize() {
     return this.rowSize;
   }
 
   public void setRowSize(int rowSize) {
     this.rowSize = rowSize;
   }
 
   public int Accept(VTrainSet trainSet, VClass class1)
     throws Exception
   {
     this.data = trainSet;
     this.class1 = class1;
     if (this.data.getValue().length != this.class1.valueObjects.length)
     {
       throw new VNotSameSizeException(
         "The Rows are not in the Same size.");
     }
     this.rowSize = this.data.getValue().length;
     return 0;
   }
 
   public int Append(VTrainSet trainSet, VClass class1)
     throws Exception
   {
     int trainSetLen = trainSet.getValue().length;
     if (this.data.getValue().length != this.class1.valueObjects.length)
     {
       throw new VNotSameSizeException(
         "The Rows are not in the Same size.");
     }
     this.rowSize += trainSetLen;
     VTrainSet.Row[] rows = this.data.getValue();
     VTrainSet.Row[] newRows = (VTrainSet.Row[])Arrays.copyOf(rows, this.rowSize);
     System.arraycopy(trainSet.getValue(), 0, newRows, rows.length, 
       trainSetLen);
 
     this.data.setValue(newRows);
     Object[] classes = this.class1.valueObjects;
     Object[] newClasses = Arrays.copyOf(classes, this.rowSize);
     System.arraycopy(class1.valueObjects, 0, newClasses, classes.length, 
       trainSetLen);
     this.class1.valueObjects = newClasses;
     return 0;
   }
 }

/* Location:           E:\EAMath.jar
 * Qualified Name:     impl.VFeatureSizeNoMatterMethod
 * JD-Core Version:    0.6.0
 */