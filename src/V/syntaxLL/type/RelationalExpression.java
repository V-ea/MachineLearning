package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class RelationalExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(new ShiftExpression(), index, env);
			index =Want(new RE_prime(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}
	class RE_prime extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if(units[index].type==VLexUnit.BOPER)
				{
					index =Want(VLexUnit.BOPER, new String[]{">=","<=",">","<"},index, env);
					index =Want(new ShiftExpression(), index, env);
					index =Want(new RE_prime(), index, env);
					return index;
				}
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}
		
	}

}
