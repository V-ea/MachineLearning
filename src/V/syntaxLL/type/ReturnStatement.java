package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class ReturnStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(VLexUnit.IDENTIFIER, new String[]{"return"}, index, env);
			if(units[index].type!=VLexUnit.END)
			{
				VSyntaxBase vSyntaxBase=null;
				index =Want(vSyntaxBase=new Expression(), index, env);
				if (calcEnable)
					env.AddVariable("return", vSyntaxBase.result);// 0  代表返回值
				//System.out.println("["+vSyntaxBase.result);
			}
			index =Want(VLexUnit.END, null, index, env);                                               
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
