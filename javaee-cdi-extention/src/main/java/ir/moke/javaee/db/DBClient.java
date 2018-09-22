package ir.moke.javaee.db;

public class DBClient {

    private String hostname;

    public DBClient(String hostname) {
        this.hostname = hostname;
    }

    public void connect(String username, String password) {
        System.out.println("[DBClient] Connect to server : " + hostname);
        System.out.println("[DBClient] Username : " + username + " > Password : " + password);
    }

    public void disconnect() {
        System.out.println("[DBClient] Disconnect from server");
    }
}
