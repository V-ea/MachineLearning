 package impl;
 
 import data.VClass;
 import data.VTrainSet;
 import data.VTrainSet.Row;
 import exception.VNotSameSizeException;
 import java.util.Arrays;
 import util.VMethod;
 
 public abstract class VFeatureSameSizeMethod
   implements VMethod
 {
   protected VTrainSet data = null;
   protected VClass class1 = null;
   private int columnSize = -1;
   private int rowSize = 0;
 
   public int getColumnSize() {
     return this.columnSize;
   }
 
   public int getRowSize() {
     return this.rowSize;
   }
 
   public int Accept(VTrainSet trainSet, VClass class1)
     throws Exception
   {
     this.data = trainSet;
     this.class1 = class1;
     int cs = -1;
     for (int i1 = 0; i1 < trainSet.getValue().length; i1++) {
       if (i1 == 0) {
         cs = trainSet.getValue()[i1].getArray().length;
       } else {
         int cs2 = trainSet.getValue()[i1]
           .getArray().length;
         if (cs2 != cs) {
           throw new VNotSameSizeException(
             "The Rows are not in the Same size.");
         }
       }
     }
     int trainSetLen = trainSet.getValue().length;
     int classLen = class1.valueObjects.length;
     if (classLen != trainSetLen)
       throw new VNotSameSizeException(
         "TrainSet and Class are not int the Same size.");
     this.columnSize = cs;
     this.rowSize = trainSetLen;
 
     return 0;
   }
 
   public int Append(VTrainSet trainSet, VClass class1)
     throws Exception
   {
     int cs = -1;
     for (int i1 = 0; i1 < trainSet.getValue().length; i1++) {
       if (i1 == 0) {
         cs = trainSet.getValue()[i1].getArray().length;
       } else {
         int cs2 = trainSet.getValue()[i1].getArray().length;
         if ((cs2 != cs) || (this.columnSize != cs)) {
           throw new VNotSameSizeException(
             "The Rows are not in the Same size.");
         }
       }
     }
     int trainSetLen = trainSet.getValue().length;
     int classLen = class1.valueObjects.length;
     if (classLen != trainSetLen)
       throw new VNotSameSizeException(
         "TrainSet and Class are not int the Same size.");
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
 * Qualified Name:     impl.VFeatureSameSizeMethod
 * JD-Core Version:    0.6.0
 */