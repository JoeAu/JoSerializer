
/**
 * JoSerializer
 * Author: glsensor@gmail.com (Joe Au)
 * @version 1.3 2012/11/28
 */

package com.JoeAu.JoSerializer;

/**
 * Author: glsensor@gmail.com (Joe Au)
 * @version 1.3 2012/11/28
 */
import java.io.IOException;

public class JoBuffer extends CoreBuffer {

	public JoBuffer(int size) {
		super(size);
	}

	public JoBuffer(byte[] buffer) {
		super(buffer);
	}

	public JoBuffer(byte[] buffer, int start, int size) {
		super(buffer, start, size);
	}

	/*
	 * Read Array
	 */

	public boolean[] readBools() throws IOException {
		final int size = readInt32();
		boolean[] value;
		if (size > 0) {
			value = new boolean[size];
			for (int i = 0; i < size; ++i)
				value[i] = readBool();
		} else
			value = null;
		return value;
	}

	public int[] readSInt32s() throws IOException {
		final int size = readInt32();
		int[] value;
		if (size > 0) {
			value = new int[size];
			for (int i = 0; i < size; ++i)
				value[i] = readSInt32();
		} else
			value = null;
		return value;
	}

	public long[] readSInt64s() throws IOException {
		final int size = readInt32();
		long[] value;
		if (size > 0) {
			value = new long[size];
			for (int i = 0; i < size; ++i)
				value[i] = readSInt64();
		} else
			value = null;
		return value;
	}

	public float[] readFloats() throws IOException {
		final int size = readInt32();
		float[] value;
		if (size > 0) {
			value = new float[size];
			for (int i = 0; i < size; ++i)
				value[i] = readFloat();
		} else
			value = null;
		return value;
	}

	public double[] readDoubles() throws IOException {
		final int size = readInt32();
		double[] value;
		if (size > 0) {
			value = new double[size];
			for (int i = 0; i < size; ++i)
				value[i] = readDouble();
		} else
			value = null;
		return value;
	}

	public String[] readStrings() throws IOException {
		final int size = readInt32();
		String[] value;
		if (size > 0) {
			value = new String[size];
			for (int i = 0; i < size; ++i)
				value[i] = readString();
		} else
			value = null;
		return value;
	}

	/**
	 * Write Array
	 */

	public void writeBools(final boolean[] value) throws IOException {
		if (value != null && value.length > 0) {
			final int length = value.length;
			writeInt32(length);
			for (int i = 0; i < length; ++i)
				writeBool(value[i]);
		} else
			writeInt32(0);
	}
	
	public void writeSInt32s(final int[] value) throws IOException {
		if (value != null && value.length > 0) {
			final int length = value.length;
			writeInt32(length);
			for (int i = 0; i < length; ++i)
				writeSInt32(value[i]);
		} else
			writeInt32(0);
	}

	public void writeSInt64s(final long[] value) throws IOException {
		if (value != null && value.length > 0) {
			final int length = value.length;
			writeInt32(length);
			for (int i = 0; i < length; ++i)
				writeSInt64(value[i]);
		} else
			writeInt32(0);
	}

	public void writeFloats(final float[] value) throws IOException {
		if (value != null && value.length > 0) {
			final int length = value.length;
			writeInt32(length);
			for (int i = 0; i < length; ++i)
				writeFloat(value[i]);
		} else
			writeInt32(0);
	}

	public void writeDoubles(final double[] value) throws IOException {
		if (value != null && value.length > 0) {
			final int length = value.length;
			writeInt32(length);
			for (int i = 0; i < length; ++i)
				writeDouble(value[i]);
		} else
			writeInt32(0);
	}

	public void writeStrings(final String[] value) throws IOException {
		if (value != null && value.length > 0) {
			final int length = value.length;
			writeInt32(length);
			for (int i = 0; i < length; ++i)
				writeString(value[i]);
		} else
			writeInt32(0);
	}

}
