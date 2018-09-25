package com.old.chong;

public class Testcount {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,1,2,3,4,5,Integer.MAX_VALUE,199};
        System.out.println(getcount(arr));
    }

    public static int getcount(int[] arr){
        //定义字节数组的长度也就是行数
        int rows=Integer.MAX_VALUE/8+1;
        byte[] bytes = new byte[rows];
        int count=0;
        for (int i : arr) {
            //定位整数的行数
            int row=i/8;
            //定位整数的列数
            int col=i%8;
            //计算这个整数在字节的位上是否存在
            int x=(bytes[row])&(1<<col);
            if(x==0){
                count++;
                //把字节上面对应的位定为1
                bytes[row]=(byte)((bytes[row])|(1<<col));
            }
        }
        return count;
    }
}
