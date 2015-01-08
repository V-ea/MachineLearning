package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class FormalParameterList extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(new FormalParameter(), index, env);
			index =Want(new FPL_prime(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}
	class FPL_prime extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			if(units[index].type!=VLexUnit.COMMA)
			{
				return index;//<NULL>
			}
			try {
				index =Want(VLexUnit.COMMA, null, index, env);
				index =Want(new FormalParameter(), index, env);
				index =Want(new FPL_prime(), index, env);
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return UNMATCHED;
		}
		
	}
}
