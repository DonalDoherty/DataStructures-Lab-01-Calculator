	package Stack;

	public interface StackInterface < Object >
	{
    /** Task: Adds a new entry to the top of the stack.
    * @param newEntry an object to be added to the stack */
    public void
        push (Object newEntry);
    /** Task: Removes and returns the stacks top entry.
    * @return either the object at the top of the stack or, if the
    * stack is empty before the operation, null */
    public
        Object pop ();
    /** Task: Retrieves the stacks top entry.
    * @return either the object at the top of the stack or null if
    * the stack is empty */
    public
        Object peek ();
    /** Task: Detects whether the stack is empty.
    * @return true if the stack is empty */
    public boolean
        isEmpty ();
    /** Task: Removes all entries from the stack */
    public void
        clear ();
} // end StackInterface