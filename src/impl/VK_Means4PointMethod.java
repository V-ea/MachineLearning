 package impl;
 
 import data.VTrainSet;
 import data.VTrainSet.Row;
 import java.io.PrintStream;
 import java.util.Arrays;
 import similarity.VEuclideanMetricSimilarity;
 import util.VSimilarity;
 
 public class VK_Means4PointMethod extends VFeatureSameSizeMethod
 {
   private int k = 0;
   private int[] center = null;
   private int[] surr = null;
   private boolean isEnd = true;
   private VSimilarity similarity = new VEuclideanMetricSimilarity();
 
   public int getK() {
     return this.k;
   }
 
   public void setK(int k) {
     this.k = k;
   }
 
   private void CalculateOnce() {
     this.isEnd = true;
     for (int ii = 0; ii < getRowSize(); ii++) {
       float smallest = 0.0F;
       int smallestindex = 0;
       VTrainSet.Row[] rows = this.data.getValue();
       smallest = this.similarity.Similarity(rows[ii], rows[this.center[0]]);
       for (int jj = 1; jj < this.k; jj++) {
         float tm = this.similarity.Similarity(rows[ii], 
           rows[this.center[jj]]);
         if (tm < smallest) {
           smallest = tm;
           smallestindex = jj;
         }
       }
 
       if (this.surr[ii] != smallestindex)
       {
         this.isEnd = false;
       }
       this.surr[ii] = smallestindex;
     }
   }
 
   private int[] CalculateCenter() {
     float[][] csum = new float[this.k][getColumnSize()];
     int[] count = new int[this.k];
     for (int u = 0; u < this.k; u++)
       count[u] = 0;
     for (int l = 0; l < getRowSize(); l++) {
       for (int m = 0; m < getColumnSize(); m++) {
         csum[this.surr[l]][m] += ((Float)this.data.getValue()[l].getArray()[m]).floatValue();
       }
 
       count[this.surr[l]] += 1;
     }
     int[] smallestindex = new int[this.k];
     float[] smallest = new float[this.k];
     for (int h = 0; h < this.k; h++)
       smallestindex[h] = 0;
     for (int l = 0; l < getRowSize(); l++) {
       if (smallestindex[this.surr[l]] == 0) {
         smallestindex[this.surr[l]] = l;
         smallest[this.surr[l]] = 0.0F;
         for (int m = 0; m < getColumnSize(); m++) {
           float a = ((Float)this.data.getValue()[l].getArray()[m]).floatValue();
           float b = csum[this.surr[l]][m] / count[this.surr[l]];
           smallest[this.surr[l]] += (a - b) * (a - b);
         }
       }
       else {
         float ys = 0.0F;
         for (int m = 0; m < getColumnSize(); m++) {
           float a = ((Float)this.data.getValue()[l].getArray()[m]).floatValue();
           float b = csum[this.surr[l]][m] / count[this.surr[l]];
           ys += (a - b) * (a - b);
         }
 
         if (ys < smallest[this.surr[l]]) {
           smallestindex[this.surr[l]] = l;
           smallest[this.surr[l]] = ys;
         }
       }
 
     }
 
     return smallestindex;
   }
 
   private boolean CalculateEndPoint(int[] newCenterIndex) {
     if (!this.isEnd) return false;
     for (int kk = 0; kk < this.k; kk++) {
       if (newCenterIndex[kk] != this.center[kk]) {
         this.center = Arrays.copyOf(newCenterIndex, this.k);
         return false;
       }
     }
 
     return true;
   }
 
   public int Calculate()
   {
     CalculateOnce();
     int[] newCenterIndex = CalculateCenter();
     while (!CalculateEndPoint(newCenterIndex)) {
       CalculateOnce();
       newCenterIndex = CalculateCenter();
     }
     return 0;
   }
 
   public int PreCalculate()
   {
     this.center = new int[this.k];
     int[] used = new int[this.k];
     for (int kf = 0; kf < this.k; kf++) used[kf] = 0;
     int len = getRowSize();
     for (int kjj = 0; kjj < this.k; kjj++) {
       if (kjj < len)
       {
         int l = (int)(Math.random() * this.k);
         while (used[l] == 1)
         {
           l = (int)(Math.random() * this.k);
         }
         used[l] = 1;
         this.center[kjj] = l;
       } else {
         this.center[kjj] = 0;
       }
     }
 
     this.surr = new int[getRowSize()];
     try
     {
       CheckAllRight();
     }
     catch (Exception e) {
       e.printStackTrace();
     }
     return 0;
   }
 
   public int PostCalculate()
   {
     System.out.println("center is:");
     for (int j = 0; j < this.k; j++) {
       for (int m = 0; m < getColumnSize(); m++) {
         System.out.print("(" + 
           this.data.getValue()[this.center[j]].getArray()[m] + 
           ")");
       }
       System.out.println();
     }
     return 0;
   }
 
   public VK_Means4PointMethod(int k)
   {
     this.k = k;
   }
 
   public Object Predict(VTrainSet.Row row)
   {
     return null;
   }
 
   public int UnSerialize()
   {
     return 0;
   }
 
   public int Serialize()
   {
     return 0;
   }
 
   public boolean CheckAllRight(VTrainSet.Row row)
     throws Exception
   {
     return this.similarity.CheckAllRight(row);
   }
 
   public boolean CheckAllRight() throws Exception {
     for (int j = 0; j < getRowSize(); j++) {
       CheckAllRight(this.data.getValue()[j]);
     }
     return true;
   }
 }

/* Location:           E:\EAMath.jar
 * Qualified Name:     impl.VK_Means4PointMethod
 * JD-Core Version:    0.6.0
 */