package util;

import data.VClass;
import data.VTrainSet;

public abstract interface VEstimate extends VBase
{
  public abstract float Check(VTrainSet paramVTrainSet, VClass paramVClass, VHyphothesis paramVHyphothesis);
}

/* Location:           E:\EAMath.jar
 * Qualified Name:     util.VEstimate
 * JD-Core Version:    0.6.0
 */