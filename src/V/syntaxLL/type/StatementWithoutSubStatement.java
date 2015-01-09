package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class StatementWithoutSubStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			if(units[index].type==VLexUnit.LEFTB)
			{
				index= Want(new Block(), index, env);
				return index;
			}
			if(units[index].type==VLexUnit.END)
			{
				index= Want(new EmptyStatement(), index, env);
				return index;
			}
			if(units[index].data.equals("break"))
			{
				index= Want(new BreakStatement(), index, env);
				return index;
			}
			else 
			if(units[index].data.equals("continue"))
			{
				index= Want(new ContinueStatement(), index, env);
				return index;
			}
			//Switch needed ,next version I will add it here . eap chen.
			index = Want(new ExpressionStatement(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
