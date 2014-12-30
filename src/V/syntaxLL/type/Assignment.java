package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.exception.VParseException;

/**
 * 赋值 sequence: Identifier = <Expression>
 * 				or
 * 				 Identifier ++ /--
 * 				or
 * 				 Identifier +=、-=、/=、%=、*= <Expression>
 * @author Administrator
 *
 */
public class Assignment extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		try{
			if(units[index++].type==VLexUnit.IDENTIFIER)
			{
				if(units[index++].type==VLexUnit.EQUAL)
				{
					VSyntaxBase vsbBase=new Expression();
					vsbBase.Accept(units, index,env);
					//vsbBase.result
				}
			}
			else {
				
				VParseException e=new VParseException();
				e.info="需要一个标识符。";
				throw e;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			if(e instanceof VParseException)
			{
				System.out.println("解析出错，第"+index+"个位置。错误原因："+((VParseException)e).info);
			}
			if(e instanceof ArrayIndexOutOfBoundsException)
			{
				System.out.println("尚未解析完毕的赋值句子.");
			}
			e.printStackTrace();
		}
		return 0;
	}

}
