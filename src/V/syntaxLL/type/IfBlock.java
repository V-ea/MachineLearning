package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * Èç¹û¿é£ºsequence: if(<BooleanExpression>){<SentencesBlock>}<ElseBlock>
 * 					or
 * 					 if(<BooleanExpression>)<Sentence><ElseBlock>
 * @author Administrator
 *
 */
public class IfBlock extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		return 0;
	}

}
