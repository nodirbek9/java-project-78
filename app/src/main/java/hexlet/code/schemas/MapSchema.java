package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer sizeForMap = null;

    @Override
    public boolean validate(Map value) {
        if (sizeForMap == null) {
            return true;
        }
        if (sizeForMap != null && value.size() == sizeForMap) {
            return true;
        }
        return false;
    }


    public MapSchema sizeof(Integer sizeNum) {
        this.sizeForMap = sizeNum;
        return this;
    }


}
