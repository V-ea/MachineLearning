package V.syntaxLL.type;

import V.lex.VLex;
import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class PrimaryNoNewArray extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			if(units[index].data.equals("("))
			{
				index = Want(VLexUnit.LEFTX,null, index, env);
				index =Want(new Expression(), index, env);
				index = Want(VLexUnit.RIGHTX,null, index, env);
				return index ;
			}
			if(units[index].type==VLexUnit.IDENTIFIER&&isKeyword(units[index])==-1)
			{
				index =Want(new MethodInvocation(), index, env);
				return index;
			}
			index = Want(new Literal(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
