package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * 参数列表  sequence: Parameter,<ParametersList>
 * 					or 
 * 					 Parameter
 * @author Administrator
 *
 */
public class ParametersList extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
