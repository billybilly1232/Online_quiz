import org.hibernate.HibernateException;
import org.hibernate.Session;
import csc1035.project2.HibernateUtil;

import java.util.*;

public class TestingManyToMany {

    public static void main(String[] args) {
        Session session = null;

        Question q1 = new Question("Why Square?","Existensial Questions","SAQ","Maths",21,false);
        Question q2 = new Question("Commit hard? \n 1. Yes \n 2. No","Git","MCQ","1",69,false);
        Set<Question> questions = new HashSet<>(Arrays.asList(q1,q2));
        Quiz quiz1 = new Quiz("Life",2);
        Set<Quiz> quizzes = new HashSet<>(Arrays.asList(quiz1));



        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (Question question : questions){
                session.persist(question);
            }
            for (Quiz quiz : quizzes){
                session.persist(quiz);
            }
            for (Question question : questions){
                question.setQuizzes(quizzes);
                session.persist(question);
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}