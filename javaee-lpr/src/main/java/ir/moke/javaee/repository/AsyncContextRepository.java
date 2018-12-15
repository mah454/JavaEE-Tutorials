package ir.moke.javaee.repository;

import javax.ejb.Stateless;
import javax.servlet.AsyncContext;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class AsyncContextRepository {
    private static final Map<String, AsyncContext> asyncMap = new HashMap<>();

    public void save(AsyncContext context, String channel) {
        asyncMap.put(channel, context);
    }

    public AsyncContext find(String channel) {
        return asyncMap.get(channel);
    }
}
