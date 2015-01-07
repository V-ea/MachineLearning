package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class AdditiveExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index=Want(new MultiplicativeExpression(), index, env);
			index =Want(new AddE_prime(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}
	class AddE_prime extends VSyntaxBase
	{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if(units[index].data.equals("+")
						||units[index].data.equals("-"))
				{
					index++;
					index =Want(new MultiplicativeExpression(), index, env);
					index =Want(new AddE_prime(), index, env);
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
