 package data;
 
 import java.util.Arrays;
 
 public class VTrainSet
 {
   private String[] ColumnName = null;
 
   private Row[] Value = null;
 
   public String[] getColumnName() { return this.ColumnName; }
 
   public void setColumnName(String[] columnName) {
     this.ColumnName = columnName;
   }
   public Row[] getValue() {
     return this.Value;
   }
   public void setValue(Row[] value) {
     this.Value = value;
   }
 
   public class Row
   {
     private Object[] Array = null;
 
     public Row() {
     }
 
     public Row(Object[] array) {
       this.Array = array;
     }
 
     public Object[] getArray() {
       return this.Array;
     }
 
     public void setArray(Object[] array)
     {
       this.Array = Arrays.copyOf(array, array.length);
     }
   }
 }

/* Location:           E:\EAMath.jar
 * Qualified Name:     data.VTrainSet
 * JD-Core Version:    0.6.0
 */