package org.nfunk.jep.kaos;
import org.nfunk.jep.function.PostfixMathCommand;
import java.lang.Math;
import java.util.*;
import org.nfunk.jep.*;


/*
 * Calculate the Sum of the Vector V 
 * @return Vector sum
*/
 @SuppressWarnings("unchecked")
public class SumVect extends PostfixMathCommand
{
	/**
	 * Constructor.
	 */
	public SumVect() {
		// Use a variable number of arguments
		numberOfParameters = -1;
	}
	
	
	public void run(Stack inStack) throws ParseException 
	{
                // check the stack
                checkStack(inStack);
                // get the parameter from the stack
                Object param = inStack.pop();
                if(param instanceof Vector){
                  double result = 0.0;
                  Vector vecParam = (Vector)param;
                  int size = vecParam.size();
                  for(int i=0; i<size; i++)
                  {
                    double value = ((Double)(vecParam.get(i))).doubleValue();
                
                    result=result+value;
                  }
                  inStack.push(result);
                }
                else{
                  throw new ParseException("Invalid parameter type");
                }
        }
}
