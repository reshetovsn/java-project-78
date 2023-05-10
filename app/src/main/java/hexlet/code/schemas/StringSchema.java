package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        Predicate<Object> isString = x -> x instanceof String;
        addPredicates(isString);
    }

    @Override
    public StringSchema required() {
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
