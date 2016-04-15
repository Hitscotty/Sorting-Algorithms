package HW7;

public class stats{

	public static double average(long[] nums) {
		double avg = 0;

		for (int i = 0; i < nums.length; i++) {
			avg += (double) nums[i];
		}

		avg /= nums.length;

		return avg;
	}

	public static double standardDeviation(long[] nums) {
		double sD = 0;
		double sum = 0;

		double average = average(nums);

		for (int i = 0; i < nums.length; i++) {
			sum += Math.pow(((double) nums[i] - average), 2.0);
		}

		sum /= nums.length;

		sD = Math.sqrt(sum);

		return sD;
	}

}
