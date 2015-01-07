package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class VariableDeclarator extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		int io=0;
		try {
			index = Want(new VariableDeclaratorId(), index, env);
			io =index;
			try {
				index =Want(VLexUnit.EQUAL, null, index, env);
			} catch (Exception e) {
				// TODO: handle exception
				return io;
			}
			index = Want(new VInit(), index, env);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
