package relationships.manytomany;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import utility.HibernateUtil;

import java.util.*;

public class TestingManyToMany {

    public static void main(String[] args) {
        Session session = null;

        // create quiz


       // Set<Quiz> sl = new HashSet<>(Arrays.asList();

        //create questions



        //Set<Question> ml = new HashSet<>(Arrays.asList());

        // Create
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // save objects to database
            for (Question question : question1) {
                session.persist(question);
            }
            for (Quiz quiz : quiz1) {
                session.persist(quiz);
            }

            // create relationship in student and save again
            // modules did not exist when first saved
            for (Question question : q1) {
                stu.setModules(quiz1);
                session.persist(question1);
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
