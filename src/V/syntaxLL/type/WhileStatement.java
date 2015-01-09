package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class WhileStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(VLexUnit.IDENTIFIER, new String[]{"while"}, index, env);
			index =Want(VLexUnit.LEFTX, null, index, env);
			index =Want(new Expression(), index, env);
			index =Want(VLexUnit.RIGHTX,null, index, env);
			index =Want(new Statement(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
