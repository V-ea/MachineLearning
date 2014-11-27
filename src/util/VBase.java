package util;

import data.VTrainSet;
import data.VTrainSet.Row;

public abstract interface VBase
{
  public abstract boolean CheckAllRight(VTrainSet.Row paramRow)
    throws Exception;
}

/* Location:           E:\EAMath.jar
 * Qualified Name:     util.VBase
 * JD-Core Version:    0.6.0
 */