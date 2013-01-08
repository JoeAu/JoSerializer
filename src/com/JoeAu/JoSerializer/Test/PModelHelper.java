
/**
 * Author: glsensor@gmail.com (Joe Au)
 * https://github.com/JoeAu/
 */

package com.JoeAu.JoSerializer.Test;

import com.JoeAu.JoSerializer.Test.PEnum1;
import com.JoeAu.JoSerializer.Test.PModel;
import com.JoeAu.JoSerializer.Test.PModel1;
import com.JoeAu.JoSerializer.Test.PModel2;
import com.JoeAu.JoSerializer.Test.ModelA.PBEnum1.PBEnum1Entity;
import com.JoeAu.JoSerializer.Test.ModelA.PBModel.PBModelEntity;
import com.JoeAu.JoSerializer.Test.ModelA.PBModel1.PBModel1Entity;
import com.JoeAu.JoSerializer.Test.ModelA.PBModel2.PBModel2Entity;

public class PModelHelper {
	public static PModel GetPModelForTest() {
		PModel model = new PModel();

		model.b = new byte[] { -11, -22, -33, -44, -55, -66 };
		model.bo = true;
		model.i = -3;
		model.l = -4l;
		model.f = -5.1f;
		model.d = -6.2d;

		model.e = PEnum1.yyy;
		model.str = "some string here.";
		PModel1 model1 = new PModel1();
		model1.str1 = "oh, some string here too.";
		model.model1 = model1;
		PModel2 model2 = new PModel2();
		model2.pid = -123455;
		model.model2 = model2;

		model.Arrayb = new byte[][] {
				new byte[] { -11, -22, -33, -44, -55, -66 },
				new byte[] { 77, 88, 99 } };
		model.Arraybo = new boolean[] { true, false, true };
		model.Arrayi = new int[] { -2322, -1212, -343 };
		model.Arrayl = new long[] { -1212l, -3333l, -1212l, -1212l };
		model.Arrayf = new float[] { -33.22f, -12334.22f, -122.2f };
		model.Arrayd = new double[] { -12.33d, -12121212.444d, -121239.23d };

		model.Arraye = new PEnum1[] { PEnum1.ccc, PEnum1.xxx, PEnum1.yyy };

		model.Arraystr = new String[] { "hello world!", "good night" };

		model.Arraymodel1 = new PModel1[] { model1, model1 };

		return model;
	}

	public static PBModelEntity GetPBModelEntity() {
		PBModelEntity model = new PBModelEntity();
		PModel pmodel = GetPModelForTest();
		model.b = pmodel.b;
		model.bo = pmodel.bo;
		model.i = pmodel.i;
		model.l = pmodel.l;
		model.f = pmodel.f;
		model.d = pmodel.d;

		model.e = PBEnum1Entity.valueOf(pmodel.e.value());
		model.str = pmodel.str;
		PBModel1Entity model1 = new PBModel1Entity();
		model1.str1 = pmodel.model1.str1;
		model.model1 = model1;
		PBModel2Entity model2 = new PBModel2Entity();
		model2.pid = pmodel.model2.pid;
		model.model2 = model2;

		model.Arrayb = pmodel.Arrayb;
		model.Arraybo = pmodel.Arraybo;
		model.Arrayi = pmodel.Arrayi;
		model.Arrayl = pmodel.Arrayl;
		model.Arrayf = pmodel.Arrayf;
		model.Arrayd = pmodel.Arrayd;

		model.Arraye = new PBEnum1Entity[] {
				PBEnum1Entity.valueOf(pmodel.Arraye[0].value()),
				PBEnum1Entity.valueOf(pmodel.Arraye[1].value()),
				PBEnum1Entity.valueOf(pmodel.Arraye[2].value()) };

		model.Arraystr = pmodel.Arraystr;

		model.Arraymodel1 = new PBModel1Entity[] { model1, model1 };

		return model;
	}
}
