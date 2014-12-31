package V.runtime.type;

public class VString extends VObject {
	public String value=null;
	
	public static VString get(String data) {
		// TODO Auto-generated method stub
		VString s1=new VString();
		s1.value=data.substring(1, data.length()-1);
		return s1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}
}	
