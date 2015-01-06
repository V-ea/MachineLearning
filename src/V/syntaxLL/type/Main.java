package V.syntaxLL.type;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import V.lex.VLex;
import V.lex.VLexUnit;
import V.runtime.env.VEnv;
import V.runtime.type.VInt;

public class Main {
	/**
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Èç¹û
		String ss="L:\\test.txt";
//		if (args.length != 1) {
//			System.out.println("Usage:VEaFileLoader [ea_file]");
//			return;
//		}
		String string = "";
		InputStreamReader read;
		try {
			read = new InputStreamReader(new FileInputStream(ss), "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String Txt = null;
			try {
				while (((Txt = bufferedReader.readLine()) != null)) {
					string += Txt;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*VShellScript vss = new VShellScript();
		try {
			vss.Run(string, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		VLex vLex=new VLex();
		VLexUnit[] units=null;
		try {
			units=vLex.process(string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VSyntaxBase startNode=new Start();
		VEnv env=new VEnv();
		startNode.Accept(units, 0, env);
		System.out.println(((VInt)env.getVariable("A")).value);
		
	}

}
