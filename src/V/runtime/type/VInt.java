package V.runtime.type;

public class VInt extends VObject {
	public int value=0;
	
	public static VInt get(String data) {
		// TODO Auto-generated method stub
		VInt int1=new VInt();
		int1.value=Integer.parseInt(data);
		return int1;
	}
	@Override
	public VObject Clone()
	{
		VInt ret=new VInt();
		ret.value=this.value;
		return ret;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+value;
	}
}
