package frsf.cidisi.faia.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import frsf.cidisi.faia.solver.search.NTree;

public class TreeMLWriter {

	private static final HashMap TYPES = new HashMap();
	static {
		TYPES.put(int.class, Constantes.INT);
		TYPES.put(Integer.class, Constantes.INTEGER);
		TYPES.put(Long.class, Constantes.LONG);
		TYPES.put(Float.class, Constantes.FLOAT);
		TYPES.put(Double.class, Constantes.DOUBLE);
		TYPES.put(boolean.class, Constantes.BOOLEAN);
		TYPES.put(String.class, Constantes.STRING);
		TYPES.put(Date.class, Constantes.DATE);
	}

	private static int fileIdx = 0;
	private static final String searchTreesDir = "TreeML/";

	public static void printFile(NTree graph) {

		File f = new File(searchTreesDir);
		if (!f.exists()) {
			f.mkdir();
		}

		FileOutputStream os = null;
		try {
			os = new FileOutputStream(new File(searchTreesDir + fileIdx
					+ ".xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (os != null) {

			PrintWriter out = new PrintWriter(os);
			XMLWriter xml = new XMLWriter(out);

			xml.startTree(new Integer(fileIdx).toString());
			fileIdx = fileIdx + 1;

			NTree node = (NTree) graph.clone();

			while (node.getParent() != null)
				node = node.getParent();

			Vector<NTree> nodos = new Vector<NTree>();
			nodos.add(node);
			nodos.addAll(node.getSonsTotal());

			int totalNodes = nodos.size();

			for (int i = 0; i < totalNodes; i++) {
				NTree nodo = nodos.get(i);
				escribirNodo(nodo, xml);
				//escribirEnlaces(nodo, xml);
			}

			for (int i = 0; i < totalNodes; i++) {
				NTree nodo = nodos.get(i);
				escribirEnlaces(nodo, xml);
			}

			xml.end();
			xml.finish();
		}
	}

	private static void escribirNodo(NTree tree, XMLWriter xml) {
		String tag = Constantes.NODE;
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();

		names.add(Constantes.ID);
		names.add(Constantes.ACTION);
		names.add(Constantes.COST);
		names.add(Constantes.AGENT_STATE);

		Integer i = new Integer(tree.getExecutionOrder());
		values.add(i.toString());
		if (tree.getAction() == null)
			values.add("null");
		else
			values.add(tree.getAction().toString());

		values.add(new Double(tree.getCost()).toString());
		values.add(tree.getAgentState().toString());

		xml.tag(tag, names, values, 4);

	}

	private static void escribirEnlaces(NTree tree, XMLWriter xml) {
		String tag = Constantes.EDGE;
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();

		NTree s = (NTree) tree.clone();
		Vector<NTree> ts = s.getSons();

		for (int i = 0; i < ts.size(); i++) {
			names.add(Constantes.SOURCE);
			names.add(Constantes.TARGET);
			
			values.add(new Integer(s.getExecutionOrder()).toString());
			values.add(new Integer(ts.get(i).getExecutionOrder()).toString());

			xml.tag(tag, names, values, 2);
			names.clear();
			values.clear();
		}
	}
}
