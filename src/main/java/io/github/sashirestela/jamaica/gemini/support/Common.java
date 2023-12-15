package io.github.sashirestela.jamaica.gemini.support;

import java.util.List;

public class Common {

    private Common() {
    }

    public static void validateExactlyOneIsSetted(List<?> fieldsValues, String fieldsNames) {
        var totalNonNull = totalNonNullFields(fieldsValues);
        if (totalNonNull < 1) {
            throw new IllegalArgumentException("At least a field must be setted (" + fieldsNames + ".");
        } else if (totalNonNull > 1) {
            throw new IllegalArgumentException("Only a field must be setted (" + fieldsNames + ".");
        }
    }

    public static void validateOnlyOneIsSetted(List<?> fieldsValues, String fieldsNames) {
        var totalNonNull = totalNonNullFields(fieldsValues);
        if (totalNonNull > 1) {
            throw new IllegalArgumentException("Only a field must be setted (" + fieldsNames + ".");
        }
    }

    private static int totalNonNullFields(List<?> fieldsValues) {
        var totalNonNull = 0;
        for (Object value : fieldsValues) {
            totalNonNull += (value != null ? 1 : 0);
        }
        return totalNonNull;
    }
}
