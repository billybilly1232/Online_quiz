package testing;

import csc1035.project2.Database;
import csc1035.project2.Question;
import csc1035.project2.Quiz;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import csc1035.project2.hibernate.HibernateUtil;

import java.util.*;

public class TestingManyToMany {
    /*
    public static void main(String[] args) {
        Session session = null;

        csc1035.project2.Question q1 = new csc1035.project2.Question("Why Square?","Existensial Questions","SAQ","Maths",21,false);
        csc1035.project2.Question q2 = new csc1035.project2.Question("Commit hard? \n 1. Yes \n 2. No","Git","MCQ","1",69,false);
        Set<csc1035.project2.Question> questions = new HashSet<>(Arrays.asList(q1,q2));
        csc1035.project2.Quiz quiz1 = new csc1035.project2.Quiz("iMAGE","Life",2);
        Set<csc1035.project2.Quiz> quizzes = new HashSet<>(Arrays.asList(quiz1));



        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (csc1035.project2.Question question : questions){
                session.persist(question);
            }
            for (csc1035.project2.Quiz quiz : quizzes){
                session.persist(quiz);
            }
            for (csc1035.project2.Question question : questions){
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

     */
    public static void main(String[] args) {
        Database d = new Database();
        List<Question> questionList = new ArrayList<>();
        questionList.addAll(d.readAllQuestions().subList(0,3));
        Set<Question> questions = new HashSet<>(questionList);
        List<Quiz> quizzesList = new ArrayList<>();
        quizzesList.add(d.readAllQuizzes().get(0));
        Set<Quiz> quizzes = new HashSet<>(quizzesList);
        // method for creating a quiz
        Session s = null;
        try {
            // creates a new session with the database
            s = HibernateUtil.getSessionFactory().openSession();
            // begins the transaction
            s.beginTransaction();
            for (Question question : questions){
                s.update(question);
            }
            for (Quiz quiz : quizzes){
                s.update(quiz);
            }
            for (Question question : questions){
                question.setQuizzes(quizzes);
                s.update(question);
            }
            // saves the transaction
            s.getTransaction().commit();
        } catch (HibernateException e) {
            // if something goes wrong, rollback to the previous transaction
            if (s != null) s.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
                // closes the session
            }
        }

    }

}