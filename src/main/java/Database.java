import csc1035.project2.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Database {


    public void createQuestion(Question questionDetails) {
        // need to figure out how to add the created question to the db
        Session s = null;
        try {
            // creates a new session with the database
            s = HibernateUtil.getSessionFactory().openSession();
            // begins the transaction
            s.beginTransaction();
            s.save(questionDetails);
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

    public Question readQuestion(int questionID) {
        // method for reading/viewing a question
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query questionQuery = s.createQuery("from QUESTION q where q.questionID = :question");
        questionQuery.setParameter("question", questionID);
        s.getTransaction().commit();
        Question question = (Question) questionQuery.getResultList().get(0);
        s.close();
        return question;
    }

    public void updateQuestion(int questionID,String detailType, String updateString) {
        // method for updating/editing a question
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Question q = (s.get(Question.class, questionID));

            switch (detailType){
                case "Topic"-> q.setTopicOfQuestion(updateString);
                case "Type"-> q.setTypeOfQuestion(updateString);
                case "Question"-> q.setQuestion(updateString);
                case "Answer"-> q.setAnswer(updateString);
                case "Marks"-> q.setMarks(Integer.parseInt(updateString));
                case "IncorrectlyAnswered"-> q.setIncorrectlyAnswered(Boolean.parseBoolean(updateString));
            }
            s.update(q);
            s.getTransaction().commit();
        } catch (HibernateException e) {
            if (s != null) s.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public void deleteQuestion(int questionID) {
        // method for deleting a question
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Question q = s.get(Question.class, questionID);
            s.delete(q);
            s.getTransaction().commit();
        } catch (HibernateException e) {
            if (s != null) s.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public List readAllQuestions(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query readAll = s.createQuery("from QUESTION");
        s.getTransaction().commit();
        s.close();
        return readAll.getResultList();
    }

    public void createQuiz(Quiz addQuiz) {
        // method for creating a quiz
        Session s = null;
        try {
            // creates a new session with the database
            s = HibernateUtil.getSessionFactory().openSession();
            // begins the transaction
            s.beginTransaction();
            s.save(addQuiz);
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

    public Quiz readQuiz(int quizID) {
        // method for reading/viewing a quiz
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query quizQuery = s.createQuery("from QUIZ q where q.quizID = :quiz");
        quizQuery.setParameter("quiz", quizID);
        s.getTransaction().commit();
        Quiz quiz = (Quiz) quizQuery.getResultList().get(0);
        s.close();
        return quiz;

    }

    public void updateQuiz(int quizID,String detailType, String updateString) {
        // method for updating/editing a question
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Quiz q = (s.get(Quiz.class, quizID));

            switch (detailType) {
                case "Topic" -> q.setTopicOfQuiz(updateString);
                case "Length" -> q.setLengthOfQuiz(Integer.parseInt(updateString));
                case "Name" -> q.setQuizName(updateString);
            }
            s.update(q);
            s.getTransaction().commit();
        } catch (HibernateException e) {
            if (s != null) s.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public void deleteQuiz(int quizID){
        // method for deleting a quiz
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Quiz q = s.get(Quiz.class, quizID);
            s.delete(q);
            s.getTransaction().commit();
        } catch (HibernateException e) {
            if (s != null) s.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public List readAllQuizzes(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query readAll = s.createQuery("from QUIZ");
        s.getTransaction().commit();
        s.close();
        return readAll.getResultList();
    }

    public void createLog(Log addLog){
        // method for creating a log
        Session s = null;
        try {
            // creates a new session with the database
            s = HibernateUtil.getSessionFactory().openSession();
            // begins the transaction
            s.beginTransaction();
            s.save(addLog);
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

    public void deleteLog(int logID){
        // method for deleting a log
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Log l = s.get(Log.class, logID);
            s.delete(l);
            s.getTransaction().commit();
        } catch (HibernateException e) {
            if (s != null) s.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public List readAllLogs(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query readAll = s.createQuery("from LOG");
        s.getTransaction().commit();
        s.close();
        return readAll.getResultList();
    }
}
