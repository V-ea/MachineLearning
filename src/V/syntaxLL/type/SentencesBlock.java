package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * ÷¥––æ‰–Ú¡–:sequence: <Null>
 * 						or
 * 						<Sentence> <SentencesBlock>
 * @author Administrator
 *
 */
public class SentencesBlock extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		try {
			while(units[index].type!=VLexUnit.RIGHTB)//NULL 
			{
				VSyntaxBase vsb2 = new Sentence();
				index = vsb2.Accept(units, index, env);
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return 0;
	}

}
