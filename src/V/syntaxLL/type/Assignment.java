package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;
import V.runtime.type.VString;

/**
 * 
 * @author Vea - Eapchen专用标签 - 代码修改请保留该选项 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class Assignment extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = null, v1 = null, v2 = null;
			index = Want(v = new Id(), index, env);
			index = Want(v1 = new AssignOper(), index, env);
			index = Want(v2 = new AssignmentExpression(), index, env);
			if (calcEnable) {
				if (v1.result instanceof VString) {
					VString string = (VString) v1.result;
					if (string.value.equals("=")) {
						env.ChangeVariable(((VString) v.result).value,
								v2.result);
						this.result = v2.result;
					} else {
						//System.out.println(((VString) v.result).value);
						VObject object = env.getVar(((VString) v.result).value);
						// System.out.println(":"+object);
						if (string.value.equals("+=")) {
							this.result = VObject.plus(object, v2.result);
							System.out.println("+= invoked");
							env.ChangeVariable(((VString) v.result).value,
									this.result);
						} else if (string.value.equals("-=")) {
							this.result = VObject.minus(object, v2.result);
							env.ChangeVariable(((VString) v.result).value,
									this.result);
						} else if (string.value.equals("*=")) {
							this.result = VObject.multi(object, v2.result);
							env.ChangeVariable(((VString) v.result).value,
									this.result);
						} else if (string.value.equals("/=")) {
							this.result = VObject.divide(object, v2.result);
							env.ChangeVariable(((VString) v.result).value,
									this.result);
						} else if (string.value.equals("^=")) {
							this.result = VObject.exp(object, v2.result);
							env.ChangeVariable(((VString) v.result).value,
									this.result);
						} else if (string.value.equals("%=")) {
							this.result = VObject.mod(object, v2.result);
							env.ChangeVariable(((VString) v.result).value,
									this.result);
						}
					}

				} else {
					throw new Exception("unknown operator in assignment.");
				}
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
