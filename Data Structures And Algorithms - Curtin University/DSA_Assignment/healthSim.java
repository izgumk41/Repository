import java.io.Serializable;

/*************************************************
 * AUTHOR: Izan Muhammad
 * PURPOSE: to start health simulator program.
*************************************************/
public class healthSim implements Serializable
{
	public static void main(String[] args)
	{
	
		if(args.length == 0)
		{
			usageInformation();
		}
		else if(args[0].equals("-i"))
		{
			try
			{
				Menu.menu();
			}
			catch(Exception e)
			{
				usageInformation();
			}
		}
		else if(args[0].equals("-s"))
		{

			try
			{
				FileManager.simulation(args);
			}
			catch (Exception e)
			{
				usageInformation();
			}
		}
		else
		{
			System.out.print("invalid usage: ");
			for(int i = 0; i < args.length; i++)
			{
				System.out.print(args[i]+" ");
			}
			usageInformation();
		}
		
	}//end main
	
	
	public static void usageInformation()
	{
		System.out.println("Usage information:");
		System.out.println("'-i': interactive testing environment");
		System.out.println("usage: java healthSim �i\n");
		System.out.println("'-s': simulation mode.");
		System.out.println("usage: java healthSim �s netfile trans_rate recov_rate death_rate int_code)");
		
	}
}//end class
