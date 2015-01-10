package V.syntaxLL.type;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VString;

public class Id extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			int index1=index;
			index = Want(VLexUnit.IDENTIFIER, null, index, env);
			if(-1!=isKeyword(units[index1]))
				return VSyntaxBase.UNMATCHED;
			VString str=new VString(); 
			str.value=units[index1].data;
			this.result =str;
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return VSyntaxBase.UNMATCHED;
	}

}
