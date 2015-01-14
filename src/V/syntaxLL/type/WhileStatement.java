package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VBoolean;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class WhileStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = null;
			int check_index=index;
			int end_index=index;
			VEnv env2 = new VEnv();
			env2.setParentEnv(env);
			boolean old_calcEnable = calcEnable;
			boolean b = true;
			index =Want(VLexUnit.IDENTIFIER, new String[]{"while"}, index, env);
			index =Want(VLexUnit.LEFTX, null, index, env);
			check_index =index;
			index =Want(v=new Expression(), check_index, env);
			index =Want(VLexUnit.RIGHTX,null, index, env);
			if (old_calcEnable) {
				end_index =index;
				if (v.result instanceof VBoolean) {
					b = ((VBoolean) v.result).value;
				} else {
					throw new Exception(
							"if statement need boolean value in ().");
				}
				while(b)
				{
					//DO STH
					Want(new Statement(), end_index, env2);
					Want(v=new Expression(), check_index, env);
					if (v.result instanceof VBoolean) {
						b = ((VBoolean) v.result).value;
					} else {
						throw new Exception(
								"if statement need boolean value in ().");
					}
				}
			}
			calcEnable = old_calcEnable;
			
			if(!calcEnable)//false= go through
				index =Want(new Statement(), index, env2);
			else
			{
				calcEnable = false;
				index =Want(new Statement(), end_index, env2);
				calcEnable =true;
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
