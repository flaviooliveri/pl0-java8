package org.flavio.pl0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class IDTable {

    private List<ID> ids = new ArrayList<>();

    public void addId(ID id, BaseAndOffset baseAndOffset) {
        Optional<ID> found = findId(id, baseAndOffset.getBase(), baseAndOffset.getOffset());
        if (found.isPresent())
            throw new IllegalArgumentException(id.getType() + " " + id.getName() + " already declared");
        ids.add(id);
        baseAndOffset.increment();
    }

    private Optional<ID> findId(ID id, int from, int to) {
        if (to < from)
            return Optional.empty();
        List<ID> sublist = ids.subList(from, to);
        Collections.reverse(sublist);
        Predicate<ID> filterByNameAndType = item -> item.getName().equals(id.getName()) && item.getType() == id.getType();
        return sublist.stream().filter(filterByNameAndType).findFirst();
    }

}
