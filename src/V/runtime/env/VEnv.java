package V.runtime.env;

import java.util.HashMap;
import java.util.Map;

import V.runtime.type.VObject;


public class VEnv {
	private VEnv parentEnv = null;
	private int Deepth = 0;

	public int getDeepth() {
		return Deepth;
	}

	public void setDeepth(int deepth) {
		Deepth = deepth;
	}

	/**
	 * 临时区域的变量列表 目前只接受VEasyFunctor、Float两种类型的值
	 */
	private Map<String, VObject> variableMap = new HashMap<String, VObject>();

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

	public VObject getVariable(String label) {
		if (variableMap.containsKey(label)) {
			return variableMap.get(label);
		}
		if (null == getParentEnv()) {
			return null;
		}
		return parentEnv.getVariable(label);
	}

	public void SetVariable(String label, VObject value) {
		this.variableMap.put(label, value);
	}

	public boolean ChangeVariable(String label, VObject value) {
		// VEnv vEnv=this;
		if (variableMap.containsKey(label)) {
			SetVariable(label, value);
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

	public Float Calc(String label, int deeps) throws Exception {
			return null;
	}

}
