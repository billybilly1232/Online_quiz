package csc1035.project2;

import csc1035.project2.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>This is the Database class</p>
 * <P>It contains all the methods that interact with the database using hibernate.
 * It's methods are for CRUD for Questions, Quizzes, Logs.
 * Along with methods used for retriving lists of the above for use in identification of them.</P>
 * @author Bartosz Czajczynski
 */
public class Database {

    /**
     *This method adds a question from the given parameter that is a question to the database.
     * @param questionDetails the question to be added.
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

    /**
     * This method is for reading a question with the primary key same as the parameter.
     * So that it may be retrieved from the database.
     * @param questionID the primary key of the question you ar looking for.
     * @return the question that has the same primary key.
     */
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
                case "csc1035.project2.Question"-> q.setQuestion(updateString);
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
            List <Quiz> testlist = readAllQuizzes();
            for (Quiz quiz : testlist){
                s.update(quiz);
                if (quiz.getQuestions().contains(q)){
                    quiz.removeQuestion(q);
                    s.update(quiz);
                }
            }
            q.setQuizzes(new HashSet<>());
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

    public List<Question> readAllQuestions(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query readAll = s.createQuery("from QUESTION");
        s.getTransaction().commit();
        List queryList = readAll.getResultList();
        s.close();
        List<Question> questions = new ArrayList<>();
        for (Object question : queryList){
            questions.add((Question) question);
        }
        return questions;
    }

    public void createQuiz(String quizName, String quizTopic, int quizLength, List<Integer> questionIDList) {
        Quiz newQuiz = new Quiz(quizName, quizTopic, quizLength);
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(newQuiz);
        Set<Quiz> quizzes = new HashSet<>(quizList);
        List<Question> questionsList = new ArrayList<>();
        for (int questionID: questionIDList) {
            questionsList.add(readQuestion(questionID));
        }
        Set<Question> questions = new HashSet<>(questionsList);
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
                s.persist(quiz);
            }
            for (Question question : questions){
                quizList = new ArrayList<>(question.getQuizzes());
                quizList.add(newQuiz);
                quizzes = new HashSet<>(quizList);
                question.setQuizzes(quizzes);
                s.save(question);
            }
            // saves the transaction
            s.persist(newQuiz);
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

    public Quiz readQuizByTopic(String topicOfQuiz){

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query quizQuery = s.createQuery("from QUIZ q where q.topicOfQuiz = :Topic");
        quizQuery.setParameter("Topic", topicOfQuiz);
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

    public void updateQuestionQuiz(int quizID, boolean add, int questionID){
        Session s = null;
        try{
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Question question = readQuestion(questionID);
            s.persist(question);
            Quiz q = (s.get(Quiz.class, quizID));
            if (add) {
                q.addQuestion(question);
                question.addQuiz(q);
                s.persist(question);
            } else{
                q.removeQuestion(question);
                question.removeQuiz(q);
                s.persist(question);
            }
            s.persist(q);
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


    public void updateLogQuiz(int quizID, boolean add, Log log){
        Session s = null;
        try{
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Quiz q = (s.get(Quiz.class, quizID));
            if (add) {
                q.addLog(log);
            } else{
                q.removeLog(log);
            }
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

    public List<Quiz> readAllQuizzes(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query readAll = s.createQuery("from QUIZ");
        s.getTransaction().commit();
        List queryList = readAll.getResultList();
        s.close();
        List<Quiz> quizzes = new ArrayList<>();
        for (Object quiz : queryList){
            quizzes.add((Quiz) quiz);
        }
        return quizzes;
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

    public List<Log> readAllLogs(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query readAll = s.createQuery("from LOG");
        s.getTransaction().commit();
        s.close();
        List<Log> logs = new ArrayList<>();
        for (Object log : readAll.getResultList()){
            logs.add((Log) log);
        }
        return logs;
    }
}
