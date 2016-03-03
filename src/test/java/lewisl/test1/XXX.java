package lewisl.test1;

import java.net.URLDecoder;

public class XXX {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String code =
                "lTDZXojfaXvis7EpoysdknGjSXDTCuOVUyCq47YbUggmUe3W%252FcuqzKrprEXlzXHpNOm5k4QN2Fav%250ASN%252FrLWjyN34UjiWP%252Bv5iVq5Z7CS7UGD%252B2STX4zgil6Nl%252BZ03Cx7yR5KsZd%252FnlicxMTpL3o8MV9LG%250AD0CCZKYURB0gopos%252B7k%253D";
        String code1="lTDZXojfaXvis7EpoysdknGjSXDTCuOVUyCq47YbUggmUe3W%252FcuqzKrprEXlzXHp%252F5Nw%252FbO%252BzzCT%250ApsGFitgcGa6ZGk5y0gTaRBcOiG%252F7TsDZSE%252Fzvw9UehiXCFbyamGCNgACOXfXKtx2BdQ7Gy5XWdLF%250AUdKxJqQCGaxMFRg3qcI%253D";
        System.out.println(URLDecoder.decode(code));
        System.out.println(URLDecoder.decode(code1));
    }
};
