package util;

import data.VTrainSet;
import data.VTrainSet.Row;

public abstract interface VSimilarity extends VBase
{
  public abstract float Similarity(VTrainSet.Row paramRow1, VTrainSet.Row paramRow2);
}

/* Location:           E:\EAMath.jar
 * Qualified Name:     util.VSimilarity
 * JD-Core Version:    0.6.0
 */