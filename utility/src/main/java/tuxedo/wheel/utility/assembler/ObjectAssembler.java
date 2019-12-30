package tuxedo.wheel.utility.assembler;

import lombok.NonNull;

public abstract class ObjectAssembler<T> {
    protected final T target;

    public ObjectAssembler(@NonNull T target) {
        this.target = target;
    }

    public T assemble() {
        return target;
    }
}
