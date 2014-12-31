package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;

/**
 * 计算方面的表达式 sequence:<BaseType> +/-/* // /^/%/&/#/<Expression>
 * 
 * @author Administrator
 *
 */
public class NormalExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		/**
		 * 安全性由Expression检测
		 */
		try {
			//System.out.println("ne");
			VSyntaxBase vsb = new BaseType();
			index = vsb.Accept(units, index, env);
			result = vsb.result;
			VSyntaxBase vsb2 = new Expression();
			String oper = units[index].data;
			index = vsb2.Accept(units, index+1, env);
			VObject reObject = vsb2.result;
			if (oper.equals("+"))
				this.result = VObject.plus(result, reObject);
			else if (oper.equals("-"))
				this.result = VObject.minus(result, reObject);
			else if (oper.equals("*"))
				this.result = VObject.multi(result, reObject);
			else if (oper.equals("/"))
				this.result = VObject.divide(result, reObject);
			else if (oper.equals("^"))
				this.result = VObject.exp(result, reObject);
			System.out.println(index);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}
