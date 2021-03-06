package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
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
