
public class TRY {

	public static void main(String[] args) {
		
		double temp=0; 
		int t=0;
		int tableSize = 0 ;
		
		
		if  (500% 1.5== 0)
		{
			temp= 500 / 1.5;
			t = (int)temp;
		}
			
		else
		{
			temp= 500 / 1.5;
			t = (int)temp +1;
		}
			
		System.out.println("1  "+temp);
		System.out.println("2  "+ t);
		
		boolean tryPrime= true;
		
		while ( tryPrime == true)
		{
			if ( isPrime(t) )
			{
				if ( (t - 3)%4 == 0 )
				{
					tableSize = t;
					tryPrime = false;
				}
				System.out.println("3  "+ t+ " "+ tableSize);
			}
			t++;
		}	
		System.out.println(tableSize);
		
	}

	public static boolean isPrime (int n)
	{
		if (n < 2) return false;
		if (n == 2) return true;
		if (n % 2 ==0) return false;
		
		for (int i=3; i<n; i+=2)
		{
			if ( n % i ==0 )
				return false;
		}
		return true;
	}

}
