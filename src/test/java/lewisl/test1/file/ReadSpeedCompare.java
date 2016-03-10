package lewisl.test1.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadSpeedCompare {

    public static void preparedData() throws IOException {
        RandomAccessFile fileSample2 = new RandomAccessFile(new File("/home/yliu/test1"), "rw");
        fileSample2.skipBytes(90000000);// 90M
        fileSample2.write(5);
        fileSample2.close();
    }

    public static void testRandomFile() throws IOException {
        long sampleTime11 = System.currentTimeMillis();
        RandomAccessFile fileSample2 = new RandomAccessFile(new File("/home/yliu/test1"), "rw");
        long sampleTime12 = System.currentTimeMillis();

        System.out.println("New File Object Time elapsed:" + (sampleTime12 - sampleTime11) + " ms");

        long sampleTime1 = System.currentTimeMillis();

        int sample2Cnt = 0;
        while (sample2Cnt++ < 3) {
            fileSample2.seek(0);
            fileSample2.skipBytes(90000000);
            int sample2Value = fileSample2.readByte();
            long sampleTime2 = System.currentTimeMillis();
            System.out.println("content:" + sample2Value + ",Time elapsed:"
                    + (sampleTime2 - sampleTime1) + " ms");
        }
        long sampleTime21 = System.currentTimeMillis();
        fileSample2.close();
        long sampleTime22 = System.currentTimeMillis();
        System.out.println("Close File Object Time elapsed:" + (sampleTime22 - sampleTime21)
                + " ms");
    }

    public static void testSequenceFile() throws IOException {
        long sampleTime11 = System.currentTimeMillis();
        FileInputStream inputStream = new FileInputStream(new File("/home/yliu/test1"));
        long sampleTime12 = System.currentTimeMillis();
        System.out.println("New File Object Time elapsed:" + (sampleTime12 - sampleTime11) + " ms");

        long sampleTime1 = System.currentTimeMillis();

        byte[] buff = new byte[1000];
        int cnt = 0;
        int currLen = 0;
        while ((currLen = inputStream.read(buff)) > 0) {
            cnt += currLen;
            if (cnt == 90000000) {
                long sampleTime2 = System.currentTimeMillis();
                System.out.println("content:" + inputStream.read() + ",Time elapsed:"
                        + (sampleTime2 - sampleTime1) + " ms");
                break;
            }
        }
        inputStream.close();

    }

    public static void main(String[] args) throws IOException {
        preparedData();
        // sequential read
        // new Thread() {
        // @Override
        // public void run() {
        // for (int i = 0; i < 10000000; i++) {
        // try {
        testSequenceFile();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // super.run();
        // }
        // }.start();
        System.out.println("############################");
        // random read

        testRandomFile();
    }

}
