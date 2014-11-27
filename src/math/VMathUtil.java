package math;

import java.util.HashMap;

import java.util.Map;

public class VMathUtil {
	private Map<String, Integer> lengthmap = new HashMap();

	public VMathUtil() {
		this.lengthmap.put("sin", Integer.valueOf(1));
		this.lengthmap.put("cos", Integer.valueOf(1));
		//this.idmap.put("sin", Integer.valueOf(1));
		//this.idmap.put("cos", Integer.valueOf(2));
	}

	public Float run(String str, Float[] floats) {
		Integer len = (Integer) this.lengthmap.get(str);
		if ((len == null) || (floats.length != len.intValue())) {
			try {
				throw new Exception(
						"no such function or parameters'count error:" + str
								+ "\n");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		if (Func.get(str) == null) {
			System.out.println("error.");
			return null;
		}
		switch (Func.get(str)) {
		case sin:
			return Float.valueOf((float) Math.sin(floats[0].floatValue()));
		case cos:
			return Float.valueOf((float) Math.cos(floats[0].floatValue()));
		}

		return Float.valueOf(0.0F);
	}

	public String getPartial(String[] strs, String[] partial) {
		Integer len = (Integer) this.lengthmap.get(strs[0]);
		if ((len == null) || (strs.length != len.intValue() + 1)) {
			try {
				throw new Exception(
						"no such function or parameters'count error:" + strs[0]
								+ "\n");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		if (Func.get(strs[0]) == null) {
			System.out.println("error.");
			return null;
		}
		switch (Func.get(strs[0])) {
		case sin:
			return pp3(":cos(" + strs[1] + ")", partial[0]);
		case cos:
			return pp2("0", pp3(":sin(" + strs[1] + ")", partial[0]));
		}

		return null;
	}

	private int getType(String str) {
		if ((str.charAt(0) >= '0') && (str.charAt(0) <= '9')) {
			return 1;
		}
		if (str.equals("e"))
			return 1;
		return 0;
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

	public static void main(String[] args) {
		Func.get("sin");
	}

	public static enum Func {
		sin, cos;

		public static Func get(String animal) {
			return valueOf(animal.toLowerCase());
		}

	}

}

/*
 * Location: E:\EAMath.jar Qualified Name: math.VMathUtil JD-Core Version: 0.6.0
 */