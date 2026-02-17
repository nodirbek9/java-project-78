package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, ?>> {

    public MapSchema required() {
        super.required();
        addCheck("required", value -> !value.isEmpty());
        return this;
    }

    public MapSchema sizeOf(Integer sizeNum) {
        addCheck("sizeOf", map -> map.size() == sizeNum);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        addCheck("shape", map -> {
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();
                Object fieldValue = map.get(key);
                if (!schema.isValid(fieldValue)) {
                    return false;
                }
            }
            return true;
        });

        return this;
    }
}
