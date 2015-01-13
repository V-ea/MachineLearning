package V.syntaxLL.type;

import com.sun.org.apache.xpath.internal.operations.Minus;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VInt;
import V.runtime.type.VObject;
import V.runtime.type.VString;

/**
 * 
 * @author Vea - Eapchenר�ñ�ǩ - �����޸��뱣����ѡ�� ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class PreDecrementExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = null;
			index = Want(VLexUnit.OPERATOR, new String[] { "-" }, index, env);
			index = Want(VLexUnit.OPERATOR, new String[] { "-" }, index, env);
			// index =Want(new UnaryExpression(), index, env);
			index = Want(v = new Id(), index, env);
			if (calcEnable) {
				this.result = env.getVar(((VString) v.result).value);
				VInt one = new VInt();
				one.value = 1;
				this.result = VObject.minus(this.result, one);
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
