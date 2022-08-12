package hexlet.code;

import hexlet.code.serivces.Status;

public final class ValueInfo<T> {

    private final T oldValue;
    private final T newValue;
    private final Status status;

    // Оставил параметры конструктора такими (не разобрался как отключить ворнинг на проверку HiddenField)
    // Ворнинг получилось приглушить только локально
    public ValueInfo(T oldVal, T newVal, Status stat) {
        this.oldValue = oldVal;
        this.newValue = newVal;
        this.status = stat;
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
