package lk.est.custom.logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import lk.est.custom.annotation.sensitive.SensitiveData;
import lk.est.custom.annotation.sensitive.Sensitive;

/**
 * The class SensitiveDataLogger is responsible for login sensitive data by masking when required.
 * 
 * @author rangalal.g
 *
 */
public class SensitiveDataLogger {

    /**
     * @param logSensitiveData - The boolean value to instruct to log sensitive data.
     * @param object - The instance of a class which may contain sensitive data.
     * @return - The formatted string.
     */
    public String logData(boolean logSensitiveData, Object object) {
        StringBuilder sb = new StringBuilder("Person info with sensitive data ");
        sb.append(logSensitiveData ? "MASKED:\n" : "EXCLUDED:\n");

        String annotatedFields = processAnnotations(logSensitiveData, object);
        sb.append(annotatedFields);
        return sb.toString();
    }

    
    /**
     * @param logSensitiveData - The boolean value to instruct to log sensitive data.
     * @param object - The instance of a class which may contain sensitive data.
     * @return - The formatted string.
     */
    public String processAnnotations(boolean logSensitiveData, Object object) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Field classField : object.getClass().getDeclaredFields()) {
                for (Annotation annotation : classField.getAnnotations()) {
                    if (annotation.annotationType() == Sensitive.class) {
                        Sensitive sensitive = (Sensitive) annotation;
                        String result = printPersonalData(logSensitiveData, classField, sensitive, object);
                        sb.append(result);
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

   
    /**
     * @param logSensitiveData - - The boolean value to instruct to log sensitive data.
     * @param classField - The class field value.
     * @param sensitive - The sensitive annotation value.
     * @param object - The instance of a class which may contain sensitive data.
     * @return - The formatted string.
     * @throws IllegalAccessException
     */
    private String printPersonalData(boolean logSensitiveData, Field classField, Sensitive sensitive, Object object)
            throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        if (sensitive.isSensitive()) {
            if (logSensitiveData) {
                sb.append(maskFieldValue(buildText(classField, object)));
            }
        } else {
            sb.append(buildText(classField, object));
        }
        return sb.toString();
    }

    /**
     * @param field - The class field
     * @param object - The formatted string.
     * @return - The sensitive data field value.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private String buildText(Field field, Object object) throws IllegalArgumentException, IllegalAccessException {

        SensitiveData SensetiveData = null;
        SensetiveData = (SensitiveData) object;

        return SensetiveData.getFieldvalue(field);
    }

    /**
     * @param value - The field value to be masked.
     * @return - The masked value.
     */
    private String maskFieldValue(String value) {

      
        int total = value.length();
        int startlen = value.length()/total, endlen = value.length()/2;
        int masklen = total - (startlen + endlen);
        StringBuffer maskedbuf = new StringBuffer(value.substring(0, startlen));
        for (int i = 0; i < masklen; i++) {
            maskedbuf.append('X');
        }
        maskedbuf.append(value.substring(startlen + masklen, total));
        String masked = maskedbuf.toString();

        return masked;
    }
}
