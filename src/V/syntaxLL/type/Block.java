package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchenר�ñ�ǩ - �����޸��뱣����ѡ��
 * ��ʲô�������� cheneap@hotmail.com ����
 *
 */
public class Block extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			boolean calc_old = calcEnable;
			index =Want(VLexUnit.LEFTB, null, index, env);
			if(units[index].type!=VLexUnit.RIGHTB)
			{
				BlockStatements v=null;
				index =Want(v=new BlockStatements(), index, env);
				if(calc_old)
					this.result = env.getDirectlyVariable("return");
			}
			//if(!calcEnable)
			calcEnable =calc_old;
			index =Want(VLexUnit.RIGHTB, null, index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
