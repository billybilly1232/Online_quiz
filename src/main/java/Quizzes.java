import javax.persistence.*;

@Entity(name = "QUIZZES")
public class Quizzes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int quizID;

    @Column
    private String topicOfQuiz;

    @Column
    private int lengthOfQuiz;

}
