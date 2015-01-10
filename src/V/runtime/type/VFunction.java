package V.runtime.type;



import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.env.VParameterList;
import V.syntaxLL.type.Block;
import V.syntaxLL.type.VSyntaxBase;

public class VFunction extends VObject{
	public VParameterList paraList=new VParameterList();
	
	public int from =-1;
	public VObject Invoke(VEnv env)
	{
		
		VLexUnit[] units=VSyntaxBase.units;
		Block block=new Block();
		try {
			int index =VSyntaxBase.Want(block, from, env);
			return block.result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
