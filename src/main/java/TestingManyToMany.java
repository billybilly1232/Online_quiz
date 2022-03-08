package relationships.manytomany;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import utility.HibernateUtil;

import java.util.*;

public class TestingManyToMany {

    public static void main(String[] args) {
        Session session = null;

        // create objects
        Question question1 = new Question("");

        Set<Question> question1 = new HashSet<>(Arrays.asList(question1));

        Quiz quiz1 = new Quiz("");

        Set<Quiz> quiz1 = new HashSet<>(Arrays.asList(quiz1));

        // Create
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // save objects to database
            for (Question question : question1) {
                session.persist(question1);
            }
            for (Quiz quiz : quiz1) {
                session.persist(quiz1);
            }

            // create relationship in student and save again
            // modules did not exist when first saved
            for (Question question : question1) {
                Question.setModules(quiz1);
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
