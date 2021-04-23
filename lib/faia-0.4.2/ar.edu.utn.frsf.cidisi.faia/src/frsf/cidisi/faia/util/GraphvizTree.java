/*
 * Copyright 2009 Daiana Murgan.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package frsf.cidisi.faia.util;

import java.io.File;

import frsf.cidisi.faia.solver.search.NTree;

public class GraphvizTree {

    private static int fileIdx = 0;
    private static String enc = "digraph g {\n" +
            "node [shape = Mrecord];\n";
    //"graph [rankdir = \"LR\"];\n";
    private static String pie = "\n}";
    private static final String searchTreesDir = "searchGVTrees/";

    public static void printFile(NTree tree) {

        PrintOut print = null;

        try {
            File f = new File(searchTreesDir);
            if (!f.exists()) {
                f.mkdir();
            }
            print = new PrintOut(searchTreesDir + fileIdx + ".dot", false);
            fileIdx = fileIdx + 1;
            print.write(enc);
            print.write(tree.toGraphviz());
            print.write(pie);
            print.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
