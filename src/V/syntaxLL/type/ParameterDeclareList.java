package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * 参数声明序列 sequence:Identifier,<ParameterssList>
 * 					or 
 * 					 Identifier
 * 					or
 * 					<NULL>
 * @author Administrator
 *
 */
public class ParameterDeclareList extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
