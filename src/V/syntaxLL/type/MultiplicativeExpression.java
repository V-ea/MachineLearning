package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class MultiplicativeExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(new UnaryExpression(), index, env);
			index =Want(new MultiE_prime(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}
	class MultiE_prime extends VSyntaxBase
	{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if(units[index].data.equals("*")||
						units[index].data.equals("/")||
						units[index].data.equals("%")||
						units[index].data.equals("^"))
				{
					index ++;
					index = Want(new UnaryExpression(), index, env);
					index =Want(new MultiE_prime(), index, env);
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
