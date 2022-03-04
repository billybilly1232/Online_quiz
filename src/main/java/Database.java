import csc1035.project2.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Database {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            /*
            test for setting a question to the db with hardcoded data.
            It did work
             */
            // creates a new session with the database
            session = HibernateUtil.getSessionFactory().openSession();
            // begins the transaction
            session.beginTransaction();
            // creates a new instance of the question class
            Question q = new Question();
            // sets the fields of the class to hardcoded data
            q.setTopic("Maths");
            q.setType("SAQ");
            q.setQuestionString("9+10?");
            q.setAnswerString("21");
            q.setMarks(2);
            q.setIncorrectlyAnswered(false);
            // saves the question
            session.save(q);
            // saves the transaction
            session.getTransaction().commit();
        } catch (HibernateException e) {
            // if something goes wrong, rollback to the previous transaction
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            // closes the session
        }
    }
}
