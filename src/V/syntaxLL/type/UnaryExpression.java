package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VInt;
import V.runtime.type.VObject;
import V.runtime.type.VString;

/**
 * 
 * @author Vea - Eapchen专用标签 - 代码修改请保留该选项 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class UnaryExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			UE_2 v = new UE_2();
			// v.prime = v1;
			index = Want(v, index, env);
			// v1.result=v.result;
			// index =Want(v1, index, env);
			if (calcEnable)
				this.result = v.result;
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

	class UE_2 extends VSyntaxBase {
		// public UE_prime prime=null;
		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				VSyntaxBase v = null;
				if (units[index].data.equals("(") // (<Expression>)
						|| units[index].type == VLexUnit.FLOAT
						|| units[index].type == VLexUnit.STRING // <Literal>
						|| (units[index].type == VLexUnit.IDENTIFIER
								&& -1 == isKeyword(units[index]) && units[index + 1].data
								.equals("("))
						|| (units[index].type == VLexUnit.IDENTIFIER
								&& -1 != isKeyword(units[index]) && (units[index].data
								.equals("true") || units[index].data
								.equals("false"))))
				// <MethodInvocation>
				{
					index = Want(v = new Primary(), index, env);
					if (calcEnable)
						this.result = v.result;
				} else if (units[index].data.equals("+")
						&& units[index + 1].data.equals("+")) {
					index = Want(v = new PreIncrementExpression(), index, env);
					this.result = v.result;
				} else if (units[index].data.equals("-")
						&& units[index + 1].data.equals("-")) {
					index = Want(v = new PreDecrementExpression(), index, env);
					if (calcEnable)
						this.result = v.result;
				} else {
					index = Want(v = new Id(), index, env);
					// this.prime.label = ;
					if (calcEnable)
						this.result = env.getVar(((VString) v.result).value);
				}
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}

	}
	// class UE_prime extends VSyntaxBase{//蹩脚的a++ 我删除了
	// public String label=null;
	// @Override
	// public int Accept(VLexUnit[] units, int index, VEnv env) {
	// // TODO Auto-generated method stub
	// try {
	// if(units[index].data.equals("+")&&units[index+1].data.equals("+"))
	// {
	// index =Want(VLexUnit.OPERATOR, new String[]{"+"}, index, env);
	// index =Want(VLexUnit.OPERATOR, new String[]{"+"}, index, env);
	// //index =Want(new UE_prime(), index, env);
	// VInt one=new VInt();
	// one.value =1;
	// this.result=VObject.plus(this.result, one);
	// //if(label)
	// return index;
	// }
	// if(units[index].data.equals("-")&&units[index+1].data.equals("-"))
	// {
	// index =Want(VLexUnit.OPERATOR, new String[]{"-"}, index, env);
	// index =Want(VLexUnit.OPERATOR, new String[]{"-"}, index, env);
	// //index =Want(new UE_prime(), index, env);
	// return index;
	// }
	// return index;//<NULL>
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	// return UNMATCHED;
	// }
	//
	// }
}
