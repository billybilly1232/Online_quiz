
import org.hibernate.HibernateException;
import org.hibernate.Session;
import csc1035.project2.HibernateUtil;

import java.util.*;

public class TestingManyToMany {

    public static void main(String[] args) {
        Session session = null;

        Question q1 = new Question("What is life?","Existensial Questions","LAQ","Nobody knows",1,false);
        Question q2 = new Question("Why Git so bad?","Git","SAQ","Science",12,false);
        Set<Question> questions = new HashSet<>(Arrays.asList(q1,q2));
        Quiz quiz1 = new Quiz("Computer Science",2);
        Set<Quiz> quizzes = new HashSet<>(Arrays.asList(quiz1));



        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (Question question : questions){
                session.save(question);
            }
            for (Quiz quiz : quizzes){
                session.save(quiz);
            }
            for (Question question : questions){
                question.setQuizzes(quizzes);
                session.save(question);
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