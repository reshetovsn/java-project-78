package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        Predicate<Object> num = x -> x instanceof Integer;
        addPredicates(num);
    }

    @Override
    public BaseSchema required() {
        super.required();
        return this;
    }
}
