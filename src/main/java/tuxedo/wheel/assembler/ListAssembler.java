package tuxedo.wheel.assembler;

import java.util.ArrayList;
import java.util.List;

public class ListAssembler<E> extends CollectionAssembler<List<E>, E> {
    public ListAssembler(List<E> target) {
        super(target);
    }

    public ListAssembler() {
        this(new ArrayList<E>());
    }
}
