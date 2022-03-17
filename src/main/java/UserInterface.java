import java.util.List;
import java.util.Scanner;

/**
 * <p> This is my user interface class</p>
 *
 * <p> It allows the user to pick what they would like to do.
 *        It also has methods that are called that take the question and quiz data, as well as
 *        the methods to update values, and to create a new instance of  a class, along with
 *        the user menu.</p>
 *
 * @author Eden Mason
 */
public class UserInterface {
    public static void main(String[] args) {
        new UserInterface().run();
    }

    /**
     * sets up my run method.
     * <p>
     *     Creates a new instance of the database class and sets quit to false.
     * </p>
     * <p>
     *     Whilst quit is false, it runs and displays the user a menu for them to choose an option for.
     * </p>
     * <p>
     *     If the user selects an option that updates either a quiz or a question, they are prompted with
     *     a respective menu for that.
     * </p>
     */
    public void run() {
        Database d = new Database();
        boolean quit = false;
        // sets the quit value to false
        Scanner sc = new Scanner(System.in);
        // initialises the scanner

        // while the value of quit is false
        while (!quit) {
            // runs the menu
            menu();
            // prompts the user to select an option
            System.out.println("Please select an option");
            // sets choice to the value the user provided
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    // create a question
                    System.out.println("Create a question.");
                    d.createQuestion(questionDetails());
                }
                case 2 ->{
                    // read a question
                    System.out.println("Read/view a question.");
                    int questionID = inputQuestion();
                    if (questionID != -1){
                        System.out.println(d.readQuestion(questionID).toString());
                    }
                    else{
                        System.out.println("An Error occurred. ");
                    }
                }
                case 3 ->{
                    // update a question
                    System.out.println("Update/edit a question.");
                    System.out.println("Which question would you like to edit? ");
                    // print out question
                    int editedQuestion = inputQuestion();
                    if (editedQuestion != -1) {
                      updateMenuQuestion();
                        int choiceUpdateQuestion = sc.nextInt();
                        switch (choiceUpdateQuestion) {
                            case 1 -> {
                                String updatedQuestion = update("Update the question itself.", "Please enter the new question: ");
                                d.updateQuestion(editedQuestion, "Question", updatedQuestion);
                            }
                            case 2 -> {
                                String updatedQuestion = update("Update the answer.", "Please enter the new answer: ");
                                d.updateQuestion(editedQuestion, "Answer", updatedQuestion);
                            }
                            case 3 -> {
                                String updatedQuestion = update("Update the type.", "Please enter the new type: ");
                                d.updateQuestion(editedQuestion, "Type", updatedQuestion);
                            }
                            case 4 -> {
                                String updatedQuestion = update("Update the topic. ", "Please enter the new topic: ");
                                d.updateQuestion(editedQuestion, "Topic", updatedQuestion);
                            }
                            case 5 -> {
                                String updatedQuestion = update("Update how many marks the question is worth.", "Please enter the new mark amount:");
                                d.updateQuestion(editedQuestion, "Marks", updatedQuestion);
                            }
                        }
                    }
                    else{
                        System.out.println("An error occurred. ");
                    }
                }
                case 4 -> {
                    //delete a question
                    System.out.println("Delete a question.");
                    int deletedQuestion = inputQuestion();
                    if (deletedQuestion != -1) {
                        Scanner scanner = new Scanner(System.in);
                        String confirmation;
                        System.out.println("Would you like to delete it? ");
                        confirmation = scanner.nextLine();
                        if (confirmation.equals("y") || confirmation.equals("Y")){
                            System.out.println("Question will now be deleted.");
                            d.deleteQuestion(deletedQuestion);
                        }
                        else{
                            System.out.println("Deletion cancelled.");
                        }
                    }
                    else{
                        System.out.println("An error occurred. ");
                    }
                }
                case 5 ->{
                    //create a quiz
                    System.out.println("Create a quiz.");
                    d.createQuiz(quizDetails());
                }
                case 6 ->{
                    //read a quiz
                    System.out.println("Read/view a quiz.");
                    int quizID = inputQuiz();
                    if (quizID != -1){
                        System.out.println(d.readQuestion(quizID).toString());
                    }
                    else{
                        System.out.println("An Error occurred. ");
                    }
                }
                case 7 ->{
                    //update a quiz
                    System.out.println("Update/edit a quiz.");
                    System.out.println("Which question would you like to edit? ");
                    // print out question
                    int editedQuiz = inputQuiz();
                    if (editedQuiz != -1) {
                        updateMenuQuiz();
                        int choiceUpdateQuestion = sc.nextInt();
                        switch (choiceUpdateQuestion) {
                            case 1 -> {
                                String updatedQuiz = update("Update the question itself.", "Please enter the new question: ");
                                d.updateQuiz(editedQuiz, "Quiz", updatedQuiz);
                            }
                            case 2 -> {
                                String updatedQuiz = update("Update the length of the quiz.", "Please enter the new answer: ");
                                d.updateQuiz(editedQuiz, "Length", updatedQuiz);
                            }
                            case 3 -> {
                                String updatedQuiz = update("Update the topic of the quiz.", "Please enter the new type: ");
                                d.updateQuiz(editedQuiz, "Topic", updatedQuiz);
                            }
                        }
                    }
                }
                case 8 ->{
                    //delete a quiz
                    System.out.println("Delete a quiz.");
                    int deletedQuiz = inputQuiz();
                    if (deletedQuiz != -1) {
                        Scanner scanner = new Scanner(System.in);
                        String confirmation;
                        System.out.println("Would you like to delete it? ");
                        confirmation = scanner.nextLine();
                        if (confirmation.equals("y") || confirmation.equals("Y")) {
                            System.out.println("Quiz will now be deleted.");
                            d.deleteQuiz(deletedQuiz);
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    }
                }
                case 9 ->{
                    //search by topic
                    System.out.println("Search by topic.");
                    System.out.println("This has not been implemented yet.");
                }
                case 10 -> {
                    //search by type
                    System.out.println("Search by type.");
                    System.out.println("This has not been implemented yet.");
                }
                case 11 ->{
                    //5 question quiz
                    System.out.println("5 question quiz.");
                    System.out.println("This has not been implemented yet.");
                }
                case 12 ->{
                    //10 question quiz
                    System.out.println("10 question quiz.");
                    System.out.println("This has not been implemented yet.");
                }
                case 13 ->{
                    //15 question quiz
                    System.out.println("15 question quiz.");
                    System.out.println("This has not been implemented yet.");
                }
                case 14 ->{
                    //20 question quiz
                    System.out.println("20 question quiz.");
                    System.out.println("This has not been implemented yet.");
                }
                case 15 ->{
                    //view statistics
                    System.out.println("View statistics.");
                    System.out.println("This has not been implemented yet.");
                }
                case 16 -> {
                    System.out.println("Quitting.");
                    quit = true;
                }
                default -> System.out.println("Not a valid option");
            }
        }
    }

    /**
     * the method for question details.
     *
     * <p>Asks the user for the question details (question, answer, marks of the question,
     * the topic of the question, the type of question.)</p>
     *
     * @return Question- a new instance of the question class
     */
    private Question questionDetails() {
        // initialises a scanner
        Scanner sc = new Scanner(System.in);
        // asks the user for the details of the sale
        System.out.println("Enter the question: ");
        String question = sc.nextLine();
        System.out.println("Enter the correct answer: ");
        String answer = sc.nextLine();
        System.out.println("Enter the question type: ");
        String typeOfQuestion = sc.nextLine();
        System.out.println("Enter the marks: ");
        int marks = Integer.parseInt(sc.nextLine());
        System.out.println("Enter the topic:");
        String topicOfQuestion = sc.nextLine();
        return new Question(question, topicOfQuestion, typeOfQuestion, answer, marks, false);
    }

    /**
     * method for displaying a list of questions that is mapped to a numerical value for the user
     *
     * @return listOfQuestions - a list of questions mapped to numerical values. or returns -1
     */
    private int inputQuestion(){
        Database d = new Database();
        Scanner sc = new Scanner(System.in);
        int userChoice;
        List<Question> listOfQuestions = d.readAllQuestions();
        if (listOfQuestions != null){
            for (int i = 0; i < listOfQuestions.size(); i++ ){
                System.out.println((i+1) +  ": " +  listOfQuestions.get(i).getQuestion());
            }
            userChoice = Integer.parseInt(sc.nextLine());
            return listOfQuestions.get(userChoice -1).getQuestionID();
        }
        return -1;
    }

    /**
     * a method to display the main menu
     */
    private void menu() {
        System.out.println("""

                1: Create a Question.
                2: Read a Question
                3: Update a Question
                4: Delete a Question
                5: Create a Quiz
                6: Read a Quiz
                7: Update a Quiz
                8: Delete a Quiz
                9: Search by Topic
                10: Search by Type
                11: 5 Question Quiz
                12: 10 Question Quiz
                13: 15 Question Quiz
                14: 20 Question Quiz
                15: View Statistics
                16: Quit
                
                """);
    }

    /**
     * a method to display the update menu for a question
     */
    private void updateMenuQuestion(){
        System.out.println("""   
                             
                What would you like to update:
                1. The question itself
                2. The answer
                3. The type of question
                4. The topic of the question
                5. How many marks the question is worth
                
                """);
    }

    /**
     * a method to display the update menu for a quiz
     */
    private void updateMenuQuiz() {
        System.out.println("""                
                                
                What would you like to update:
                1. The quiz name
                2. The length of the quiz
                3. The topic of the quiz

                """);
    }

    /**
     * my method to update what the user has selected
     *
     * @param message - displays what the user has selected to change
     * @param prompt - prompts the user to input what they want to change
     * @return updated - the updated value
     */
    public String update(String message, String prompt){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        System.out.println(prompt);
        String updated = sc.nextLine();
        return updated;
    }

    /**
     * the method for question details.
     *
     * <p>Asks the user for the quiz details (quiz name, length of quiz,
     * topic of quiz.)</p>
     *
     * @return Quiz- a new instance of the quiz class
     */
    private Quiz quizDetails(){
        // need to figure out how to actually get the questions linked to the quiz.
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the quiz: ");
        String quizName = sc.nextLine();
        System.out.println("Please enter the topic of the quiz: ");
        String topicOfQuiz = sc.nextLine();
        System.out.println("Please enter the length of the quiz: ");
        int lengthOfQuiz = Integer.parseInt(sc.nextLine());
        return new Quiz(quizName, topicOfQuiz, lengthOfQuiz);
    }

    /**
     * method for displaying a list of quizzes that is mapped to a numerical value for the user
     *
     * @return listOfQuizzes - - a list of quizzes mapped to numerical values. or returns -1
     */
    private int inputQuiz() {
        Database d = new Database();
        Scanner sc = new Scanner(System.in);
        int userChoice;
        List<Quiz> listOfQuizzes = d.readAllQuizzes();
        if (listOfQuizzes != null) {
            for (int i = 0; i < listOfQuizzes.size(); i++) {
                System.out.println((i + 1) + ": " + listOfQuizzes.get(i).getQuizName());
            }
            userChoice = Integer.parseInt(sc.nextLine());
            return listOfQuizzes.get(userChoice - 1).getQuizID();
        }
        return -1;
    }
}
