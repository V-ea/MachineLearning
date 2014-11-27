package script;

import java.util.HashMap;
import java.util.Map;

import math.VCalculateInterface;
import math.VEasyFunctor;

public class VRuntimeEnv {

	private VRuntimeEnv parentEnv = null;
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
	private Map<String, Object> variableMap = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 */
	public VRuntimeEnv getParentEnv() {
		return parentEnv;
	}

	/**
	 * 
	 * @param parentEnv
	 */
	public void setParentEnv(VRuntimeEnv parentEnv) {
		this.parentEnv = parentEnv;
	}

	public Object getVariable(String label) {
		if (variableMap.containsKey(label)) {
			return variableMap.get(label);
		}
		if (null == getParentEnv()) {
			return null;
		}
		return parentEnv.getVariable(label);
	}

	public void SetVariable(String label, Object value) {
		this.variableMap.put(label, value);
	}

	public boolean ChangeVariable(String label, Object value) {
		// VRuntimeEnv vRuntimeEnv=this;
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
		Object object = getVariable(label);
		//System.out.println("Calc"+object.toString());
		if (object == null) {// 尝试计算之
			throw new Exception(label + " is not set.");
		}
		if (deeps == 100) {
			throw new Exception("error:endless running...shutdown.");
		}
		if (object instanceof VCalculateInterface) {
			VCalculateInterface v = (VCalculateInterface) object;
			Object[] strs = v.GetUnGiven();
			if (strs!=null&&strs.length != 0)
				for (Object str11 : strs) {
					//System.out.println(str11);
					float sFloat = 0.0F;
					//System.out.println(str11+"]");
					sFloat = Calc(str11.toString(), deeps + 1);
					v.Given(str11.toString().trim(), Float.valueOf(sFloat));
				}
			return v.Calc().floatValue();
		}
		if (object instanceof Float) {
			return (Float) object;
		}
		throw new Exception(label + " is unknown format object.");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
