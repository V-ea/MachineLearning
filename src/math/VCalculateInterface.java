package math;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.VCalculateException;


public abstract class  VCalculateInterface {

	protected String Equation = null;
	public static int charType[];
	
	private static int OPER = 1;
	private static int IDEN = 2;
	private static int NUMBER = 3;
	private static int LEFT = 4;
	
	private int BrokenSignal = 0;
	public abstract Float Calc();
	public abstract void Given(String x, Float v) ;
	public static void init() {
		charType=new int[128];
		for (int k = 0; k < 128; k++)
			charType[k] = -1;
		for (char i = 'a'; i <= 'z'; i = (char) (i + '\001')) {
			charType[i] = 0;
		}
		for (char i = 'A'; i <= 'Z'; i = (char) (i + '\001')) {
			charType[i] = 0;
		}
		charType[48] = 101;
		for (char i = '1'; i <= '9'; i = (char) (i + '\001')) {
			charType[i] = 102;
		}
		charType[46] = 103;
		charType[43] = 1;
		charType[45] = 1;
		charType[42] = 2;
		charType[47] = 2;
		charType[94] = 3;
		charType[40] = 201;
		charType[95] = 0;//_
	}
	public abstract Object[] GetUnGiven();
	protected String changeFrom(String str) {
		Stack stackId = new Stack();
		Stack stackOper = new Stack();
		int length = str.length();
		String resultString = "";
		int LastStringType = 0;
		for (int i = 0; i < length;) {
			if (this.BrokenSignal == 1) {
				return null;
			}
			char ch = str.charAt(i);
			switch (this.charType[ch]) {
			case 1:

			case 2:

			case 3:
				i = accept_single_operator(str, i, resultString, stackId,
						stackOper);
				LastStringType = OPER;

				break;
			case 0:
				i = accept_identifier(str, i, stackId, stackOper);
				LastStringType = IDEN;

				break;
			case 101:

			case 102:
				i = accept_number(str, i, stackId, stackOper);
				LastStringType = NUMBER;

				break;
			case 201:
				if (LastStringType != IDEN) {
					i = accept_xiaokuohao(str, i, stackId, stackOper);
				} else {
					i = accept_function(str, i, stackId, stackOper);
				}
				LastStringType = LEFT;
				break;
			default:
				try {
					throw new Exception("unaccepted char:" + ch);
				} catch (Exception e) {
					e.printStackTrace();
					this.BrokenSignal = 1;
				}

				try {
					throw new Exception("unrecognized char:");
				} catch (Exception e) {
					e.printStackTrace();
					this.BrokenSignal = 1;
				}
			}
		}
		while (!stackOper.empty()) {
			String s2 = (String) stackId.pop();
			String s1 = (String) stackId.pop();
			String oper = (String) stackOper.pop();
			s1 = ":" + oper + "(" + s1 + "," + s2 + ")";

			stackId.push(s1);
		}
		return (String) stackId.pop();
	}
	private int accept_function(String string, int index,
			Stack<String> stackId, Stack<String> stackOper) {
		String string2 = "";
		int from = index + 1;
		int kuohaocount = 0;
		while (true) {
			if (string.charAt(from) == '(') {
				kuohaocount++;
			}
			if (string.charAt(from) == ')') {
				kuohaocount--;
				if (kuohaocount == -1) {
					from++;
					break;
				}
				if(kuohaocount<-1)
				{
					try {
						throw new Exception("Error Syntax.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			string2 = string2 + string.charAt(from);
			from++;
		}
		String[] strs=string2.split(",");
		String ret="";
		for (int ilj = 0; ilj < strs.length; ilj++) {
			if(ilj!=0)
				ret+=",";
			ret += changeFrom(strs[ilj]);
		}

		String string3 = (String) stackId.pop();
		stackId.push(":" + string3 + "(" + ret + ")");
		return from;
	}
	public String changeBack(String str,int upperOperLevel) {
		if (str.charAt(0) == ':') {
			String[] opers = VFunctor.getOper(str);
			if (opers == null) {
				try {
					throw new VCalculateException();
				} catch (VCalculateException e) {
					e.printStackTrace();
				}
			}
			if (opers.length == 3) {
				opers[0]=opers[0].trim();
				String oper1 = opers[1];
				String oper2 = opers[2];
				if (opers[0].equals("+")||opers[0].equals("-")||opers[0].equals("*")||opers[0].equals("/")||opers[0].equals("^"))
				{
					int operLevel=this.charType[(int)opers[0].charAt(0)];//error
					if(operLevel<upperOperLevel)
					{
						return "("+changeBack(oper1, operLevel)+opers[0]+changeBack(oper2, operLevel)+")";
					}
					return changeBack(oper1, operLevel)+opers[0]+changeBack(oper2, operLevel);
				}
				if (opers[0].equals("radical"))
					return "radical("+changeBack(oper1, 1)+","+changeBack(oper2, 1)+")";
				if (opers[0].equals("log"))
					return "log("+changeBack(oper1, 1)+","+changeBack(oper2, 1)+")";
			} else {
				String ret = "";
				for (int ilj = 0; ilj < opers.length - 1; ilj++) {
					if(ilj!=0)
						ret+=",";
					ret += changeBack(opers[(ilj + 1)],1);
				}
				return opers[0]+"("+ret+")";
			}
		}
		return str;
	}
	private String[] getParameter(String str)
	{
		int length_ = str.length();
		int retlen = 0;
		int jjcount = 0;
		for (int l = 0; l < length_; l++) {
			if (str.charAt(l) == '(') {
				jjcount++;
			}
			if (str.charAt(l) == ')') {
				jjcount--;
			}
			if ((str.charAt(l) == ',') && (jjcount == 1))
				retlen++;
		}
		String[] retval = new String[retlen + 2];
		int retindex = 0;
		if (str.charAt(0) == ':') {
			String operString = "";
			String v1 = "";
			String v2 = "";
			int index = 1;
			while (str.charAt(index) != '(') {
				operString = operString + str.charAt(index);
				index++;
			}
			retval[(retindex++)] = operString;
			index++;
			int jcount = 0;
			while (retlen > 0) {
				v1 = "";
				while ((str.charAt(index) != ',') || (jcount != 0)) {
					if (str.charAt(index) == '(') {
						jcount++;
					}
					if (str.charAt(index) == ')') {
						jcount--;
					}
					v1 = v1 + str.charAt(index);
					index++;
				}
				index++;
				retval[(retindex++)] = v1;

				retlen--;
			}
			while ((str.length() > index)
					&& ((str.charAt(index) != ')') || (jcount != 0))) {
				if (str.charAt(index) == '(') {
					jcount++;
				}
				if (str.charAt(index) == ')') {
					jcount--;
				}
				v2 = v2 + str.charAt(index);
				index++;
			}
			retval[(retindex++)] = v2;
			return retval;
		}
		return null;
	}
	public VCalculateInterface SetEquation(String str) {
		
		this.Equation = str;
		GetUnGiven();
		return this;
	}
	@Override
	public String toString() {
		return Equation;
	}
	public String GetEquation() {
		return Equation;
	}

	
	private int accept_number(String string, int index, Stack<String> stackId,
			Stack<String> stackOper) {
		int length = string.length();
		int from = index;
		int exists = 0;
		String string2 = "";
		string2 = string2 + string.charAt(index);
		from++;
		while ((from < length)
				&& ((this.charType[string.charAt(from)] == 101)
						|| (this.charType[string.charAt(from)] == 102) || ((this.charType[string
						.charAt(from)] == 103) && (exists != 1)))) {
			if (this.charType[string.charAt(from)] == 103) {
				exists = 1;
			}
			string2 = string2 + string.charAt(from);
			from++;
		}

		stackId.push(string2);
		return from;
	}

	private int accept_identifier(String string, int index,
			Stack<String> stackId, Stack<String> stackOper) {
		int length = string.length();
		int from = index;
		String string2 = "";
		string2 = string2 + string.charAt(index);
		from++;
		while ((from < length)
				&& ((this.charType[string.charAt(from)] == 0)
						|| (this.charType[string.charAt(from)] == 101) || (this.charType[string
						.charAt(from)] == 102))) {
			string2 = string2 + string.charAt(from);
			from++;
		}

		stackId.push(string2);
		return from;
	}

	private int accept_single_operator(String string, int index,
			String resultString, Stack<String> stackId, Stack<String> stackOper) {
		String string2 = "";
		char ch = string.charAt(index);

		int chtype = this.charType[ch];
		if (stackOper.empty()) {
			string2 = string2 + ch;
			stackOper.push(string2);
			return index + 1;
		}
		int chtop = this.charType[((String) stackOper.peek()).charAt(0)];
		if (chtop < chtype) {
			string2 = string2 + string.charAt(index);
			stackOper.push(string2);
		} else {
			String str = "";
			while (chtop >= chtype) {
				if (stackId.size() < 2) {
					try {
						throw new Exception("unaccepted stack.");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				String s2 = (String) stackId.pop();
				String s1 = (String) stackId.pop();
				String oper = (String) stackOper.pop();
				s1 = ":" + oper + "(" + s1 + "," + s2 + ")";

				stackId.push(s1);
				if (stackOper.empty()) {
					break;
				}
				chtop = this.charType[((String) stackOper.peek()).charAt(0)];
			}
			string2 = string2 + ch;

			stackOper.push(string2);
		}
		return index + 1;
	}

	private int accept_xiaokuohao(String string, int index,
			Stack<String> stackId, Stack<String> stackOper) {
		String string2 = "";
		int from = index + 1;
		int kuohaocount = 0;
		while (from<string.length()) {
			if (string.charAt(from) == '(') {
				kuohaocount++;
			}
			if (string.charAt(from) == ')') {
				kuohaocount--;
				if (kuohaocount == -1) {
					from++;
					break;
				}
			}

			string2 = string2 + string.charAt(from);
			from++;
		}

		stackId.push(changeFrom(string2));
		return from;
	}
}
