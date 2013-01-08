
/**
 * Author: glsensor@gmail.com (Joe Au)
 * https://github.com/JoeAu/JoSerializer
 */

package com.JoeAu.JoSerializer.Test;

import java.util.Arrays;

import com.JoeAu.JoSerializer.JoSerializer;
import com.JoeAu.JoSerializer.Test.ModelA;
import com.JoeAu.JoSerializer.Test.PModelHelper;

public class JoSerializerTesting {

	static PModel srcEntity;
	static StopWatch sw = new StopWatch();
	
	static ModelA.PBModel.PBModelEntity srcPBEntity;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		srcEntity = PModelHelper.GetPModelForTest();
		//srcEntity = new PModel();
		System.out.println(String.format("JoSerializer Test1£º"));
		JoSerializerTesting();
		System.out.println();
		System.out.println(String.format("JoSerializer Test2£º"));
		JoSerializerTesting();
		
		System.out.println("\n===================================================\n");

		srcPBEntity = PModelHelper.GetPBModelEntity();
		//srcPBEntity = new ModelA.PBModel.PBModelEntity();
		System.out.println(String.format("ProtocolBuffersORM Test1£º"));
		ProtocolBuffersORMTesting();
		System.out.println();
		System.out.println(String.format("ProtocolBuffersORM Test2£º"));
		ProtocolBuffersORMTesting();
	}
	
	private static void JoSerializerTesting() throws Exception{
		
		//Serialize
		byte[] buffer = null;
		sw.start();
		for(int i=0;i<10000;++i){
			JoSerializer joSer = new JoSerializer(1000);
			joSer.Serialize(srcEntity);
			joSer.flip();
			buffer = joSer.getBuffer();
		}
		sw.stop();

		sw.println(String.format("Serializer length %d£¬10000 cycles", buffer.length));
		System.out.println(String.format("%s", Arrays.toString(buffer)));
		
		//Deserialize
		PModel destEntity = null;
		sw.start();
		for(int i=0;i<10000;++i){
			JoSerializer joSer2 = new JoSerializer(buffer);
			destEntity = (PModel)joSer2.Deserialize(PModel.class);
		}
		sw.stop();
		sw.println("Deserialize£¬10000 cycles");
		
		System.out.printf("destEntity.b = %s\n", Arrays.toString(destEntity.b));
		System.out.printf("destEntity.bo = %s\n", destEntity.bo);
		System.out.printf("destEntity.i = %s\n", destEntity.i);
		System.out.printf("destEntity.l = %s\n", destEntity.l);
		System.out.printf("destEntity.f = %s\n", destEntity.f);
		System.out.printf("destEntity.d = %s\n", destEntity.d);
		System.out.printf("destEntity.e = %s\n", destEntity.e);
		System.out.printf("destEntity.str = %s\n", destEntity.str);
		
		if(destEntity.model1!=null)
			System.out.printf("destEntity.model1.str1 = %s\n", destEntity.model1.str1);
		else
			System.out.printf("destEntity.model1 = null\n");
		
		if(destEntity.model2!=null)
			System.out.printf("destEntity.model2.pid = %s\n", destEntity.model2.pid);
		else
			System.out.printf("destEntity.model2 = null\n");
		
		if(destEntity.Arrayb!=null)
			for(int i=0;i<destEntity.Arrayb.length;++i)
				System.out.printf("destEntity.Arrayb[%d] = %s\n", i, Arrays.toString(destEntity.Arrayb[i]));
		else
			System.out.printf("destEntity.Arrayb = null\n");
		
		System.out.printf("destEntity.Arraybo = %s\n", Arrays.toString(destEntity.Arraybo));
		System.out.printf("destEntity.Arrayi = %s\n", Arrays.toString(destEntity.Arrayi));
		System.out.printf("destEntity.Arrayl = %s\n", Arrays.toString(destEntity.Arrayl));
		System.out.printf("destEntity.Arrayf = %s\n", Arrays.toString(destEntity.Arrayf));
		System.out.printf("destEntity.Arrayd = %s\n", Arrays.toString(destEntity.Arrayd));
		System.out.printf("destEntity.Arraye = %s\n", Arrays.toString(destEntity.Arraye));
		System.out.printf("destEntity.Arraystr = %s\n", Arrays.toString(destEntity.Arraystr));
		
		if(destEntity.Arraymodel1!=null){
			for(int i=0;i<destEntity.Arrayb.length;++i)
				System.out.printf("destEntity.Arraymodel1[%d].str = %s\n", i, destEntity.Arraymodel1[i].str1);
		}else
			System.out.printf("destEntity.Arraymodel1 = null\n");
	}
	
	private static void ProtocolBuffersORMTesting() throws Exception{
		
		// SerializeEntity
		byte[] buffer = null;
		sw.start();
		for(int i=0;i<10000;++i){
			ModelA.PBModel pbProxy = ModelA.PBModel.SerializeEntity(srcPBEntity);
			buffer = pbProxy.toByteArray();
		}
		sw.stop();

		sw.println(String.format("Serializer length %d£¬10000 cycles", buffer.length));
		System.out.println(String.format("%s", Arrays.toString(buffer)));
		
		// DeserializeEntity
		ModelA.PBModel.PBModelEntity destEntity = null;
		
		sw.start();
		for(int i=0;i<10000;++i){
			ModelA.PBModel pbProxy2 = ModelA.PBModel.parseFrom(buffer);
			destEntity = ModelA.PBModel.DeserializeEntity(pbProxy2);
		}
		sw.stop();
		sw.println("Deserialize£¬10000 cycles");
		
		System.out.printf("destEntity.b = %s\n", Arrays.toString(destEntity.b));
		System.out.printf("destEntity.bo = %s\n", destEntity.bo);
		System.out.printf("destEntity.i = %s\n", destEntity.i);
		System.out.printf("destEntity.l = %s\n", destEntity.l);
		System.out.printf("destEntity.f = %s\n", destEntity.f);
		System.out.printf("destEntity.d = %s\n", destEntity.d);
		System.out.printf("destEntity.e = %s\n", destEntity.e);
		System.out.printf("destEntity.str = %s\n", destEntity.str);
		
		if(destEntity.model1!=null)
			System.out.printf("destEntity.model1.str1 = %s\n", destEntity.model1.str1);
		else
			System.out.printf("destEntity.model1 = null\n");
		
		if(destEntity.model2!=null)
			System.out.printf("destEntity.model2.pid = %s\n", destEntity.model2.pid);
		else
			System.out.printf("destEntity.model2 = null\n");
		
		if(destEntity.Arrayb!=null)
			for(int i=0;i<destEntity.Arrayb.length;++i)
				System.out.printf("destEntity.Arrayb[%d] = %s\n", i, Arrays.toString(destEntity.Arrayb[i]));
		else
			System.out.printf("destEntity.Arrayb = null\n");
		
		System.out.printf("destEntity.Arraybo = %s\n", Arrays.toString(destEntity.Arraybo));
		System.out.printf("destEntity.Arrayi = %s\n", Arrays.toString(destEntity.Arrayi));
		System.out.printf("destEntity.Arrayl = %s\n", Arrays.toString(destEntity.Arrayl));
		System.out.printf("destEntity.Arrayf = %s\n", Arrays.toString(destEntity.Arrayf));
		System.out.printf("destEntity.Arrayd = %s\n", Arrays.toString(destEntity.Arrayd));
		System.out.printf("destEntity.Arraye = %s\n", Arrays.toString(destEntity.Arraye));
		System.out.printf("destEntity.Arraystr = %s\n", Arrays.toString(destEntity.Arraystr));
		
		if(destEntity.Arraymodel1!=null){
			for(int i=0;i<destEntity.Arrayb.length;++i)
				System.out.printf("destEntity.Arraymodel1[%d].str = %s\n", i, destEntity.Arraymodel1[i].str1);
		}else
			System.out.printf("destEntity.Arraymodel1 = null\n");

	}
	

}
