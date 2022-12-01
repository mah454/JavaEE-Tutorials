package ir.moke.javaee;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

public abstract class AbstractBaseServlet<T extends Serializable> extends HttpServlet {

    private Jsonb jsonb;

    @PostConstruct
    public void init() {
        this.jsonb = JsonbBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.forEach((k, v) -> Arrays.stream(v).forEach(System.out::println));
        Jsonb jsonb = JsonbBuilder.create();
        PrintWriter writer = resp.getWriter();
        try {
            T t = handleRequest(parameterMap);
            String resultJson = jsonb.toJson(t);

            resp.setContentType(MediaType.APPLICATION_JSON);
            resp.setContentLength(resultJson.length());
            resp.setStatus(200);
            writer.write(resultJson);
        } catch (Exception e) {
            resp.sendError(500, e.getMessage());
        }
        writer.flush();
        writer.close();

    }

    public abstract T handleRequest(Map<String, String[]> parameterMap) throws Exception;
}
