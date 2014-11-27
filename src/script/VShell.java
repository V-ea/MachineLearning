package script;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import math.VCalculateInterface;
import math.VEasyFunctor;

public class VShell {
	private int history_count = 0;
	private Queue<String> cmd_historyQueue = new LinkedList();
	private Map<String, VCalculateInterface> mapAssignedMap = new HashMap();

	public void waitForInput() {
		System.out.print("ea>>>" + this.history_count + ":");
		this.history_count += 1;
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		try {
			handler(string);
			this.cmd_historyQueue.add(string);
		} catch (Exception localException) {
		}
	}

	private void handler(String s) {
		s = s.toLowerCase().trim();
		if (is(s, "exit")) {
			System.out.println("GoodBye.");
			System.exit(0);
		} else {
			if (is(s, "history")) {
				for (String x : this.cmd_historyQueue) {
					System.out.println(x);
				}
				return;
			}
			if (is(s, "help")) {
				help();
				return;
			}
			if (s.startsWith("ungiven ")) {
				String p = s.substring(8);
				if (this.mapAssignedMap.containsKey(p)) {
					for (Object str11 : ((VEasyFunctor) this.mapAssignedMap
							.get(p)).GetUnGiven())
						System.out.println(str11);
					System.out.println("are not given.give value to them.");
				} else {
					System.out.println("error:no such equation.");
				}
				return;
			}
			if (s.startsWith("partial ")) {
				String p = s.substring(8);
				String[] ps = p.trim().split(" ");
				if (ps.length != 3) {
					System.out
							.println("Usage:partial [function] [partial] [partial-x]");
				} else {
					ps[0] = ps[0].trim();
					ps[1] = ps[1].trim();
					ps[2] = ps[2].trim();
					if ((!checkIden(ps[0])) || (!checkIden(ps[1]))
							|| (!checkIden(ps[1]))) {
						System.out
								.println("function name or partial name must be identifiers.[a-zA-Z0-9_]");
						return;
					}
					if (this.mapAssignedMap.containsKey(ps[1])) {
						System.out
								.println("error:target has been set,choose another partial name.");
						return;
					}
					if (this.mapAssignedMap.containsKey(ps[0].trim())) {
						VEasyFunctor vef = (VEasyFunctor) this.mapAssignedMap
								.get(ps[0].trim());
						this.mapAssignedMap.put(ps[1],
								vef.Partial(ps[2].trim()));
						System.out.println("done.");
					} else {
						System.out.println("error:" + p
								+ " this function is not given.");
					}
				}
				return;
			}
			if (s.startsWith("calc ")) {
				String p = s.substring(5).trim();
				System.out.println(p);
				if (!checkIden(p)) {
					System.out
							.println("function name or partial name must be identifiers.[a-zA-Z0-9_]");
					return;
				}
				System.out.println("calculating...");
				if (this.mapAssignedMap.containsKey(p)) {
					VEasyFunctor vef = (VEasyFunctor) this.mapAssignedMap
							.get(p);
					Float result = Float.valueOf(0.0F);
					result = Float.valueOf(Calc(vef, 1));

					System.out.println("ans=" + result);

					this.mapAssignedMap.put("ans",
							new VEasyFunctor().SetEquation(result + ""));
				} else {
					System.out.println("error:" + p + " is not defined/set.");
				}
				return;
			}
			if (isassign(s)) {
				int len = s.length();
				s = s.toLowerCase();
				int i = 0;
				String left = "";
				String right = "";
				while (i < len)
					if ((i == 0) && (s.charAt(i) >= 'a')
							&& (s.charAt(i) <= 'z')) {
						left = left + s.charAt(i);
						i++;
					} else {
						if (i == 0) {
							System.out
									.println("function name or partial name must be identifiers.[a-zA-Z0-9_]");
							return;
						}

						if (((s.charAt(i) < 'a') || (s.charAt(i) > 'z'))
								&& ((s.charAt(i) < '0') || (s.charAt(i) > '9'))
								&& (s.charAt(i) != '_'))
							break;
						left = left + s.charAt(i);

						i++;
					}
				if (i >= len) {
					if (this.mapAssignedMap.containsKey(left)) {
						System.out.println(((VEasyFunctor) this.mapAssignedMap
								.get(left)).GetEquation());
					}
					return;
				}
				if ((i <= len - 2) && (s.charAt(i) == '=')) {
					i++;
					while (i < len) {
						right = right + s.charAt(i);
						i++;
					}
					VEasyFunctor vef = new VEasyFunctor();
					vef.SetEquation(right);
					if (this.mapAssignedMap.containsKey(left)) {
						System.out
								.println("old:"
										+ ((VEasyFunctor) this.mapAssignedMap
												.get(left)).GetEquation());
					}
					System.out.println("[" + left + "]=" + vef.GetEquation());
					this.mapAssignedMap.put(left, vef);
				} else {
					System.out
							.println("function name must be identifiers.[a-zA-Z0-9_]");
				}
			}
		}
	}

	private boolean checkIden(String s) {
		int i = 0;
		int len = s.length();
		while (i < len) {
			if ((i != 0) || (s.charAt(i) < 'a') || (s.charAt(i) > 'z')) {
				if (((s.charAt(i) < 'a') || (s.charAt(i) > 'z'))
						&& ((s.charAt(i) < '0') || (s.charAt(i) > '9'))
						&& (s.charAt(i) != '_')) {
					return false;
				}
			}
			i++;
		}
		return true;
	}

	private void help() {
		System.out.println("\t1.You can Assign Function Like this: ");
		System.out.println("\t\t\ty=x+1");
		System.out.println("\t*caution:Don't write any blank here.");
		System.out
				.println("\t2.for partial .\n\t\t\tpartial [function] [target_function] [partial_variable]");
		System.out
				.println("\t3.for calculating.\n\t\t\tcalc [variable/function]");
		System.out.println("\t4.for exit\n\t\t\texit");
		System.out.println("\t5.for history commands.\n\t\t\thistory");
		System.out
				.println("\t6.to see the values need to give/assign.\n\t\t\tungiven [function]");
		System.out
				.println("\t\t\t\t\tWISH YOU HAVE FUN~!\n\t\t\t\t\t\tEAPCHEN/CHENYIPU");
	}

	private float Calc(VEasyFunctor vef, int deeps) {
		if (deeps == 30) {
			System.out.println("error:endless running...shutdown.");
			return 0.0F;
		}
		Object[] strs = vef.GetUnGiven();
		if (strs != null && strs.length != 0)
			for (Object str11 : strs) {
				if (!this.mapAssignedMap.containsKey(str11.toString().trim())) {
					continue;
				}
				VEasyFunctor vef2 = (VEasyFunctor) this.mapAssignedMap
						.get(str11.toString().trim());
				float sFloat = 0.0F;
				sFloat = Calc(vef2, deeps + 1);
				vef.Given(str11.toString().trim(), sFloat);
			}
		return vef.Find();
	}

	private boolean isassign(String s) {
		return s.charAt(0) != ':';
	}

	private boolean is(String str1, String str) {
		return str1.equals(str);
	}

	public static void main(String[] args) throws IOException {
		VShell shell = new VShell();
		System.out.println(" ---------------------------------------------");
		System.out.println("|| MATH TOOLS By EapChen. ALL RIGHTS RESERVED.||");
		System.out.println("|| EMAIL:390270720@qq.com                     ||");
		System.out.println(" ---------------------------------------------");
		while (true)
			shell.waitForInput();
	}
}

/*
 * Location: E:\EAMath.jar Qualified Name: math.VShell JD-Core Version: 0.6.0
 */