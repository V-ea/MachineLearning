package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class ConditionalExpression extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			return Want(new COE(), index, env);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}
	class COE extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				index=Want(new CAE(), index, env);
				index=Want(new COE_prime(), index, env);
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}
		
	}
	class COE_prime extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if(!units[index].data.equals("||"))
					return index;
				index=Want(VLexUnit.BOPER, new String[]{"||"}, index, env);
				index =Want(new CAE(), index, env);
				index =Want(new COE_prime(), index, env);
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}
		
	}
	class CAE extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				index = Want(new IOE(), index, env);
				index =Want(new CAE_prime(), index, env);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}
		
	}
	class CAE_prime extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				if(!units[index].data.equals("&&"))
					return index;
				index=Want(VLexUnit.BOPER, new String[]{"&&"}, index, env);
				index =Want(new IOE(), index, env);
				index =Want(new CAE_prime(), index, env);
				return index;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}
		
	}
	class IOE extends VSyntaxBase{

		@Override
		public int Accept(VLexUnit[] units, int index, VEnv env) {
			// TODO Auto-generated method stub
			try {
				return Want(new EqualityExpression(),index,env);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return UNMATCHED;
		}
		
	}
}
