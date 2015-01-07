package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class AssignmentExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v=new Assignment();
			VSyntaxBase v2=new Id();
			VSyntaxBase v3 =new AssignOper();
			if(v2.First(units[index])&&v3.First(units[index+1]))
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
