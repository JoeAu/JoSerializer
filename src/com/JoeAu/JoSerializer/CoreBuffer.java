/**
 * JoSerializer
 * Author: glsensor@gmail.com (Joe Au)
 * @version 1.3 2012/11/28
 * Some compression algorithm from Google ProtocolBuffers
 */

package com.JoeAu.JoSerializer;


import java.io.IOException;

public class CoreBuffer {

	protected byte[] _buffer;

	protected int _position = 0;

	public int position() {
		return _position;
	}

	protected int _start = 0;

	public int start() {
		return _start;
	}

	protected int _bufferSize = 0;

	public int bufferSize() {
		return _bufferSize;
	}

	protected int _limit = 0;

	public int limit() {
		return _limit;
	}
	
	protected boolean _varBuffer = false;
	
	public boolean varBuffer() {
		return _varBuffer;
	}

	public CoreBuffer(int size) {
		super();
		this._buffer = new byte[size];
		this._start = 0;
		this._position = 0;
		this._bufferSize = size;
		this._limit = size;
		_varBuffer = true;
	}

	public CoreBuffer(byte[] buffer) {
		super();
		this._buffer = buffer;
		this._position = 0;
		this._start = 0;
		this._bufferSize = buffer.length;
		this._limit = buffer.length;
		_varBuffer = false;
	}

	public CoreBuffer(byte[] buffer, int start, int size) {
		super();
		this._buffer = buffer;
		this._start = start;
		this._position = start;
		this._bufferSize = size;
		this._limit = start + size;
		_varBuffer = false;
	}
	
	public int length(){
		return _limit - _start;
	}

	public final byte[] getBuffer() throws IOException {
		int length = length();
		byte[] newBuff = new byte[length];
		System.arraycopy(_buffer, _start, newBuff, 0, length);
		return newBuff;
	}

	public void flip() throws IOException {
		_limit = _position;
		_position = _start;
	}
	
	public void reset() throws IOException {
		_limit = _start + _bufferSize;
		_position = _start;
	}

	public void resize(int size) throws IOException {

	}
	
	public void skip(int size) throws IOException {
		_position+=size;
	}

	/*
	 * Read
	 */

	public byte readByte() throws IOException {
		return _buffer[_position++];
	}

	public boolean readBool() throws IOException {
		return readVarint32() != 0;
	}

	public int readInt32() throws IOException {
		return readVarint32();
	}

	public int readSInt32() throws IOException {
		return decodeZigZag32(readVarint32());
	}

	public long readInt64() throws IOException {
		return readVarint64();
	}

	public long readSInt64() throws IOException {
		return decodeZigZag64(readVarint64());
	}

	public float readFloat() throws IOException {
		return Float.intBitsToFloat(readLittleEndian32());
	}

	public double readDouble() throws IOException {
		return Double.longBitsToDouble(readLittleEndian64());
	}

	public String readString() throws IOException {
		final String result;
		final int size = readVarint32();
		if (size > 0) {
			result = new String(_buffer, _position, size, "UTF-8");
			_position += size;
		} else
			result = null;
		return result;
	}

	public byte[] readBytes() throws IOException {
		byte[] bytes;
		final int size = readVarint32();
		if (size > 0)
			bytes = readBytesDirect(size);
		else
			bytes = null;
		return bytes;
	}

	public byte[] readBytesDirect(int size) throws IOException {
		byte[] bytes = new byte[size];
		System.arraycopy(_buffer, _position, bytes, 0, size);
		_position += size;
		return bytes;
	}

	protected int readVarint32() throws IOException {
		byte tmp = readByte();
		if (tmp >= 0) {
			return tmp;
		}
		int result = tmp & 0x7f;
		if ((tmp = readByte()) >= 0) {
			result |= tmp << 7;
		} else {
			result |= (tmp & 0x7f) << 7;
			if ((tmp = readByte()) >= 0) {
				result |= tmp << 14;
			} else {
				result |= (tmp & 0x7f) << 14;
				if ((tmp = readByte()) >= 0) {
					result |= tmp << 21;
				} else {
					result |= (tmp & 0x7f) << 21;
					result |= (tmp = readByte()) << 28;
					if (tmp < 0) {
						// Discard upper 32 bits.
						for (int i = 0; i < 5; i++) {
							if (readByte() >= 0) {
								return result;
							}
						}
						throw new IOException();
					}
				}
			}
		}
		return result;
	}

	protected long readVarint64() throws IOException {
		int shift = 0;
		long result = 0;
		while (shift < 64) {
			final byte b = readByte();
			result |= (long) (b & 0x7F) << shift;
			if ((b & 0x80) == 0) {
				return result;
			}
			shift += 7;
		}
		throw new IOException();
	}

	protected int readLittleEndian32() throws IOException {
		final byte b1 = readByte();
		final byte b2 = readByte();
		final byte b3 = readByte();
		final byte b4 = readByte();
		return (((int) b1 & 0xff)) | (((int) b2 & 0xff) << 8)
				| (((int) b3 & 0xff) << 16) | (((int) b4 & 0xff) << 24);
	}

	protected long readLittleEndian64() throws IOException {
		final byte b1 = readByte();
		final byte b2 = readByte();
		final byte b3 = readByte();
		final byte b4 = readByte();
		final byte b5 = readByte();
		final byte b6 = readByte();
		final byte b7 = readByte();
		final byte b8 = readByte();
		return (((long) b1 & 0xff)) | (((long) b2 & 0xff) << 8)
				| (((long) b3 & 0xff) << 16) | (((long) b4 & 0xff) << 24)
				| (((long) b5 & 0xff) << 32) | (((long) b6 & 0xff) << 40)
				| (((long) b7 & 0xff) << 48) | (((long) b8 & 0xff) << 56);
	}

	protected int decodeZigZag32(final int n) {
		return (n >>> 1) ^ -(n & 1);
	}

	protected long decodeZigZag64(final long n) {
		return (n >>> 1) ^ -(n & 1);
	}

	/*
	 * Write
	 */

	public void writeByte(final byte value) throws IOException {
		_buffer[_position++] = value;
	}

	public void writeByte(final int value) throws IOException {
		writeByte((byte) value);
	}

	public void writeBool(final boolean value) throws IOException {
		writeByte(value ? 1 : 0);
	}

	public void writeInt32(final int value) throws IOException {
		writeVarint32(value);
	}

	public void writeSInt32(final int value) throws IOException {
		writeVarint32(encodeZigZag32(value));
	}

	public void writeInt64(final long value) throws IOException {
		writeVarint64(value);
	}

	public void writeSInt64(final long value) throws IOException {
		writeVarint64(encodeZigZag64(value));
	}

	public void writeFloat(final float value) throws IOException {
		writeLittleEndian32(Float.floatToRawIntBits(value));
	}

	public void writeDouble(final double value) throws IOException {
		writeLittleEndian64(Double.doubleToRawLongBits(value));
	}

	public void writeString(final String value) throws IOException {
		if (value != null)
			writeBytes(value.getBytes("UTF-8"));
		else
			writeBytes(null);
	}

	public void writeBytes(final byte[] value) throws IOException {
		int length = (value == null) ? 0 : value.length;
		writeVarint32(length);
		if (length > 0) {
			writeBytesDirect(value, 0, length);
		}
	}

	public void writeBytesDirect(final byte[] value, int offset, int length)
			throws IOException {
		System.arraycopy(value, offset, _buffer, _position, length);
		_position += length;
	}

	protected void writeVarint32(int value) throws IOException {
		while (true) {
			if ((value & ~0x7F) == 0) {
				writeByte(value);
				return;
			} else {
				writeByte((value & 0x7F) | 0x80);
				value >>>= 7;
			}
		}
	}

	protected void writeVarint64(long value) throws IOException {
		while (true) {
			if ((value & ~0x7FL) == 0) {
				writeByte((int) value);
				return;
			} else {
				writeByte(((int) value & 0x7F) | 0x80);
				value >>>= 7;
			}
		}
	}

	protected void writeLittleEndian32(final int value) throws IOException {
		writeByte((value) & 0xFF);
		writeByte((value >> 8) & 0xFF);
		writeByte((value >> 16) & 0xFF);
		writeByte((value >> 24) & 0xFF);
	}

	protected void writeLittleEndian64(final long value) throws IOException {
		writeByte((int) (value) & 0xFF);
		writeByte((int) (value >> 8) & 0xFF);
		writeByte((int) (value >> 16) & 0xFF);
		writeByte((int) (value >> 24) & 0xFF);
		writeByte((int) (value >> 32) & 0xFF);
		writeByte((int) (value >> 40) & 0xFF);
		writeByte((int) (value >> 48) & 0xFF);
		writeByte((int) (value >> 56) & 0xFF);
	}

	protected int encodeZigZag32(final int n) {
		return (n << 1) ^ (n >> 31);
	}

	protected long encodeZigZag64(final long n) {
		return (n << 1) ^ (n >> 63);
	}
}