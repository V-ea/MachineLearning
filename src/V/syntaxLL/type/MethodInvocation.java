package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class MethodInvocation extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(new Id(), index, env);
			index =Want(VLexUnit.LEFTX, null, index, env);
			if(units[index].type!=VLexUnit.RIGHTX) 
				index =Want(new ArgumentList(), index, env);//OPT
			index =Want(VLexUnit.RIGHTX, null, index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
