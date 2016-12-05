package ru.job4j;

/**
* Class Triangle.
* @author dgagarsky
* @since 04.12.2016
*/
public class Triangle {
	/**
	* Point a.
	*/
	private Point a;
	/**
	* Point b.
	*/
	private Point b;
	/**
	* Point c.
	*/
	private Point c;
	/**
	* Constructor. Set triangle.
	* @param a pointA.
	* @param b pointB.
	* @param c pointC.
	*/
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	* Calculate the triangle area.
	* @return zero if this isnt triangle.
	*/
	public double area() {
		double distanceAB = a.distanceTo(b);
		double distanceBC = b.distanceTo(c);
		double distanceAC = a.distanceTo(c);
		boolean thisIsTriangle = ((distanceAB + distanceBC > distanceAC) & (distanceBC + distanceAC > distanceAB) & (distanceAB + distanceAC > distanceBC));
		double result;
		if (thisIsTriangle) {
			double p = (distanceAB + distanceBC + distanceAC) / 2;
			result = Math.sqrt(p * (p - distanceAB) * (p - distanceBC) * (p - distanceAC));
		} else {
			result = 0;
		}
		return result;
	}
}