package script;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;

import libs.VRandom;
import libs.VRegex;
import math.VBoolean;
import math.VEasyFunctor;
import exception.VSyntaxException;

public class VSentence {
	/**
	 * 根据一段代码生成语句顺序数组。
	 * 
	 * @param sentences
	 * @return
	 */

	// private static String
	// FUNC_LOGI="([a-zA-Z_]{1}[a-zA-Z_0-9]*)[ ]*==[ ]*([a-zA-Z_]{1}[a-zA-Z_0-9]*)[ ]*;";
	private static String VAR_SEN = "var[ ]+([a-zA-Z_]{1}[a-zA-Z_0-9]*)[ ]*=([^;]*);$";
	private static String VAR_ONLY = "var[ ]+([a-zA-Z_]{1}[a-zA-Z_0-9]*)[ ]*;$";
	private static String VAR__SEN = "var[ ]+([a-zA-Z_]{1}[a-zA-Z_0-9]*)[ ]*:=([^;]*);$";
	private static String VAR2_SEN = "([a-zA-Z_]{1}[a-zA-Z_0-9]*)[ ]*=([^;]*);$";
	private static String VAR2__SEN = "([a-zA-Z_]{1}[a-zA-Z_0-9]*)[ ]*:=([^;]*);$";
	private static String WHILE = "while([^\\{]*)";
	private static String IF = "if([^\\{]*)";
	private static String ELSE = "else[\\s]*";
	//
	// private static String IDEN="([a-zA-Z_]{1}[a-zA-Z_0-9]*)";
	private static String NUMBER = "([\\-]{0,1}[1-9][0-1]*\\.[0-1]*)";
	// functions
	private static String PRINT = "print\\([\\s]*(.*)[\\s]*\\)";
	private static String PRINT_STRING = "\"([^\"]*)\"";

	public static VSentence[] generateSentences(String sentences)
			throws VSyntaxException {
		int index = 0, len = sentences.length();
		int inkuohao = 0, inkuohao2 = 0, sen_count = 0;
		List<String> ssList = new ArrayList<String>();
		String senString = "";
		while (index < len) {
			if (inkuohao < 0 || inkuohao2 < 0) {
				throw new VSyntaxException();
			}
			char ch = sentences.charAt(index);
			if (ch == '\t' || ch == '\r' || ch == '\n') {
				ch = ' ';
			}
			if (inkuohao == 0 && inkuohao2 == 0 && ';' == ch) {
				senString += ';';
				sen_count++;
				ssList.add(senString);
				senString = "";
			} else {
				senString += ch;
				if ('(' == ch) {
					inkuohao2++;
				}
				if (')' == ch) {
					inkuohao2--;
				}
				if ('{' == ch) {
					inkuohao++;
				}
				if ('}' == ch) {
					inkuohao--;
					if (inkuohao == 0 && inkuohao2 == 0) {
						sen_count++;
						ssList.add(senString);
						senString = "";
					}
				}
			}
			index++;
		}
		if (inkuohao != 0 || inkuohao2 != 0 || !senString.trim().equals("")) {
			throw new VSyntaxException();
		}
		Object[] arrObjects = ssList.toArray();
		VSentence[] retSentences = new VSentence[arrObjects.length];
		int c = 0, i = 0;
		for (Object oj : arrObjects) {
			// System.out.println((c++)+":"+oj.toString().trim());//for debug
			retSentences[i] = new VSentence();
			retSentences[i++].setSentence(oj.toString().trim());
		}
		return retSentences;
	}

	// 一个语句
	private String sentence = null;

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	private Float accept_number(String str) {
		return Float.parseFloat(str);
	}

	private boolean accept_logic(String str, VRuntimeEnv vRuntimeEnv)
			throws Exception {
		// System.out.println(str);
		VBoolean vBoolean = new VBoolean();
		vBoolean.SetEquation(str);
		return vBoolean.Calc(vRuntimeEnv);
	}

	//
	public void RunWith(VShellScript vShellScript, VRuntimeEnv vRuntimeEnv)
			throws Exception {
		// TODO Auto-generated method stub
		// System.out.println(sentence);
		if (sentence.trim().equals("")) {
			return;
		}
		VRegex rgRegex = new VRegex();
		Matcher matcher = null;
		if (this.sentence.startsWith("if(")) {
			matcher = rgRegex.Regex(IF, sentence);
			if (matcher.find()) {
				// System.out.println(matcher.group(1));
				boolean judge = accept_logic(matcher.group(1), vRuntimeEnv);
				String sent = sentence.replaceFirst(IF, "").trim();
				sent = sent.substring(1, sent.length() - 1);
				// 因为分割时候已经匹配好 所以不用检验length
				// System.out.println(matcher.group(2));
				if (judge) {
					vShellScript.Run(sent, vRuntimeEnv);
					vShellScript.LastIsIf(-1);
				} else {
					vShellScript.LastIsIf(1);
				}
				return;
			}
		}
		if (this.sentence.startsWith("else")) {
			if (0 == vShellScript.LastIsIf())
				throw new Exception("if segment needed.");
			if (-1 == vShellScript.LastIsIf()) {
				// reset
				vShellScript.LastIsIf(0);
				return;
			}
			matcher = rgRegex.Regex(ELSE, sentence);
			// reset
			vShellScript.LastIsIf(0);
			if (matcher.find()) {
				String sent = sentence.replaceFirst(ELSE, "").trim();
				sent = sent.substring(1, sent.length() - 1);
				vShellScript.Run(sent, vRuntimeEnv);
				return;
			}
		}
		// reset
		vShellScript.LastIsIf(0);
		if (this.sentence.startsWith("var ")) {
			//var a;
			matcher = rgRegex.Regex(VAR_ONLY, sentence);
			if (matcher.find()) {
				vRuntimeEnv.SetVariable(matcher.group(1), (Float) 0.0f);
				return;
			}
			//var a=sin(X);
			matcher = rgRegex.Regex(VAR_SEN, sentence);//
			if (matcher.find()) {
				//var a=9.0;
				Matcher matcher2 = rgRegex
						.Regex(NUMBER + "$", matcher.group(2));
				if (matcher2.find()) {
					vRuntimeEnv.SetVariable(matcher.group(1),
							accept_number(matcher.group(2)));
					return;
				}
				//var a=func();
				VEasyFunctor vef = new VEasyFunctor();
				vef.SetEquation(matcher.group(2));
				vRuntimeEnv.SetVariable(matcher.group(1), vef);
				return;
			}
			matcher = rgRegex.Regex(VAR__SEN, sentence);//
			if (matcher.find()) {
				Matcher matcher4 = rgRegex
						.Regex(NUMBER + "$", matcher.group(2));
				if (matcher4.find()) {
					vRuntimeEnv.SetVariable(matcher.group(1),
							accept_number(matcher.group(2)));
					return;
				}
				VEasyFunctor vef = new VEasyFunctor();
				vef.SetEquation(matcher.group(2));
				vRuntimeEnv.SetVariable(matcher.group(1), vef);
				Float float1 = vRuntimeEnv.Calc(matcher.group(1), 1);
				vRuntimeEnv.SetVariable(matcher.group(1), float1);
				return;
			}
		}
		if (this.sentence.startsWith("while(")) {
			matcher = rgRegex.Regex(WHILE, sentence);
			if (matcher.find()) {
				// System.out.println(matcher.group(1));
				boolean judge = accept_logic(matcher.group(1), vRuntimeEnv);
				String sent = sentence.replaceFirst(WHILE, "").trim();
				sent = sent.substring(1, sent.length() - 1);
				// 因为分割时候已经匹配好 所以不用检验length
				// System.out.println(matcher.group(2));
				while (judge) {
					vShellScript.Run(sent, vRuntimeEnv);
					judge = accept_logic(matcher.group(1), vRuntimeEnv);
				}
				return;
			}
		}

		if (this.sentence.startsWith("print")) {
			matcher = rgRegex.Regex(PRINT, sentence);
			if (matcher.find()) {
				String[] strs = matcher.group(1).split("\\+");

				for (int i = 0; i < strs.length; i++) {
					Matcher temp = rgRegex.Regex(PRINT_STRING, strs[i].trim());
					if (temp.find()) {
						System.out.print(temp.group(1));
					} else {
						
						System.out
								.print(vRuntimeEnv.getVariable(strs[i].trim()));
					}
				}
				System.out.println();
				return;
			}
		}
		// match y=VEasyFunctor
		Matcher matcher2 = rgRegex.Regex(VAR2_SEN, sentence);
		if (matcher2.find()) {
			// System.out.println(matcher.group(2));
			Matcher matcher3 = rgRegex.Regex(NUMBER + "$", matcher2.group(2));
			if (matcher3.find()) {
				boolean C_ = vRuntimeEnv.ChangeVariable(matcher2.group(1),
						accept_number(matcher2.group(2)));
				if (C_ == false) {
					throw new Exception(matcher2.group(1)
							+ " is not declared. so you cant use it.");
				}
				return;
			}
			VEasyFunctor vef = new VEasyFunctor();
			vef.SetEquation(matcher2.group(2));
			boolean C_ = vRuntimeEnv.ChangeVariable(matcher2.group(1), vef);
			if (C_ == false) {
				throw new Exception(matcher2.group(1)
						+ " is not declared. so you cant use it.");
			}
			return;
		}
		// match y:=VEasyFunctor
		matcher = rgRegex.Regex(VAR2__SEN, sentence);
		if (matcher.find()) {
			Matcher matcher4 = rgRegex.Regex(NUMBER + "$", matcher.group(2));
			if (matcher4.find()) {
				vRuntimeEnv.SetVariable(matcher.group(1),
						accept_number(matcher.group(2)));
				return;
			}
			String r1 = "";
			r1 = VRandom.random();
			while (vRuntimeEnv.getVariable(r1) != null) {
				r1 = VRandom.random();
			}
			VEasyFunctor vef = new VEasyFunctor();
			vef.SetEquation(matcher.group(2));
			vRuntimeEnv.SetVariable(r1, vef);
			Float float1 = vRuntimeEnv.Calc(r1, 1);
			vRuntimeEnv.ChangeVariable(matcher.group(1), float1);
			return;

		}
		System.out.println(sentence);
		throw new VSyntaxException();
	}
}
