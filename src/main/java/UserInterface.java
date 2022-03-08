import java.util.Scanner;
public class UserInterface {
    public static void main(String[] args) {
        new UserInterface().run();
    }

    public void run() {
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
                case 1 ->// option 1
                        System.out.println("Option 1");
                case 2 -> // tbc
                        System.out.println("Option 2");
                case 3 -> // tbc
                        System.out.println("Option 3");
                case 4 -> //tbc
                        System.out.println("Option 4");
                case 5 -> //tbc
                        System.out.println("Option 5");
                case 6 ->{
                    System.out.println("Quitting");
                    quit = true;
                } //tbc
               default -> System.out.println("Not a valid option");
            }
        }
    }

    private void menu() {
        System.out.println("""

                1: option 1
                2: option 2
                3: option 3
                4: option 4
                5: option 5
                6: Quit

                """);
    }
}
    /*




        // the following is the user menu
         * Displays the menu to the user, so they know what each numerical option does.
        private void menu () {
            System.out.println("""

                    1: Add branch
                    2: Add sale
                    3: View the branch with the largest average sale of a given year
                    4: View the largest sale ever recorded
                    5: View all sales greater than a specified value
                    6: Quit

                    """);
        }
    }
}
     */
