package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class BlockStatement extends VSyntaxBase {// δƥ��ɹ�����ζ��BlockStatementsΪ��

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		VSyntaxBase v = new LocalVariableDeclarationStatement();

		if (units[index].data.equals("var")) {
			try {
				return Want(v, index, env);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		v = new MethodDeclaration();
		if (units[index].data.equals("function")) {
			try {
				return Want(v, index, env);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return VSyntaxBase.UNMATCHED;
			}
		}
		v = new Statement();

		try {
			return Want(v, index, env);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return VSyntaxBase.UNMATCHED;
		}
	}

}
