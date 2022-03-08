import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "QUIZ")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int quizID;

    @Column
    private String topicOfQuiz;

    @Column
    private int lengthOfQuiz;

    @ManyToMany(mappedBy = "quizzes")
    private Set<Question> questions = new HashSet<>();

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
}
