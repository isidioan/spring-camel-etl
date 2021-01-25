package com.iioannou.cameletlprocessor.service.validator;

import com.iioannou.cameletlprocessor.util.Keys;
import com.iioannou.cameletlprocessor.util.ParseUtility;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static com.iioannou.cameletlprocessor.util.Keys.*;


public class ShipmentValidator {

    private final List<Long> orderIds;

    public ShipmentValidator(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public Map<String, Object> validateRecord(Map<String, Object> obj, int index) {

        Map<String, Object> clone = new LinkedHashMap<>(obj);

        for (Map.Entry<String, Object> ent : obj.entrySet()) {

            if (!ent.getKey().equals(Keys.ID) && !ent.getKey().equals(ORDER_ID) &&
                    !ent.getKey().equals(QUANTITY) && !ent.getKey().equals(STATUS) && !ent.getKey().equals(CREATED_DATETIME) &&
                    !ent.getKey().equals(LAST_UPDATE_DATETIME) && !ent.getKey().equals(ERROR_CODE) &&
                    !ent.getKey().equals(ERROR_MSG)) {
                clone.put(ERROR_CODE, MALFORMED_ERROR);
                clone.put(ERROR_MSG, index + "-" + ent.getKey());
                return clone;

            } else if ((ent.getKey().contains(CREATED_DATETIME) || ent.getKey().equals(LAST_UPDATE_DATETIME))
                    && StringUtils.isNotEmpty(ent.getValue().toString())) {
                Optional<Date> date = ParseUtility.parseDateValue(ent.getValue().toString());
                if (date.isEmpty()) {
                    clone.put(ERROR_CODE, MALFORMED_ERROR);
                    clone.put(ERROR_MSG, index + "-" + ent.getKey());
                    return clone;
                }
            } else if (!ent.getKey().equals(LAST_UPDATED_DATETIME) && StringUtils.isEmpty(ent.getValue().toString())) {
                clone.put(ERROR_CODE, MISSING_ERROR);
                clone.put(ERROR_MSG, index + "-" + ent.getKey());
                return clone;
            } else if (!ent.getKey().contains("DATE") && StringUtils.isNotEmpty(ent.getValue().toString()) && !StringUtils.isNumeric(ent.getValue().toString())) {
                clone.put(ERROR_CODE, MALFORMED_ERROR);
                clone.put(ERROR_MSG, index + "-" + ent.getKey());
                return clone;
            } else if (ent.getKey().equals(ORDER_ID) && ent.getValue() != null
                    && orderIds.stream().noneMatch(i -> i.equals(Long.parseLong(ent.getValue().toString())))) {
                clone.put(ERROR_CODE, FOREIGN_KEY_ERROR);
                clone.put(ERROR_MSG, index + "-" + ent.getKey());
                return clone;
            }
        }
        clone.put(ERROR_CODE, "0");
        clone.put(ERROR_MSG, "");
        return clone;
    }
}
