import csc1035.project2.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Database {
/*
create question
read/list questions
update questions
delete questions
 */

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
                case "Topic":{
                    q.setTopicOfQuestion(updateString);
                }
                case "Type":{
                    q.setTypeOfQuestion(updateString);
                }
                case "Question":{
                    q.setQuestion(updateString);
                }
                case "Answer":{
                    q.setAnswer(updateString);
                }
                case "Marks":{
                    q.setMarks(Integer.parseInt(updateString));
                }
                case "IncorrectlyAnswered":{
                    q.setIncorrectlyAnswered(Boolean.parseBoolean(updateString));
                }
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

    public void createQuiz() {
        // method for creating a quiz
    }

    public void readQuiz() {
        // method for reading/viewing a quiz
    }

    public void updateQuiz() {
        // method for updating/editing a quiz
    }

    public void deleteQuiz(){
        // method for deleting a quiz
    }

    public void createLog(){
        // method for creating a log
    }

    public void deleteLog(){
        // method for deleting a log
    }
}
