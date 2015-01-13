package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VInt;
import V.runtime.type.VObject;
import V.runtime.type.VString;

/**
 * 
 * @author Vea - Eapchen专用标签 - 代码修改请保留该选项 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class PreIncrementExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = null;
			index = Want(VLexUnit.OPERATOR, new String[] { "+" }, index, env);
			index = Want(VLexUnit.OPERATOR, new String[] { "+" }, index, env);
			// index =Want(new UnaryExpression(), index, env);
			index = Want(v = new Id(), index, env);
			if (calcEnable) {
				this.result = env.getVar(((VString) v.result).value);
				VInt one = new VInt();
				one.value = 1;
				this.result = VObject.plus(this.result, one);
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
