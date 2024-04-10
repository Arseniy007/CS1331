import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Clinic {
    // Instance variables
    private File patientFile;
    private int day;
    private static final int defaultDay = 1;

    // Constructors
    public Clinic(File file) {
        this.patientFile = file;
        this.day = defaultDay;
    }

    public Clinic(String fileName) {
        this(new File(fileName));
    }

    public String nextDay(File f) throws FileNotFoundException {
        this.day++;
        String result = "";
        Scanner fileScan = new Scanner(f);
        Scanner input = new Scanner(System.in);
        String line = null;

        while (fileScan.hasNextLine()) {
            line = fileScan.nextLine();
            String[] tokens = line.split(",");
            String name = tokens[0];
            String typeOfPet = tokens[1];
            String info = tokens[2];
            String startTime = tokens[3];

            if (!(typeOfPet.equals("Dog") || typeOfPet.equals("Cat"))) {
                throw new InvalidPetException();
            }
            System.out.printf(
                "Consultation for %s the %s at %s.\nWhat is the health of %s?\n", 
                name, typeOfPet, startTime, name
            );
            double health;
            while (true) {
                if (input.hasNextDouble()) {
                    health = input.nextDouble();
                    break;
                }
                else {
                    input.nextLine();
                    System.out.println("Could you please provide a valid number");
                }
            }
            int painLevel;
            System.out.printf("On a scale of 1 to 10, how much pain is %s in right now?\n", name);
            while (true) {
                if (input.hasNextInt()) {
                    painLevel = input.nextInt();
                    break;
                }
                else {
                    input.nextLine();
                    System.out.println("Could you please provide a valid number");
                }
            }
            Pet currentPet;
            if (typeOfPet.equals("Dog")) {
                currentPet = new Dog(name, health, painLevel, Double.parseDouble(info));
            }
            else if (typeOfPet.equals("Cat")) {
                currentPet = new Cat(name, health, painLevel, Integer.parseInt(info));
            }
            else {
                throw new InvalidPetException();
            }
            currentPet.speak();
            int minutesPassed = currentPet.treat();
            String endTime = addTime(startTime, minutesPassed);

            String oneAppointment = String.format(
                "%s,%s,%s,Day %d,%s,%s,%s,%d\n", 
                name, typeOfPet, info, this.day, startTime, endTime, 
                String.valueOf(health), painLevel
            );
            result += oneAppointment;
        }
        fileScan.close();
        input.close();
        return result.trim();
    }

    public String nextDay(String fileName) throws FileNotFoundException {
        return nextDay(new File(fileName));
    }

    public boolean addToFile(String patientInfo) {
        String result = "";
        Scanner fileScan = null;
        PrintWriter writer = null;
        try {
            fileScan = new Scanner(this.patientFile);
            String[] tokens = patientInfo.split(",");
            String name = tokens[0];
            String line = null;
            boolean newPatient = true;

            while(fileScan.hasNextLine()) {
                line = fileScan.nextLine();
                if (name.equals(line.split(",")[0])) {
                    newPatient = false;
                    line += "," + String.join(",", Arrays.copyOfRange(tokens, 3, tokens.length));
                }
                result += line + "\n";
            }
            if (newPatient) {
                result += patientInfo;
            }
            fileScan.close();
            writer = new PrintWriter(this.patientFile);
            writer.print(result);
            return true;
        }
        catch (Exception e) {
            return false;
        }
        finally {
            if (fileScan != null) {
                fileScan.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    private String addTime(String timeIn, int treatmentTime) {
        int startMinutes = Integer.parseInt(timeIn.substring(2));
        int bothMinutes = startMinutes + treatmentTime;
        if (bothMinutes < 60) {
            return timeIn.substring(0, 2) + String.valueOf(bothMinutes);
        }
        int hoursPassed = bothMinutes / 60;
        int extraMinutes = bothMinutes % 60;
        int finalHour = Integer.parseInt(timeIn.substring(0, 2)) + hoursPassed;
        String finalHourString;
        if (finalHour < 10) {
            finalHourString = "0" + String.valueOf(finalHour);
        }
        else {
            finalHourString = String.valueOf(finalHour);
        }
        String finalMinutesString;
        if (extraMinutes < 10) {
            finalMinutesString = "0" + String.valueOf(extraMinutes);
        }
        else {
            finalMinutesString = String.valueOf(extraMinutes);
        }
        return finalHourString + finalMinutesString;
    }
}
