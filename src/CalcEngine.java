import java.util.ArrayList;
import java.util.Collections;
import Stack.MyStack;
/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  M. Kolling 
 * @version 0.1 (incomplete)
 */
public class CalcEngine
{
	MyStack<String> opStack;
	String[] tokens;
	MyStack<String> output;
	MyStack<Integer> answer;

    /**
     * Create a CalcEngine instance. Initialise its state so that it is ready 
     * for use.
     */
    public CalcEngine()
    {
		output = new MyStack<String>();
		opStack = new MyStack<String>();
		answer = new MyStack<Integer>();
    }

    //This method converts the infix input to postfix.
    public int postFix(String in)
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
    				for(int j = i+1; tokens[j] == ")" || j<tokens.length; j++)
    				{
    					
    					if(priority(tokens[j]) > 0)
    					{
    						output.push(tokens[j]);
    					}
    				}
					output.push(opStack.pop());	
    			}
    			else if(opStack.isEmpty() == true  || priority(token) > priority((String) opStack.peek()))
    					{
    					opStack.push(token);
    					}
    			else
    			{
    				while(opStack.isEmpty() == false)
    				{
        				output.push(opStack.pop());
    				}
    				opStack.push(token);
    			}
    		}
    	}
    	//this while loop ensures that no operators are left in the opstack
    	while(opStack.isEmpty() == false)
        	output.push(opStack.pop());	
    	//This creates a new arrayList of string to hold the data from the output stack
    	ArrayList<String> postTokens = new ArrayList<String>();
    	while(output.isEmpty() == false)
    		postTokens.add(output.pop());
    	//reverses the arrayList so the postfix expression is in the right order
    	Collections.reverse(postTokens);
    	//the result of the expression is calculated with the answer(); formula
    	int result = answer(postTokens);
    	return result;
    }
    
    //This method uses a simple algorithm to produce the answer for the expression
    public int answer(ArrayList<String> postTokens)
    {
    	for(int i = 0; i<postTokens.size(); i++)
    	{
    		if (priority(postTokens.get(i)) == 0)
    		{
    			answer.push(Integer.parseInt(postTokens.get(i)));
    		}
    		if (postTokens.get(i).equals("^"))
    		{
    			int b = answer.pop();
    			int a = answer.pop();
				int power = a;
    			while (i < b)
    				i++;
    				power = power*a;
    			answer.push(power);
    		}
    		if (postTokens.get(i).equals("*"))
    		{
    			int b = answer.pop();
    			int a = answer.pop();
    			answer.push(a*b);
    		}
    		if (postTokens.get(i).equals("/"))
    		{
    			int b = answer.pop();
    			int a = answer.pop();
    			answer.push(a/b);
    		}
    		if (postTokens.get(i).equals("+"))
    		{
    			int b = answer.pop();
    			int a = answer.pop();
    			answer.push(a+b);
    		}

    		if (postTokens.get(i).equals("-"))
    		{
    			int b = answer.pop();
    			int a = answer.pop();
    			answer.push(a-b);
    		}

    		
    	}
    	return answer.pop();
    }
    
    /**
     * The 'C' (clear) button was pressed.
     */
    public void clear()
    {
    	opStack.clear();
    	output.clear();
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
        return("Donal Doherty");
    }

    /**
     * Return the version number of this engine. This string is displayed as 
     * it is, so it should say something like "Version 1.1".
     */
    public String getVersion()
    {
        return("Ver. 1.5");
    }
	
	//Compares Operator to priorities 
	public int priority(String ch)
	{
		if (ch.equals("+")) {
			return 1;
		}
	    else if (ch.equals("-")) {
			return 1;
		}
		else if (ch.equals("/")) {
			return 2;
		}
		else if (ch.equals("*")) {
			return 2;
		}
		else if (ch.equals("^")) {
			return 3;
		}
		else if (ch.equals("("))
		{
			return 4;
		}
		else
		{
			return 0;
		}
	}

}