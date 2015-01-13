package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class BreakStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(VLexUnit.IDENTIFIER, new String[]{"break"}, index, env);
			index =Want(VLexUnit.END, null, index, env);
			if(calcEnable)
				env.AddVariable("break", new VObject() {
				
				@Override
				public String toString() {
					// TODO Auto-generated method stub
					return null;
				}
			});
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
