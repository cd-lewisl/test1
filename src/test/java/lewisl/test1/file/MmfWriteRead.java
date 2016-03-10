package lewisl.test1.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

public class MmfWriteRead {

    public static int totalBytes = 1024 * 1024 * 10;

    public static void mmpWrite() throws IOException {
        RandomAccessFile file = new RandomAccessFile(new File("mmp_1.txt"), "rwd");

        MappedByteBuffer buffer = file.getChannel().map(MapMode.READ_WRITE, 0, totalBytes);

        for (int i = 0; i < totalBytes; i++) {
            buffer.put((byte) 5);
        }

        file.close();
    }

    public static void sequentialWrite() throws IOException {
        File file = new File("_2.txt");

        FileOutputStream fos = new FileOutputStream(file);
        for (int i = 0; i < totalBytes; i++) {
            fos.write((byte) 5);
        }

        fos.close();
    }

    public static void main(String[] args) throws IOException {
        long time1 = System.currentTimeMillis();
        mmpWrite();
        long time2 = System.currentTimeMillis();

        System.out.println("MMP Write time elapsed:" + (time2 - time1) + " ms");
        sequentialWrite();
        long time3 = System.currentTimeMillis();
        System.out.println("Regular File Write time elapsed:" + (time3 - time2) + " ms");

    }
}
