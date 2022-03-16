import java.util.List;
import java.util.Scanner;
public class UserInterface {
    public static void main(String[] args) {
        new UserInterface().run();
    }

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
                      updateMenu();
                        int choiceUpdateQuestion = sc.nextInt();
                        switch (choiceUpdateQuestion) {
                            case 1 -> {
                                String updatedQuestion = updateQuestion("Update the question itself.", "Please enter the new question: ");
                                d.updateQuestion(editedQuestion, "Question", updatedQuestion);
                            }
                            case 2 -> {
                                String updatedQuestion = updateQuestion("Update the answer.", "Please enter the new answer: ");
                                d.updateQuestion(editedQuestion, "Answer", updatedQuestion);
                            }
                            case 3 -> {
                                String updatedQuestion = updateQuestion("Update the type.", "Please enter the new type: ");
                                d.updateQuestion(editedQuestion, "Type", updatedQuestion);
                            }
                            case 4 -> {
                                String updatedQuestion = updateQuestion("Update the topic. ", "Please enter the new topic: ");
                                d.updateQuestion(editedQuestion, "Topic", updatedQuestion);
                            }
                            case 5 -> {
                                String updatedQuestion = updateQuestion("Update how many marks the question is worth.", "Please enter the new mark amount:");
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
                        System.out.println("You have selected: " + deletedQuestion);
                        String confirmation;
                        System.out.println("Would you like to delete it? ");
                        confirmation = sc.nextLine();
                        if (confirmation.equals("y") || confirmation.equals("Y")){
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
                    System.out.println("This has not been implemented yet.");
                }
                case 6 ->{
                    //update a quiz
                    System.out.println("Read/view a quiz.");
                    System.out.println("This has not been implemented yet.");
                }
                case 7 ->{
                    //view a quiz
                    System.out.println("Update/edit a quiz.");
                    System.out.println("This has not been implemented yet.");
                }
                case 8 ->{
                    //delete a quiz
                    System.out.println("Delete a quiz.");
                    System.out.println("This has not been implemented yet.");
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

    private int inputQuestion(){
        Database d = new Database();
        Scanner sc = new Scanner(System.in);
        int userChoice;
        List<Question> listOfQuestions = d.readAllQuestions();
        if (listOfQuestions != null){
            for (int i = 0; i < listOfQuestions.size(); i++ ){
                System.out.println((i+1) +  listOfQuestions.get(i).getQuestion());
            }
            userChoice = Integer.parseInt(sc.nextLine());
            return listOfQuestions.get(userChoice -1).getQuestionID();
        }
        return -1;
    }

    private void menu() {
        System.out.println("""

                1: Create a Question.
                2: Read a Question
                3: Update a Question
                4: Delete a Question
                5: Create a Quiz
                6: Update a Quiz
                7: Read a Quiz
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

    private void updateMenu(){
        System.out.println("""
                                What would you like to update:
                                1. The question itself
                                2. The answer
                                3. The type of question
                                4. The topic of the question
                                5. How many marks the question is worth""");
    }

    public String updateQuestion(String message, String prompt){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        System.out.println(prompt);
        String updatedQuestion = sc.nextLine();
        return updatedQuestion;
    }
}
