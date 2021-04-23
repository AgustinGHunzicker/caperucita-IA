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
package frsf.cidisi.faia.state;

public abstract class MatlabEnvironmentState extends EnvironmentState {

    private int startTime;
    private int endTime;
    private int step;

    public MatlabEnvironmentState(int startTime, int step) {
        this.startTime = startTime;
        this.step = step;
        this.endTime = this.startTime + this.step;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getStep() {
        return step;
    }

    public void nextTime() {
        this.startTime = this.endTime;
        this.endTime = this.startTime + this.step;
    }
}
