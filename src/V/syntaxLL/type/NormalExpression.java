package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * 计算方面的表达式 sequence:<Expression> +/-/* // /^/%/&/#/<Expression>
 * @author Administrator
 *
 */
public class NormalExpression extends VSyntaxBase{

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
