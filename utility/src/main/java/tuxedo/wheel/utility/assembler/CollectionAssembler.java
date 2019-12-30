package tuxedo.wheel.utility.assembler;

import java.util.Collection;

public abstract class CollectionAssembler<C extends Collection<E>, E> extends ObjectAssembler<C> {
    public CollectionAssembler(C target) {
        super(target);
    }

    public CollectionAssembler<C, E> add(E e) {
        target.add(e);
        return this;
    }

    public CollectionAssembler<C, E> addAll(Collection<? extends E> c) {
        target.addAll(c);
        return this;
    }
}
