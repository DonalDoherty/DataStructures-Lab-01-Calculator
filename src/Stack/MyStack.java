package Stack;

import java.util.*;

public class MyStack<E> implements StackInterface< E > {
	
	//The ArrayList that will form the basis of the stack
	private ArrayList<E> content;
	
	public MyStack()
	{
		content = new ArrayList<E>();
	}
	
	//This Method adds a new object to the stack
	@Override
	public void push(E newEntry) {
		content.add(newEntry);
	}
	
	//returns value on top of stack while removing it
	@Override
	public E pop() {
		E pop = content.remove(content.size() - 1);
		return pop;
	}
	
	//returns value on top of stack
	@Override
	public E peek() {
		return content.get(content.size() - 1);
	}

	//returns boolean value to tell if stack is empty or not
	@Override
	public boolean isEmpty() {
		if (content.size() == 0)
		{
			return true;
		}
		else
		{
		return false;
		}
	}

	//Deletes everything in stack
	@Override
	public void clear() {
		content.clear();
	}
	
}
