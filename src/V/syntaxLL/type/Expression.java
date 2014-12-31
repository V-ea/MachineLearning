package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;

/**
 * ±Ì¥Ô Ω£∫sequence: <BaseType>
 * 					or
 * 					 <FunctionInvokeExpression>
 * 					or
 * 					 <NormalExpression>
 * 					or
 * 					 <BooleanExpression>
 * 					or
 * 					 <Assignment>
 *					or
 * 					( <Expression> )
 * @author Administrator
 *
 */
public class Expression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index,VEnv env) {
		// TODO Auto-generated method stub
		try {
			switch (units[index++].type) {
			// first: int float string
			case VLexUnit.FLOAT:
			case VLexUnit.STRING:

				switch (units[index--].type) {
				// second:+-*/
				case VLexUnit.OPERATOR:
					VSyntaxBase vsb2=new NormalExpression();
					index =vsb2.Accept(units, index, env);
					this.result = vsb2.result;
					return index;
				// second:bool_operator
				case VLexUnit.BOPER:
					VSyntaxBase vsb21=new BooleanExpression();
					index =vsb21.Accept(units, index, env);
					this.result = vsb21.result;
					return index;
				// else : only <basetype>
				default:
					System.out.println("base");
					VSyntaxBase vsb211=new BaseType();
					index =vsb211.Accept(units, index, env);
					this.result = vsb211.result;
					System.out.println(this.result.toString()+index);
					return index;
				}
			//first: identifier
			case VLexUnit.IDENTIFIER:
				switch (units[index--].type) {
				// second:+-*/
				case VLexUnit.OPERATOR:
					VSyntaxBase vsb2=new NormalExpression();
					index =vsb2.Accept(units, index, env);
					this.result = vsb2.result;
					return index;
				// second:bool_operator
				case VLexUnit.BOPER:
					VSyntaxBase vsb21=new BooleanExpression();
					index =vsb21.Accept(units, index, env);
					this.result = vsb21.result;
					return index;
				// second:assignment
				case VLexUnit.EQUAL:
					System.out.println("ass");
					VSyntaxBase vsb211=new Assignment();
					index =vsb211.Accept(units, index, env);
					this.result = vsb211.result;
					return index;
					// second:assignment
				case VLexUnit.LEFTX:
					VSyntaxBase vsb2111=new FunctionInvokeExpression();
					index =vsb2111.Accept(units, index, env);
					this.result = vsb2111.result;
					return index;
				// else : only IDENTIFIER
				default:
					VObject object=env.getVariable(units[index].data);
					if(object==null)
					{
						throw new Exception(units[index].data+" is not declared.");
					}
					this.result=object;
					return index+1;
				}
			//first : (
			case VLexUnit.LEFTX:
				VSyntaxBase vsb2111=new Expression();
				index =vsb2111.Accept(units, index, env);
				if(units[index].type==VLexUnit.RIGHTX)
				{
					this.result = vsb2111.result;
					return index+1;	
				}
				throw new Exception(") needed .");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}
