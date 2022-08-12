package hexlet.code.serivces;

public final class ValueInfo {

    private final Object oldValue;
    private final Object newValue;
    private final Status status;

    public ValueInfo(Object inputOldValue, Object inputNewValue, Status inputStatus) {
        this.oldValue = inputOldValue;
        this.newValue = inputNewValue;
        this.status = inputStatus;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Status getStatus() {
        return status;
    }
}
