package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class Statement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			if(units[index].data.equals("if"))
			{
				index =Want(new IfThenElseStatement(), index, env);
				return index;
			}
			if(units[index].data.equals("while"))
			{
				index =Want(new WhileStatement(), index, env);
				return index;
			}
			if(units[index].data.equals("for"))
			{
				index =Want(new ForStatement(), index, env);
				return index;
			}
			index =Want(new StatementWithoutSubStatement(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return -1;
	}

}
