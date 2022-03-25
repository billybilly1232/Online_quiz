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
        //opens the session
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        //creates the query
        Query questionQuery = s.createQuery("from QUESTION q where q.questionID = :question");
        questionQuery.setParameter("question", questionID);
        s.getTransaction().commit();
        //gets the first index from the results to make an object instead of list
        Question question = (Question) questionQuery.getResultList().get(0);
        s.close();
        // returns the question
        return question;
    }

    /**
     * This method is for updating the questions objects in the database with a switch case to give options to which
     * field is to be changed.
     * @param questionID the primary key of the question.
     * @param detailType the field to be changed.
     * @param updateString the string to be saved.
     */

    public void updateQuestion(int questionID,String detailType, String updateString) {
        //sets up try and catch
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            //retrieves the question from the database
            Question q = (s.get(Question.class, questionID));
            //switch to change each field
            switch (detailType){
                case "Topic"-> q.setTopicOfQuestion(updateString);
                case "Type"-> q.setTypeOfQuestion(updateString);
                case "csc1035.project2.Question"-> q.setQuestion(updateString);
                case "Answer"-> q.setAnswer(updateString);
                case "Marks"-> q.setMarks(Integer.parseInt(updateString));
                case "IncorrectlyAnswered"-> q.setIncorrectlyAnswered(Boolean.parseBoolean(updateString));
            }
            //updates the changes
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

    /**
     * This method deletes the question with the primary key from the parameter from the database.
     * @param questionID the primary key of the question to be deleted.
     */

    public void deleteQuestion(int questionID) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            //retrieves the question from the database
            Question q = s.get(Question.class, questionID);
            //creates a list for all the quizzes
            List <Quiz> testlist = readAllQuizzes();
            //iterates through and removes the question from linking set if its in it.
            for (Quiz quiz : testlist){
                s.update(quiz);
                if (quiz.getQuestions().contains(q)){
                    quiz.removeQuestion(q);
                    s.update(quiz);
                }
            }
            //sets the question's linking set to empty
            q.setQuizzes(new HashSet<>());
            //deletes it from the database
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

    /**
     * This method returns all the questions from the database.
     * @return a list of all the questions.
     */

    public List<Question> readAllQuestions(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        //creates query to select all questions
        Query readAll = s.createQuery("from QUESTION");
        s.getTransaction().commit();
        //results list
        List queryList = readAll.getResultList();
        s.close();
        //list of questions
        List<Question> questions = new ArrayList<>();
        //iterates through results list and adds the questions to the list of questions
        for (Object question : queryList){
            questions.add((Question) question);
        }
        return questions;
    }

    /**
     * This method creates a quiz object and adds it to the database along with its relationship with quiestions.
     * @param quizName the name of the quiz.
     * @param quizTopic the topic of the quiz.
     * @param quizLength the length of the quiz.
     * @param questionIDList the list of ids of linked questions.
     */

    public void createQuiz(String quizName, String quizTopic, int quizLength, List<Integer> questionIDList) {
        //creates a new quiz
        Quiz newQuiz = new Quiz(quizName, quizTopic, quizLength);
        //adds it to a list
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(newQuiz);
        //then adds the list to a set
        Set<Quiz> quizzes = new HashSet<>(quizList);
        //list of for linking questions
        List<Question> questionsList = new ArrayList<>();
        for (int questionID: questionIDList) {
            questionsList.add(readQuestion(questionID));
        }
        //adds the list to a set
        Set<Question> questions = new HashSet<>(questionsList);
        // method for creating a quiz
        Session s = null;
        try {
            // creates a new session with the database
            s = HibernateUtil.getSessionFactory().openSession();
            // begins the transaction
            s.beginTransaction();
            //iterates through the questions to initialize
            for (Question question : questions){
                s.update(question);
            }
            //initialises the quiz
            for (Quiz quiz : quizzes){
                s.persist(quiz);
            }
            //iterates through the questions and adding to their sets the new quiz and adding the question to the quiz set
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

    /**
     * This method reads the question with id from parameter and returns its object from the database.
     * @param quizID the id of the quiz.
     * @return the quiz object.
     */

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

    /**
     * This takes in a topic and returns all the quizzes with that topic.
     * @param topicOfQuiz the topic searching by.
     * @return list of quizzes.
     */
    public Quiz readQuizByTopic(String topicOfQuiz){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        //query to search by topic
        Query quizQuery = s.createQuery("from QUIZ q where q.topicOfQuiz = :Topic");
        quizQuery.setParameter("Topic", topicOfQuiz);
        s.getTransaction().commit();
        Quiz quiz = (Quiz) quizQuery.getResultList().get(0);
        s.close();
        return quiz;
    }

    /**
     * This method is for updating the quizzes objects in the database with a switch case to give options to which
     * field is to be changed.
     * @param quizID the id of the quiz.
     * @param detailType the field to be changed.
     * @param updateString the string to be updated.
     */
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

    /**
     *This method takes in parameters to add or remove a link between a question and quiz.
     * @param quizID the id of the quiz.
     * @param add boolean add or remove.
     * @param questionID the id of the question.
     */
    public void updateQuestionQuiz(int quizID, boolean add, int questionID){
        Session s = null;
        try{
            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            //retrieves the question from the database
            Question question = readQuestion(questionID);
            //initializes the question
            s.persist(question);
            //retrives the quiz from the database
            Quiz q = (s.get(Quiz.class, quizID));
            if (add) {
                //adds to the respective sets the new question/quiz and saves
                q.addQuestion(question);
                question.addQuiz(q);
                s.persist(question);
            } else{
                //removes question/quiz from the sets and saves
                q.removeQuestion(question);
                question.removeQuiz(q);
                s.persist(question);
            }
            //saves the changes in quiz
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

    /**
     *This method updates the relationship between quiz and log.
     * @param quizID the id of the quiz.
     * @param add boolean add or remove.
     * @param log the new log object.
     */
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

    /**
     *This method deletes a quiz.
     * @param quizID the id of the quiz
     */
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

    /**
     *This method reads all quizzes.
     * @return list of all quizzes.
     */
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

    /**
     *This method adds a log object to the database.
     * @param addLog the log object.
     */
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

    /**
     *Thsi method deletes a log.
     * @param logID the id of the log.
     */
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

    /**
     *This method reads all the logs.
     * @return list of all the logs.
     */
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
