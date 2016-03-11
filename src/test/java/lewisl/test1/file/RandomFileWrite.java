package lewisl.test1.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomFileWrite {

    public static void writeData() throws IOException {
        RandomAccessFile fileSample2 = new RandomAccessFile(new File("r1.txt"), "rw");
        fileSample2.write("你好的".getBytes());
        fileSample2.seek(0);
        fileSample2.skipBytes(3);// 90M
        fileSample2.write("小".getBytes());
        fileSample2.close();
        
        //the enventual string will be "你小的" 
    }

    public static void main(String[] args) throws IOException {
        writeData();
    }

}
