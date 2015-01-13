package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VFunction;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class MethodDeclaration extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			//calcEnable=false;
			VSyntaxBase v=new MethodHeader();
			VSyntaxBase v1=new MethodBody();
			int index1 = Want(v, index, env);
			index =Want(v1, index1, env);
			if(calcEnable)
			{
				VFunction function=(VFunction)v.result;
				function.from=index1;
				if (env.getDirectlyVariable(function.label) == null) {
					env.AddVariable(function.label, function);
					// System.out.println(env.getVar(label));
				} else {
					throw new Exception(function.label + " is redefined.");
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
