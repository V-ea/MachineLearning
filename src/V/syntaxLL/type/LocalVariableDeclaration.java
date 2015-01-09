package V.syntaxLL.type;

import com.sun.xml.internal.ws.message.stream.StreamAttachment;

import V.lex.VLexUnit;
import V.runtime.env.VEnv;

public class LocalVariableDeclaration extends VSyntaxBase {

	@Override
	public int Accept(VLexUnit[] units, int index, VEnv env) {
		// TODO Auto-generated method stub
		try {
			index =Want(VLexUnit.IDENTIFIER,new String[]{"var"}, index, env);
			index =Want(new VariableDeclaratorId(), index, env);
			index =Want(VLexUnit.EQUAL,null, index, env);
			index =Want(new VInit(), index, env);
			return index;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return UNMATCHED;
	}

}
