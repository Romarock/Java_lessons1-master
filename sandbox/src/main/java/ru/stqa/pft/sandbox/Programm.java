package ru.stqa.pft.sandbox;

import java.sql.SQLOutput;

public class Programm{

	public static void main (String[] args) {

		hello("world");
		hello("User");
		hello("Roman");
		Square s = new Square(5);
		Point p1 = new Point (5,5);
		Point p2 = new Point (3,3);

		System.out.println("Площадь квадрата со тороной " + s.l + " = " + s.area());
		Rectangle r =  new Rectangle(4, 6);


		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
		System.out.println("Расстояние между точками = " + distance(p1, p2));
	}
	public static void hello(String somebody) {

		System.out.println("Hello " + somebody);

	}

public static double distance(Point p1, Point p2) {

		return Math.sqrt(((p1.x - p2.x)*(p1.x-p2.x))+((p1.y - p2.y)*(p1.y-p2.y)));
}






}