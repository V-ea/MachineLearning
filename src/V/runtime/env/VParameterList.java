package V.runtime.env;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import V.runtime.type.VObject;

public class VParameterList extends VObject{
	private int count=0;//parameter count
	private int nextParaIndex=0;
	private List<String> list=new ArrayList<String>();
	private Map<String, VObject> value=new HashMap<String, VObject>();
	private String id="";
	public VParameterList() {
		super();
		id=""+(int)(Math.random()*10000);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "VParameterList [count=" + count + ", nextParaIndex="
				+ nextParaIndex + ", list=" + list + ", value=" + value
				+ ", id=" + id + "]";
	}
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
	public VParameterList Clone() {
		// TODO Auto-generated method stub
		VParameterList parameterList=new VParameterList();
		parameterList.count=this.count;
		parameterList.list=this.list;
		//parameterList.value.putAll(this.value;
		parameterList.value=new HashMap<String, VObject>();
		return parameterList;
	}
}
