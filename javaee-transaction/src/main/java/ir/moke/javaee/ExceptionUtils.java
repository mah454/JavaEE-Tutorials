package ir.moke.javaee;

import java.sql.SQLException;

public interface ExceptionUtils {
    static Exception getSQLCause(Exception e) {
        if (e.getCause() instanceof SQLException sqlException) return sqlException ;
        return getSQLCause((Exception) e.getCause());
    }
}
