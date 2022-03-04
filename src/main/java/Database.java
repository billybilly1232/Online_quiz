import csc1035.project2.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Database {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Question q = new Question();
            q.setTopic("Maths");
            q.setType("SAQ");
            q.setQuestionString("9+10?");
            q.setAnswerString("21");
            q.setMarks(2);
            q.setIncorrectlyAnswered(false);
            session.save(q);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
