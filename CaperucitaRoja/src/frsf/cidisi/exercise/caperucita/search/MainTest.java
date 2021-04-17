package frsf.cidisi.exercise.caperucita.search;

import java.util.HashSet;
import java.awt.Point;
import java.util.ArrayList;

public class MainTest {

  public static void main(String[] args) {
//	ArrayList<Object> ar = new ArrayList<Object>();
//    ar.add(new ArrayList<Integer>());
//    ar.add("Una string");
//    HashSet<Point> ar = new HashSet<Point>();
//    ar.add(new Point(1,2));
//    ar.add(new Point(2,1));
//    ar.add(new Point(1,2));
//    
//    for (Point temp : ar) {
//        System.out.println(temp);
//     }
	  
	  Point x1 = new Point(-1, -1);
	  Point x2 = new Point(-1, -1);
//	  
//	  System.out.println(x1.equals(x2));
	  
	  HashSet<Point> posDulces = new HashSet<Point>();
	  
	  posDulces.addAll(new HashSet<Point>());
	  posDulces.addAll(new HashSet<Point>());
	  posDulces.addAll(new HashSet<Point>());
	  System.out.println(posDulces.size());
	  
  }
}
