package ir.moke.javaee.model;

public enum RoleType {
    ADMIN("admin"), MEMBER("member");

    private String role;

    RoleType(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
