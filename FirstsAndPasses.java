import java.util.Scanner;

public class FirstsAndPasses {

	public static void main(String[] args) {

		// Open scanner
		Scanner s = new Scanner(System.in);

		// Declare constants
		final double PERC70 = 70;
		final double PERC40 = 40;
		int firstHonour = 0; 	
		int fails = 0; 
		double percPass = 0;


		// Inputs
		System.out.println("Enter the number of "
				+ "students in the class>");
		double students = s.nextDouble();
		double percent = 0;

		// While loop
		int counter = 0;
		int studCount = 1;
		while (counter < students)
		{
			System.out.println("Enter the percentage "
					+ "obtained by student "+ studCount++ +">");
			percent = s.nextDouble();
			counter++;

			if (percent >= PERC70)
			{
				firstHonour++ ; 	
			} 
			if (percent < PERC40) 
			{
				fails++ ;	
			}
			if (percent < PERC70 && percent >= PERC40 ) 
			{
				fails++ ;	
			}
			if (percent >= PERC40)
			{
				percPass++ ; 	
			} 
			
		}

		// Percent calculations
		double passPerc = (percPass/students) * 100 ;
		
		// Outputs
		if (students <= 0) 
		{
			System.out.println("Error:  The number of "
					+ "students must be greater than 0 ");	
		}
		else 
		{
			System.out.printf("In this class of "+ "%.0f" +" students,"+
					"%.0f" +" got a first class honour and " + "%.2f" + "%"
					+ " passed the class", students, firstHonour, passPerc);
		}
		
	}

}
