package org.nfunk.jep.kaos;
import java.util.Stack;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

@SuppressWarnings("unchecked")
public class Cube extends PostfixMathCommand
{
	
  public Cube()
  {
    this.numberOfParameters = 1;
  }
  
  public void run(Stack paramStack)
    throws ParseException
  {
    checkStack(paramStack);
    

    Object localObject = paramStack.pop();
    if ((localObject instanceof Double))
    {
      double d1 = ((Double)localObject).doubleValue();
      double d2 = d1 * d1 * d1;
      
      paramStack.push(new Double(d2));
    }
    else
    {
      throw new ParseException("Invalid parameter type");
    }
  }
}
