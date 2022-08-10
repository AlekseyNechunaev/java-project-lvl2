package hexlet.code;

import hexlet.code.serivces.Status;

public final class ValueInfo<T> {

    private final T oldValue;
    private final T newValue;
    private final Status status;

    public ValueInfo(T oldValue, T newValue, Status status) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.status = status;
    }

    public T getOldValue() {
        return oldValue;
    }

    public T getNewValue() {
        return newValue;
    }

    public Status getStatus() {
        return status;
    }
}
