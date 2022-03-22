package testing;

import csc1035.project2.hibernate.HibernateUtil;
import csc1035.project2.Question;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class TestQuery {
    /*public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM QUESTION";
        s.beginTransaction();

        Query query = s.createQuery(hql);

        List<csc1035.project2.Question> results = query.list();

        s.getTransaction().commit();

        for (csc1035.project2.Question q : results){
            System.out.println(q.toString());
        }
    }
*/
    public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        String hql = "from QUESTION q where q.topicOfQuestion = 'memes'";
        s.beginTransaction();

        Query query = s.createQuery(hql);

        s.getTransaction().commit();
        for (Object q: query.getResultList()){
            Question tmp = (Question) q;
            System.out.println(tmp.toString());
        }


    }
}
