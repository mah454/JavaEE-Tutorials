package ir.moke.javaee.repository;

import javax.ejb.Singleton;
import javax.servlet.AsyncContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class AsyncContextRepository {
    private static final Map<String, AsyncContext> asyncMap = new HashMap<>();

    public void save(String channel, AsyncContext context) {
        asyncMap.put(channel, context);
    }

    public AsyncContext find(String channel) {
        return asyncMap.get(channel);
    }

    public Map<String, AsyncContext> find() {
        return asyncMap;
    }

    public List<String> listChannels() {
        return asyncMap.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public void removeChannel(String channel) {
        asyncMap.remove(channel);
    }
}
