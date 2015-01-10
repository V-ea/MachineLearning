package V.runtime.type;

public class VBoolean extends VObject {
	public boolean value=false;

	public static VBoolean get(String data) {
		// TODO Auto-generated method stub
		VBoolean Boolean1=new VBoolean();
		Boolean1.value=Boolean.parseBoolean(data);
		return Boolean1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+value;
	}
	public static void main(String[] args) {
		System.out.println(new VBoolean().get("false"));
	}
}
