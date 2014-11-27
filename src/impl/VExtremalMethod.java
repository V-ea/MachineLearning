 package impl;
 
 import data.VTrainSet;
import data.VTrainSet.Row;
 import math.VEasyFunctor;
 
 public abstract class VExtremalMethod extends VFeatureSameSizeMethod
 {
   private VEasyFunctor calcFunctor = new VEasyFunctor();
   private VEasyFunctor partialFunctor = new VEasyFunctor();
 
   public int Calculate()
   {
     return 0;
   }
 
   public int PreCalculate()
   {
     return 0;
   }
 
   public VEasyFunctor getCalcFunctor() {
     return this.calcFunctor;
   }
 
   public void setCalcFunctor(VEasyFunctor calcFunctor) {
     this.calcFunctor = calcFunctor;
   }
 
   public VEasyFunctor getPartialFunctor() {
     return this.partialFunctor;
   }
 
   public void setPartialFunctor(VEasyFunctor partialFunctor) {
     this.partialFunctor = partialFunctor;
   }
 
   public int PostCalculate()
   {
     return 0;
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
     return false;
   }
 }

/* Location:           E:\EAMath.jar
 * Qualified Name:     impl.VExtremalMethod
 * JD-Core Version:    0.6.0
 */