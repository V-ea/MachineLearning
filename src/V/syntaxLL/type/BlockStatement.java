package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class BlockStatement extends VSyntaxBase {// 未匹配成功就意味着BlockStatements为空

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		VSyntaxBase v = new LocalVariableDeclarationStatement();

		if (units[index].data.equals("var")) {
			try {
				return Want(v, index, env);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		v = new MethodDeclaration();
		if (units[index].data.equals("function")) {
			try {
				return Want(v, index, env);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return VSyntaxBase.UNMATCHED;
			}
		}
		v = new Statement();

		try {
			return Want(v, index, env);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return VSyntaxBase.UNMATCHED;
		}
	}

}
