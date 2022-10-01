package ir.moke.javaee.db;

public class DBClient {

    private final String hostname;
    private final String username;
    private final String password;

    public DBClient(String hostname, String username, String password) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        System.out.println("[DBClient] Connect to server : " + hostname);
        System.out.println("[DBClient] Username : " + username );
        System.out.println("[DBClient] Password : " + password );
    }

    public void disconnect() {
        System.out.println("[DBClient] Disconnect from server");
    }
}
