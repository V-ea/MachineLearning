package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class VInit extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			return Want(new Expression(), index, env);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return VSyntaxBase.UNMATCHED;
	}

}
