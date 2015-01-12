package V.runtime.env;

import java.util.HashMap;
import java.util.Map;

import V.runtime.type.VObject;


public class VEnv {
	@Override
	public String toString() {
		return "VEnv [parentEnv=" + parentEnv + ", Deepth=" + Deepth
				+ ", variableMap=" + variableMap + ", parameterList="
				+ parameterList + "]";
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
	 * ��ʱ����ı����б� 
	 */
	private Map<String, VObject> variableMap = new HashMap<String, VObject>();
	public VParameterList parameterList=null;
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
	public VObject getDirectlyVariable(String label)
	{
		System.out.println(this);
		if (variableMap.containsKey(label)) {
			return variableMap.get(label);
		}
		return null;
	}
	public VObject getVar(String label) throws Exception {
		VObject object = getVariable(label);
		System.out.println(this);
		if(object==null)
			throw new Exception(label +" is not defined ");
		return object;
	}
	public VObject getVariable(String label) {
		if (variableMap.containsKey(label)) {
			return variableMap.get(label);
		}
		if (null == getParentEnv()) {
			if(parameterList==null)//
				return null;
			else{
				return parameterList.GetInInvocation(label);
			}
		}
		VObject object =parentEnv.getVariable(label);
		if (null == object) {
			if(parameterList==null)//
				return null;
			else{
				return parameterList.GetInInvocation(label);
			}
		}
		return object;
	}

	public void AddVariable(String label, VObject value) {
		this.variableMap.put(label, value);
	}

	public boolean ChangeVariable(String label, VObject value) {
		// VEnv vEnv=this;
		if (variableMap.containsKey(label)) {
			AddVariable(label, value);
			return true;
		}
		if (null == getParentEnv()) {
			return false;
		}
		return parentEnv.ChangeVariable(label, value);
	}

	public void RemoveVariable(String label) {
		this.variableMap.remove(label);
	}
}
