
/**
 * Author: glsensor@gmail.com (Joe Au)
 * https://github.com/JoeAu/
 */

package com.JoeAu.JoSerializer.Test;

import java.util.Arrays;

import com.JoeAu.JoSerializer.JoSerializer;
import com.JoeAu.JoSerializer.Test.ModelA;
import com.JoeAu.JoSerializer.Test.PModelHelper;

public class JoSerializerTesting {

	static PModel srcEntity;
	static StopWatch sw = new StopWatch();
	static JoSerializer joSer;
	
	static ModelA.PBModel.PBModelEntity srcPBEntity;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		joSer = new JoSerializer(1000);
		
		srcEntity = PModelHelper.GetPModelForTest();
		System.out.println(String.format("JoSerializer Test1£º"));
		JoSerializerTesting();
		System.out.println(String.format("JoSerializer Test2£º"));
		JoSerializerTesting();
		
		System.out.println("\n===================================================\n");

		srcPBEntity = PModelHelper.GetPBModelEntity();
		System.out.println(String.format("ProtocolBuffersORM Test1£º"));
		ProtocolBuffersORMTesting();
		System.out.println(String.format("ProtocolBuffersORM Test2£º"));
		ProtocolBuffersORMTesting();
	}
	
	private static void JoSerializerTesting() throws Exception{
		
		//Serialize
		byte[] buffer = null;
		sw.start();
		for(int i=0;i<10000;++i){
			joSer.Serialize(srcEntity);
			joSer.flip();
			buffer = joSer.getBuffer();
			joSer.reset();
		}
		sw.stop();

		sw.println(String.format("Serializer length %d£¬10000 cycles", buffer.length));
		System.out.println(String.format("%s", Arrays.toString(buffer)));
		
		//Deserialize
		JoSerializer joSer2 = new JoSerializer(buffer);
		
		PModel destEntity = null;
		Object obj = null;
		sw.start();
		for(int i=0;i<10000;++i){
			obj = joSer2.Deserialize(PModel.class);
			joSer2.reset();
		}
		sw.stop();
		sw.println("Deserialize£¬10000 cycles");
		
		destEntity = (PModel)obj;
		
		System.out.printf("destEntity.b = %s\n", Arrays.toString(destEntity.b));
		System.out.printf("destEntity.bo = %s\n", destEntity.bo);
		System.out.printf("destEntity.i = %s\n", destEntity.i);
		System.out.printf("destEntity.l = %s\n", destEntity.l);
		System.out.printf("destEntity.f = %s\n", destEntity.f);
		System.out.printf("destEntity.d = %s\n", destEntity.d);
		System.out.printf("destEntity.e = %s\n", destEntity.e);
		System.out.printf("destEntity.str = %s\n", destEntity.str);
		System.out.printf("destEntity.model1.str1 = %s\n", destEntity.model1.str1);
		System.out.printf("destEntity.model2.pid = %s\n", destEntity.model2.pid);
		
		System.out.printf("destEntity.Arrayb = [%s, %s]\n", Arrays.toString(destEntity.Arrayb[0]), Arrays.toString(destEntity.Arrayb[1]));
		System.out.printf("destEntity.Arraybo = %s\n", Arrays.toString(destEntity.Arraybo));
		System.out.printf("destEntity.Arrayi = %s\n", Arrays.toString(destEntity.Arrayi));
		System.out.printf("destEntity.Arrayl = %s\n", Arrays.toString(destEntity.Arrayl));
		System.out.printf("destEntity.Arrayf = %s\n", Arrays.toString(destEntity.Arrayf));
		System.out.printf("destEntity.Arrayd = %s\n", Arrays.toString(destEntity.Arrayd));
		System.out.printf("destEntity.Arraye = %s\n", Arrays.toString(destEntity.Arraye));
		System.out.printf("destEntity.Arraystr = %s\n", Arrays.toString(destEntity.Arraystr));
		System.out.printf("destEntity.Arraymodel1[0].str = %s\n", destEntity.Arraymodel1[0].str1);
		System.out.printf("destEntity.Arraymodel1[1].str = %s\n", destEntity.Arraymodel1[1].str1);
		
		
	}
	
	private static void ProtocolBuffersORMTesting() throws Exception{
		
		// SerializeEntity
		ModelA.PBModel pbProxy = null;
		byte[] buffer = null;
		sw.start();
		for(int i=0;i<10000;++i){
			pbProxy = ModelA.PBModel.SerializeEntity(srcPBEntity);
			buffer = pbProxy.toByteArray();
		}
		sw.stop();

		sw.println(String.format("Serializer length %d£¬10000 cycles", buffer.length));
		System.out.println(String.format("%s", Arrays.toString(buffer)));
		
		// DeserializeEntity
		ModelA.PBModel.PBModelEntity destEntity = null;
		
		ModelA.PBModel pbProxy2 = ModelA.PBModel.parseFrom(buffer);
		
		sw.start();
		for(int i=0;i<10000;++i){
			destEntity = ModelA.PBModel.DeserializeEntity(pbProxy2);
		}
		sw.stop();
		sw.println("Deserialize£¬10000 cycles");
		
		byte[] buffer2 = pbProxy2.toByteArray();
		
		System.out.printf("destEntity.b = %s\n", Arrays.toString(destEntity.b));
		System.out.printf("destEntity.bo = %s\n", destEntity.bo);
		System.out.printf("destEntity.i = %s\n", destEntity.i);
		System.out.printf("destEntity.l = %s\n", destEntity.l);
		System.out.printf("destEntity.f = %s\n", destEntity.f);
		System.out.printf("destEntity.d = %s\n", destEntity.d);
		System.out.printf("destEntity.e = %s\n", destEntity.e);
		System.out.printf("destEntity.str = %s\n", destEntity.str);
		System.out.printf("destEntity.model1.str1 = %s\n", destEntity.model1.str1);
		System.out.printf("destEntity.model2.pid = %s\n", destEntity.model2.pid);
		
		System.out.printf("destEntity.Arrayb = [%s, %s]\n", Arrays.toString(destEntity.Arrayb[0]), Arrays.toString(destEntity.Arrayb[1]));
		System.out.printf("destEntity.Arraybo = %s\n", Arrays.toString(destEntity.Arraybo));
		System.out.printf("destEntity.Arrayi = %s\n", Arrays.toString(destEntity.Arrayi));
		System.out.printf("destEntity.Arrayl = %s\n", Arrays.toString(destEntity.Arrayl));
		System.out.printf("destEntity.Arrayf = %s\n", Arrays.toString(destEntity.Arrayf));
		System.out.printf("destEntity.Arrayd = %s\n", Arrays.toString(destEntity.Arrayd));
		System.out.printf("destEntity.Arraye = %s\n", Arrays.toString(destEntity.Arraye));
		System.out.printf("destEntity.Arraystr = %s\n", Arrays.toString(destEntity.Arraystr));
		System.out.printf("destEntity.Arraymodel1[0].str = %s\n", destEntity.Arraymodel1[0].str1);
		System.out.printf("destEntity.Arraymodel1[1].str = %s\n", destEntity.Arraymodel1[1].str1);

	}
	

}
