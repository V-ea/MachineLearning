package V.runtime.type;



import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.env.VParameterList;
import V.syntaxLL.type.Block;
import V.syntaxLL.type.VSyntaxBase;

public class VFunction extends VObject{
	public VParameterList paraList=new VParameterList();
	public String label=null;
	public int from =-1;
	public VObject Invoke(VEnv env)
	{
		env.parameterList = paraList;
		VLexUnit[] units=VSyntaxBase.units;
		Block block=new Block();
		try {
			System.out.println(this);
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
		return "VFunction [paraList=" + paraList + ", label=" + label
				+ ", from=" + from + "]";
	}
	public VFunction Clone() {
		// TODO Auto-generated method stub
		VFunction function=new VFunction();
		function.paraList=this.paraList.Clone();
		function.label=this.label;
		function.from =this.from;
		return function;
	}
}
