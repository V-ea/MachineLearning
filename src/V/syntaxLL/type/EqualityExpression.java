package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VBoolean;
import V.runtime.type.VObject;

/**
 * 
 * @author Vea - Eapchenר�ñ�ǩ - �����޸��뱣����ѡ�� ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class EqualityExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = null;
			VObject resultObject1 = null;
			index = Want(v = new RelationalExpression(), index, env);
			if (calcEnable)
				resultObject1 = v.result;
			if (units[index].data.equals("=")) {
				index++;
				index = Want(VLexUnit.EQUAL, null, index, env);
				index = Want(v = new RelationalExpression(), index, env);
				if (calcEnable) {
					VBoolean vBoolean = new VBoolean();
					vBoolean.value = true;
					if (0 != VObject.compare(resultObject1, v.result)) {
						vBoolean.value = false;
					}
					this.result = vBoolean;
				}
				return index;
			} else if (units[index].data.equals("!")) {
				index++;
				index = Want(VLexUnit.EQUAL, null, index, env);
				index = Want(v = new RelationalExpression(), index, env);
				if (calcEnable) {
					VBoolean vBoolean = new VBoolean();
					vBoolean.value = true;
					if (0 == VObject.compare(resultObject1, v.result)) {
						vBoolean.value = false;
					}
					this.result = vBoolean;
				}
				return index;
			}
			if (calcEnable)
				this.result = resultObject1;
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
