package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class Block extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			if(units[index].type==VLexUnit.LEFTB)
			{
				VSyntaxBase vsb2=new SentencesBlock();
				index =vsb2.Accept(units, index+1, env);
				if(units[index].type==VLexUnit.RIGHTB)
				{
					this.result = vsb2.result;
					return index+1;
				}
			}
				
			else
				throw new Exception("a block start with {");
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}
