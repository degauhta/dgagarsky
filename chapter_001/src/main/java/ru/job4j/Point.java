package ru.job4j;

/**
* Class Point.
* @author dgagarsky
* @since 04.12.2016
*/
public class Point {
	/**
	* Horizontal coordinate.
	*/
	private double x;
	/**
	* Vertical coordinate.
	*/
	private double y;
	/**
	* Constructor. Set coordinate of Point.
	* @param x horizontal coordinate.
	* @param y vertical coordinate.
	*/
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	* Calculate distance between two points.
	* @param point point.
	* @return distance.
	*/
	public double distanceTo(Point point) {
		return Math.sqrt(Math.pow((this.x - point.x), 2) + Math.pow((this.y - point.y), 2));
		}
}