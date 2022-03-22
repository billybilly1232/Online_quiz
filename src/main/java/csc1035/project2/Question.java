package csc1035.project2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "QUESTION")
//@NamedQueries({
//        @NamedQuery(name = "SearchByType",query = "select * from QUESTION where typeOFQuestion = :name" )
//})
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

    @ManyToMany
    @JoinTable(
            name = "QUIZ_QUESTION",
            joinColumns = {@JoinColumn(name = "questionID")},
            inverseJoinColumns = {@JoinColumn(name = "quizID")})
    private Set<Quiz> quizzes = new HashSet<>();

    // constrictor for the csc1035.project2.Question class
    public Question(){}

    public Question(String question, String topicOfQuestion, String typeOfQuestion, String answer, int marks, boolean incorrectlyAnswered) {
        this.question = question;
        this.topicOfQuestion = topicOfQuestion;
        this.typeOfQuestion = typeOfQuestion;
        this.answer = answer;
        this.marks = marks;
        this.incorrectlyAnswered = incorrectlyAnswered;
    }

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

    public void setTopicOfQuestion(String topicOfQuestion) {
        this.topicOfQuestion = topicOfQuestion;
    }

    public String getTypeOfQuestion() {
        return typeOfQuestion;
    }

    public void setTypeOfQuestion(String typeOfQuestion) {
        this.typeOfQuestion = typeOfQuestion;
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

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void addQuiz(Quiz quiz){this.quizzes.add(quiz);}

    public void removeQuiz(Quiz quiz){this.quizzes.remove(quiz);}

    @Override
    public String toString() {
        return "csc1035.project2.Question{" +
                "questionID=" + questionID +
                ", question='" + question + '\'' +
                ", topicOfQuestion='" + topicOfQuestion + '\'' +
                ", typeOfQuestion='" + typeOfQuestion + '\'' +
                ", answer='" + answer + '\'' +
                ", marks=" + marks +
                ", incorrectlyAnswered=" + incorrectlyAnswered +
                '}';
    }
}
