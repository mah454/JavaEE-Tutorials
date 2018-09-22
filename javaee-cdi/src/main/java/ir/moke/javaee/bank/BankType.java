package ir.moke.javaee.bank;

public enum BankType {
    SADERAT("Saderat"), MELLAT("mellat");

    private String type;

    BankType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
