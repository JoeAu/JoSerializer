
/**
 * JoSerializer
 * Author: glsensor@gmail.com (Joe Au)
 * @version 1.3 2012/11/28
 */

package com.JoeAu.JoSerializer;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

public final class JoSerializer {

	JoBuffer buffer;
	
	public JoSerializer(int size) {
		this.buffer = new JoBuffer(size);
	}

	public JoSerializer(byte[] buffer) {
		this.buffer = new JoBuffer(buffer);
	}

	public JoSerializer(byte[] buffer, int start, int size) {
		this.buffer = new JoBuffer(buffer, start, size);
	}
	
	private void writeNull(int bit) throws IOException{
		buffer.writeByte(bit);
	}
	
	public byte[] getBuffer() throws IOException{
		return buffer.getBuffer();
	}
	
	public void flip() throws IOException {
		buffer.flip();
	}
	
	public void reset() throws IOException {
		buffer.reset();
	}
	
	private boolean writeObjectNull(Object obj) throws IOException{
		boolean retb = (obj!=null);
		buffer.writeByte(retb?1:0);
		return retb;
	}
	
	private void readNull() throws IOException{
		buffer.skip(1);
	}
	
	private boolean readObjectNull() throws IOException{
		byte b = buffer.readByte();
		return (b==1);
	}
	
	/*
	 * Serialize a object
	 */
	public void Serialize(Object obj) throws Exception  {
		if(obj==null){
			writeNull(0);
			return;
		}
		
		SerializeField(obj, null);
	}
	
	private void SerializeField(Object obj, Field field) throws Exception  {
		if(obj==null && field==null){
			writeNull(0);
			return;
		}
		
		Class<?> c = null;
		if(field!=null){
			c = field.getType();
		}else{
			c = obj.getClass();
		}
		
		if(c.isArray()){
			Class<?> c1 = c.getComponentType();
			if(c1.isPrimitive()){
				if(c1.equals(byte.class))
					buffer.writeBytes((byte[])obj);
				else if(c1.equals(boolean.class))
					buffer.writeBools((boolean[])obj);
				else if(c1.equals(int.class))
					buffer.writeSInt32s((int[])obj);
				else if(c1.equals(long.class))
					buffer.writeSInt64s((long[])obj);
				else if(c1.equals(float.class))
					buffer.writeFloats((float[])obj);
				else if(c1.equals(double.class))
					buffer.writeDoubles((double[])obj);
				else 
					writeNull(0);
			}else {
				if(c1.equals(String.class))
					buffer.writeStrings((String[])obj);

				else if(c1.isEnum()){
					if(writeObjectNull(obj)){
						Object[] array = (Object[])obj;
						// length
						buffer.writeInt32(array.length);
						for(Object o : array){
							Serialize(o);
						}
					}
				}
				
				else if(ISerializable.class.isAssignableFrom(c1)){
					if(writeObjectNull(obj)){
						Object[] array = (Object[])obj;
						// length
						buffer.writeInt32(array.length);
						for(Object o : array){
							Serialize(o);
						}
					}
				}
				else {
					Class<?> c2 = c1.getComponentType();
					if(c1.isArray() && c2.equals(byte.class)){
						byte[][] array = (byte[][])obj;
						// length
						buffer.writeInt32(array.length);
						for(byte[] o : array){
							Serialize(o);
						}
					}
					else
						writeNull(0);
				}
			}
		}else{
			if(c.isPrimitive()){
				if(c.equals(byte.class))
					buffer.writeByte((Byte)obj);
				else if(c.equals(boolean.class))
					buffer.writeBool((Boolean)obj);
				else if(c.equals(int.class))
					buffer.writeSInt32((Integer)obj);
				else if(c.equals(long.class))
					buffer.writeSInt64((Long)obj);
				else if(c.equals(float.class))
					buffer.writeFloat((Float)obj);
				else if(c.equals(double.class))
					buffer.writeDouble((Double)obj);
				else
					writeNull(0);
			}else{
				if(c.equals(String.class))
					buffer.writeString((String)obj);
				else if(c.equals(Date.class))
					buffer.writeInt64(((Date)obj).getTime());
				else if(c.isEnum()){
					// Enum
					if(writeObjectNull(obj)){
						Method m = c.getMethod("ordinal", null); 
						int val = ((Integer)m.invoke(obj, null)).intValue(); 
						buffer.writeInt32(val);
					}
				}

				else if(ISerializable.class.isAssignableFrom(c)){
					// Object 
					if(writeObjectNull(obj)){
						// Fields
						Field[] fs = c.getFields();
						
						for(int i=0;i<fs.length;++i){
							Field f = fs[i];
							// Serialize Field
							Object fobj = null;
							try {
								fobj = f.get(obj);
							} catch (Exception e) {
							}
							SerializeField(fobj, f);
						}
					}
				}
				else
					writeNull(0);
			}
		}
	}
		
	/*
	 * Deserialize a object
	 */
	public Object Deserialize(Class<?> c) throws Exception {
		Object obj = null;
		
		if(c.isArray()){
			Class<?> c1 = c.getComponentType();
			if(c1.isPrimitive()){
				if(c1.equals(byte.class))
					obj = buffer.readBytes();
				else if(c1.equals(boolean.class))
					obj = buffer.readBools();
				else if(c1.equals(int.class))
					obj = buffer.readSInt32s();
				else if(c1.equals(long.class))
					obj = buffer.readSInt64s();
				else if(c1.equals(float.class))
					obj = buffer.readFloats();
				else if(c1.equals(double.class))
					obj = buffer.readDoubles();
				else
					readNull();
			}else{
				if(c1.equals(String.class))
					obj = buffer.readStrings();
				
				else if(c1.isEnum()){
					if(readObjectNull()){
						// length
						int len = buffer.readInt32();
						Object[] array = (Object[])Array.newInstance(c1, len);
						for(int i=0;i<len;++i){
							array[i] = Deserialize(c1);
						}
						obj = array;
					}
				}

				else if(ISerializable.class.isAssignableFrom(c1)){
					if(readObjectNull()){
						// length
						int len = buffer.readInt32();
						Object[] array = (Object[])Array.newInstance(c1, len);
						for(int i=0;i<len;++i){
							array[i] = Deserialize(c1);
						}
						obj = array;
					}
				}
				else{
					Class<?> c2 = c1.getComponentType();
					if(c1.isArray() && c2.equals(byte.class)){
						// length
						int len = buffer.readInt32();
						byte[][] array = (byte[][])Array.newInstance(c1, len);
						for(int i=0;i<len;++i){
							array[i] = (byte[])Deserialize(c1);
						}
						obj = array;
					}
					else
						readNull();
				}
			}
		}else{
			if(c.isPrimitive()){
				if(c.equals(byte.class))
					obj = buffer.readByte();
				else if(c.equals(boolean.class))
					obj = buffer.readBool();
				else if(c.equals(int.class))
					obj = buffer.readSInt32();
				else if(c.equals(long.class))
					obj = buffer.readSInt64();
				else if(c.equals(float.class))
					obj = buffer.readFloat();
				else if(c.equals(double.class))
					obj = buffer.readDouble();
				else
					readNull();
			}else{
				if(c.equals(String.class))
					obj = buffer.readString();
				else if(c.equals(Date.class))
					obj = new Date(buffer.readInt64());
				else if(c.isEnum()){
					if(readObjectNull()){
						int val = buffer.readInt32();
						Method m = c.getMethod("ordinal", null);
						Object[] consts = c.getEnumConstants();
						for(Object o : consts){
							Integer v = (Integer)m.invoke(o, null); 
							if(v.intValue()==val){
								obj = o;
								break;
							}
						}
					}
				}
				
				else if(ISerializable.class.isAssignableFrom(c)){
					// Object
					if(readObjectNull()){
						obj = c.newInstance();
						// Fields count
						Field[] fs = c.getFields();
						for(int i=0;i<fs.length;++i){
							Field f = fs[i];
							// Serialize Field
							Object fobj = Deserialize(f.getType());
							f.set(obj, fobj);
						}
					}
				}
				else
					readNull();
			}
		}
		return obj;
	}
}
