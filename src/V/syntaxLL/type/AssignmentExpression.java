package V.syntaxLL.type;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class AssignmentExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v=new Assignment();
			if((units[index].type==VLexUnit.IDENTIFIER&&isKeyword(units[index])==-1)
					&&(units[index].type==VLexUnit.EQUAL||units[index].type==VLexUnit.OPERATOR))
			{
				return Want(v, index, env);
			}
			else {
				return Want(new ConditionalExpression(), index, env);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return VSyntaxBase.UNMATCHED;
	}

}
