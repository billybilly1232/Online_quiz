import javax.persistence.*;

@Entity(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int questionID;

    @Column
    private String question;

    @Column
    private String topicOfQuestion;

    @Column
    private String typeOfQuestion;

    @Column
    private String answer;

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTopicOfQuestion() {
        return topicOfQuestion;
    }

    public void setTopicOfQuestion(String topic) {
        this.topicOfQuestion = topic;
    }

    public String getTypeOfQuestion() {
        return typeOfQuestion;
    }

    public void setType(String type) {
        this.typeOfQuestion = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
