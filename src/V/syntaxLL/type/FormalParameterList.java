package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VFunction;
import V.runtime.type.VString;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class FormalParameterList extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VFunction function=new VFunction();
			VSyntaxBase v=new FormalParameter();
			
			VSyntaxBase v1=new FPL_prime();
			
			index =Want(v, index, env);
			if(calcEnable)
			{
				function.paraList
					.DeclareParameterInDeclaration(((VString)v.result).value);
				v1.result = function;
			}
			index =Want(v1, index, env);
			this.result=v1.result;
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}
	class FPL_prime extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			if(units[index].type!=VLexUnit.COMMA)
			{
				return index;//<NULL>
			}
			try {
				VFunction function=(VFunction)this.result;
				VSyntaxBase v1=new FormalParameter();
				VSyntaxBase v2=new FPL_prime();
				index =Want(VLexUnit.COMMA, null, index, env);
				index =Want(v1, index, env);
				if(calcEnable)
				{
					function.paraList
						.DeclareParameterInDeclaration(((VString)v1.result).value);
					v2.result = function;
				}
				index =Want(v2, index, env);
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return UNMATCHED;
		}
		
	}
}
