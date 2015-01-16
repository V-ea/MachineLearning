package V.runtime.function;

import V.runtime.env.VEnv;
import V.runtime.type.VObject;

public class VLibFunction implements VIFunction {
	private VParameterList parameterList=new VParameterList();
	
	public VLibFunction() {
		super();
		try {
			parameterList.DeclareParameterInDeclaration("x");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public VObject Invoke(VEnv env) {
		// TODO Auto-generated method stub
		System.out.println(env.parameterList.GetInInvocation("x"));
		return new VObject() {
			
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public VObject Clone() {
				// TODO Auto-generated method stub
				return this;
			}
		};
	}

	@Override
	public VParameterList Parameter() {
		// TODO Auto-generated method stub
		return parameterList;
	}

	@Override
	public String GetLabel() {
		// TODO Auto-generated method stub
		return "print";
	}

	@Override
	public void SetLabel(String a) {
		// TODO Auto-generated method stub
		
	}

}
