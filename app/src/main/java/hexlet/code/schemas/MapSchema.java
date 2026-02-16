package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, ?>> {
    private Integer sizeForMap = null;
    private Map<String, BaseSchema<String>> shapes = Map.of();

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    @Override
    public boolean validate(Map<String, ?> value) {
        if (sizeForMap != null && value.size() != sizeForMap) {
            return false;
        }
        if (shapes.isEmpty()) {
            return true;
        } else {
            for (var shape : shapes.entrySet()) {
                String key = shape.getKey();
                BaseSchema sch = shape.getValue();
                Object fieldValue = (value.get(key));
                if (!sch.isValid(fieldValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    public MapSchema sizeof(Integer sizeNum) {
        this.sizeForMap = sizeNum;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        this.shapes = schemas;
        return this;
    }
}
