package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    protected boolean canBeNull = true;
    protected List<Predicate> predicates = new ArrayList<>();

    public void addPredicates(Predicate state) {
        predicates.add(state);
    }

    public void required() {
        this.canBeNull = false;
    }

//    public BaseSchema notRequired() {
//        this.canBeNull = true;
//        return this;
//    }

    public boolean isValid(Object obj) {
        if (canBeNull && (obj == null || obj.equals(""))) {
            return true;
        } else if (!canBeNull && (obj == null || obj.equals(""))) {
            return false;
        } else {
            return predicates.stream()
                    .allMatch(predicate -> predicate.test(obj));
        }
    }
}
