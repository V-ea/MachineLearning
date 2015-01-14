package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.env.VFunctionEnv;
import V.runtime.type.VFunction;
import V.runtime.type.VInt;
import V.runtime.type.VObject;
import V.runtime.type.VString;

/**
 * 
 * @author Vea - Eapchen专用标签 - 代码修改请保留该选项 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class MethodInvocation extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = null;
			VEnv env2 = null;
			VFunction function = null;
			index = Want(v = new Id(), index, env);
			if (calcEnable) {
				System.out.println("Invoke");
				VFunction object_ = VEnv.getFunction(((VString) v.result).value);
				if (object_==null)
					throw new Exception("no such function named:"
							+ ((VString) v.result).value);
				env2 = new VFunctionEnv();
				function = ((VFunction) object_).Clone();
				env.PreparedParaList = function.paraList.Clone();
			}
			index = Want(VLexUnit.LEFTX, null, index, env);
			if (units[index].type != VLexUnit.RIGHTX)
				index = Want(new ArgumentList(), index, env);// OPT
			index = Want(VLexUnit.RIGHTX, null, index, env);
			if (calcEnable) {
				function.paraList=env.PreparedParaList;
				this.result = function.Invoke(env2);
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
