package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class ForStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(VLexUnit.IDENTIFIER, new String[]{"for"}, index, env);
			index =Want(VLexUnit.LEFTX, null, index, env);
			if(units[index].type!=VLexUnit.END)
				index =Want(new ForInit(), index, env);
			index =Want(VLexUnit.END,null, index, env);
			if(units[index].type!=VLexUnit.END)
				index =Want(new Expression(), index, env);
			index =Want(VLexUnit.END,null, index, env);
			if(units[index].type!=VLexUnit.RIGHTX)
				index =Want(new ForUpdate(), index, env);
			index =Want(VLexUnit.RIGHTX,null, index, env);
			index =Want(new Statement(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
