package script;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import math.VCalculateInterface;

import exception.VSyntaxException;

public class VShellScript {
	private int IF_SEN=0;//1:else -1:no else 0:else is error 
	public int LastIsIf() {
		return IF_SEN;
	}
	public void LastIsIf(int else_state) {
		IF_SEN=else_state;
	}
	public VShellScript() {
		VCalculateInterface.init();
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public void Run(String script,VRuntimeEnv vParentRuntimeEnv) throws Exception
	{
		//
		VSentence[] sentences=VSentence.generateSentences(script);
		VRuntimeEnv vRuntimeEnv=new VRuntimeEnv();
		if(vParentRuntimeEnv==null)
			vRuntimeEnv.setDeepth(1);
		else {
			vRuntimeEnv.setDeepth(vParentRuntimeEnv.getDeepth()+1);
		}
		vRuntimeEnv.setParentEnv(vParentRuntimeEnv);
		if(sentences!=null)
		{
			for(int i=0;i<sentences.length;i++)
				sentences[i].RunWith(this,vRuntimeEnv);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string="var y=1;\nvar x=pi/2;\n\ny:=sin(x);if(y!=sin(x)/sin(x)||1==1){print(\"yes\");\nprint(\"yes\");}\nelse\n{\nprint(\"no\"+y);\n}\n\nprint(\"y:\"+y);";
		VShellScript vss=new VShellScript();
		VCalculateInterface.init();
		try {
			vss.Run(string,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(vss.globalEnv.getVariable("y"));
	}

}
