package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VFloat;
import V.runtime.type.VInt;
import V.runtime.type.VObject;
import V.runtime.type.VString;

public abstract class VSyntaxBase {
	public VObject result=null;
	public abstract int Accept(VLexUnit[] units,int index,VEnv env); 
	public VObject need(VLexUnit unit) {
		if(unit.type==VLexUnit.FLOAT)
		{
			if(unit.data.indexOf(".")!=-1)//FLOAT
				return VFloat.get(unit.data);
			else {						  //INT
				return VInt.get(unit.data);
			}
		}
		if(unit.type==VLexUnit.STRING)	  //STRING
		{
			return VString.get(unit.data);
		}
		return null;
	}
	public boolean pass(VLexUnit unit,int type) {
		if(unit.type==type)
			return true;
		return false;
	}
}
