package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * ±Ì¥Ô Ω£∫sequence: <BaseType>
 * 					or
 * 					 <FunctionInvokeExpression>
 * 					or
 * 					 <NormalExpression>
 * 					or
 * 					 <BooleanExpression>
 * 					or
 * 					 <Assignment>
 *					or
 * 					( <Expression> )
 * @author Administrator
 *
 */
public class Expression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
