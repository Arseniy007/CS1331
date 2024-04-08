public class Gameplay {
    public static void main(String[] args) {
        // Creating astronauts
        BlueAstronaut blue1 = new BlueAstronaut("Bob", 20, 6, 30);
        BlueAstronaut blue2 = new BlueAstronaut("Heath", 30, 3, 21);
        BlueAstronaut blue3 = new BlueAstronaut("Albert", 44, 2, 0);
        BlueAstronaut blue4 = new BlueAstronaut("Angel", 0, 1, 0);
        RedAstronaut red1 = new RedAstronaut("Liam", 19, "experienced");
        RedAstronaut red2 = new RedAstronaut("Suspicious Person", 100, "expert");

        red1.sabotage(blue1);
        System.out.println(blue1);

        red1.freeze(red2);
        red1.freeze(blue3);
        System.out.println(blue3);

        blue3.emergencyMeeting();
        red2.emergencyMeeting();
        blue1.emergencyMeeting();
        System.out.println(red2);
        for (int i = 0; i < 3; i++) {
            blue2.completeTask();
            System.out.println(blue2);
        }
        red1.freeze(blue4);
        System.out.println(blue4);
        System.out.println(red1);

        for (int i = 0; i < 2; i++) {
            red1.sabotage(blue1);
            System.out.println(blue1);
        }
        red1.freeze(blue1);
        System.out.println(blue1);
        blue4.emergencyMeeting();
    }
}
