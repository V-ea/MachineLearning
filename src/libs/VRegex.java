package libs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VRegex {
	public Matcher Regex(String regx,String str)
	{
		Pattern pattern=Pattern.compile(regx);
		return pattern.matcher(str);
	}
	/**
	 * ���Եļ�������������ƥ�䵽�ĵ�i��
	 * @param regx
	 * @param u
	 * @param i
	 * @return
	 */
	public static String[] getRegex(String regx, String u, int i) {
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(u);
		int c = 0;
		while (m.find()) {
			c++;
		}
		String[] ret = new String[c];
		m = p.matcher(u);
		c = 0;
		while (m.find()) {
			ret[c] = m.group(i);
			c++;
		}
		return ret;
	}
}
