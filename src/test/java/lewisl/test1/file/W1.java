package lewisl.test1.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class W1 {

    public static void main(String[] args) throws IOException {
        // w();
        // r();

        ByteBuffer bb = ByteBuffer.allocate(10);
        bb.put("abcdefghkk".getBytes());
        bb.position(2);
        bb.limit(4);
        System.out.println(bb.get());
        bb.rewind();
        System.out.println(bb.get());
        bb.rewind();
        System.out.println(bb.get());

        // FileChannel.open(path, options)

    }

    public static void w() throws IOException {
        File file = new File("a");
        FileOutputStream os = new FileOutputStream(file, true);
        int i = 0;
        String s = "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
        while (i <= 10000) {
            os.write(s.getBytes());
        }
        os.close();
    }

    public static void r() throws IOException {
        File file = new File("a");

        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        //
        MappedByteBuffer buffer = channel.map(MapMode.READ_ONLY, 10000, 20);
        buffer = buffer.load();

        // buffer.flip();
        // System.out.println(buffer.array().length);
        //
        // System.out.println(channel.size());
        // byte[] b = new byte[33];
        // fis.read(b);
        // System.out.println(new String(b));
        fis.close();
    }

}
