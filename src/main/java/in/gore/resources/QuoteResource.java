package in.gore.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by mihirgore on 21/06/16.
 */
@Path("/quotes")
public class QuoteResource {

    @GET
    public String newQuote() {
        return "Life is like riding a bicycle. You have to keep moving to keep your balance";
    }
}
