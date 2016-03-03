package lewisl.test1;

import java.io.IOException;
import java.net.URL;

public class T {
    public static void main(String[] args) throws IOException {
        // URL url = new URL("https://watch.tclclouds.com");
        URL url = new URL("https://login.alcatelonetouch.com/page/1.2/web/reg.html");
        url.openStream();
    }
}
