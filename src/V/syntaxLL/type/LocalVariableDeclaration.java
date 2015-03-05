package V.syntaxLL.type;

import com.sun.xml.internal.ws.message.stream.StreamAttachment;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;
import V.runtime.type.VString;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class LocalVariableDeclaration extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v1=null,v2=null;
			index =Want(VLexUnit.IDENTIFIER,new String[]{"var"}, index, env);
			index =Want(v1=new VariableDeclaratorId(), index, env);
			index =Want(VLexUnit.EQUAL,null, index, env);
			index =Want(v2=new VInit(), index, env);
			if (calcEnable) {
				VString id=(VString)v1.result; 
				String label = id.value;
				VObject object = v2.result;
				// System.out.println(object);
				if (env.getDirectlyVariable(label) == null) {
					env.AddVariable(label, object);
					//System.out.println(env.getVar(label));
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
