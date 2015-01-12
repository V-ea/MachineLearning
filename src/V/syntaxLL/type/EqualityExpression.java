package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VBoolean;
import V.runtime.type.VObject;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class EqualityExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v=null;
			VObject resultObject1=null; 
			index =Want(v=new RelationalExpression(), index, env);
			resultObject1 = v.result;
			if (units[index].data.equals("=")) {
				index++;
				index =Want(VLexUnit.EQUAL, null, index, env);
				index =Want(v=new RelationalExpression(), index, env);
				VBoolean vBoolean=new VBoolean();
				vBoolean.value = true;
				if(0!=VObject.compare(resultObject1, v.result))
				{
					vBoolean.value = false;
				}
				this.result =vBoolean;
				return index;
			}
			else if (units[index].data.equals("!")) {
				index++;
				index =Want(VLexUnit.EQUAL, null, index, env);
				index =Want(new RelationalExpression(), index, env);
				VBoolean vBoolean=new VBoolean();
				vBoolean.value = true;
				if(0==VObject.compare(resultObject1, v.result))
				{
					vBoolean.value = false;
				}
				this.result =vBoolean;
				return index;
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
