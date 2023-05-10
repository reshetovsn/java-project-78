package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        Predicate<Object> isString = x -> x instanceof String;
        addPredicates(isString);
    }

    @Override
    public BaseSchema required() {
        super.required();
        return this;
    }

    public void minLength(int minLen) {
        Predicate<String> lengthPredicate = x -> x.length() >= minLen;
        addPredicates(lengthPredicate);
    }

    public StringSchema contains(String str) {
        Predicate<String> containsSubstring = x -> x.contains(str);
        addPredicates(containsSubstring);
        return this;
    }
}
