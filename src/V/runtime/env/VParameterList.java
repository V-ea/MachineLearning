package V.runtime.env;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import V.runtime.type.VObject;

public class VParameterList {
	private List<String> list=new ArrayList<String>();
	private Map<String, VObject> value=new HashMap<String, VObject>();
	private int count=0;//parameter count
	private int nextParaIndex=0;
	public void Clear()// for next invocation
	{
		value.clear();
		nextParaIndex =0;
	}
	public VObject GetInInvocation(String string)
	{
		if(value.containsKey(string))
		{
			return value.get(string);
		}
		return null;
	}
	public void SetParameterInInvocation(VObject object) throws Exception
	{
		if(nextParaIndex>=count)
		{
			throw new Exception("parameter count > needed."+nextParaIndex);
		}
		value.put(list.get(nextParaIndex++), object);
	}
	public boolean SetParameterInBlock(String string,VObject object) throws Exception
	{
		if(nextParaIndex>=count)
		{
			throw new Exception("parameter count > needed."+nextParaIndex);
		}
		if(list.contains(string))
			value.put(string, object);
		else
			return false;
		return true;
	}
	public void DeclareParameterInDeclaration(String string) throws Exception
	{
		if(list.contains(string))
		{
			throw new Exception("one variable label can just be declared only once.");
		}
		list.add(string);
		count ++;
	}
}
