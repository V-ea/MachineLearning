package V.syntaxLL.type;


import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class AssignmentExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v=new Assignment();
			if((units[index].type==VLexUnit.IDENTIFIER&&isKeyword(units[index])==-1)
					&&(units[index].type==VLexUnit.EQUAL||units[index].type==VLexUnit.OPERATOR))
			{
				index =Want(v, index, env);
				if(calcEnable)
					this.result =v.result;
				return index;
			}
			else {
				index = Want(v=new ConditionalExpression(), index, env);
				if(calcEnable)
					this.result =v.result;
				return index;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return VSyntaxBase.UNMATCHED;
	}

}
