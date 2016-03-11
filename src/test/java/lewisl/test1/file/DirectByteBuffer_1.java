package lewisl.test1.file;

import java.nio.ByteBuffer;
import java.util.concurrent.locks.LockSupport;

public class DirectByteBuffer_1 {
    public static void main(String[] args) {
        ByteBuffer.allocate(1200 * 1024 * 1024); //吃掉物理内存1.2G
        LockSupport.park();
    }
}
