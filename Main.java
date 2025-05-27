import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BestFitMemoryAlgorithm algorithm = new BestFitMemoryAlgorithm();
        Scanner sc = new Scanner(System.in);

        // Initialize memory blocks
        algorithm.addProcess(2);
        algorithm.addProcess(120);
        algorithm.addProcess(20);
        algorithm.addProcess(150);
        algorithm.addProcess(160);
        algorithm.addProcess(1);
        algorithm.addProcess(4);
        algorithm.addProcess(554);
        algorithm.addProcess(124);

        

        while (true) {
            System.out.println("\n=== Memory Management Menu ===");
            System.out.println("1. Allocate Memory");
            System.out.println("2. Deallocate Memory");
            System.out.println("3. Show Memory");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter process name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter memory size to allocate: ");
                    int size = sc.nextInt();
                    algorithm.allocateMemory(name, size);
                    break;
                case 2:
                    System.out.print("Enter process name to deallocate: ");
                    String pname = sc.nextLine();
                    algorithm.deallocateMemory(pname);
                    break;
                case 3:
                    algorithm.showMemory();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
