package lewisl.test1.snappy;

import java.io.IOException;

import org.xerial.snappy.Snappy;

public class Snappy_1 {

    public static void testScatterdStr() throws IOException {
        String originalStr = "你好啊我很好";
        byte newBytes[] = Snappy.compress(originalStr.getBytes());
        System.out.println(originalStr.getBytes().length);
        System.out.println("Compressed Size:" + newBytes.length);

        System.out.println(Snappy.uncompressString(newBytes));
    }

    public static void testHighRepetitiveStr() throws IOException {
        String originalStr = "你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊你好啊";
        byte newBytes[] = Snappy.compress(originalStr.getBytes());
        System.out.println(originalStr.getBytes().length);
        System.out.println("Compressed Size:" + newBytes.length);

        System.out.println(Snappy.uncompressString(newBytes));
    }

    public static void main(String[] args) throws IOException {
        testScatterdStr();
        testHighRepetitiveStr();
    }
}
