package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * ����ֵ���ʽ sequence : Identifier BooleanOper <Expression>
 * 							or
 * 						 <BaseType> BooleanOper <Expression>
 * 							or
 * 						<BooleanExpression> ||/&& <BooleanExpression>
 * @author Administrator
 *
 */
public class BooleanExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
