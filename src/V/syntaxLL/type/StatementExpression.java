package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class StatementExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			if((units[index].type==VLexUnit.IDENTIFIER&&-1==isKeyword(units[index]))
				&&((units[index+1].type==VLexUnit.OPERATOR&&units[index+2].type==VLexUnit.EQUAL)||units[index+1].type==VLexUnit.EQUAL))
			{
				index =Want(new Assignment(), index, env);
				return index;
			}
			index =Want(new UnaryExpression(), index, env);//little change.
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return UNMATCHED;
	}

}
