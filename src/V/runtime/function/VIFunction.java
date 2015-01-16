package V.runtime.function;

import V.runtime.env.VEnv;
import V.runtime.type.VObject;

public interface VIFunction {
	public VObject Invoke(VEnv env);
	public VParameterList Parameter();
	public String GetLabel();
	public void SetLabel(String a);
}
