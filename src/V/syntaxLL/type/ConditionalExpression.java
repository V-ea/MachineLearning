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
public class ConditionalExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v = null;
			// System.out.println("=======================");
			index = Want(v = new COE(), index, env);
			if (calcEnable)
				this.result = v.result.Clone();
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

	class COE extends VSyntaxBase {

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				VObject object = null;
				VSyntaxBase v = null;
				index = Want(v = new CAE(), index, env);
				if (calcEnable)
					object = v.result;
				// System.out.println(object);
				v = new COE_prime();
				index = Want(v, index, env);
				if (calcEnable) {
					if (v.result instanceof VBoolean) {
						if (!(object instanceof VBoolean))
							throw new Exception("||:boolean value needed .");
						VBoolean vBoolean = new VBoolean();
						vBoolean.value = ((VBoolean) object).value
								|| ((VBoolean) v.result).value;
						this.result = vBoolean;
					} else if (v.result == null) {
						this.result = v.result;
					} else {
						throw new Exception("||:boolean value needed .");
					}
					this.result = object;
				}
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}

	}

	class COE_prime extends VSyntaxBase {

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				VObject object = null, object1 = null;
				VSyntaxBase v = null, v1 = null;
				if (!units[index].data.equals("||"))
					return index;
				index = Want(VLexUnit.BOPER, new String[] { "||" }, index, env);
				index = Want(v = new CAE(), index, env);
				if (calcEnable)
					object = v.result;
				index = Want(v1 = new COE_prime(), index, env);
				if (calcEnable) {
					object1 = v1.result;
					if (!(object instanceof VBoolean)
							|| !(object1 instanceof VBoolean)) {
						throw new Exception("||:boolean value needed .");
					}
					VBoolean oBoolean = new VBoolean();
					oBoolean.value = ((VBoolean) object).value
							|| ((VBoolean) object1).value;
					this.result = oBoolean;
				}
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}

	}

	class CAE extends VSyntaxBase {

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				VSyntaxBase v = null;
				VObject object = null;
				index = Want(v = new IOE(), index, env);
				if (calcEnable)
					object = v.result;
				index = Want(v = new CAE_prime(), index, env);
				if (calcEnable) {
					if (v.result instanceof VBoolean) {
						if (!(object instanceof VBoolean))
							throw new Exception("&&:boolean value needed .");
						VBoolean vBoolean = new VBoolean();
						vBoolean.value = ((VBoolean) object).value
								&& ((VBoolean) v.result).value;
						this.result = vBoolean;
					} else if (v.result == null) {
						this.result = v.result;
					} else {
						throw new Exception("&&:boolean value needed .");
					}
					this.result = object;
				}
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}

	}

	class CAE_prime extends VSyntaxBase {

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				VObject object = null, object1 = null;
				VSyntaxBase v = null, v1 = null;
				if (!units[index].data.equals("&&"))
					return index;
				index = Want(VLexUnit.BOPER, new String[] { "&&" }, index, env);
				index = Want(v = new IOE(), index, env);
				if (calcEnable)
					object = v.result;
				index = Want(v1 = new CAE_prime(), index, env);
				if (calcEnable) {
					object1 = v1.result;
					if (!(object instanceof VBoolean)
							|| !(object1 instanceof VBoolean)) {
						throw new Exception("&&:boolean value needed .");
					}
					VBoolean oBoolean = new VBoolean();
					oBoolean.value = ((VBoolean) object).value
							&& ((VBoolean) object1).value;
					this.result = oBoolean;
				}
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}

	}

	class IOE extends VSyntaxBase {

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				VSyntaxBase v = null;
				index = Want(v = new EqualityExpression(), index, env);
				this.result = v.result;
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}

	}
}
