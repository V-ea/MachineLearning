package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class BlockStatements extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		int index_old = index;
		try {
			index = Want(new BlockStatement(), index_old, env);
		} catch (Exception e) {//NULL
			// TODO: handle exception
			if(index_old>=units.length-1)//Start 调用
			{
				System.exit(0);
				return index_old+1;
			}
			if(units[index_old+1].data.equals("}")) //Block 调用
				return index_old+1;
		}
		try {
			return Want(new BlockStatements(), index, env);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return VSyntaxBase.UNMATCHED;
	}

}
