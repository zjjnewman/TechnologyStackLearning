package com.jin.atguigu.jvm;

/**
 * 测试byte Byte 占用空间大小
 */
public class LocationByteTest {
    public static void main(String[] args) {

        /**
         *  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
         * 512.0  512.0   0.0   480.0   2048.0   437.4     7168.0     1232.0   4864.0 3394.6 512.0  378.0       1    0.003   0      0.000    0.003
         */
//        byte[] b = new byte[1024*1024*1];

        /**
         *  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
         * 512.0  512.0   0.0   480.0   2048.0   452.7     7168.0     4288.0   4864.0 3380.6 512.0  375.8       1    0.002   0      0.000    0.002
         */
        Byte[] a = new Byte[1024*1024*1];
//        System.out.println(RamUsageEstimator.sizeOf(object) + "B");

        while (true){

        }
    }
}
