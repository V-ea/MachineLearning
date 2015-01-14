package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class ReturnStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(VLexUnit.IDENTIFIER, new String[]{"return"}, index, env);
			if(units[index].type!=VLexUnit.END)
			{
				VSyntaxBase vSyntaxBase=null;
				index =Want(vSyntaxBase=new Expression(), index, env);
				if (calcEnable)
					env.AddVariable("return", vSyntaxBase.result);// 0  ������ֵ
				//System.out.println("["+vSyntaxBase.result);
			}
			index =Want(VLexUnit.END, null, index, env);                                               
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
