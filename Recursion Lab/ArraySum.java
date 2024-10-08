
public class ArraySum 
{

		public int sumOfArray (Integer[] a, int index)
		
		{
			int sum;
			
			if (index == 0)
			{
				sum = a[index];	
			} else {
				sum = a[index] + sumOfArray(a, --index);
			}
		return sum;
	}

}
