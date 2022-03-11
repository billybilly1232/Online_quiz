import csc1035.project2.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Database {
    Session session = HibernateUtil.getSessionFactory().openSession();
/*
create question
read/list questions
update questions
delete questions
 */

    public void createQuestion(Question questionDetails) {
        // need to figure out how to add the created question to the db
        try {
            // creates a new session with the database
            session = HibernateUtil.getSessionFactory().openSession();
            // begins the transaction
            session.beginTransaction();
            session.save(questionDetails);
            // saves the transaction
            session.getTransaction().commit();
        } catch (HibernateException e) {
            // if something goes wrong, rollback to the previous transaction
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            // closes the session
        }
    }

    public void readQuestion() {
        // method for reading/viewing a question
    }

    public void updateQuestion() {
        // method for updating/editing a question
    }

    public void deleteQuestion(){
        // method for deleting a question
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
