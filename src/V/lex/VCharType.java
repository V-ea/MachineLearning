package V.lex;

public class VCharType {
	public static final int CHAR_n=0;//nouse -
	public static final int CHAR_w=1;//a-zA-z_ - +
	public static final int CHAR_zero=2;//0   +
	public static final int CHAR_1_9=3;//1-9  +
	public static final int CHAR_o=4;//²Ù×÷·û + - * / ^ 
	public static final int CHAR_logic1=5;//Âß¼­ÔËËã·û & + 
	public static final int CHAR_logic2=6;//Âß¼­ÔËËã·û | +
	public static final int CHAR_logic3=7;//Âß¼­ÔËËã·û < > +
	public static final int CHAR_equal=8;//Âß¼­ÔËËã·û = +
	public static final int CHAR_no=9;//Âß¼­ÔËËã·û ! 
	public static final int CHAR_ls=10;//Ë«ÒýºÅ - +
	public static final int CHAR_ld=10;//µ¥ÒýºÅ - +
	public static final int CHAR_mao=12;//Ã°ºÅ : -
	public static final int CHAR_ju=13;//·ÖºÅ;
	public static final int CHAR_p=14;//. 
	public static final int CHAR_ws=15;//°×¿Õ¸ñ
	public static final int CHAR_ws2=16;//°×¿Õ¸ñ \r\n
	public static final int CHAR_lb=17;//{ -
	public static final int CHAR_rb=18;//} -
	public static final int CHAR_lx=19;//( -
	public static final int CHAR_rx=20;//) -
	public static final int CHAR_lz=21;//[ -
	public static final int CHAR_rz=22;//] -
	public static final int CHAR_comma=23;//, -
	public static final int CHAR_other=24;// -
	private static int [] chartype=new int[]
			// 0	1		2		3		4		5	6		7  
			{/*0*/CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,
			/*8*/CHAR_n,CHAR_ws,CHAR_ws2,CHAR_n,CHAR_n,CHAR_ws2,CHAR_n,CHAR_n,
			/*16*/CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,
			/*24*/CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,CHAR_n,//4
			/*32*/CHAR_ws,CHAR_no,CHAR_ls,CHAR_n,CHAR_n,CHAR_n,CHAR_logic1,CHAR_ld,
			/*40*/CHAR_lx,CHAR_rx,CHAR_o,CHAR_o,CHAR_comma,CHAR_o,CHAR_p,CHAR_o,
			/*48*/CHAR_zero,CHAR_1_9,CHAR_1_9,CHAR_1_9,CHAR_1_9,CHAR_1_9,CHAR_1_9,CHAR_1_9,
			/*56*/CHAR_1_9,CHAR_1_9,CHAR_mao,CHAR_ju,CHAR_logic3,CHAR_equal,CHAR_logic3,CHAR_n,//8
			/*64*/CHAR_n,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,
			/*72*/CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,
			/*80*/CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,
			/*88*/CHAR_w,CHAR_w,CHAR_w,CHAR_lz,CHAR_n,CHAR_rz,CHAR_o,CHAR_w,//12
			/*96*/CHAR_n,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,
			/*104*/CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,
			/*112*/CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,CHAR_w,
			/*120*/CHAR_w,CHAR_w,CHAR_w,CHAR_lb,CHAR_logic2,CHAR_rb,CHAR_n,CHAR_n,//16
			};
	public static int at(int i)
	{
		return chartype[i];
	}
}
