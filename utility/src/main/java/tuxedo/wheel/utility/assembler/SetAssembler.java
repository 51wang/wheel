package tuxedo.wheel.utility.assembler;

import java.util.HashSet;
import java.util.Set;

public class SetAssembler<E> extends CollectionAssembler<Set<E>, E> {
    public SetAssembler(Set<E> target) {
        super(target);
    }

    public SetAssembler() {
        this(new HashSet<E>());
    }
}
