package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class Id extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index = Want(VLexUnit.IDENTIFIER, null, index, env);
			if(-1!=isKeyword(units[index-1]))
				return VSyntaxBase.UNMATCHED;
			return index;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return VSyntaxBase.UNMATCHED;
	}

}
