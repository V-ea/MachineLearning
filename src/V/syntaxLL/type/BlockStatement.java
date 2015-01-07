package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class BlockStatement extends VSyntaxBase {//δƥ��ɹ�����ζ��BlockStatementsΪ��

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		VSyntaxBase v=new LocalVariableDeclarationStatement();
		
		if(v.First(units[index]))
		{
			try {
				return Want(v, index, env);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		v=new Statement();
		if(v.First(units[index]))
		{
			try {
				return Want(v, index, env);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return VSyntaxBase.UNMATCHED;
			}
		}
		v=new Statement();
		if(v.First(units[index]))
		{
			try {
				return Want(v, index, env);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return VSyntaxBase.UNMATCHED;
			}
		}
		v=new MethodDeclaration();
		if(v.First(units[index]))
		{
			try {
				return Want(v, index, env);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return VSyntaxBase.UNMATCHED;
			}
		}
		//System.out.println("unknown BlockStateMent.");
		return VSyntaxBase.UNMATCHED;//����
	}

}
