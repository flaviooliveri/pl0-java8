package org.flavio.pl0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.flavio.pl0.IDType.CONST;
import static org.flavio.pl0.IDType.PROCEDURE;
import static org.flavio.pl0.IDType.VAR;

public class IDTable {

    private List<ID> ids = new ArrayList<>();

    public boolean addId(ID id, BaseAndOffset baseAndOffset) {
        Optional<ID> found = findId(id.getName(), id.getType(), baseAndOffset.getBase(), baseAndOffset.getOffset());
        if (found.isPresent())
            return false;
        ids.add(id);
        baseAndOffset.increment();
        return true;
    }

    private Optional<ID> findId(String name, IDType type, int from, int to) {
        if (to < from)
            return Optional.empty();
        List<ID> sublist = ids.subList(from, to);
        Collections.reverse(sublist);
        Predicate<ID> filterByNameAndType = item -> item.getName().equals(name) && item.getType() == type;
        return sublist.stream().filter(filterByNameAndType).findFirst();
    }

    public Optional<ID> findVariable(String name, BaseAndOffset baseAndOffset) {
        return findId(name, VAR, 0, baseAndOffset.getBasePlusOffset());
    }

    public Optional<ID> findConst(String name, BaseAndOffset baseAndOffset) {
        return findId(name, CONST, 0, baseAndOffset.getBasePlusOffset());
    }

    public Optional<ID> findProcedure(String name, BaseAndOffset baseAndOffset) {
        return findId(name, PROCEDURE, 0, baseAndOffset.getBasePlusOffset());
    }

}
