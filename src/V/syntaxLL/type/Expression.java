package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class Expression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v=null;
			index = Want(v=new AssignmentExpression(), index, env);
			this.result =v.result;
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return VSyntaxBase.UNMATCHED;
	}

}
