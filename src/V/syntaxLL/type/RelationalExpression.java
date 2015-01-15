package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VBoolean;
import V.runtime.type.VObject;

/**
 * 
 * @author Vea - Eapchen专用标签 - 代码修改请保留该选项 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class RelationalExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = null;
			VObject object = null;
			index = Want(v = new ShiftExpression(), index, env);
			if (calcEnable)
				object = v.result.Clone();
			v = new RE_prime();
			if (calcEnable)
				v.result = object.Clone();
			index = Want(v, index, env);
			if (calcEnable)
				this.result = v.result.Clone();
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

	class RE_prime extends VSyntaxBase {

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if (units[index].type == VLexUnit.BOPER
						&& (!units[index].data.equals("||"))
						&& (!units[index].data.equals("&&"))) {
					VSyntaxBase v = null;
					String operString = units[index].data;
					index = Want(VLexUnit.BOPER, new String[] { ">=", "<=",
							">", "<" }, index, env);
					index = Want(v = new ShiftExpression(), index, env);
					if (calcEnable) {
						VObject aObject = this.result;
						VObject bObject = v.result;
						VBoolean reObject = new VBoolean();
						reObject.value = false;
						int i = VObject.compare(aObject, bObject);
						if ((i > 0 || i == 0) && operString.equals(">="))
							reObject.value = true;
						else if (i > 0 && operString.equals(">"))
							reObject.value = true;
						else if ((i < 0 || i == 0) && operString.equals("<="))
							reObject.value = true;
						else if (i < 0 && operString.equals("<"))
							reObject.value = true;
						this.result = reObject;
					}
					// index =Want(new RE_prime(), index, env);
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

}
