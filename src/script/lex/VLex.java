package script.lex;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import data.VDataLoader;

/**
 * 第二次大修改过程中负责分割词汇的处理单元功能包括读取和判定。
 * 
 * @author Administrator
 *
 */
public class VLex {

	public VLex() {

	}
	private VTrainsitionDiagram vtdDiagram=new VTrainsitionDiagram();
	/**
	 * 接受词段并返回词单元信息
	 * @throws Exception 
	 * 
	 */
	public VLexUnit[] process(String script) throws Exception {
		int len = script.length();
		int i = 0;
		LinkedList<VLexUnit> list=new LinkedList<VLexUnit>();
		VLexUnit buff = new VLexUnit();
		while (i < len) {
			i = accept_identifier(script, i, buff);
			if (buff.data.equals("")) {
				i = accept_float(script, i, buff);
				if (buff.data.equals("")) {
					i = accept_eq(script, i, buff);
					if (buff.data.equals("")) {
						i = accept_boolean_eq(script, i, buff);
						if (buff.data.equals("")) {
							i = accept_string(script, i, buff);
							if (buff.data.equals("")) {
								i = accept_other(script, i, buff);
								if (buff.data.equals("")) {
									throw new Exception("unexpected char.["+script.charAt(i)+"]");
								}
							}
						}
					}
				}
			}
			if(buff.data.trim()!="")
			{
				list.add(buff);
				System.out.println(buff.toString());
			}
			buff=new VLexUnit();
		}
		return null;
	}

	private int accept_other(String script, int i, VLexUnit buff) {
		// TODO Auto-generated method stub
		
		char ch=script.charAt(i);
		if(ch<0||ch>=128)
		{
			return i;
		}
		else
		{
			int chartype=VCharType.at(ch);
			switch (chartype) {
			case VCharType.CHAR_o:
				buff.type=VLexUnit.OPERATOR;
				break;
			case VCharType.CHAR_no:
				buff.type=VLexUnit.NOT;
				break;
			case VCharType.CHAR_ju:
				buff.type=VLexUnit.END;
				break;
			case VCharType.CHAR_mao:
				buff.type=VLexUnit.COLON;
				break;
			case VCharType.CHAR_p:
				buff.type=VLexUnit.POINT;
				break;
			case VCharType.CHAR_lz:
				buff.type=VLexUnit.LEFTZ;
				break;
			case VCharType.CHAR_rz:
				buff.type=VLexUnit.RIGHTZ;
				break;
			case VCharType.CHAR_lx:
				buff.type=VLexUnit.LEFTX;
				break;
			case VCharType.CHAR_rx:
				buff.type=VLexUnit.RIGHTX;
				break;
			case VCharType.CHAR_lb:
				buff.type=VLexUnit.LEFTB;
				break;
			case VCharType.CHAR_rb:
				buff.type=VLexUnit.RIGHTB;
				break;
			case VCharType.CHAR_ws:
			case VCharType.CHAR_ws2:
				buff.type=VLexUnit.SPACE;
				break;
			case VCharType.CHAR_comma:
				buff.type=VLexUnit.COMMA;
				break;
			default:
				return i;
			}
			buff.data+=ch;
		}
		return i+1;
	}

	private int accept_string(String script, int i, VLexUnit buff) throws Exception {
		// TODO Auto-generated method stub
		int[][] array=vtdDiagram.string;
		return accept(array, script, i, buff, VLexUnit.STRING);
	}

	private int accept_boolean_eq(String script, int i, VLexUnit buff) throws Exception {
		// TODO Auto-generated method stub
		int[][] array=vtdDiagram.boolean_eq;
		return accept(array, script, i, buff, VLexUnit.BOPER);
	}

	private int accept_eq(String script, int i, VLexUnit buff) throws Exception {
		// TODO Auto-generated method stub
		int[][] array=vtdDiagram.eq;
		return accept(array, script, i, buff, VLexUnit.EQUAL);
	}

	private int accept_float(String script, int i, VLexUnit buff) throws Exception {
		// TODO Auto-generated method stub
		int[][] array=vtdDiagram.float_;
		return accept(array, script, i, buff, VLexUnit.FLOAT);
	}

	private int accept_identifier(String script, int ii, VLexUnit buff) throws Exception {
		// TODO Auto-generated method stub
		int[][] array=vtdDiagram.indentifier;
		return accept(array, script, ii, buff, VLexUnit.IDENTIFIER);
	}
	public int accept(int[][] array,String script,int i,VLexUnit buff,int id) throws Exception
	{
		buff.type=id;
		int len=script.length();
		int chartype=0;
		int last_state=0;
		
		while(i<len)
		{
			int h=script.charAt(i);
			
			if(h<0||h>=128)
			{
				chartype=VCharType.CHAR_other;
			}
			else
			{
				chartype=VCharType.at(h);
				if(chartype==VCharType.CHAR_n)
				{
					throw new Exception("unknown char."+(int)h);
				}
			}
			//System.out.println(chartype);
			chartype= vtdDiagram.getTransitionType(id, chartype);
			int current_state=array[last_state][chartype];
			if(current_state==-1)
			{
				return i;
			}
			if(current_state==-4)
			{
				buff.data+=script.charAt(i);
				i++;
				return i;
			}
			if(current_state==-3)
			{
				return i;
			}
			if(current_state==-2)
			{
				throw new Exception("lexical error.："+script.charAt(i));
			}
			last_state=current_state;
			buff.data+=script.charAt(i);
			i++;
		}
		//给一个白空格 不能得出-1 -4的 就是失败
		chartype= vtdDiagram.getTransitionType(id, VCharType.CHAR_ws);
		int current_state=array[last_state][chartype];
		if(current_state==-1)
		{
			return i;
		}
		if(current_state==-4)
		{
			buff.data+=script.charAt(i);
			i++;
			return i;
		}
		if(current_state==-3)
		{
			return i;
		}
		if(current_state==-2)
		{
			throw new Exception("lexical error.");
		}
		return i;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new VLex().process("if(x=4){print(0);}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class VLexUnit {
	public static final int NOT = 1;
	public static final int END = 2;
	public static final int POINT=3;
	public static final int COLON=4;
	public static final int SPACE=5;
	/**
	 * 操作符 + - * / ^
	 */
	public final static int OPERATOR = 6;
	public final static int BOPER=7;
	public final static int EQUAL=8;
	/**
	 * 浮点数
	 */
	public final static int FLOAT = 9;
	/**
	 * 字符串
	 */
	public final static int STRING = 10;
	/**
	 * 标识符
	 */
	public final static int IDENTIFIER = 11;
	/**
	 * 关键字
	 */
	public final static int KEYWORD = 12;

	/**
	 * {}()[]
	 */
	public final static int LEFTB = 13;
	public final static int RIGHTB = 14;
	public final static int LEFTX = 15;
	public final static int RIGHTX = 16;
	public final static int LEFTZ = 17;
	public final static int RIGHTZ = 18;
	public final static int COMMA = 19;
	@Override
	public String toString() {
		return "VLexUnit [data=" + data + ", type=" + type + "]";
	}
	public String data="";
	public int type;
}