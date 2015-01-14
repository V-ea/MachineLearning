package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.env.VFunctionEnv;
import V.runtime.type.VFunction;
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
				{
					VEnv env2=env;
					while(!(env2 instanceof VFunctionEnv)&&env2.getParentEnv()!=null)
					{
						env2=env2.getParentEnv();
					}
					env2.AddVariable("return", vSyntaxBase.result);// 0  ������ֵ
				}
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
