package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class ArgumentList extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase vSyntaxBase=null;
			index =Want(vSyntaxBase=new Expression(), index, env);
			env.parameterList.SetParameterInInvocation(vSyntaxBase.result);
			index =Want(new ArguL_prime(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}
	class ArguL_prime extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if(units[index].type==VLexUnit.COMMA)
				{
					index = Want(VLexUnit.COMMA, null, index, env);
					index =Want(new Expression(), index, env);
					index =Want(new ArguL_prime(), index, env);
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
