package TEAM4;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BWT4");
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
