package math;

import exception.VCalculateException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import libs.VRegex;
/**
 * 数学函数 可以求导数
 * @author Administrator
 *
 */
public class VFunctor{
	private String Equation = null;
	private Map<String, Float> valueMap = new HashMap();

	private Map<String, Float> constValueMap = new HashMap();

	public VFunctor() {
		// 设置常量
		this.constValueMap.put("pi", Float.valueOf(3.141593F));
		// this.constValueMap.put("pi", Float.valueOf(3.141593F));
	}

	public VFunctor SetEquation(String str) {
		this.Equation = str;
		return this;
	}

	public String GetEquation() {
		return this.Equation;
	}

	public int GetConstSize() {
		return this.constValueMap.size() + 1;
	}

	public boolean IsConst(String string) {
		if (string.equals("e")) {
			return true;
		}

		return this.constValueMap.containsKey(string);
	}

	public VFunctor Given(String x, Float value) {
		this.valueMap.put(x, value);
		return this;
	}

	public Float Find() {
		return calc(this.Equation);
	}

	public VFunctor Partial(String partialVariable) {
		String str = partial(this.Equation, partialVariable);
		VFunctor vFunctor = new VFunctor();
		vFunctor.SetEquation(str);
		return vFunctor;
	}

	private Float calc(String str) {
		if (str.charAt(0) == ':') {
			String[] opers = getOper(str);
			if (opers == null) {
				try {
					throw new VCalculateException();
				} catch (VCalculateException e) {
					e.printStackTrace();
				}
			}
			if (opers.length == 3) {
				String oper1 = opers[1];
				String oper2 = opers[2];
				if (opers[0].equals("+"))
					return a1(calc(oper1), calc(oper2));
				if (opers[0].equals("-"))
					return a2(calc(oper1), calc(oper2));
				if (opers[0].equals("*"))
					return a3(calc(oper1), calc(oper2));
				if (opers[0].equals("/"))
					return a4(calc(oper1), calc(oper2));
				if (opers[0].equals("^"))
					return a5(calc(oper1), calc(oper2));
				if (opers[0].equals("radical"))
					return a6(calc(oper1), calc(oper2));
				if (opers[0].equals("log"))
					return a7(calc(oper1), calc(oper2));
			} else {
				Float[] fsFloat = new Float[opers.length - 1];
				for (int ilj = 0; ilj < opers.length - 1; ilj++) {
					fsFloat[ilj] = calc(opers[(ilj + 1)]);
				}
				return new VMathUtil().run(opers[0], fsFloat);
			}
		} else {
			if (getType(str) == 1) {
				if (str.equals("e")) {
					return Float.valueOf(2.718282F);
				}
				String hh = "";
				hh = hh + str.charAt(0);
				if (str.length() == 1)
					return Float.valueOf(Float.parseFloat(hh));
				int index1 = 1;
				while ((str.length() > index1)
						&& (((str.charAt(index1) >= '0') && (str.charAt(index1) <= '9')) || (str
								.charAt(index1) == '.'))) {
					hh = hh + str.charAt(index1);
					index1++;
				}
				return Float.valueOf(Float.parseFloat(hh));
			}
			if (this.constValueMap.containsKey(str))
				return (Float) this.constValueMap.get(str);
			if (this.valueMap.containsKey(str)) {
				return (Float) this.valueMap.get(str);
			}
			System.out.println("[" + str + "] is not given.");
			try {
				throw new VCalculateException();
			} catch (VCalculateException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private Float a1(Float v1, Float v2) {
		return Float.valueOf(v1.floatValue() + v2.floatValue());
	}

	private Float a2(Float v1, Float v2) {
		return Float.valueOf(v1.floatValue() - v2.floatValue());
	}

	private Float a3(Float v1, Float v2) {
		return Float.valueOf(v1.floatValue() * v2.floatValue());
	}

	private Float a4(Float v1, Float v2) {
		return Float.valueOf(v1.floatValue() / v2.floatValue());
	}

	private Float a5(Float v1, Float v2) {
		return Float
				.valueOf((float) Math.pow(v1.floatValue(), v2.floatValue()));
	}

	private Float a6(Float v1, Float v2) {
		return Float.valueOf((float) Math.pow(v1.floatValue(),
				1.0F / v2.floatValue()));
	}

	private Float a7(Float v1, Float v2) {
		return Float.valueOf((float) (Math.log(v1.floatValue()) / Math.log(v2
				.floatValue())));
	}

	private int getType(String str) {
		if ((str.charAt(0) >= '0') && (str.charAt(0) <= '9')) {
			return 1;
		}
		if (str.equals("e"))
			return 1;
		return 0;
	}

	private String partial(String str, String partialString) {
		if (str.charAt(0) == ':') {
			String[] opers = getOper(str);
			if (opers == null) {
				try {
					throw new VCalculateException();
				} catch (VCalculateException e) {
					e.printStackTrace();
				}
			}
			if (opers.length == 3) {
				String oper1 = opers[1];
				String oper2 = opers[2];
				if (opers[0].equals("+"))
					return p1(oper1, partial(oper1, partialString), oper2,
							partial(oper2, partialString));
				if (opers[0].equals("-"))
					return p2(oper1, partial(oper1, partialString), oper2,
							partial(oper2, partialString));
				if (opers[0].equals("*"))
					return p3(oper1, partial(oper1, partialString), oper2,
							partial(oper2, partialString));
				if (opers[0].equals("/"))
					return p4(oper1, partial(oper1, partialString), oper2,
							partial(oper2, partialString));
				if (opers[0].equals("^"))
					return p5(oper1, partial(oper1, partialString), oper2,
							partial(oper2, partialString));
				if (opers[0].equals("radical"))
					return p6(oper1, partial(oper1, partialString), oper2,
							partial(oper2, partialString));
				if (opers[0].equals("log"))
					return p7(oper1, partial(oper1, partialString), oper2,
							partial(oper2, partialString));
			} else {
				String[] partials = new String[opers.length - 1];
				for (int i = 0; i < opers.length - 1; i++) {
					partials[i] = partial(opers[(i + 1)], partialString);
				}
				return new VMathUtil().getPartial(opers, partials);
			}
		} else {
			if (getType(str) == 1) {
				String hh = "";
				hh = hh + str.charAt(0);
				if (str.length() == 1)
					return "0";
				int index1 = 1;
				while ((str.length() > index1)
						&& (((str.charAt(index1) >= '0') && (str.charAt(index1) <= '9')) || (str
								.charAt(index1) == '.'))) {
					hh = hh + str.charAt(index1);
					index1++;
				}
				return "0";
			}
			if ((partialString.equals(str))
					&& (!this.constValueMap.containsKey(str))) {
				return "1";
			}
			return "0";
		}
		return null;
	}

	private String p7(String oper1, String partial, String oper2,
			String partial2) {
		return pp3(pp4("1", pp3(oper1, pp7(oper2, "e"))), partial);
	}

	private String pp7(String oper1, String oper2) {
		if (oper1.equals(oper2)) {
			if (oper1.equals("e"))
				return "1";
			if (Float.parseFloat(oper2) <= 0.0F) {
				try {
					throw new Exception("log is not right const.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return "1";
		}

		if (!oper2.equals("e")) {
			if ((getType(oper2) != 1) || (Float.parseFloat(oper2) <= 0.0F)) {
				try {
					throw new Exception("log is not right const.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (getType(oper1) == 1) {
				if (oper1.equals("e"))
					return ":log(" + oper1 + "," + oper2 + ")";
				if (Float.parseFloat(oper2) <= 0.0F) {
					try {
						throw new Exception("log is not right const.");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return a7(Float.valueOf(Float.parseFloat(oper1)),
						Float.valueOf(Float.parseFloat(oper2)))
						+ "";
			}
		}

		return ":log(" + oper1 + "," + oper2 + ")";
	}

	private String p6(String oper1, String partial, String oper12,
			String partial2) {
		return pp3(
				pp5("e", pp3(pp7(oper1, "e"), pp4("1", oper12))),
				pp1(pp3(partial, pp4(pp4("1", oper12), oper1)),
						pp3(pp3(pp7(oper1, "e"), partial2),
								pp2("0", pp4("1", pp3(oper12, oper12))))));
	}

	private String p5(String oper1, String partial, String oper12,
			String partial2) {
		return pp3(
				pp5("e", pp3(pp7(oper1, "e"), oper12)),
				pp1(pp3(partial, pp4(oper12, oper1)),
						pp3(pp7(oper1, "e"), partial2)));
	}

	private String p4(String oper1, String partial, String oper12,
			String partial2) {
		return pp4(pp2(pp3(partial, oper12), pp3(partial2, oper1)),
				pp3(oper12, oper12));
	}

	private String p3(String oper1, String partial, String oper12,
			String partial2) {
		return pp1(pp3(oper1, partial2), pp3(oper12, partial));
	}

	private String p2(String oper1, String partial, String oper12,
			String partial2) {
		return pp2(partial, partial2);
	}

	private String p1(String oper1, String partial, String oper12,
			String partial2) {
		return pp1(partial, partial2);
	}

	private String pp1(String a, String b) {
		if ((a.equals("0")) || (a.equals("0.0"))) {
			return b;
		}
		if ((b.equals("0")) || (b.equals("0.0"))) {
			return a;
		}
		if ((!a.equals("e")) && (!b.equals("e")) && (getType(a) == 1)
				&& (getType(b) == 1)) {
			return Float.parseFloat(a) + Float.parseFloat(b) + "";
		}
		return ":+(" + a + "," + b + ")";
	}

	private String pp2(String a, String b) {
		if ((b.equals("0")) || (b.equals("0.0"))) {
			return a;
		}
		if (a.equals(b)) {
			return "0";
		}
		if ((!a.equals("e")) && (!b.equals("e")) && (getType(a) == 1)
				&& (getType(b) == 1)) {
			return Float.parseFloat(a) - Float.parseFloat(b) + "";
		}
		return ":-(" + a + "," + b + ")";
	}

	private String pp3(String a, String b) {
		if ((a.equals("0")) || (a.equals("0.0")) || (b.equals("0"))
				|| (b.equals("0.0"))) {
			return "0";
		}
		if ((a.equals("1")) || (a.equals("1.0"))) {
			return b;
		}
		if ((b.equals("1")) || (b.equals("1.0"))) {
			return a;
		}

		if ((!a.equals("e")) && (!b.equals("e")) && (getType(a) == 1)
				&& (getType(b) == 1)) {
			return Float.parseFloat(a) * Float.parseFloat(b) + "";
		}
		return ":*(" + a + "," + b + ")";
	}

	private String pp5(String a, String b) {
		if ((a.equals("0")) || (a.equals("0.0"))) {
			return "0";
		}
		if ((b.equals("0")) || (b.equals("0.0"))) {
			return "1";
		}
		if ((b.equals("1")) || (b.equals("1.0"))) {
			return a;
		}
		return ":^(" + a + "," + b + ")";
	}

	private String pp4(String a, String b) {
		if ((a.equals("0")) || (a.equals("0.0"))) {
			return "0";
		}
		if ((b.equals("0")) || (b.equals("0.0"))) {
			try {
				throw new Exception("/ Zero ");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ((!a.equals("e")) && (!b.equals("e")) && (getType(a) == 1)
				&& (getType(b) == 1)) {
			return Float.parseFloat(a) / Float.parseFloat(b) + "";
		}
		return ":/(" + a + "," + b + ")";
	}

	public static  String[] getOper(String str) {
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

	public static void main(String[] args) {
		VFunctor functor = new VFunctor();
		functor.SetEquation(":+(:*(x,:cos(x)),:sin(x))");
		VFunctor functor2 = functor.Partial("x1");
		functor.Given("x", Float.valueOf(2.0F));

		System.out.println(functor.Find());
	}

	public String toString() {
		return "VFunctor [Equation=" + this.Equation + "]";
	}
	protected Object[] getUnGiven() {
		String string = this.GetEquation();
		Map<String, Integer> maptemp=new HashMap<String, Integer>();
		String[] strs0 = VRegex.getRegex("^([a-zA-Z][a-zA-Z0-9_]*)$", string, 0);
		if ((strs0 != null) && (strs0.length != 0)) {
			int index = 0;
		for (int h = 0; h < strs0.length; h++) {
			if (!this.IsConst(strs0[h]))
				maptemp.put(strs0[h], 1);
		}
		return  maptemp.keySet().toArray();
	}
	String[] strs1 = VRegex.getRegex("\\(([a-zA-Z][a-zA-Z0-9_]*),", string, 1);
	String[] strs2 = VRegex.getRegex(",([a-zA-Z][a-zA-Z0-9_]*)\\)", string, 1);
	String[] strs3 = VRegex.getRegex("\\(([a-zA-Z][a-zA-Z0-9_]*)\\)", string, 1);
	String[] strs4 = VRegex.getRegex(",([a-zA-Z][a-zA-Z0-9_]*),", string, 1);
	int cc = 0;
	for (int i = 0; i < strs1.length; i++) {
		if (!this.IsConst(strs1[i]))
			cc++;
	}
	for (int k = 0; k < strs2.length; k++) {
		if (!this.IsConst(strs2[k]))
			cc++;
	}
	for (int i = 0; i < strs3.length; i++) {
		if (!this.IsConst(strs3[i]))
			cc++;
	}
	for (int k = 0; k < strs4.length; k++) {
		if (!this.IsConst(strs4[k]))
			cc++;
	}
	String[] strs_ = new String[cc];
	int index = 0;
	for (int i = 0; i < strs1.length; i++) {
		if (this.IsConst(strs1[i]))
			continue;
		strs_[(index++)] = strs1[i];
	}
	for (int k = 0; k < strs2.length; k++) {
		if (!this.IsConst(strs2[k]))
			strs_[(index++)] = strs2[k];
	}
	for (int i = 0; i < strs3.length; i++) {
		if (this.IsConst(strs3[i]))
			continue;
		strs_[(index++)] = strs3[i];
	}
	for (int k = 0; k < strs4.length; k++) {
		if (!this.IsConst(strs4[k]))
			strs_[(index++)] = strs4[k];
	}
	if ((strs_ != null) && (strs_.length != 0)) {
		index = 0;
		for (int h = 0; h < strs_.length; h++) {
			if (!this.IsConst(strs_[h]))
				maptemp.put(strs_[h], 1);
		}
		return maptemp.keySet().toArray();
	}
		return null;
	}
}

/*
 * Location: E:\EAMath.jar Qualified Name: math.VFunctor JD-Core Version: 0.6.0
 */