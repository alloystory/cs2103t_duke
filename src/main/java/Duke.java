import java.util.Scanner;

public class Duke {
    public static final int MAX_STRING_LENGTH = 60;
    public static final int MAX_TASKS = 100;
    public static final String WELCOME_MESSAGE = "Wussup Dawggg! I'm Dukeee\nWhat you want me do?";
    public static final String GOODBYE_MESSAGE = "Bye!\nStay cool bruh! (((:";
    
    public static void main(String[] args) {
        print(WELCOME_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager(MAX_TASKS);
          
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                String[] parsedInput = input.split(" ");
                String command = parsedInput[0];

                if (command.equals("bye"))            break;
                else if (command.equals("list"))      print(taskManager.toString());
                else if (command.equals("done"))      print(taskManager.setDone(Integer.parseInt(parsedInput[1])));
                else                                  print(taskManager.add(input));
            } catch (Exception e) {
                print("YOU MADE SOME STUPID MISTAKE BOOOHOOO");
            }
        }

        scanner.close();
        print(GOODBYE_MESSAGE);
    }

    // -----------------------------------------------------------------------------------------
    // Helper Methods
    private static void print(String message) {
        System.out.println("    ____________________________________________________________");

        for (String str : message.split("\n")) {
            int index = 0;
            while (index < str.length()) {
                String indentation = "      ";
                int print_length = Math.min(str.length() - index, MAX_STRING_LENGTH - 2);

                System.out.println(indentation + str.substring(index, index + print_length));
                index += print_length;
            }
        }
        
        System.out.println("    ____________________________________________________________");
    }
}
