package exception;

import java.io.PrintStream;

public class VNotSameSizeException extends Exception
{
private static final long serialVersionUID=1800418108517304071L;

public VNotSameSizeException(String string)
{
System.out.println("NotSameSizeException:"+string);
}
}

/*Location:E:\EAMath.jar
*QualifiedName:exception.VNotSameSizeException
*JD-CoreVersion:0.6.0
*/