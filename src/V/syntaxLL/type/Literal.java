package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VBoolean;
import V.runtime.type.VFloat;
import V.runtime.type.VInt;
import V.runtime.type.VString;

/**
 * 
 * @author Vea - Eapchenר�ñ�ǩ - �����޸��뱣����ѡ�� ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class Literal extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			if (units[index].type == VLexUnit.FLOAT) {
				if (-1 == units[index].data.indexOf(".")) {
					if (calcEnable) {
						VInt int1 = VInt.get(units[index].data);
						this.result = int1;
					}
					return index + 1;
				}
				if (calcEnable) {
					VFloat foFloat = VFloat.get(units[index].data);
					this.result = foFloat;
				}
				return index + 1;
			}
			if (units[index].type == VLexUnit.STRING) {
				if (calcEnable) {
					VString string = VString.get(units[index].data);
					this.result = string;
				}
				return index + 1;
			}
			if (units[index].type == VLexUnit.IDENTIFIER
					&& (units[index].data.equals("true") || units[index].data
							.equals("false"))) {
				if (calcEnable) {
					VBoolean b = VBoolean.get(units[index].data);
					this.result = b;
				}
				// System.out.println(b);
				return index + 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
