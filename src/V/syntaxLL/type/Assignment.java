package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.exception.VParseException;
import V.runtime.type.VInt;

/**
 * 赋值 sequence: Identifier = <Expression> or Identifier ++ /-- or Identifier
 * +=、-=、/=、%=、*= <Expression>
 * 
 * @author Administrator
 *
 */
public class Assignment extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			if (units[index++].type == VLexUnit.IDENTIFIER) {
				if (units[index++].type == VLexUnit.EQUAL) {
					VSyntaxBase vsbBase = new Expression();
					String id = units[index-2].data;
					index = vsbBase.Accept(units, index, env);
					this.result = vsbBase.result;
					env.SetVariable(id, this.result);
					return index;
				}
			}
			VParseException e = new VParseException();
			e.info = "an identifier needed.";
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			if (e instanceof VParseException) {
				System.out.println("解析出错，第" + index + "个位置"+units[index].data+"。错误原因："
						+ ((VParseException) e).info);
			}
			if (e instanceof ArrayIndexOutOfBoundsException) {
				System.out.println("unexpected end.");
			}
			e.printStackTrace();
		}
		return -1;
	}

}
