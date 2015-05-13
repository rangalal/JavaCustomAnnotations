package lk.est.custom.annotation.sensitive;

import java.lang.reflect.Field;

/**
 * @author rangalal.g
 *
 */
public interface SensitiveData {
    
    /**
     * @param field the Field 
     * @return the field value 
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String getFieldvalue(Field field) throws IllegalArgumentException, IllegalAccessException;

}

