package V.syntaxLL.type;

import V.lex.VLex;
import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class PrimaryNoNewArray extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v=null;
			if(units[index].data.equals("("))
			{
				index = Want(VLexUnit.LEFTX,null, index, env);
				index =Want(v=new Expression(), index, env);
				index = Want(VLexUnit.RIGHTX,null, index, env);
				if (calcEnable)
					this.result = v.result.Clone();
				return index ;
			}
			if((units[index].type==VLexUnit.IDENTIFIER&&isKeyword(units[index])==-1)
					&&units[index+1].type==VLexUnit.LEFTX)
			{
				//System.out.println("PrimaryNoNewArray:MethodInvocation");
				index =Want(v=new MethodInvocation(), index, env);
				if (calcEnable)
				{
					System.out.println("in pnn:"+v.result);
					this.result = v.result.Clone();
				}
				return index;
			}
			index = Want(v=new Literal(), index, env);
			if (calcEnable)
				this.result = v.result.Clone();
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
