package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VFunction;
import V.runtime.type.VString;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class MethodDeclarator extends VSyntaxBase {
	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VFunction function=new VFunction();
			VSyntaxBase v=null;
			String label=null;
			index =Want(v=new Id(), index, env);
			if(calcEnable)
				label=((VString)v.result).value;
			index =Want(VLexUnit.LEFTX, null, index, env);
			if(units[index].type!=VLexUnit.RIGHTX)
			{
				index =Want(v=new FormalParameterList(), index, env);
				if(calcEnable)
				{
					function=(VFunction)v.result;
				}
			}
			index =Want(VLexUnit.RIGHTX, null, index, env);
			if(calcEnable)
			{
				this.result=function;
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
