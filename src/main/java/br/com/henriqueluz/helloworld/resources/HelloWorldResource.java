package br.com.henriqueluz.helloworld.resources;

import br.com.henriqueluz.helloworld.model.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong idCounter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.idCounter = new AtomicLong();
    }

    @GET
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String content = String.format(template, name.orElse(defaultName));
        return new Saying(idCounter.incrementAndGet(), content);
    }
}
