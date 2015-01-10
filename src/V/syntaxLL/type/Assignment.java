package V.syntaxLL.type;

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
public class Assignment extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v=null,v1=null,v2=null;
			index =Want(v=new Id(), index, env);
			index =Want(v1=new AssignOper(), index, env);
			index =Want(v2=new AssignmentExpression(), index, env);
			if(v1.result instanceof VString)
			{
				VString string =(VString) v1.result;
				if(string.value.equals("="))
				{
					env.ChangeVariable(((VString)v.result).value, v2.result);
				}
				else
				{
					VObject object = env.getVar(((VString)v.result).value);
					System.out.println(object);
					if(string.value.equals("+="))
					{
						env.ChangeVariable(((VString)v.result).value, 
								VObject.plus(v2.result, object));
					}else
					if(string.value.equals("-="))
					{
						env.ChangeVariable(((VString)v.result).value, 
								VObject.minus(object,v2.result));
					}else
					if(string.value.equals("*="))
					{
						env.ChangeVariable(((VString)v.result).value, 
								VObject.multi(object,v2.result));
					}else if(string.value.equals("/=")){
						env.ChangeVariable(((VString)v.result).value, 
								VObject.divide(object,v2.result));
					}
					else if(string.value.equals("^=")){
						env.ChangeVariable(((VString)v.result).value, 
								VObject.exp(object,v2.result));
					}
					else if(string.value.equals("%=")){
						env.ChangeVariable(((VString)v.result).value, 
								VObject.divide(object,v2.result));
					}
				}
			}else {
				throw new Exception("unknown operator in assignment.");
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
