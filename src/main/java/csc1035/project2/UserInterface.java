package csc1035.project2;

import java.util.*;
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
        // runs a new instance of the user interface
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
        // sets up a new instance of the database class
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
            // for the choice the user selects, it runs the respective case
            switch (choice) {
                case 1 -> {
                    // create a question
                    System.out.println("Create a question.");
                    d.createQuestion(questionDetails());
                }
                case 2 -> {
                    // read a question
                    System.out.println("Read/view a question.");
                    // calls the input question function
                    int questionID = inputQuestion();
                    if (questionID != -1){
                        // if question ID does not equal -1, print out the specified question
                        System.out.println(d.readQuestion(questionID).toString());
                    }
                    else{
                        // else output an error occurred
                        System.out.println("An error occurred. ");
                    }
                }
                case 3 -> {
                    // update a question
                    System.out.println("Update/edit a question.");
                    System.out.println("Which question would you like to edit? ");
                    // calls the input question function
                    int editedQuestion = inputQuestion();
                    if (editedQuestion != -1) {
                        // if the given value does not equal -1, call the update menu for questions
                      updateMenuQuestion();
                        int choiceUpdateQuestion = sc.nextInt();
                        // for the user choice provided, run the respective case
                        switch (choiceUpdateQuestion) {
                            case 1 -> {
                                /* update the question itself
                                calls the update function with the parameters specified
                                 */
                                String updatedQuestion = update("Update the question itself.", "Please enter the new question: ");
                                // updates the question with the values provided
                                d.updateQuestion(editedQuestion, "csc1035.project2.Question", updatedQuestion);
                            }
                            case 2 -> {
                                  /* update the answer
                                calls the update function with the parameters specified
                                 */
                                String updatedQuestion = update("Update the answer.", "Please enter the new answer: ");
                                // updates the question with the values provided
                                d.updateQuestion(editedQuestion, "Answer", updatedQuestion);
                            }
                            case 3 -> {
                                 /* update the type
                                calls the update function with the parameters specified
                                 */
                                String updatedQuestion = update("Update the type.", "Please enter the new type: ");
                                // updates the question with the values provided
                                d.updateQuestion(editedQuestion, "Type", updatedQuestion);
                            }
                            case 4 -> {
                                  /* update the topic
                                calls the update function with the parameters specified
                                 */
                                String updatedQuestion = update("Update the topic. ", "Please enter the new topic: ");
                                // updates the question with the values provided
                                d.updateQuestion(editedQuestion, "Topic", updatedQuestion);
                            }
                            case 5 -> {
                                 /* update the marks
                                calls the update function with the parameters specified
                                 */
                                String updatedQuestion = update("Update how many marks the question is worth.", "Please enter the new mark amount:");
                                // updates the question with the values provided
                                d.updateQuestion(editedQuestion, "Marks", updatedQuestion);
                            }
                        }
                    }
                    else{
                        // outputs that an error occurred
                        System.out.println("An error occurred. ");
                    }
                }
                case 4 -> {
                    /* delete a question
                    calls the input question function
                     */
                    int deleteQuestionNumber = inputQuestion();
                    if (deleteQuestionNumber != -1) {
                        // if deleteQuestionNumber doesn't equal -1, calls the delete function with the parameters provided
                        Boolean deletedQuestion = delete("Delete a question. ", "Would you like to delete it? ");
                        if (deletedQuestion){
                            // if deleted question is returned as true, deletes the question
                            d.deleteQuestion(deleteQuestionNumber);
                            System.out.println("csc1035.project2.Question deleted.");
                        }
                        else{
                            // else, outputs that the deletion was cancelled
                            System.out.println("Deletion cancelled.");
                        }
                    }
                    else{
                        // else outputs that an error occurred
                        System.out.println("An error occurred.");
                    }
                }
                case 5 -> {
                    //create a quiz
                    System.out.println("Create a quiz.");
                    createQuiz();
                }
                case 6 -> {
                    //read a quiz
                    System.out.println("Read/view a quiz.");
                    // calls the input quiz function
                    int quizID = inputQuiz();
                    if (quizID != -1){
                        // if the quiz ID doesn't equal -1, outputs the specified quiz
                        System.out.println(d.readQuiz(quizID).toString());
                    }
                    else{
                        // else outputs that an error occurred
                        System.out.println("An Error occurred. ");
                    }
                }
                case 7 -> {
                    //update a quiz
                    System.out.println("Update/edit a quiz.");
                    System.out.println("Which question would you like to edit? ");
                    // calls the input quiz method
                    int editedQuiz = inputQuiz();
                    if (editedQuiz != -1) {
                        // if the specified quiz is not equal to -1, calls rhe update menu function
                        updateMenuQuiz();
                        int choiceUpdateQuestion = sc.nextInt();
                        // for the choice the user specifies, runs the given case for that number
                        switch (choiceUpdateQuestion) {
                            case 1 -> {
                                /*
                                update the quiz name itself
                                calls the update function with the parameters specified
                                 */
                                String updatedQuiz = update("Update the question itself.", "Please enter the new question: ");
                                // updates the question with the values provided
                                d.updateQuiz(editedQuiz, "csc1035.project2.Quiz", updatedQuiz);
                            }
                            case 2 -> {
                                  /*
                                update the length of the quiz
                                calls the update function with the parameters specified
                                 */
                                String updatedQuiz = update("Update the length of the quiz.", "Please enter the new answer: ");
                                // updates the question with the values provided
                                d.updateQuiz(editedQuiz, "Length", updatedQuiz);
                            }
                            case 3 -> {
                                 /*
                                update the topic of the quiz
                                calls the update function with the parameters specified
                                 */
                                String updatedQuiz = update("Update the topic of the quiz.", "Please enter the new type: ");
                                // updates the question with the values provided
                                d.updateQuiz(editedQuiz, "Topic", updatedQuiz);
                            }
                        }
                    }
                    else{
                        // else, output an error occurred
                        System.out.println("An error occurred.");
                    }
                }
                case 8 -> {
                    /*delete a quiz
                    calls the input quiz function
                     */
                    int deleteQuizNumber = inputQuiz();
                    if (deleteQuizNumber != -1) {
                        // if the chosen quiz does not equal -1, call the delete function with the parameters provided
                        Boolean deletedQuiz = delete("Delete a quiz. ", "Would you like to delete it? ");
                        if (deletedQuiz){
                            // if delete quiz is true, delete the quiz
                            d.deleteQuestion(deleteQuizNumber);
                            System.out.println("csc1035.project2.Quiz deleted.");
                        }
                        else{
                            // deletion is cancelled
                            System.out.println("Deletion cancelled.");
                        }
                    }
                    else{
                        // output an error occurred
                        System.out.println("An error occurred.");
                    }
                }
                case 9 -> {
                    //search by topic
                    System.out.println("Search by topic.");
                    System.out.println("This has not been implemented yet.");
                }
                case 10 -> {
                    //search by type
                    System.out.println("Search by type.");
                    System.out.println("This has not been implemented yet.");
                }
                case 11 -> {
                    //view statistics
                    System.out.println("View statistics.");
                    System.out.println("This has not been implemented yet.");
                }
                case 12 -> {
                    // quits the program
                    System.out.println("Quitting.");
                    quit = true;
                }
                // notifies the user if they select an invalid option
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
     * @return csc1035.project2.Question- a new instance of the question class
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
        // returns a new instance of the question class with the values provided
        return new Question(question, topicOfQuestion, typeOfQuestion, answer, marks, false);
    }

    /**
     * method for displaying a list of questions that is mapped to a numerical value for the user
     *
     * @return listOfQuestions - a list of questions mapped to numerical values. or returns -1
     */
    private int inputQuestion() {
        // initialises a new instance of the database and a new scanner
        Database d = new Database();
        Scanner sc = new Scanner(System.in);
        int userChoice;
        // creates a list of all questions by reading the database
        List<Question> listOfQuestions = d.readAllQuestions();
        if (listOfQuestions != null){
            // if the list of questions is not null
            for (int x = 0; x < listOfQuestions.size(); x++ ){
                // for x is less than the list of questions
                System.out.println((x + 1) +  ": " +  listOfQuestions.get(x).getQuestion());
                // output x+1 along with the question that value is associated with from the list of questions
            }
            // takes the user choice from the displayed list of questions
            userChoice = Integer.parseInt(sc.nextLine());
            // returns the id of the question linked to that choice
            return listOfQuestions.get(userChoice -1).getQuestionID();
        }
        // if the list is null, return -1
        return -1;
    }

    /**
     * a method to display the main menu
     */
    private void menu() {
        // prints out the main menu
        System.out.println("""

                1: Create a csc1035.project2.Question.
                2: Read a csc1035.project2.Question
                3: Update a csc1035.project2.Question
                4: Delete a csc1035.project2.Question
                5: Create a csc1035.project2.Quiz
                6: Read a csc1035.project2.Quiz
                7: Update a csc1035.project2.Quiz
                8: Delete a csc1035.project2.Quiz
                9: Search by Topic
                10: Search by Type
                11: View Statistics
                12: Quit
                
                """);
    }

    /**
     * a method to display the update menu for a question
     */
    private void updateMenuQuestion() {
        // prints out the menu for updating a question
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
        // prints out the menu for updating a quiz
        System.out.println("""                
                                
                What would you like to update:
                1. The quiz name
                2. The length of the quiz
                3. The topic of the quiz

                """);
    }

    /**
     * a method to display the menu of quiz lengths
     */
    private void quizLengthMenu(){
        // output the quiz length menu
        System.out.println("""
                    1. 5
                    2. 10
                    3. 15
                    4. 20
                    """);
    }

    /**
     * my method to update what the user has selected
     *
     * @param message - displays what the user has selected to change
     * @param prompt - prompts the user to input what they want to change
     * @return updated - the updated value
     */
    public String update(String message, String prompt) {
        // initialises a new instance of the database and a new scanner
        Scanner sc = new Scanner(System.in);
        // outputs the given message, and the given prompt
        System.out.println(message);
        System.out.println(prompt);
        // returns the user's answer to the prompt
        return sc.nextLine();
    }

    /**
     * my method to delete what the user has selected
     *
     * @param message - displays what the user has selected to change
     *  @param prompt - prompts the user to input what they want to change
     * @return a boolean value of true or false
     */
    public Boolean delete(String message, String prompt) {
        // initialises a new instance of the database and a new scanner
        Scanner sc = new Scanner(System.in);
        // outputs the given message and the given prompt
        System.out.println(message);
        System.out.println(prompt);
        // takes the value of delete to be what the user input
        String delete = sc.nextLine();
        if (delete.equals("y") || delete.equals("Y")){
            // if delete is yes, returns true
            System.out.println("csc1035.project2.Question will now be deleted.");
            return true;
        }
        // else returns false
        return false;
    }

    /**
     * the method for question details.
     *
     * <p>Asks the user for the quiz details (quiz name, length of quiz,
     * topic of quiz.)
     * </p>
     *<p>
     *     And creates the quiz with the respective number of questions.
     *</p>
     *
     */
    private void createQuiz() {
        // sets up the scanners and a new instance of the database class and a new instance of Random
        Scanner sc = new Scanner(System.in);
        Database d = new Database();
        Random r = new Random();
        // takes the user input for the quiz details, and if they want it to be randomly generated
        System.out.println("Please enter the name of the quiz: ");
        String quizName = sc.nextLine();
        System.out.println("Please enter the topic of the quiz: ");
        String quizTopic = sc.nextLine();
        System.out.println("Please enter if you want to randomly generate: (true/false):");
        boolean choice = Boolean.parseBoolean(sc.nextLine());
        // sets up a new QuestionID array list
        List<Integer> questionIDList = new ArrayList<>();
        int lengthOfQuiz = 0;
        if (choice){
            // if choice is true, then display the menu of lengths
            quizLengthMenu();
            int menuChoice = Integer.parseInt(sc.nextLine());
            // ask the user for their choice and run the respective case
            switch (menuChoice){
                case 1 -> lengthOfQuiz = 5;
                case 2 -> lengthOfQuiz = 10;
                case 3 -> lengthOfQuiz = 15;
                case 4 -> lengthOfQuiz = 20;
            }
            for (int x =0; x < lengthOfQuiz; x ++){
                /* for x is zero and less than the length of the quiz
                add the question ID to the list of question ID
                 */
                questionIDList.add(d.readAllQuestions().get(r.nextInt(d.readAllQuestions().size()-1)).getQuestionID());
            }
        } else {
            // if the user does not want a randomly generated quiz, manually enter the length and manually add the questions
            System.out.println("Please enter the length of the quiz: ");
            lengthOfQuiz = Integer.parseInt(sc.nextLine());
            for (int x = 0; x < lengthOfQuiz; x++) {
                questionIDList.add(inputQuestion());
            }
        }
        // add the quizzes with the questions to the database
        d.createQuiz(quizName, quizTopic, lengthOfQuiz, questionIDList);
    }

    /**
     * method for displaying a list of quizzes that is mapped to a numerical value for the user
     *
     * @return listOfQuizzes - - a list of quizzes mapped to numerical values. or returns -1
     */
    private int inputQuiz() {
        // initialises a new instance of the database class and a new scanner
        Database d = new Database();
        Scanner sc = new Scanner(System.in);
        int userChoice;
        // creates a list of all quizzes by reading the database
        List<Quiz> listOfQuizzes = d.readAllQuizzes();
        // if the list of quizzes is not null
        if (listOfQuizzes != null) {
            for (int x = 0; x < listOfQuizzes.size(); x++) {
                // for x is less than the list of quizzes
                System.out.println((x + 1) + ": " + listOfQuizzes.get(x).getQuizName());
                // output x+1 along with the question that value is associated with from the list of quizzes
            }
            // takes the user choice from the displayed list of quizzes
            userChoice = Integer.parseInt(sc.nextLine());
            // returns the id of the quiz linked to that choice
            return listOfQuizzes.get(userChoice - 1).getQuizID();
        }
        // if the list is null, return -1
        return -1;
    }
}
