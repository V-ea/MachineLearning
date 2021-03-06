package V.runtime.env;

import java.util.HashMap;
import java.util.Map;

import V.runtime.function.VFunction;
import V.runtime.function.VIFunction;
import V.runtime.function.VParameterList;
import V.runtime.type.VInt;
import V.runtime.type.VObject;

public class VEnv {
	@Override
	public String toString() {
		return "VEnv [parentEnv=" + parentEnv + ", Deepth=" + Deepth
				+ ", variableMap=" + variableMap + ", parameterList="
				+ parameterList + ", PreparedParaList=" + PreparedParaList
				+ ", id=" + id + "]";
	}

	private VEnv parentEnv = null;
	private int Deepth = 0;

	public int getDeepth() {
		return Deepth;
	}

	public void setDeepth(int deepth) {
		Deepth = deepth;
	}

	/**
	 * 临时区域的变量列表
	 */
	private Map<String, VObject> variableMap = new HashMap<String, VObject>();
	public VParameterList parameterList = null;
	public VParameterList PreparedParaList = null;
	private static Map<String, VIFunction> functionMap = new HashMap<String, VIFunction>();
	private String id="";
	public VEnv() {
		super();
		id="env_id_"+(int)(Math.random()*10000);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return
	 */
	public VEnv getParentEnv() {
		return parentEnv;
	}

	/**
	 * 
	 * @param parentEnv
	 */
	public void setParentEnv(VEnv parentEnv) {
		this.parentEnv = parentEnv;
	}

	public VObject getDirectlyVariable(String label) {
		//System.out.println(this);
		if (variableMap.containsKey(label)) {
			return variableMap.get(label).Clone();
		}
		return null;
	}

	public VObject getVar(String label) throws Exception {
		VObject object = getVariable(label);
		//System.out.println(this);
		if (object == null)
			throw new Exception(label + " is not defined ");
		return object;
	}

	public VObject getVariable(String label) {
		if (variableMap.containsKey(label)) {
			return variableMap.get(label).Clone();
		}
		if (null == getParentEnv()) {
			if (parameterList == null)//
				return null;
			else {
				VObject retObject=parameterList.GetInInvocation(label);
				if(retObject!=null)
					retObject =retObject.Clone();
				return retObject;
			}
		}
		if (parameterList == null)//
			return parentEnv.getVariable(label);
		else {
			VObject object = parameterList.GetInInvocation(label);
			if (object == null) {
				return parentEnv.getVariable(label);
			}
			return object.Clone();
		}

	}

	public void AddVariable(String label, VObject value) {
		this.variableMap.put(label, value);
	}

	public boolean ChangeVariable(String label, VObject value) throws Exception {
		// VEnv vEnv=this;
		if (variableMap.containsKey(label)) {
			AddVariable(label, value);
			return true;
		}
		if (null == getParentEnv()) {
			throw new Exception(label + " must be declared before use.");
		}
		return parentEnv.ChangeVariable(label, value);
	}

	public void RemoveDirectlyVariable(String label) {
		this.variableMap.remove(label);
	}

	public static VIFunction getFunction(String label) {
		return functionMap.get(label);
	}

	public static void setFunction(String label, VIFunction function)
			throws Exception {
		if (functionMap.containsKey(label)) {
			throw new Exception("function " + label
					+ " has already been declared.");
		}
		functionMap.put(label, function);
	}

	public static void main(String[] args) throws Exception {
		VEnv env = new VEnv();
		VEnv env2 = new VEnv();
		VInt aInt = new VInt();
		VInt bInt = new VInt();
		bInt.value = 90;
		VParameterList parameterList = new VParameterList();
		parameterList.DeclareParameterInDeclaration("b");
		parameterList.SetParameterInInvocation(bInt);
		env2.parameterList = parameterList;
		env.setParentEnv(env2);
		aInt.value = 1;
		env.AddVariable("a", aInt);
		System.out.println(env.getVar("b"));
	}
}
