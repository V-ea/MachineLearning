package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VBoolean;

/**
 * 
 * @author Vea - Eapchen专用标签 - 代码修改请保留该选项 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class IfThenElseStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = null;
			VBoolean boolean1 = null;
			VEnv env2 = new VEnv();
			env2.setParentEnv(env);
			boolean old_calcEnable = calcEnable;
			boolean b = true;
			index = Want(VLexUnit.IDENTIFIER, new String[] { "if" }, index, env);
			index = Want(VLexUnit.LEFTX, null, index, env);
			index = Want(v = new Expression(), index, env);
			index = Want(VLexUnit.RIGHTX, null, index, env);
			if (old_calcEnable) {
				if (v.result instanceof VBoolean) {
					b = ((VBoolean) v.result).value;
				} else {
					throw new Exception(
							"if statement need boolean value in ().");
				}
				if (!b) {
					calcEnable = false;
				}
			}
			
			index = Want(new Statement(), index, env2);
			if (old_calcEnable) {
				if (!b) {
					calcEnable = true;
				} else {
					calcEnable = false;
				}
			}
			if (index<units.length&&units[index].data.equals("else")) {
				index = Want(new Statement(), index + 1, env2);
			}
			calcEnable = old_calcEnable;
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
