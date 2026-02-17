package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {

    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(Integer sizeNum) {
        addCheck("sizeOf", map -> map.size() == sizeNum);
        return this;
    }

    public MapSchema<T> shape(Map<String, BaseSchema<T>> schemas) {
        addCheck("shape", map -> {
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();
                T fieldValue = map.get(key);
                if (!schema.isValid(fieldValue)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
