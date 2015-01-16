package V.runtime.function;



import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;
import V.syntaxLL.type.Block;
import V.syntaxLL.type.VSyntaxBase;

public class VFunction extends VObject implements VIFunction{
	private VParameterList paraList=new VParameterList();
	private String label=null;
	public int from =-1;
	public VObject Invoke(VEnv env)
	{
		Block block=new Block();
		try {
			int index =VSyntaxBase.Want(block, from, env);
			//System.out.println("invoke end:"+env.getDirectlyVariable("return"));
			return env.getDirectlyVariable("return").Clone();
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
	@Override
	public VParameterList Parameter() {
		// TODO Auto-generated method stub
		return this.paraList;
	}
	@Override
	public String GetLabel() {
		// TODO Auto-generated method stub
		return label;
	}
	@Override
	public void SetLabel(String aString) {
		// TODO Auto-generated method stub
		label = aString;
	}
}
