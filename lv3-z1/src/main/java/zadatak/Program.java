package zadatak;

import java.util.Scanner;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        Imenik imenik = new Imenik();
        Scanner ulaz  = new Scanner(System.in);

        while(true) {
            System.out.println("Unesite opciju: ");
            System.out.println("1. Dodaj kontakt");
            System.out.println("2. Broj korisnika");
            System.out.println("3. Ime korisnika");
            System.out.println("4. Lista korisnika cije ime pocinje slovom");
            System.out.println("5. Lista imena korisnika iz grada");
            System.out.println("6. Lista brojeva korisnika iz grada");
            System.out.println("0. Izlaz");

            int option = ulaz.nextInt();
            ulaz.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    System.out.println("Unesite ime: ");
                    String name = ulaz.nextLine();
                    System.out.println("Unesite koji tip broja unosite(F/M/I): ");
                    char tip = ulaz.nextLine().charAt(0);

                    TelefonskiBroj broj = null;
                    if (tip == 'F') {
                        System.out.println("Unesite grad: ");
                        Grad grad = Grad.valueOf(ulaz.nextLine());
                        System.out.println("Unesite broj bez pozivnog broja: ");
                        String brojString = ulaz.nextLine();
                        broj = new FiksniBroj(grad, brojString);
                    } else if (tip == 'M') {
                        System.out.println("Unesite mobilnu mrezu(60-67): ");
                        int mreza = ulaz.nextInt();
                        ulaz.nextLine();
                        System.out.println("Unesite broj bez pozivnog broja:");
                        String brojString = ulaz.nextLine();
                        try {
                            broj = new MobilniBroj(mreza, brojString);
                        } catch(MobilniBroj.PogresnaMobilnaMreza e) {
                            System.out.println(e);
                        }
                    } else if (tip == 'I') {
                        System.out.println("Unesite broj: ");
                        String brojString = ulaz.nextLine();
                        System.out.println("Unesite drzavu: ");
                        String drzava = ulaz.nextLine();
                        broj = new MedunarodniBroj(drzava, brojString);
                    } else {
                        System.out.println("Nevalidna komanda!");
                        break;
                    }

                    imenik.dodaj(name, broj);
                    System.out.println("Kontakt je dodan.");
                    break;

                case 2:
                    System.out.println("Unesite ime:");
                    String lookupName = ulaz.nextLine();
                    String phoneNumber = imenik.dajBroj(lookupName);
                    System.out.println("Broj telefona: " + phoneNumber);
                    break;

                case 3:
                    System.out.println("Unesite broj telefona:");
                    String lookupPhoneNumber = ulaz.nextLine();
                    String contactName = imenik.dajIme(new FiksniBroj(Grad.SARAJEVO, lookupPhoneNumber));
                    System.out.println("Name: " + contactName);
                    break;

                case 4:
                    System.out.println("Unesite slovo za pretragu:");
                    char letter = ulaz.nextLine().charAt(0);
                    Set<String> contactsStartingWithLetter = imenik.naSlovo(letter);
                    for (String ime : contactsStartingWithLetter) {
                        System.out.println(ime);
                    }
                    break;

                case 5:
                    System.out.println("Unesite grad:");
                    Grad city = Grad.valueOf(ulaz.nextLine());
                    Set<String> contactsInCity = imenik.izGrada(city);
                    for (String contact : contactsInCity) {
                        System.out.println(contact);
                    }
                    break;

                case 6:
                    System.out.println("Unesite grad: ");
                    Grad cityPhoneNumbers = Grad.valueOf(ulaz.nextLine());
                    Set<TelefonskiBroj> phoneNumbersInCity = imenik.izGradaBrojevi(cityPhoneNumbers);
                    for (TelefonskiBroj br : phoneNumbersInCity) {
                        System.out.println(br.ispisi());
                    }
                    break;

                case 0:
                    ulaz.close();
                    System.exit(0);

                default:
                    System.out.println("Nevalidna komanda!");
            }
        }
    }
}
