package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * 单个句子 sequence：<Expression> ; <- 注意还有个 ;
 * 					or
 * 					<IfBlock>
 * 					or
 * 					<WhileBlock>
 * @author Administrator
 *
 */
public class Sentence extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
