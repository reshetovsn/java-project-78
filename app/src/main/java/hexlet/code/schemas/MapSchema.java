package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> isMap = x -> x instanceof Map;
        addPredicates(isMap);
    }

    public MapSchema required() {
        canBeNull = false;
        return this;
    }

    public void sizeof(int num) {
        Predicate<Map<?, ?>> exactSize = x -> x.size() == num;
        addPredicates(exactSize);
    }

    public void shape(Map<String, BaseSchema> conditionsMap) {
        Predicate<Map<?, ?>> shape = inputMap -> conditionsMap.entrySet().stream()
                .allMatch(condition -> {
//                    System.out.println(condition.getValue());
                    Object key = condition.getKey();
                    return condition.getValue().isValid(inputMap.get(key));
                });
        addPredicates(shape);
    }
}
