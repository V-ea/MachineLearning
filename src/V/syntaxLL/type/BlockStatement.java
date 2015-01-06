package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class BlockStatement extends VSyntaxBase {

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
				e.printStackTrace();
			}
		}
		v=new MethodDeclaration();
		if(v.First(units[index]))
		{
			try {
				return Want(v, index, env);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("unknown error."+units[index]);
		System.exit(-1);
		return 0;
	}

}
