package V.lex;

public class VLexUnit {
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
	
	
	public final static int IF_KEYWORD = 32767;
	public final static int WHILE_KEYWORD = 32766;
	public final static int ELSE_KEYWORD = 32765;
	
	@Override
	public String toString() {
		return "VLexUnit [data=" + data + ", type=" + type + ", line=" + line
				+ ", column=" + column + "]";
	}
	public String data="";
	public int type;
	public int line;
	public int column;
	public int isKeyword()
	{
		if(type==IDENTIFIER&&data.equals("if"))
		{
			return IF_KEYWORD;
		}
		if(type==IDENTIFIER&&data.equals("else"))
		{
			return ELSE_KEYWORD;
		}
		if(type==IDENTIFIER&&data.equals("while"))
		{
			return WHILE_KEYWORD;
		}
		return 0;
	}
}