package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;

/**
 * 
 * @author Vea - Eapchenר�ñ�ǩ - �����޸��뱣����ѡ�� ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class AdditiveExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = new MultiplicativeExpression(), v1 = new AddE_prime();
			index = Want(v, index, env);
			if (calcEnable)
			{
				v1.result = v.result.Clone();
				
			}
			index = Want(v1, index, env);
			if (calcEnable)
				this.result = v1.result.Clone();
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

	class AddE_prime extends VSyntaxBase {

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if (units[index].data.equals("+")
						|| units[index].data.equals("-")) {
					String operString = units[index].data;
					index++;
					VObject object = null;
					VSyntaxBase v1 = new MultiplicativeExpression();
					VSyntaxBase v2 = new AddE_prime();
					index = Want(v1, index, env);
					if (calcEnable) {
						if (operString.equals("+"))
							v2.result = VObject.plus(this.result, v1.result);
						else {
							v2.result = VObject.minus(this.result, v1.result);
						}
					}
					index = Want(v2, index, env);
					if (calcEnable) {
						this.result = v2.result.Clone();
					}
					//System.out.println(this.result + ":");
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
