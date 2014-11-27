 package similarity;
 
 import data.VTrainSet;
import data.VTrainSet.Row;
 import exception.VUsageException;
 import util.VSimilarity;
 
 public class VEuclideanMetricSimilarity
   implements VSimilarity
 {
   private int Length = 0;
 
   public boolean CheckAllRight(VTrainSet.Row row) throws Exception
   {
     if (this.Length == 0) {
       this.Length = row.getArray().length;
     }
     else if (this.Length != row.getArray().length)
     {
       throw new VUsageException();
     }
 
     return true;
   }
 
   public float Similarity(VTrainSet.Row row1, VTrainSet.Row row2)
   {
     float total = 0.0F;
     for (int i = 0; i < this.Length; i++)
     {
       total = total + (((Float)row2.getArray()[i]).floatValue() - ((Float)row1.getArray()[i]).floatValue()) * (
         ((Float)row2.getArray()[i]).floatValue() - ((Float)row1.getArray()[i]).floatValue());
     }
     return (float)Math.sqrt(total);
   }
 }

/* Location:           E:\EAMath.jar
 * Qualified Name:     similarity.VEuclideanMetricSimilarity
 * JD-Core Version:    0.6.0
 */