package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class VariableDeclarators extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index = Want(new VariableDeclarator(), index, env);
			return Want(new VDS_prime(), index, env);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return VSyntaxBase.UNMATCHED;
	}

	class VDS_prime extends VSyntaxBase {

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				
				if (units[index].type == VLexUnit.COMMA) {
					index=Want(VLexUnit.COMMA, null, index, env);
				} else {
					return index;
				}
				index = Want(new VariableDeclarator(), index, env);
				index = Want(new VDS_prime(), index, env);
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return VSyntaxBase.UNMATCHED;
			}

		}

	}
}
