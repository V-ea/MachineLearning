package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;
import V.runtime.type.VString;

/**
 * 
 * @author Vea - Eapchenר�ñ�ǩ - �����޸��뱣����ѡ�� ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class VariableDeclarator extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		int io = 0;
		try {
			VSyntaxBase v = null;
			String label = null;
			index = Want(v = new VariableDeclaratorId(), index, env);
			if (calcEnable)
				label = ((VString) v.result).value;
			if (units[index].type != VLexUnit.EQUAL) {
				return index;
			}
			index++;
			index = Want(v = new VInit(), index, env);
			if (calcEnable) {
				VObject object = v.result;
				// System.out.println(object);
				if (env.getDirectlyVariable(label) == null) {
					env.AddVariable(label, object);
					// System.out.println(env.getVar(label));
				} else {
					throw new Exception(label + " is redefined.");
				}
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
