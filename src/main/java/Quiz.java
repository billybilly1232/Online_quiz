import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "QUIZ")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int quizID;

    @Column
    private String quizName;

    @Column
    private String topicOfQuiz;

    @Column
    private int lengthOfQuiz;

    @ManyToMany(mappedBy = "quizzes")
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "quiz")
    private List<Log> logs;



    public Quiz() {
    }

    public Quiz(String name, String topicOfQuiz, int lengthOfQuiz) {
        this.quizName = name;
        this.topicOfQuiz = topicOfQuiz;
        this.lengthOfQuiz = lengthOfQuiz;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getTopicOfQuiz() {
        return topicOfQuiz;
    }

    public void setTopicOfQuiz(String topicOfQuiz) {
        this.topicOfQuiz = topicOfQuiz;
    }

    public int getLengthOfQuiz() {
        return lengthOfQuiz;
    }

    public void setLengthOfQuiz(int lengthOfQuiz) {
        this.lengthOfQuiz = lengthOfQuiz;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
}
