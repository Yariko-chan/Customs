package main.models;

public class DbResult<T> {
    private T value;

    private boolean isSqlException = false;
    private boolean isConnectionError = false;
    private boolean isAuthError = false;

    public DbResult() {
    }

    public T value() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isAnyError() {
        return isAuthError || isConnectionError || isSqlException;
    }

    public boolean isSqlException() {
        return isSqlException;
    }

    public boolean isConnectionError() {
        return isConnectionError;
    }

    public boolean isAuthError() {
        return isAuthError;
    }

    public void setSqlException() {
        isSqlException = true;
    }

    public void setConnectionError() {
        isConnectionError = true;
    }

    public void setAuthError() {
        isAuthError = true;
    }
}
