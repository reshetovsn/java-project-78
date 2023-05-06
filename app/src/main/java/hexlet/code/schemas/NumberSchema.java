package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        Predicate<Object> num = x -> x instanceof Integer;
        addPredicates(num);
    }

    @Override
    public void required() {
        super.required();
    }

    public NumberSchema positive() {
        Predicate<Integer> positive = x -> x > 0;
        addPredicates(positive);
        return this;
    }

    public void range(Integer x1, Integer x2) {
        Predicate<Integer> inRange = x -> (x >= x1 && x <= x2);
        addPredicates(inRange);
    }
}
