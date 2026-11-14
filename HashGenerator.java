import java.security.MessageDigest;
import java.util.Scanner;
import java.io.FileWriter;

public class HashGenerator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Display menu
        System.out.println("Choose Hash Algorithm:");
        System.out.println("1. MD5");
        System.out.println("2. SHA-1");
        System.out.println("3. SHA-256");
        System.out.println("4. SHA-512");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // clear buffer

        String algorithm = "";

        if (choice == 1) {
            algorithm = "MD5";
        } else if (choice == 2) {
            algorithm = "SHA-1";
        } else if (choice == 3) {
            algorithm = "SHA-256";
        } else if (choice == 4) {
            algorithm = "SHA-512";
        } else {
            System.out.println("Invalid Choice!");
            return;
        }

        System.out.print("Enter text to hash: ");
        String text = sc.nextLine();

        try {
            // Create MessageDigest object
            MessageDigest md = MessageDigest.getInstance(algorithm);

            // Generate hash
            byte[] hashBytes = md.digest(text.getBytes());

            // Convert bytes into hexadecimal string
            String hash = "";

            for (byte b : hashBytes) {
                hash += String.format("%02x", b);
            }

            System.out.println("\nGenerated Hash:");
            System.out.println(hash);

            // Save hash to file
            FileWriter writer = new FileWriter("hash_results.txt", true);

            writer.write("Algorithm : " + algorithm + "\n");
            writer.write("Input     : " + text + "\n");
            writer.write("Hash      : " + hash + "\n");
            writer.write("------------------------------\n");

            writer.close();

            System.out.println("Hash saved in hash_results.txt");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}