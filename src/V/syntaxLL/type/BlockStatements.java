package V.syntaxLL.type;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VObject;

/**
 * 
 * @author Vea - Eapchen专用标签 - 代码修改请保留该选项 有什么问题请向 cheneap@hotmail.com 反馈
 *
 */
public class BlockStatements extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		int index_old = index;
		if (index_old >= units.length - 1)// Start 调用
		{
			return index_old + 1;
		}
		if (units[index_old].data.equals("}")) // Block 调用
			return index_old;
		try {
			index = Want(new BlockStatement(), index_old, env);
			for(int o=index_old;o<index;o++)
			{
				System.out.print(units[o].data+" ");
			}
			System.out.println(calcEnable+" return:"+env.getDirectlyVariable("return"));
			if (calcEnable == true) {
				if (env.getDirectlyVariable("return") != null) {
					calcEnable =false;
				}
			}
		} catch (Exception e) {// NULL
			// TODO: handle exception
			System.out.println("basic block statement error at "
					+ units[index_old]);
			e.printStackTrace();
			System.exit(0);
		}
		try {
			VSyntaxBase v = null;
			index = Want(v = new BlockStatements(), index, env);
			return index;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return VSyntaxBase.UNMATCHED;
	}

}
