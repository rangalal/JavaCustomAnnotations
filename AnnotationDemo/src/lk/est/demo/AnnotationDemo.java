package lk.est.demo;

import lk.est.custom.logger.SensitiveDataLogger;
import lk.est.model.PersonInfo;

public class AnnotationDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {

        PersonInfo personInfo = new PersonInfo("Rangalal", "Gamage", "prangalal@gmail.com", "+94 77 23 86 260", 35);

        SensitiveDataLogger sensitiveDataLogger = new SensitiveDataLogger();
        System.out.println("With sensitive data enabled");
        System.out.println(sensitiveDataLogger.logData(true, personInfo));

        System.out.println("\nWith sensitive data disabled");
        System.out.println(sensitiveDataLogger.logData(false, personInfo));

        
    }

}
