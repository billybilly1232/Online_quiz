import javax.persistence.*;

@Entity(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int questionID;

    @Column
    private String questionString;

    @Column
    private String topic;

    @Column
    private String type;

    @Column
    private String answerString;

    @Column
    private int marks;

    @Column
    private boolean incorrectlyAnswered;

    // constrictor for the Question class
    public Question(){}

    // getters and setters for question class
    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswerString() {
        return answerString;
    }

    public void setAnswerString(String answerString) {
        this.answerString = answerString;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public boolean isIncorrectlyAnswered() {
        return incorrectlyAnswered;
    }

    public void setIncorrectlyAnswered(boolean incorrectlyAnswered) {
        this.incorrectlyAnswered = incorrectlyAnswered;
    }
}
