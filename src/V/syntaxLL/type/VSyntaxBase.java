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
	
	
}
