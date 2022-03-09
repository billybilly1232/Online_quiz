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
            /*
            test for setting a question to the db with hardcoded data.
            It did work
             */
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
}
