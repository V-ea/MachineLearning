package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class AdditiveExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index=Want(new MultiplicativeExpression(), index, env);
			index =Want(new AddE_prime(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}
	class AddE_prime extends VSyntaxBase
	{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if(units[index].data.equals("+")
						||units[index].data.equals("-"))
				{
					index++;
					VSyntaxBase v1=new MultiplicativeExpression();
					VSyntaxBase v2 =new AddE_prime();
					index =Want(v1, index, env);
					index =Want(v2, index, env);
					this.result = VObject.plus(v1.result,v2.result);
					return index;
				}
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}
	}
}
