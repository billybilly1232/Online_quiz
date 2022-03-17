import csc1035.project2.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class TestingOneToMany {
    public static void main(String[] args) {
        Session session = null;
        Log l1 = new Log(21);
        List<Log> logList  = new ArrayList<>();
        logList.add(l1);

        Quiz q1 = new Quiz("Star Trek","Star Trek", 50);

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.persist(q1);
            l1.setQuiz(q1);

            for (Log log : logList){
                session.persist(log);
            }
            session.getTransaction().commit();
        }  catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
                e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
}
