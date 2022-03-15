import javax.xml.bind.SchemaOutputResolver;
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
            // creates a scanner for the input
            Scanner input = new Scanner(System.in);
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
                    System.out.println("This has not been implemented yet.");
                }
                case 3 ->{
                    // update a question
                    System.out.println("Update/edit a question.");
                    int editedQuestion;
                    System.out.println("Which question would you like to edit? ");
                    // print out questions
                    editedQuestion = Integer.parseInt(sc.nextLine());
                    System.out.println("""
                            What would you like to update:
                            1. The Question itself
                            2. The Answer.
                            3. The Type of question
                            4. The Topic of the question
                            5. How many marks the question is worth.""");
                    int choiceUpdateQuestion = sc.nextInt();
                    switch (choiceUpdateQuestion) {
                            case 1 -> {
                                System.out.println("Update the question itself.");
                                String updatedQuestion;
                                System.out.println("Please enter the new question: ");
                                updatedQuestion = sc.nextLine();
                                d.updateQuestion(editedQuestion,"Question", updatedQuestion);
                            }
                            case 2 ->{
                                System.out.println("Update the answer.");
                                String updatedAnswer;
                                System.out.println("Please enter the new answer: ");
                                updatedAnswer = sc.nextLine();
                                d.updateQuestion(editedQuestion, "Answer", updatedAnswer);
                            }
                        case 3 ->{
                            System.out.println("Update the type of the question.");
                            String updatedType;
                            System.out.println("Please enter the new type: ");
                            updatedType = sc.nextLine();
                            d.updateQuestion(editedQuestion, "Type", updatedType);
                        }
                        case 4 ->{
                            System.out.println("Update the topic of the question");
                            String updatedTopic;
                            System.out.println("Please enter the new topic: ");
                            updatedTopic = sc.nextLine();
                            d.updateQuestion( editedQuestion, "Topic",  updatedTopic);
                        }
                        case 5 ->{
                            System.out.println("Update how many marks the question is worth.");
                            int updatedMarks;
                            System.out.println("Please enter the new mark amount: ");
                            updatedMarks = Integer.parseInt(sc.nextLine());
                            d.updateQuestion(editedQuestion, "Marks", String.valueOf(updatedMarks));
                        }
                    }

                }
                case 4 -> {
                    //delete a question
                    System.out.println("Delete a question.");
                    System.out.println("This has not been implemented yet.");
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

    private void menu() {
        System.out.println("""

                1: Create a Question.
                2: Update a Question
                3: Read a Question
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
}
