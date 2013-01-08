JoSerializer
============

Serialization, Deserialization, reflection Object-relational mapping

1) The test need Protocol Buffers project.

2) vs ProtocolBuffersORM
<pre>
<code>
JoSerializer Test1：
Serializer length 231，10000 cycles elapsed 359ms
[1, 6, -11, -22, -33, -44, -55, -66, 1, 5, 7, 51, 51, -93, -64, -51, -52, -52, -52, -52, -52, 24, -64, 1, 2, 17, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 46, 1, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46, 1, -3, -120, 15, 2, 6, -11, -22, -33, -44, -55, -66, 3, 77, 88, 99, 3, 1, 0, 1, 3, -93, 36, -9, 18, -83, 5, 4, -9, 18, -119, 52, -9, 18, -9, 18, 3, 72, -31, 4, -62, -31, -72, 64, -58, 102, 102, -12, -62, 3, 41, 92, -113, -62, -11, -88, 40, -64, 125, 63, 53, -114, -113, 30, 103, -63, -31, 122, 20, -82, 115, -103, -3, -64, 3, 1, 1, 1, 0, 1, 2, 2, 12, 104, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 10, 103, 111, 111, 100, 32, 110, 105, 103, 104, 116, 2, 1, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46, 1, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46]
Deserialize，10000 cycles elapsed 375ms
destEntity.b = [-11, -22, -33, -44, -55, -66]
destEntity.bo = true
destEntity.i = -3
destEntity.l = -4
destEntity.f = -5.1
destEntity.d = -6.2
destEntity.e = yyy
destEntity.str = some string here.
destEntity.model1.str1 = oh, some string here too.
destEntity.model2.pid = -123455
destEntity.Arrayb[0] = [-11, -22, -33, -44, -55, -66]
destEntity.Arrayb[1] = [77, 88, 99]
destEntity.Arraybo = [true, false, true]
destEntity.Arrayi = [-2322, -1212, -343]
destEntity.Arrayl = [-1212, -3333, -1212, -1212]
destEntity.Arrayf = [-33.22, -12334.22, -122.2]
destEntity.Arrayd = [-12.33, -1.2121212444E7, -121239.23]
destEntity.Arraye = [ccc, xxx, yyy]
destEntity.Arraystr = [hello world!, good night]
destEntity.Arraymodel1[0].str = oh, some string here too.
destEntity.Arraymodel1[1].str = oh, some string here too.

JoSerializer Test2：
Serializer length 231，10000 cycles elapsed 297ms
[1, 6, -11, -22, -33, -44, -55, -66, 1, 5, 7, 51, 51, -93, -64, -51, -52, -52, -52, -52, -52, 24, -64, 1, 2, 17, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 46, 1, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46, 1, -3, -120, 15, 2, 6, -11, -22, -33, -44, -55, -66, 3, 77, 88, 99, 3, 1, 0, 1, 3, -93, 36, -9, 18, -83, 5, 4, -9, 18, -119, 52, -9, 18, -9, 18, 3, 72, -31, 4, -62, -31, -72, 64, -58, 102, 102, -12, -62, 3, 41, 92, -113, -62, -11, -88, 40, -64, 125, 63, 53, -114, -113, 30, 103, -63, -31, 122, 20, -82, 115, -103, -3, -64, 3, 1, 1, 1, 0, 1, 2, 2, 12, 104, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 10, 103, 111, 111, 100, 32, 110, 105, 103, 104, 116, 2, 1, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46, 1, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46]
Deserialize，10000 cycles elapsed 343ms
destEntity.b = [-11, -22, -33, -44, -55, -66]
destEntity.bo = true
destEntity.i = -3
destEntity.l = -4
destEntity.f = -5.1
destEntity.d = -6.2
destEntity.e = yyy
destEntity.str = some string here.
destEntity.model1.str1 = oh, some string here too.
destEntity.model2.pid = -123455
destEntity.Arrayb[0] = [-11, -22, -33, -44, -55, -66]
destEntity.Arrayb[1] = [77, 88, 99]
destEntity.Arraybo = [true, false, true]
destEntity.Arrayi = [-2322, -1212, -343]
destEntity.Arrayl = [-1212, -3333, -1212, -1212]
destEntity.Arrayf = [-33.22, -12334.22, -122.2]
destEntity.Arrayd = [-12.33, -1.2121212444E7, -121239.23]
destEntity.Arraye = [ccc, xxx, yyy]
destEntity.Arraystr = [hello world!, good night]
destEntity.Arraymodel1[0].str = oh, some string here too.
destEntity.Arraymodel1[1].str = oh, some string here too.

===================================================

ProtocolBuffersORM Test1：
Serializer length 258，10000 cycles elapsed 203ms
[10, 6, -11, -22, -33, -44, -55, -66, 16, 1, 24, 5, 32, 7, 45, 51, 51, -93, -64, 49, -51, -52, -52, -52, -52, -52, 24, -64, 56, 2, 66, 17, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 46, 74, 27, 10, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46, 82, 4, 8, -3, -120, 15, 90, 6, -11, -22, -33, -44, -55, -66, 90, 3, 77, 88, 99, 98, 3, 1, 0, 1, 106, 6, -93, 36, -9, 18, -83, 5, 114, 8, -9, 18, -119, 52, -9, 18, -9, 18, 122, 12, 72, -31, 4, -62, -31, -72, 64, -58, 102, 102, -12, -62, -126, 1, 24, 41, 92, -113, -62, -11, -88, 40, -64, 125, 63, 53, -114, -113, 30, 103, -63, -31, 122, 20, -82, 115, -103, -3, -64, -120, 1, 1, -120, 1, 0, -120, 1, 2, -110, 1, 12, 104, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, -110, 1, 10, 103, 111, 111, 100, 32, 110, 105, 103, 104, 116, -102, 1, 27, 10, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46, -102, 1, 27, 10, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46]
Deserialize，10000 cycles elapsed 94ms
destEntity.b = [-11, -22, -33, -44, -55, -66]
destEntity.bo = true
destEntity.i = -3
destEntity.l = -4
destEntity.f = -5.1
destEntity.d = -6.2
destEntity.e = yyy
destEntity.str = some string here.
destEntity.model1.str1 = oh, some string here too.
destEntity.model2.pid = -123455
destEntity.Arrayb[0] = [-11, -22, -33, -44, -55, -66]
destEntity.Arrayb[1] = [77, 88, 99]
destEntity.Arraybo = [true, false, true]
destEntity.Arrayi = [-2322, -1212, -343]
destEntity.Arrayl = [-1212, -3333, -1212, -1212]
destEntity.Arrayf = [-33.22, -12334.22, -122.2]
destEntity.Arrayd = [-12.33, -1.2121212444E7, -121239.23]
destEntity.Arraye = [ccc, xxx, yyy]
destEntity.Arraystr = [hello world!, good night]
destEntity.Arraymodel1[0].str = oh, some string here too.
destEntity.Arraymodel1[1].str = oh, some string here too.

ProtocolBuffersORM Test2：
Serializer length 258，10000 cycles elapsed 125ms
[10, 6, -11, -22, -33, -44, -55, -66, 16, 1, 24, 5, 32, 7, 45, 51, 51, -93, -64, 49, -51, -52, -52, -52, -52, -52, 24, -64, 56, 2, 66, 17, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 46, 74, 27, 10, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46, 82, 4, 8, -3, -120, 15, 90, 6, -11, -22, -33, -44, -55, -66, 90, 3, 77, 88, 99, 98, 3, 1, 0, 1, 106, 6, -93, 36, -9, 18, -83, 5, 114, 8, -9, 18, -119, 52, -9, 18, -9, 18, 122, 12, 72, -31, 4, -62, -31, -72, 64, -58, 102, 102, -12, -62, -126, 1, 24, 41, 92, -113, -62, -11, -88, 40, -64, 125, 63, 53, -114, -113, 30, 103, -63, -31, 122, 20, -82, 115, -103, -3, -64, -120, 1, 1, -120, 1, 0, -120, 1, 2, -110, 1, 12, 104, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, -110, 1, 10, 103, 111, 111, 100, 32, 110, 105, 103, 104, 116, -102, 1, 27, 10, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46, -102, 1, 27, 10, 25, 111, 104, 44, 32, 115, 111, 109, 101, 32, 115, 116, 114, 105, 110, 103, 32, 104, 101, 114, 101, 32, 116, 111, 111, 46]
Deserialize，10000 cycles elapsed 63ms
destEntity.b = [-11, -22, -33, -44, -55, -66]
destEntity.bo = true
destEntity.i = -3
destEntity.l = -4
destEntity.f = -5.1
destEntity.d = -6.2
destEntity.e = yyy
destEntity.str = some string here.
destEntity.model1.str1 = oh, some string here too.
destEntity.model2.pid = -123455
destEntity.Arrayb[0] = [-11, -22, -33, -44, -55, -66]
destEntity.Arrayb[1] = [77, 88, 99]
destEntity.Arraybo = [true, false, true]
destEntity.Arrayi = [-2322, -1212, -343]
destEntity.Arrayl = [-1212, -3333, -1212, -1212]
destEntity.Arrayf = [-33.22, -12334.22, -122.2]
destEntity.Arrayd = [-12.33, -1.2121212444E7, -121239.23]
destEntity.Arraye = [ccc, xxx, yyy]
destEntity.Arraystr = [hello world!, good night]
destEntity.Arraymodel1[0].str = oh, some string here too.
destEntity.Arraymodel1[1].str = oh, some string here too.

</code>
</pre>
