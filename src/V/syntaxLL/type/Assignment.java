package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.exception.VParseException;

/**
 * ��ֵ sequence: Identifier = <Expression>
 * 				or
 * 				 Identifier ++ /--
 * 				or
 * 				 Identifier +=��-=��/=��%=��*= <Expression>
 * @author Administrator
 *
 */
public class Assignment extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		try{
			if(units[index++].type==VLexUnit.IDENTIFIER)
			{
				if(units[index++].type==VLexUnit.EQUAL)
				{
					VSyntaxBase vsbBase=new Expression();
					vsbBase.Accept(units, index,env);
					//vsbBase.result
				}
			}
			else {
				
				VParseException e=new VParseException();
				e.info="��Ҫһ����ʶ����";
				throw e;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			if(e instanceof VParseException)
			{
				System.out.println("����������"+index+"��λ�á�����ԭ��"+((VParseException)e).info);
			}
			if(e instanceof ArrayIndexOutOfBoundsException)
			{
				System.out.println("��δ������ϵĸ�ֵ����.");
			}
			e.printStackTrace();
		}
		return 0;
	}

}
