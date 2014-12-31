package V.runtime.type;

public class VFloat extends VObject {
	public float value=0.0f;

	public static VFloat get(String data) {
		// TODO Auto-generated method stub
		VFloat float1=new VFloat();
		float1.value=Float.parseFloat(data);
		return float1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+value;
	}
}
