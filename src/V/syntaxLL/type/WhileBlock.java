package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * while—≠ª∑:sequence :while(<BooleanExpression>){<SentencesBlock>}
 * 					or
 * 					 while(<BooleanExpression>)<Sentence>
 * @author Administrator
 *
 */
public class WhileBlock extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
