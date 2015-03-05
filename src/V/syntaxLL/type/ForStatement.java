package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VBoolean;
/**
 * 
 * @author Vea -  Eapchen专用标签 - 代码修改请保留该选项
 * 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class ForStatement extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			VSyntaxBase v2=null;
			int index0=-1,index1=-1,index2=-1,index3=-1,end=-1;
			boolean oldCanCalc=calcEnable;
			if(oldCanCalc)
				calcEnable=false;
			index =Want(VLexUnit.IDENTIFIER, new String[]{"for"}, index, env);
			index =Want(VLexUnit.LEFTX, null, index, env);
			if(units[index].type!=VLexUnit.END)
			{
				index0=index;
				index =Want(new ForInit(), index, env);
			}
			index =Want(VLexUnit.END,null, index, env);
			if(units[index].type!=VLexUnit.END)
			{
				index1=index;
				index =Want(v2=new Expression(), index, env);
			}
			index =Want(VLexUnit.END,null, index, env);
			if(units[index].type!=VLexUnit.RIGHTX)
			{
				index2=index;
				index =Want(new ForUpdate(), index, env);
			}
			index =Want(VLexUnit.RIGHTX,null, index, env);
			index3=index;
			index =Want(new Statement(), index, env);
			end =index;
			//calc
			calcEnable =oldCanCalc;
			if(calcEnable)
			{
				//init
				if(index0!=-1)
				{
					VSyntaxBase vtp=null;
					Want(vtp=new ForInit(), index0, env);
				}
				//boolean run =true;
				//check
				boolean check=true;
				while(check)
				{
					if(index1!=-1)
					{
						VSyntaxBase vtp=null;
						Want(vtp=new Expression(), index1, env);
						if(vtp.result instanceof VBoolean)
						{
							boolean b=((VBoolean)vtp.result).value;
							if(b)
								check =true;
							else
								check=false;
						}
						else
							throw new Exception("in the middle of for(),expression returns boolean was needed.");
						if(check){
							if(index3!=-1)
								Want(new Statement(), index3, env);
							if(index2!=-1)
								Want(new ForUpdate(), index2, env);
						}
					}
					//System.out.println(0);
				}
			}
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
