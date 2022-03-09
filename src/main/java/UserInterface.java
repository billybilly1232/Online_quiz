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
                case 2 -> // update a question
                        System.out.println("Option 2");
                case 3 -> // read a question
                        System.out.println("Option 3");
                case 4 -> //delete a question
                        System.out.println("Option 4");
                case 5 -> //create a quiz
                        System.out.println("Option 5");
                case 6 -> //update a quiz
                        System.out.println("Option 6");
                case 7 -> //view a quiz
                        System.out.println("Option 7");
                case 8 -> //delete a quiz
                        System.out.println("Option 8");
                case 9 -> //search by topic
                        System.out.println("Option 9");
                case 10 -> //search by type
                        System.out.println("Option 10");
                case 11 -> //5 question quiz
                        System.out.println("Option 11");
                case 12 -> //10 question quiz
                        System.out.println("Option 12");
                case 13 -> //15 question quiz
                        System.out.println("Option 13");
                case 14 -> //20 question quiz
                        System.out.println("Option 14");
                case 15 -> //view statistics
                        System.out.println("Option 15");
                case 16 -> {
                    System.out.println("Quitting");
                    quit = true;
                } //tbc
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
        System.out.println("Enter the marks ");
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
