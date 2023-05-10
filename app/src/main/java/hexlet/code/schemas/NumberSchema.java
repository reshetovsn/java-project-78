package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        Predicate<Object> isInt = x -> x instanceof Integer;
        addPredicates(isInt);
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> isPos = x -> x > 0;
        addPredicates(isPos);
        return this;
    }

    public void range(Integer x1, Integer x2) {
        Predicate<Integer> inRange = x -> (x >= x1 && x <= x2);
        addPredicates(inRange);
    }
}
