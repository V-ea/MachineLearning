package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class FormalParameter extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v=null;
			index =Want(v=new VariableDeclaratorId(), index, env);
			if(calcEnable)
				this.result =v.result.Clone();
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
