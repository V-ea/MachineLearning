package V.runtime.type;

public abstract class VObject {
	public abstract String toString();
	public static VObject multi(VObject result2, VObject reObject) throws Exception
	{
		if(result2 instanceof VFloat&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat2.value*=vFloat.value;
			return  vFloat;
		}
		if(result2 instanceof VFloat&&reObject instanceof VInt)
		{
			VInt vFloat=(VInt)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat2.value*=vFloat.value;
			return  vFloat2;
		}
		if(result2 instanceof VInt&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VInt vFloat2=(VInt)result2;
			vFloat2.value*=vFloat.value;
			return  vFloat;
		}
		if(result2 instanceof VInt&&reObject instanceof VInt)
		{
			VInt vInt=(VInt)reObject;
			VInt vInt2=(VInt)result2;
			vInt2.value*=vInt.value;
			return  vInt;
		}
		throw new Exception("multi:unexcepted VObject.");
	}
	public static VObject plus(VObject result2, VObject reObject) throws Exception {
		// TODO Auto-generated method stub
		if(result2 instanceof VFloat&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat.value+=vFloat2.value;
			return  vFloat;
		}
		if(result2 instanceof VFloat&&reObject instanceof VInt)
		{
			VInt vFloat=(VInt)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat2.value+=vFloat.value;
			return  vFloat2;
		}
		if(result2 instanceof VInt&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VInt vFloat2=(VInt)result2;
			vFloat.value+=vFloat2.value;
			return  vFloat;
		}
		if(result2 instanceof VInt&&reObject instanceof VInt)
		{
			VInt vInt=(VInt)reObject;
			VInt vInt2=(VInt)result2;
			vInt.value+=vInt2.value;
			return  vInt;
		}
		if(result2 instanceof VString|| reObject instanceof VString)
		{
			String aString= result2.toString()+reObject.toString();
			VString vString=new VString();
			vString.value = aString;
			return vString;
		}
		throw new Exception("plus:unexcepted VObject.");
	}
	public static VObject minus(VObject result2, VObject reObject) throws Exception {
		// TODO Auto-generated method stub
		if(result2 instanceof VFloat&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat2.value-=vFloat.value;
			return  vFloat;
		}
		if(result2 instanceof VFloat&&reObject instanceof VInt)
		{
			VInt vFloat=(VInt)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat2.value-=vFloat.value;
			return  vFloat2;
		}
		if(result2 instanceof VInt&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VInt vFloat2=(VInt)result2;
			vFloat.value-=vFloat2.value;
			return  vFloat;
		}
		if(result2 instanceof VInt&&reObject instanceof VInt)
		{
			VInt vInt=(VInt)reObject;
			VInt vInt2=(VInt)result2;
			vInt.value-=vInt2.value;
			return  vInt;
		}
		throw new Exception("minus:unexcepted VObject.");
	}
	public static VObject divide(VObject result2, VObject reObject) throws Exception {
		// TODO Auto-generated method stub
		if(result2 instanceof VFloat&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat2.value/=vFloat.value;
			return  vFloat;
		}
		if(result2 instanceof VFloat&&reObject instanceof VInt)
		{
			VInt vFloat=(VInt)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat2.value/=vFloat.value;
			return  vFloat2;
		}
		if(result2 instanceof VInt&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VInt vFloat2=(VInt)result2;
			vFloat2.value/=vFloat.value;
			return  vFloat;
		}
		if(result2 instanceof VInt&&reObject instanceof VInt)
		{
			VInt vInt=(VInt)reObject;
			VInt vInt2=(VInt)result2;
			vInt2.value/=vInt.value;
			return  vInt;
		}
		throw new Exception("divide:unexcepted VObject.");
	}
	public static VObject mod(VObject result2, VObject reObject) throws Exception {
		// TODO Auto-generated method stub
		if(result2 instanceof VFloat&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat2.value%=vFloat.value;
			return  vFloat;
		}
		if(result2 instanceof VFloat&&reObject instanceof VInt)
		{
			VInt vFloat=(VInt)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat2.value%=vFloat.value;
			return  vFloat2;
		}
		if(result2 instanceof VInt&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VInt vFloat2=(VInt)result2;
			vFloat2.value%=vFloat.value;
			return  vFloat;
		}
		if(result2 instanceof VInt&&reObject instanceof VInt)
		{
			VInt vInt=(VInt)reObject;
			VInt vInt2=(VInt)result2;
			vInt2.value%=vInt.value;
			return  vInt;
		}
		throw new Exception("mod:unexcepted VObject.");
	}
	public static VObject exp(VObject result2, VObject reObject) throws Exception {
		// TODO Auto-generated method stub
		if(result2 instanceof VFloat&&reObject instanceof VInt)
		{
			VInt vFloat=(VInt)reObject;
			VFloat vFloat2=(VFloat)result2;
			vFloat2.value = (float) Math.pow(vFloat2.value,vFloat.value);
			return  vFloat2;
		}
		if(result2 instanceof VInt&&reObject instanceof VInt)
		{
			VInt vInt=(VInt)reObject;
			VInt vInt2=(VInt)result2;
			VFloat vFloat=new VFloat();
			vFloat.value=(float) Math.pow(vInt2.value,vInt.value);
			
			return  vFloat;
		}
		throw new Exception("exp:unexcepted VObject.");
	}
	public static int compare(VObject result2, VObject reObject) throws Exception {
		// TODO Auto-generated method stub
		if(result2 instanceof VFloat&&reObject instanceof VFloat)
		{
			VFloat vFloat=(VFloat)reObject;
			VFloat vFloat2=(VFloat)result2;
			if(vFloat2.value>vFloat.value)
				return 1;
			else if(vFloat2.value==vFloat.value)
				return 0;
			return  -1;
		}
		if(result2 instanceof VInt&&reObject instanceof VInt)
		{
			VInt vFloat=(VInt)reObject;
			VInt vFloat2=(VInt)result2;
			if(vFloat2.value>vFloat.value)
				return 1;
			else if(vFloat2.value==vFloat.value)
				return 0;
			return  -1;
		}
		if(result2 instanceof VBoolean&&reObject instanceof VBoolean)
		{
			VBoolean vFloat=(VBoolean)reObject;
			VBoolean vFloat2=(VBoolean)result2;
			if(vFloat2.value==vFloat.value)
				return 0;
			return  -1;
		}
		throw new Exception("compare VObject.need type.(int,int),(float,float),(boolean,boolean).");
	}
}
