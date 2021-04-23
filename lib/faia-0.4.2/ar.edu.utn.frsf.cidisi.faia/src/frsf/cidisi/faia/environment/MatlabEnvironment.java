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
package frsf.cidisi.faia.environment;

import java.io.File;
import java.util.Hashtable;

import jmatlink.JMatLink;
import frsf.cidisi.faia.state.MatlabEnvironmentState;

public abstract class MatlabEnvironment extends Environment {

    private JMatLink engine;

    public MatlabEnvironment() {
        this.engine = new JMatLink();
        this.engine.engOpen("matlab -nosplash -nojvm");

        // Change matlab current directory
        // First, we get the path specified by the user
        String userPath =
                "cd '" +
                System.getProperty("user.dir") + "/" +
                this.getMatlabProjectPath() + "'";

        // Then we need to change that path to a system dependent one,
        // changing the path separator accordingly
        String systemPath = userPath.replace("\\/", File.pathSeparator);

        this.engine.engEvalString(systemPath);
    }

    @Override
    public void close() {
        try {
            this.engine.engClose();
        } catch (Exception ex) {
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            this.engine.engClose();
        } catch (Exception ex) {
        } finally {
            super.finalize();
        }
    }

    @Override
    public MatlabEnvironmentState getEnvironmentState() {
        return (MatlabEnvironmentState) this.environmentState;
    }

    protected Hashtable<String, double[][]> startSimulation() {
        this.engine.engEvalString(
                "[" + this.join(this.getMatlabFunctionReturnVariables(), ",") + "] = " +
                this.getMatlabFunctionName() + "(" +
                this.join(this.getMatlabFunctionParameters(), ",") + ");");

        Hashtable<String, double[][]> returnVariables =
                new Hashtable<String, double[][]>();

        double[][] matrix;

        for (Object obj : this.getMatlabFunctionReturnVariables()) {
            matrix = this.engine.engGetArray(obj.toString());

            returnVariables.put(obj.toString(), matrix);
        }

        return returnVariables;
    }

    private String join(Object[] array, String separator) {
        StringBuffer sb = new StringBuffer();

        sb.append(array[0].toString());

        for (int i = 1; i < array.length; i++) {
            sb.append(separator + array[i].toString());
        }

        return sb.toString();
    }

    protected abstract String getMatlabProjectPath();

    protected abstract String getMatlabFunctionName();

    protected abstract Object[] getMatlabFunctionParameters();

    protected abstract Object[] getMatlabFunctionReturnVariables();
}
