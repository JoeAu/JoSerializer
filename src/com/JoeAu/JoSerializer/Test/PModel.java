
/**
 * Author: glsensor@gmail.com (Joe Au)
 * https://github.com/JoeAu/
 */

package com.JoeAu.JoSerializer.Test;

import com.JoeAu.JoSerializer.ISerializable;

public class PModel implements ISerializable {
	public byte[] b;
	public boolean bo;
	public int i;
	public long l;
	public float f;
	public double d;

	public PEnum1 e;
	public String str;
	public PModel1 model1;
	public PModel2 model2;

	public byte[][] Arrayb;
	public boolean[] Arraybo;
	public int[] Arrayi;
	public long[] Arrayl;
	public float[] Arrayf;
	public double[] Arrayd;
	
	public PEnum1[] Arraye;
	public String[] Arraystr;
	public PModel1[] Arraymodel1;
}


