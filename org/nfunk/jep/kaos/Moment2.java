package org.nfunk.jep.kaos;
import org.nfunk.jep.function.PostfixMathCommand;
import java.lang.Math;
import java.util.*;
import org.nfunk.jep.*;

/*
 * Calculate the Second Order Moment of two vectors U,V
 * @return second order moment value 
*/

@SuppressWarnings("unchecked")
public class Moment2 extends PostfixMathCommand
{
	public Moment2()
	{
		numberOfParameters = 2;
	}
	
	public void run(Stack inStack)
		throws ParseException 
	{
		checkStack(inStack);// check the stack
		Object param2 = inStack.pop();
		Object param1 = inStack.pop();
		
		if ((param1 instanceof Vector) && (param2 instanceof Vector))
		{  /*
			double divisor = ((Number)param2).doubleValue();
			double dividend = ((Number)param1).doubleValue();
		
			double result = dividend % divisor;
	
			inStack.push(new Double(result));
			*/
			
			      double result = 0.0;
                  Vector vecParam1 = (Vector)param1;
                  Vector vecParam2 = (Vector)param2;
                  int size = vecParam1.size();
                  for(int i=0; i<size; i++)
                  {
                    double value1 = ((Double)(vecParam1.get(i))).doubleValue();
                    double value2 = ((Double)(vecParam2.get(i))).doubleValue();
                    result=result+(value1*value1*value2);
                  }
                  inStack.push(result);
		}
		else
		{
			throw new ParseException("Invalid parameter type");
		}
		return;
	}
}
