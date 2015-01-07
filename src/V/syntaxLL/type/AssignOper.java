package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class AssignOper extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			switch (units[index].type) {
			case VLexUnit.EQUAL:
				index =Want(VLexUnit.EQUAL, null, index, env);
				break;

			default:
				index=Want(VLexUnit.OPERATOR,null,index, env);
				index=Want(VLexUnit.EQUAL,null, index, env);
				break;
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return VSyntaxBase.UNMATCHED;
	}

}
