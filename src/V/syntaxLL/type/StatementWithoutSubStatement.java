package V.syntaxLL.type;

import sun.security.krb5.internal.EncAPRepPart;
import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class StatementWithoutSubStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			if(units[index].type==VLexUnit.LEFTB)
			{
			
				VEnv env2=new VEnv();
				env2.setParentEnv(env);
				index= Want(new Block(), index, env2);
				return index;
			}
			if(units[index].type==VLexUnit.END)
			{
				index= Want(new EmptyStatement(), index, env);
				return index;
			}
			if(units[index].data.equals("break"))
			{
				index= Want(new BreakStatement(), index, env);
				return index;
			}
			else 
			if(units[index].data.equals("continue"))
			{
				index= Want(new ContinueStatement(), index, env);
				return index;
			}
			if(units[index].data.equals("return"))
			{
				index= Want(new ReturnStatement(), index, env);
				return index;
			}
			//Switch needed ,next version I will add it here . eap chen.
			index = Want(new ExpressionStatement(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
