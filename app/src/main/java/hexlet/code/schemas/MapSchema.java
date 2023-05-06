package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> map = x -> x instanceof Map;
        addPredicates(map);
    }

    @Override
    public BaseSchema required() {
        super.required();
        return this;
    }

    public void sizeof(int num) {
        Predicate<Map> exactSize = x -> x.size() == num;
        addPredicates(exactSize);
    }

    public void shape(Map<String, BaseSchema> map) {
    }
}
