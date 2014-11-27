package libs;

import java.util.Random;

public class VRandom {

	public static String random() {
		Random ra = new Random();
		double b = ra.nextDouble();
		return ((Double)(b)).toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(random());
		System.out.println(random());
		System.out.println(random());
	}

}
