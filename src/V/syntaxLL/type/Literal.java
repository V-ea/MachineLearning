package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class Literal extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			if(units[index].type==VLexUnit.FLOAT)
			{
				if(-1==units[index].data.indexOf("."))
				{
					return index+1;
				}
				return index+1;
			}
			if(units[index].type==VLexUnit.STRING)
			{
				units[index].data.subSequence(1, units[index].data.length()-1);
				return index+1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
