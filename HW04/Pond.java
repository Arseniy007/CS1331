public class Pond {
    public static void main(String[] args) {

        // Initializing frogs
        Frog peepoFrog = new Frog("Peepo");
        Frog pepeFrog = new Frog("Pepe", 10, 15);
        Frog peepawFrog = new Frog("Peepaw", 4.6, 5);
        Frog senchesFrog = new Frog("Senches", 23, 20);

        // Initializing flies
        Fly someFly = new Fly(6);

        Frog.setSpecies("1331 Frogs");
        System.out.println(peepoFrog);

        peepoFrog.eat(someFly);
        System.out.println(someFly);

        peepoFrog.grow(8);
        peepoFrog.eat(someFly);
        System.out.println(someFly);
        System.out.println(peepoFrog);
        System.out.println(senchesFrog);

        peepawFrog.grow(4);
        System.out.println(peepawFrog);
        System.out.println(pepeFrog);
    }
}
