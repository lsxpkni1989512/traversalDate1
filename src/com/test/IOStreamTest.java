package com.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IOStreamTest {
    public static void main(String[] args) {
           Map<String,Object> map = new HashMap<>();
           map.put("a","");
           if(null ==map.get("a")){
               System.out.println("a为空字符串");
           }
           if(null ==map.get("b")){
               System.out.println("b为空");
           }


    }
    //把信息写到文件
    public void m1() throws IOException {
        try{
            String filename = "F:\\workspace\\traversalDate1\\src\\com\\test\\files\\a.txt";
            FileWriter fw = new FileWriter(filename);
            fw.write("Text\n");
            fw.close();
        }catch (IOException ioE){

        }
    }
    //从文件中读取内容
    public void m2() throws IOException {
        String filename = "F:\\workspace\\traversalDate1\\src\\com\\test\\files\\a.txt";
        FileReader fr2 = null;
        BufferedReader bufr = null;
        try {
            fr2 = new FileReader(filename);
            bufr = new BufferedReader(fr2);
            String line = null;
            //BufferedReader提供了按行读取文本文件的方法readLine()
            //readLine()返回行有效数据，不包含换行符，未读取到数据返回null
            while((line = bufr.readLine())!=null) {
                System.out.println(line);
            }
        }catch(IOException e) {
            System.out.println("异常：" + e.toString());
        }finally {
            try {
                if(bufr!=null)
                    bufr.close();
            }catch(IOException e) {
                System.out.println("异常：" + e.toString());
            }
        }
    }
}
