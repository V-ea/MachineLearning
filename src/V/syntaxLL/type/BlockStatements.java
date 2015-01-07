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
			
		}
		if(index==-1)// BlockStatements 为 <NULL> 或者BlockStatement出错
		{
			if(index_old>=units.length)//Start 调用
				return index_old;
			if(units[index_old].data.equals("}")) //Block 调用
				return index_old;
		}
		try {
			return Want(new BlockStatements(), index, env);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return VSyntaxBase.UNMATCHED;
	}

}
