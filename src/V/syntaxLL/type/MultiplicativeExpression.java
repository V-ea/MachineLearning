package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;

/**
 * 
 * @author Vea - Eapchen专用标签 - 代码修改请保留该选项 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class MultiplicativeExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = new UnaryExpression(), v1 = new MultiE_prime();
			index = Want(v, index, env);
			if (calcEnable)
				v1.result = v.result.Clone();
			// System.out.println("["+v1.result);
			index = Want(v1, index, env);
			if (calcEnable)
				this.result = v1.result.Clone();
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

	class MultiE_prime extends VSyntaxBase {

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				String oper = null;
				if (units[index].data.equals("*")
						|| units[index].data.equals("/")
						|| units[index].data.equals("%")
						|| units[index].data.equals("^")) {
					oper = units[index].data;
					VSyntaxBase v1 = null, v2 = new MultiE_prime();
					index++;
					index = Want(v1 = new UnaryExpression(), index, env);
					if (calcEnable) {
						if (oper.equals("*"))
							v2.result = VObject.multi(this.result, v1.result);
						if (oper.equals("/"))
							v2.result = VObject.divide(this.result, v1.result);
						if (oper.equals("%"))
							v2.result = VObject.mod(this.result, v1.result);
						if (oper.equals("^"))
							v2.result = VObject.exp(this.result, v1.result);
						index = Want(v2, index, env);
						this.result = v2.result;
					}
					return index;
				}
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}
	}
}
