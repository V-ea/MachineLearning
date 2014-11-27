package util;

import data.VTrainSet;
import data.VTrainSet.Row;

public abstract interface VHyphothesis extends VBase
{
  public abstract Object H(VTrainSet.Row paramRow);
}

/* Location:           E:\EAMath.jar
 * Qualified Name:     util.VHyphothesis
 * JD-Core Version:    0.6.0
 */