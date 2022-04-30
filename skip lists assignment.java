import java.lang.*;
import java.util.*;
import java.io.*;

class Node
{
	public Node(int data)
	{
		this.data = data;
	}

	public int data;
	public Node next;
}

class SkipList
{
	public Node head = null;
	public Node tail = null;

	public void createNode(int data)
 	{
 			Node temp = new Node(data);

 			if (head == null)
 			{
 				head = tail = temp;
 			}
 			else if (data > tail.data)
 			{
 				this.tail.next = temp;
 				this.tail = temp;
 			}
 			else if (data < head.data)
 			{
 				temp.next = this.head;
 				this.head = temp;
 			}
 			else
 			{
 				Node currentNode = head;

 				while (currentNode.next.data <= data)
 					currentNode = currentNode.next;
 				temp.next = currentNode.next;
 				currentNode.next = temp;
 			}
 	}

	/*=================================================================================delete=================================================================================*/
	public boolean delete(int data)
	{
		if (head == null)
		{
			return false;
		}
		else if (head == tail && head.data == data)
		{
			head = null;
			tail = null;
			return true;
		}
		else if (head.data == data)
		{
			head = head.next;
			return true;
		}
		else
		{
			for (Node currentNode = head; currentNode.next != null; currentNode = currentNode.next)
				if (currentNode.next.data == data)
				{
					currentNode.next = currentNode.next.next;
					return true;
				}
		}
		return false;
	}
	/*=================================================================================search=================================================================================*/
	public boolean search(int data)
	{
		if (head == null)
		{
			return false;
		}
		else if (head == tail && head.data == data || head.data == data)
		{
			return true;
		}
		else
		{
			for (Node currentNode = head; currentNode.next != null; currentNode = currentNode.next)
				if (currentNode.next.data == data)
				{
					return true;
				}
		}
		return false;
	}
}

class List
{
	public static Random dunno = new Random();

	public List(Integer subSeed)
	{
		dunno.setSeed(subSeed);
	}

	public SkipList[] createSkipList()
	{
		SkipList[] height = new SkipList[15];
		for (int i = 0; i < 15; i++)
	    {
			height[i] = new SkipList();
			height[i].createNode(Integer.MIN_VALUE);
			height[i].createNode(Integer.MAX_VALUE);
	    }

		return height;
	}

	/*=================================================================================insert=================================================================================*/
	public int insert(int data, SkipList[] height)
	{
		int i = 0;

		for(Node currentNode = height[i].head; currentNode.next != null; currentNode = currentNode.next)
		{
			if (currentNode.data == data)
			{
				return ++i;
			}
		}
		height[i].createNode(data);

		while (coinFlip())
		{
			i++;
			height[i].createNode(data);
		}
		return ++i;
	}
   
	/*=================================================================================search=================================================================================*/
	public void search(int data, SkipList[] height, int maxH)
	{
		boolean found = false;
		
		for(int i = maxH - 1; i >= 0 && !found; i--)
		{
			if(height[i].search(data))
			{
				found = true;
			}
			else
			{
				found = false;
			}
		}

		if(found)
		{
			System.out.println(data + " found");
		}
		else
		{
			System.out.println(data + " NOT FOUND");
		}
		return;
	}
   
	/*=================================================================================delete=================================================================================*/
	public void delete(int data, SkipList[] height, int maxH)
	{
		boolean deleted = false;

		for(int i = maxH - 1; i >= 0; i--)
		{
			if(height[i].delete(data))
			{
				deleted = true;
			}
		}

		if(deleted)
		{
			System.out.println(data + " deleted");
		}
		else
		{
			System.out.println(data + " integer not found - delete not successful");
		}
		return;
	}

	/*=================================================================================print=================================================================================*/
	public void print(SkipList[] height, int maxH, int maxL)
	{
		System.out.println("the current Skip List is shown below:");

		int count = 0;
		String[] output = new String[maxL];
		String breakup;

		for(int i = 0; i < maxH; i++)
		{
			if(i == 0)
			{
				for(Node currentNode = height[i].head; currentNode != null; currentNode = currentNode.next)
               	{
					output[count++] =" " + currentNode.data + "; ";
					maxL = count;
               	}
			}
			else
			{
				for(Node currentNode = height[i].head; currentNode != null; currentNode = currentNode.next)
				{
					count = 0;

					breakup = " " + currentNode.data + "; ";

					if(currentNode.data != Integer.MAX_VALUE && currentNode.data != Integer.MIN_VALUE)
					{
						for(int k = 1; k < i; k++)
							breakup = breakup + " " + currentNode.data + "; ";

						while(!breakup.equals(output[count]))
						{
							count++;
						}

						output[count] = output[count] + " " + currentNode.data + "; ";
					}
				}
			}
		}

		for (int j = 0; j < maxL; j++)
		{
			if(output[j].equals(" " + Integer.MIN_VALUE + "; "))
			{
				System.out.println("---infinity");
			}
			else if(output[j].equals(" " + Integer.MAX_VALUE + "; "))
			{
				System.out.println("+++infinity");
			}
			else
			{
				System.out.println(output[j]);
			}
		}

		System.out.println("---End of Skip List---");
	}

	/*=================================================================================quit=================================================================================*/
	public void quit()
	{
		System.exit(0);
	}

	/*=================================================================================50/50=================================================================================*/
	public boolean coinFlip()
	{
		if(((dunno.nextInt()% 2)+2)%2 == 1) 
		{
			return true;
		}
		else
		{
			return false; 
		}
	}
}

public class Hw02
{ 
	/*=================================================================================complexity=================================================================================*/
	public static void complexityIndicator()
	{
		System.err.println("no545885;5;46");
	}
	
	/*=================================================================================main=================================================================================*/
	public static void main(String[] args) throws IOException
	{	 
		complexityIndicator(); 
		
		String[] command = new String[700000];
		String seedString;
		String fileName = args[0];
		Scanner in = new Scanner(new File(fileName));
		
		int seed = 42;
		int maxH = 1;
		int temp;
		int x = 0;
		
		while(in.hasNext())
		{
			command[x++] = in.next();
		}
		
		System.out.println("For the input file named " + fileName);
		
		if (args.length >1 && args[1].equalsIgnoreCase("R"))
		{ 
			seed = (int) (System.currentTimeMillis());
			seedString = "With the RNG seeded with " + seed + ",";
		}
		else 
		{
			seedString ="With the RNG unseeded,"; //continues when print method is called after the insertions 
		}
		System.out.println(seedString);

		List script = new List(seed);
		SkipList[] skipList = script.createSkipList();

		for (int i = 0; i < command.length; i++)
		{
			switch(command[i]) 
			{
	    	
	    	case "i": 
	    		i++;
	            temp = script.insert(Integer.parseInt(command[i]), skipList);
	            if (temp > maxH)
	            {
	            	maxH = temp;
	            }
	            break;
	            
	    	case "d":
	    		i++;
	    		script.delete(Integer.parseInt(command[i]), skipList, maxH);
	            break;
	            
	    	case "s":
	    		i++;
	    		script.search(Integer.parseInt(command[i]), skipList, maxH);
	            break;
	            
	    	case "p":
	    		script.print(skipList, maxH, command.length);
	    		break;
	    		
	    	case "q":
	    		script.quit(); 
			}
		}    
	}
}