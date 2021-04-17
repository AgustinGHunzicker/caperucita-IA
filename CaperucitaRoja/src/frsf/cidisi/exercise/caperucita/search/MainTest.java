package frsf.cidisi.exercise.caperucita.search;

import java.util.HashSet;
import java.awt.Point;
import java.util.ArrayList;

public class MainTest {

  public static void main(String[] args) {
//	ArrayList<Object> ar = new ArrayList<Object>();
//    ar.add(new ArrayList<Integer>());
//    ar.add("Una string");
    HashSet<Point> ar = new HashSet<Point>();
    ar.add(new Point(1,2));
    ar.add(new Point(2,1));
    ar.add(new Point(1,2));
    
    for (Point temp : ar) {
        System.out.println(temp);
     }
  }
}
