/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa
 * y Milton Pividori.
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

import java.io.*;

class PrintOut {

    private File outputFile;
    private FileWriter out;

    PrintOut(String name) throws Exception {
        this(name, true);
    }

    PrintOut(String name, boolean append) throws Exception {

        outputFile = new File(name);

        try {

            out = new FileWriter(outputFile, append);

        } catch (IOException ex) {
            throw new Exception(ex.getMessage());

        }

    }

    public void write(String texto) throws Exception {
        try {

            out.write(texto);

        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void close() throws Exception {
        try {

            out.close();

        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
