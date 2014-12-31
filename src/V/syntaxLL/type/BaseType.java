package V.syntaxLL.type;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import sun.nio.cs.ext.ISCII91;
import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VFloat;
import V.runtime.type.VInt;
import V.runtime.type.VString;

public class BaseType extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		switch (units[index].type) {
		case VLexUnit.STRING:
			VString vString=new VString();
			vString.value=units[index].data.substring(1, units[index].data.length()-1);
			return index+1;
		case VLexUnit.FLOAT:
			if(units[index].data.indexOf(".")==-1)
			{
				//System.out.println("int");
				VInt int1=VInt.get(units[index].data);
				//System.out.println(int1.value);
				this.result=int1;
				return index+1;
			}
			VFloat vFloat=VFloat.get(units[index].data);
			this.result=vFloat;
			return index+1;

		default:
			return -1;
		}
	}

}
