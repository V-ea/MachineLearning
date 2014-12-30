package V.runtime.type;

public class VInt extends VObject {
	public int value=0;
	
	public static VInt get(String data) {
		// TODO Auto-generated method stub
		VInt int1=new VInt();
		int1.value=Integer.parseInt(data);
		return int1;
	}
}
