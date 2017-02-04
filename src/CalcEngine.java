import java.util.ArrayList;

import Stack.MyStack;
/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  M. Kolling 
 * @version 0.1 (incomplete)
 */
public class CalcEngine
{
	MyStack opStack;
	String[] tokens;
	MyStack output;
    char operator;
    int displayValue, operand1;

    /**
     * Create a CalcEngine instance. Initialise its state so that it is ready 
     * for use.
     */
    public CalcEngine()
    {
        operator =' ';
        displayValue=0;
		operand1 = 0;
		output = new MyStack<String>();
		opStack = new MyStack<String>();
    }

    /**
     * Return the value that should currently be displayed on the calculator
     * display.
     */
    public int getDisplayValue()
    {
        return(displayValue);
    }

    /**
     * A number button was pressed. Do whatever you have to do to handle it.
     * The number value of the button is given as a parameter.
     */
    public void numberPressed(int number)
    {
        displayValue = displayValue *10 + number;
    }
    
    //This method converts the infix input to postfix.
    public String postFix(String in)
    {
    	//This line uses a postive lookbehind and lookahead to split the infix string into tokens, allowing for more than 1 didgit numbers
    	tokens = in.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");
    	for(int i = 0; i<tokens.length; i++)
    	{
    		String token = tokens[i];
    		if(priority(token) == 0)
    		{
    			output.push(token);
    		}
    		else if(priority(token) > 0)
    		{
    			if(token == "(")
    			{
    				opStack.push(token);
    				for(int j = i+1; tokens[j] == ")" || j<tokens.length; j++)
    				{
    					
    					if(priority(tokens[j]) > 0)
    					{
    						opStack.push(tokens[j]);
    					}
    				}
    				opStack.push(")");
    				for(int k = 0; opStack.peek() == "("; k++)
    				{
    					output.push(opStack.pop());
    				}
					output.push(opStack.pop());	
    			}
    			else if(priority(token) > priority((String) opStack.peek()) || opStack.isEmpty() == true )
    					{
    					opStack.push(token);
    					}
    			else
    			{
    				while(opStack.isEmpty() == false)
    				output.push(opStack.pop());
    			}
    		}
    	}
    	return null;
    }
    

    /**
     * The 'plus' button was pressed. 
     */
    public void plus()
    {
       operand1 = displayValue;
	   displayValue = 0;
       operator = '+';
    }
    
    /**
     * The 'minus' button was pressed.
     */
    public void minus()
    {
        operand1 = displayValue;
	   displayValue = 0;
       operator = '-'; 
    }

public void multiply()
    {
        operand1 = displayValue;
	   displayValue = 0;
       operator = '*'; 
    }

public void divide()
    {
        operand1 = displayValue;
	   displayValue = 0;
       operator = '/'; 
    }

    /**
     * The '=' button was pressed.
     */
    public void equals()
    {
        if (operator == '+') {
			displayValue += operand1;
			operand1 = 0;
		}
	    else if (operator == '-') {
			displayValue = operand1-displayValue;
			operand1 = 0;
		}
		else if (operator == '*') {
			displayValue = operand1*displayValue;
			operand1 = 0;
		}
		else if (operator == '/') {
			displayValue = operand1/displayValue;
			operand1 = 0;
		}

    }

    /**
     * The 'C' (clear) button was pressed.
     */
    public void clear()
    {
        displayValue = 0;
		operand1 = 0;

    }

    /**
     * Return the title of this calculation engine.
     */
    public String getTitle()
    {
        return("My Calculator");
    }

    /**
     * Return the author of this engine. This string is displayed as it is,
     * so it should say something like "Written by H. Simpson".
     */
    public String getAuthor()
    {
        return("Joe Daly");
    }

    /**
     * Return the version number of this engine. This string is displayed as 
     * it is, so it should say something like "Version 1.1".
     */
    public String getVersion()
    {
        return("Ver. 1.0");
    }

	public void power() 
	{
		// TODO Auto-generated method stub
		
	}
	
	//Compares Operator to priorities 
	public int priority(String ch)
	{
		if (ch == "+") {
			return 1;
		}
	    else if (ch == "-") {
			return 1;
		}
		else if (ch == "/") {
			return 2;
		}
		else if (ch == "*") {
			return 2;
		}
		else if (ch == "^") {
			return 3;
		}
		else if (ch == "(")
		{
			return 4;
		}
		else
		{
			return 0;
		}
	}

}