package in.gore.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by mihirgore on 21/06/16.
 */
@Path("/quotes")
public class QuoteResource {

    public static class MyException extends Exception {
        public MyException(String str) {
            super(str);
        }
    }

    @GET
    public String newQuote() throws Exception {

//        if (Math.random() < 0.5)
//            throw new MyException("My Exception");

        return "Life is like riding a bicycle. You have to keep moving to keep your balance";
    }
}
