package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VString;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class AssignOper extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		VString string =new VString();
		string.value="";
		try {
			switch (units[index].type) {
			case VLexUnit.EQUAL:
				index =Want(VLexUnit.EQUAL, null, index, env);
				string.value+="=";
				break;

			default:
				int index_o=index;
				index=Want(VLexUnit.OPERATOR,null,index, env);
				string.value+=units[index_o].data;
				index=Want(VLexUnit.EQUAL,null, index, env);
				string.value+="=";
				break;
			}
			this.result =string;
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return VSyntaxBase.UNMATCHED;
	}

}
