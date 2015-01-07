package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;

public abstract class VSyntaxBase {
	public static VLexUnit[] units=null;
	public static final int UNMATCHED = -1;
	public VObject result=null;
	protected VLexUnit[] firstSet=null;
	public abstract int Accept(VLexUnit[] units,int index,VEnv env); 
	public boolean First(VLexUnit unit)
	{
		if(firstSet==null)//doesn't matter
			return true;
		for(int i=0;i<firstSet.length;i++)
			if(unit.type==firstSet[i].type)
			{
				if (firstSet[i].data.equals("")) {
					return true;
				}
				else {
					if(unit.data.equals(firstSet[i].data))
						return true;
				}
			}
		return false;
	}
	public static int Want(VSyntaxBase vsb,int index,VEnv env) throws Exception
	{
		if(index>=units.length)
		{
			if(vsb instanceof BlockStatement)
				return VSyntaxBase.UNMATCHED;
			System.exit(-1);
		}
		int index1 = vsb.Accept(units, index, env);
		if(index1==VSyntaxBase.UNMATCHED)
		{
			throw new Exception("error:unmatched"+units[index]);				
		}
		else {
			index=index1;
			return index;
		}
	}
	public static int Want(int type,String[] list,int index,VEnv env) throws Exception
	{
		if(index>=units.length)
		{
			System.exit(-1);
		}
		if (units[index].type == type)
			if(list!=null)
				for(int i=0;i<list.length;i++)
					if(units[index].data.equals(list[i]))
						return index+1;
		throw new Exception("error:unmatched"+units[index]);
	}
	public static String[] keywords=null;
	static{
		keywords=new String[]{"if","var","function","else","while","for"};
	}
	public static int isKeyword(VLexUnit unit)
	{
		for(int i=0;i<keywords.length;i++)
			if(keywords[i].equals(unit.data))
			{
				return i;
			}
		return -1;
	}
}
