package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class UnaryExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index = Want(new UE_2(), index, env);
			index =Want(new UE_prime(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}
	class UE_2 extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if(units[index].data.equals("(") //(<Expression>)
						||units[index].type==VLexUnit.FLOAT
						||units[index].type==VLexUnit.STRING //<Literal>
						||(units[index].type==VLexUnit.IDENTIFIER&&-1==isKeyword(units[index])&&units[index+1].data.equals("("))
						||(units[index].type==VLexUnit.IDENTIFIER&&-1!=isKeyword(units[index])&&(units[index].data.equals("true")||units[index].data.equals("false"))))
					//<MethodInvocation>
				{
					index =Want(new Primary(), index, env);
				}
				else if (units[index].data.equals("+")) {
					index =Want(new PreIncrementExpression(), index, env);
				}
				else if (units[index].data.equals("-")) {
					index =Want(new PreDecrementExpression(), index, env);
				}
				else {
					index =Want(new Id(), index, env);
				}
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}
		
	}
	class UE_prime extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if(units[index].data.equals("+")&&units[index+1].data.equals("+"))
				{
					index =Want(VLexUnit.OPERATOR, new String[]{"+"}, index, env);
					index =Want(VLexUnit.OPERATOR, new String[]{"+"}, index, env);
					index =Want(new UE_prime(), index, env);
					return index;
				}
				if(units[index].data.equals("-")&&units[index+1].data.equals("-"))
				{
					index =Want(VLexUnit.OPERATOR, new String[]{"-"}, index, env);
					index =Want(VLexUnit.OPERATOR, new String[]{"-"}, index, env);
					index =Want(new UE_prime(), index, env);
					return index;
				}
				return index;//<NULL>
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}
		
	}
}
