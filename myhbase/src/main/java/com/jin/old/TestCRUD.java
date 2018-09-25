package com.jin.old;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.text.DecimalFormat;

public class TestCRUD {
    @Test
    public void put() throws Exception {
        //创建conf对象
        Configuration conf = HBaseConfiguration.create();
        //通过连接工厂创建链接对象
        Connection conn = ConnectionFactory.createConnection(conf);
        //通过链接查询table对象
        TableName tname = TableName.valueOf("ns1:t1");
        Table table = conn.getTable(tname);
        //通过bytes的工具类创建字节数组
        byte[] rowid = Bytes.toBytes("row1");
        //创建put对象，指定rowid
        Put put = new Put(rowid);
        byte[] f1 = Bytes.toBytes("f1");
        byte[] id = Bytes.toBytes("id");
        byte[] value = Bytes.toBytes(100);
        put.addColumn(f1,id,value);
        //执行插入操作
        table.put(put);
    }

    @Test
    public void get() throws Exception{
        //创建conf对象
        Configuration conf = HBaseConfiguration.create();
        //通过连接工厂创建链接对象
        Connection conn = ConnectionFactory.createConnection(conf);
        //通过链接查询table对象
        TableName tname = TableName.valueOf("ns1:t1");
        Table table = conn.getTable(tname);
        Get get = new Get(Bytes.toBytes("row1"));
        Result r = table.get(get);
        byte[] value = r.getValue(Bytes.toBytes("f1"), Bytes.toBytes("id"));
        System.out.println(Bytes.toInt(value));
    }

    @Test
    public void bigput() throws Exception {
        long start = System.currentTimeMillis();
        Configuration conf = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(conf);
        TableName tname = TableName.valueOf("ns1:t1");
        HTable table = (HTable) conn.getTable(tname);
        //不要自动清理缓冲区
        table.setAutoFlush(false);
        //格式化rowkey
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("0000000");
        for(int i=2;i<1000;i++){
            Put put = new Put(Bytes.toBytes("row"+format.format(i)));
            //关闭写前日志
            put.setWriteToWAL(false);
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("id"), Bytes.toBytes(i));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("tom"+i));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("id"), Bytes.toBytes(i%100));
            table.put(put);
            if(i%2000==0){
                table.flushCommits();
            }
        }
        table.flushCommits();
        System.out.println(System.currentTimeMillis() - start);
    }
}















