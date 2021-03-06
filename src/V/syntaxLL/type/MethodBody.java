package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * 
 * @author Vea - Eapchen专用标签 - 代码修改请保留该选项 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class MethodBody extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		if (units[index].type == VLexUnit.END) {
			return index + 1;
		}
		try {
			// VEnv env2=new VEnv();
			// env2.setParentEnv(env);
			if (calcEnable == true) {
				calcEnable = false;
				index = Want(new Block(), index, env);
				calcEnable = true;
			} else {
				index = Want(new Block(), index, env);
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
