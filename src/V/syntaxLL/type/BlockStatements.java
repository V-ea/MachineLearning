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
public class BlockStatements extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		int index_old = index;
		if(index_old>=units.length-1)//Start ����
		{
			return index_old+1;
		}
		if(units[index_old].data.equals("}")) //Block ����
			return index_old;
		try {
			index = Want(new BlockStatement(), index_old, env);
			if(env.getDirectlyVariable("0")!=null)
			{
				this.result = env.getDirectlyVariable("0");
				int balance = 0;
				for(int i=index;i<units.length;i++)//return ������
				{
					if(units[i].type==VLexUnit.RIGHTB)
					{
						balance--;
					}
					if(units[i].type==VLexUnit.LEFTB)
					{
						balance++;
					}
					if(balance==-1)
					{
						return i;
					}
				}
				return units.length;
			}
		} catch (Exception e) {//NULL
			// TODO: handle exception
			System.out.println("basic block statement error at "+units[index_old]);
			System.exit(0);
		}
		try {
			VSyntaxBase v=null;
			index = Want(v=new BlockStatements(), index, env);
			this.result = v.result;
			return index;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return VSyntaxBase.UNMATCHED;
	}

}
