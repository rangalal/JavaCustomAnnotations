package lk.est.model;

import java.lang.reflect.Field;

import lk.est.custom.annotation.sensitive.SensitiveData;
import lk.est.custom.annotation.sensitive.Sensitive;

/**
 * @author rangalal.g
 * 
 */
public class PersonInfo implements SensitiveData {

    @Sensitive(isSensitive = false)
    private String name;

    @Sensitive(isSensitive = false)
    private String surname;

    @Sensitive(isSensitive = true)
    private String email;

    @Sensitive(isSensitive = true)
    private String phoneNumber;

    @Sensitive(isSensitive = false)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

  
    /**
     * @param name
     * @param surname
     * @param email
     * @param phoneNumber
     * @param age
     */
    public PersonInfo(String name, String surname, String email, String phoneNumber, int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    @Override
    public String getFieldvalue(Field f) throws IllegalArgumentException, IllegalAccessException {
        return f.getName() + ": " + f.get(this) + "\n";
    }
}
