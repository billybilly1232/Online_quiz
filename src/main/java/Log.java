import javax.persistence.*;
import java.util.List;

@Entity(name = "LOG")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int logID;

    @Column
    private int marks;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Quiz quiz;

    public Log() {
    }

    public Log(int marks) {
        this.marks = marks;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
