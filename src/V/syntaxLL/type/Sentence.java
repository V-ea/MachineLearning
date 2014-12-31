package V.syntaxLL.type;


import V.lex.VLexUnit;
import V.runtime.env.VEnv;

/**
 * 单个句子 sequence：<Expression> ; <- 注意还有个 ; or <IfBlock> or <WhileBlock>
 * 
 * @author Administrator
 *
 */
public class Sentence extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {

			switch (units[index].type) {
			case VLexUnit.IDENTIFIER:
				if (units[index].data.equals("if")) {
					VSyntaxBase vsb2 = new IfBlock();
					index = vsb2.Accept(units, index, env);
					// this.result = vsb2.result;
					return index;
				}
				if (units[index].data.equals("while")) {
					VSyntaxBase vsb2 = new WhileBlock();
					index = vsb2.Accept(units, index, env);
					// this.result = vsb2.result;
					return index;
				}
			}
			//
			System.out.println("Expression");
			VSyntaxBase vsb2 = new Expression();
			index = vsb2.Accept(units, index, env);
			// this.result = vsb2.result;
			if (units[index].type == VLexUnit.END)
				return index + 1;
			else {
				throw new Exception("; is excepted after "+index
						);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}

}
