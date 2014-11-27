package math;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import libs.VRandom;
import libs.VRegex;
import script.VRuntimeEnv;

public class VBoolean {
	private static String COMP_LOGI = "^([^=]*)(==|!=|>|>=|<=|<)([^=]*?)$";
	//private static String SELF_LOGI = "^([^=]*)$";
	private String equation = null;
	private String last_equation=null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (false & false || true && false) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		VBoolean vBoolean = new VBoolean();
		vBoolean.SetEquation("((((1!=a)||(1==1)&&(2==3))))");
		VRuntimeEnv vRuntimeEnv=new VRuntimeEnv();
		vRuntimeEnv.SetVariable("a", (Float)33f);
		vRuntimeEnv.SetVariable("b", (Float)3f);
		vRuntimeEnv.SetVariable("c", (Float)3f);
		//vRuntimeEnv.SetVariable("b", (Float)3f);
		try {
			System.out.println(vBoolean.Calc(vRuntimeEnv));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String GetEquation() {
		return equation;
	}

	public void SetEquation(String equation) {
		this.equation = equation;
	}
	public void SetLastEquation(String equation) {
		this.last_equation = equation.trim();
	}
	private boolean Compare(Float float1,Float float2,String operString)
	{
		//System.out.println(float1+"="+float2);
		if (operString.equals("==")) {
			return float1.equals(float2);
		}
		if (operString.equals("!=")) {
			return  !float1.equals(float2);
		}
		if (operString.equals(">")) {
			return  float1 > float2;
		}
		if (operString.equals(">=")) {
			return  float1 >= float2;
		}
		if (operString.equals("<")) {
			return  float1 < float2;
		}
		if (operString.equals("<=")) {
			return  float1 <= float2;
		}
		return false;
	}
	public static int balanceOfPair(String str,char left,char right)
	{
		int len=str.length();
		int index =0;
		int k=0;
		while (index<len) {
			char ch=str.charAt(index++);
			if(ch==left)
				k++;
			if(ch==right)
				k--;
			if(k<0)
			{
				return -1;
			}
			
		}
		return k;
	}
	public boolean Calc(VRuntimeEnv vRuntimeEnv) throws Exception {
		if (equation == null) {
			throw new NullPointerException();
		}
		String tempString = equation.trim();
		int len = tempString.length(), index = 0, pa = 0, endwith = 0;
		
		String piece = "";
		//把组合句分隔开
		//System.out.println(tempString);
		List<String> list = new ArrayList<String>();
		while (index < len - 1) {
			char ch = tempString.charAt(index);
			if (ch == '(')
				pa++;
			else if (ch == ')')
				pa--;
			if(pa<0)
				throw new Exception("( ) not matched."+equation);
			if (((ch == '&' && '&' == tempString.charAt(index + 1)) || (ch == '|' && '|' == tempString
					.charAt(index + 1))) && pa == 0) {
				list.add(piece.trim());
				list.add("" + ch + tempString.charAt(index + 1));
				piece = "";
				index++;
				endwith = 1;
			} else {
				piece += ch;
				endwith = 0;
			}
			index++;
		}
		if(tempString.charAt(len - 1)==')')
		{
			pa--;
		}
		if (pa != 0) {
			throw new Exception("( ) not matched."+equation);
		}
		if (endwith == 1) {//不能以操作符结束
			throw new Exception("unknown format.bad end:" + tempString);
		}
		piece += tempString.charAt(len - 1);
		list.add(piece.trim());
		Object[] objects = list.toArray();
		VRegex regex = new VRegex();
		int and_or = -1;
		boolean ret = true;//返回值
		boolean cmd = true;//语句与操作符切换
		for (Object object : objects) {
			String objString=object.toString();
			//System.out.println(object.toString());
			if (cmd == true) {//语句
				//能去则去()
				if(objString.startsWith("(")&&objString.endsWith(")"))
				{
					int k=balanceOfPair(objString.substring(1, objString.length()-1), '(', ')');
					if(k==0)
						object=objString.substring(1, objString.length()-1);
				}
				Matcher matcher = regex.Regex(COMP_LOGI, object.toString()
						.trim());
				boolean temp = true;
				if (matcher.find()) {//临时变量
					String r1="";r1=VRandom.random();while(vRuntimeEnv.getVariable(r1)!=null){r1=VRandom.random();}
					String r2="";r2=VRandom.random();while(vRuntimeEnv.getVariable(r2)!=null){r2=VRandom.random();}
					VEasyFunctor veFunctor=new VEasyFunctor();
					VEasyFunctor veFunctor2=new VEasyFunctor();
					veFunctor.SetEquation(matcher.group(1));
					veFunctor2.SetEquation(matcher.group(3));
					vRuntimeEnv.SetVariable(r1, veFunctor);
					vRuntimeEnv.SetVariable(r2, veFunctor2);
					Float float1 = vRuntimeEnv.Calc(r1, 1);
					Float float2 = vRuntimeEnv.Calc(r2, 1);
					String operString = matcher.group(2).trim();
					vRuntimeEnv.RemoveVariable(r1);
					vRuntimeEnv.RemoveVariable(r2);
					temp=Compare(float1, float2, operString);
				} else {
					if(objects.length==1&&last_equation!=null&&last_equation.equals(equation))
					{
						throw new Exception("unknown format:"+equation);
					}
					VBoolean vBoolean=new VBoolean();
					vBoolean.SetEquation(object.toString());
					if(objects.length==1)
						vBoolean.SetLastEquation(equation);
					temp=vBoolean.Calc(vRuntimeEnv);
				}
				//System.out.println(temp+"-"+ret);
				if(and_or==-1){ret=temp;}//第一个语句直接赋值
				//虽然不严密但是考虑到以上分割已经做到 语句 操作符相间，所以不用处理 连续两个语句的问题
				else
				{
					if(and_or==1)
					{
						ret=ret&&temp;
					}
					else {
						ret=ret||temp;
					}
				}
				cmd=false;
			}
			else  {//操作符
				cmd=true;
				String string=object.toString();
				if(string.equals("&&"))
				{
					and_or=1;//与运算
					continue;
				}
				if(string.equals("||"))
				{
					and_or=0;//或运算
					continue;
				}
				throw new Exception("unknown format.");
			}
		}
		return ret;
	}
}
