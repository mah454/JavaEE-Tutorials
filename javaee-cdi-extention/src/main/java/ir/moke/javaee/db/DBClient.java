package ir.moke.javaee.db;

public class DBClient {

    private String hostname;
    private String username;
    private String password;

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
