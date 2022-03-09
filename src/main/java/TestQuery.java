import csc1035.project2.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class TestQuery {
    /*public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM QUESTION";
        s.beginTransaction();

        Query query = s.createQuery(hql);

        List<Question> results = query.list();

        s.getTransaction().commit();

        for (Question q : results){
            System.out.println(q.toString());
        }
    }
*/
    public static void main(String[] args) {

    }
}
