package org.nfunk.jep.kaos;
import org.nfunk.jep.function.PostfixMathCommand;
import java.lang.Math;
import java.util.*;
import org.nfunk.jep.*;


/*
 * Calculate the Gap function
 * @return gap function value
*/
 @SuppressWarnings("unchecked")
public class Gap extends PostfixMathCommand
{
	/**
	 * Constructor.
	 */
	public Gap() {
		// Use a variable number of arguments
		numberOfParameters = -1;
	}
	
	
	public void run(Stack inStack) throws ParseException 
	{
                // check the stack
                checkStack(inStack);
                // get the parameter from the stack
                Object param = inStack.pop();
                if(param instanceof Vector)
                {
                  double result = 0.0;
                  Vector vecParam = (Vector)param;
                  int size = vecParam.size();
                  int n=Math.round((size-1)/2); 
   	 
				  double sum1=0;
				  double sum2=0;
                
                  for(int i=0; i<n; i++)
                  {
                    double value = ((Double)(vecParam.get(i))).doubleValue();
                
                    sum2=sum2+value;
                  }
                  for(int i=n+1; i<size; i++)
                  {
                    double value = ((Double)(vecParam.get(i))).doubleValue();
                
                    sum1=sum1+value;
                  }
                  result=sum1-sum2;
                  inStack.push(result);
                }
                else{
                  throw new ParseException("Invalid parameter type");
                }
        }
}
