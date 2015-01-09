package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class IfThenElseStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(VLexUnit.IDENTIFIER, new String[]{"if"}, index, env);
			index =Want(VLexUnit.LEFTX, null, index, env);
			index =Want(new Expression(), index, env);
			index =Want(VLexUnit.RIGHTX,null, index, env);
			index =Want(new Statement(), index, env);
			if(units[index].data.equals("else"))
			{
				index =Want(new Statement(), index+1, env);
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
