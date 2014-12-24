package V.lex;

public class VTrainsitionDiagram {
	//-3 不符合 -1，-4 接受 -2 出错 
	//CHAR_nouse cant be input -3只能出现在第一行
	//operator 不用判别 就一个//-1和-3不会吃掉字符 -4会
	public final int  indentifier[][]=new int[][]{ //STATE 0 1  input CHAR_w:0 CHAR_zero:1 CHAR_1_9:2 other 3 
			{1,-3,-3,-3},
			{1, 1, 1,-1}
	};//2 was final state 
	public final int  float_[][]=new int[][]{ //STATE 0 1 2  input CHAR_zero:0 CHAR_1_9,CHAR_p:1 CHAR_ws,CHAR_o,CHAR_logic1,CHAR_logic2,CHAR_logic3,CHAR_equal,CHAR_no,CHAR_ju:2 other 3 
			{ 1, 2,-3,-3,-3},
			{-2,-2,3,-1,-2},
			{ 2, 2,3,-1,-2},
			{ 3, 3,-2,-1,-2},
	};//这里将.当做1-9来处理
	public final int string[][]=new int[][]{ //STATE 0 1   input CHAR_ls:0 CHAR_ws2:1 other 2 
			{1,-3,-3},
			{-4,-2,1},
	};
	//after eq check boolean_eq;
	public final int eq[][]=new int[][]{ // STATE 0 1 input CHAR_equal 0 other 1 
			{1,-3},
			{-3,-1}
	};
	public final int boolean_eq[][]=new int[][]{ //STATE 0 1 2 3 4  input CHAR_logic1 ：0 ,CHAR_logic2 1,CHAR_logic3 2,CHAR_equal:3   other 4
			{1,  2, 3, 4,-3},
			{-4,-2,-2,-2,-2},
			{-2,-4,-2,-2,-2},
			{-2,-2,-2,-4,-1},
			{-2,-2,-2,-4,-2},
	};
	
	public int identifier_char(int old)
	{
		switch (old)
		{
		case VCharType.CHAR_w:
			return 0;
		case VCharType.CHAR_zero:
			return 1;
		case VCharType.CHAR_1_9:
			return 2;
		default:
			return 3;
		}
	}
	public int float_char(int old)
	{
		switch (old)
		{
		case VCharType.CHAR_zero:
			return 0;
		case VCharType.CHAR_1_9:
			return 1;
		case VCharType.CHAR_p:
			return 2;
		//后跟支持
		case VCharType.CHAR_ws:
		case VCharType.CHAR_o:
		case VCharType.CHAR_logic1:
		case VCharType.CHAR_logic2:
		case VCharType.CHAR_logic3:
		case VCharType.CHAR_equal:
		case VCharType.CHAR_no:
		case VCharType.CHAR_ju:
		case VCharType.CHAR_rx:
		case VCharType.CHAR_rz:
		case VCharType.CHAR_rb:
			return 3;
		default:
			return 4;
		}
	}
	public int string_char(int old)
	{
		switch (old)
		{
		case VCharType.CHAR_ls:
			return 0;
		case VCharType.CHAR_ws2:
			return 1;
		default:
			return 2;
		}
	}
	public int eq_char(int old)
	{
		switch (old)
		{
		case VCharType.CHAR_equal:
			return 0;
		default:
			return 1;
		}
	}
	public int boolean_char(int old)
	{
		switch (old)
		{
		case VCharType.CHAR_logic1:
			return 0;
		case VCharType.CHAR_logic2:
			return 1;
		case VCharType.CHAR_logic3:
			return 2;
		case VCharType.CHAR_equal:
			return 3;
		default:
			return 4;
		}
	}
	public int getTransitionType(int id,int old_type)
	{
		switch (id)
		{
		case VLexUnit.IDENTIFIER:
			return identifier_char(old_type);
		case VLexUnit.STRING:
			return string_char(old_type);
		case VLexUnit.FLOAT:
			return float_char(old_type);
		case VLexUnit.BOPER:
			return boolean_char(old_type);
		case VLexUnit.EQUAL:
			return eq_char(old_type);
		default:
			return -1;
		}
	}	
}
