/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bussprojekt;

import java.util.Scanner;

public class Bussprojekt {

    static long[] platser = new long[21];
    static String[] namn = new String[21];
    static String[] kön_fält = new String[21];
    static long[] platser_temp = new long[21];
    static String[] namn_temp = new String[21];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Meny:");
        System.out.println("1. Boka en obokad plats");
        System.out.println("2. Skriv ut hur många lediga platser det finns");
        System.out.println("3. Beräkna vinsten av antalet sålda biljetter (299.90 kr/st)");
        System.out.println("4. Avsluta programmet");
        System.out.println("5. Hitta bokning");
        System.out.println("6. Ta bort bokning");
        System.out.println("7. Visa alla bokningar som är över/under 18");
        System.out.println("8. Visa en sorterad lista på alla passagerare");
        while (true) {
            System.out.println("Skriv ett nummer för att välja från menyn: ");
            String meny_val = scan.nextLine();

            switch (meny_val) {
                case "1":
                    System.out.println("Vad är ditt personnummer?");
                    long person_nr = scan.nextLong();
                    scan.nextLine();
                    System.out.println("Skriv ditt förnamn och efternamn");
                    var fullt_namn = scan.nextLine();
                    System.out.println("Vilket kön är du? (man/kvinna/annat)");
                    var kön = scan.nextLine().toLowerCase();
                    switch (kön) {
                        case "man":
                            break;
                        case "kvinna":
                            break;
                        case "annat":
                            break;
                        default:
                            System.out.println("KAN DU INTE LÄSA EN JÄVLA INSTRUKTION??? BARA FÖR ATT DU SKREV FEL SÅ KOMMER JAG SÄTTA DITT KÖN TILL *IDIOT*");
                            kön = "idiot";
                    }

                    System.out.println("Vill du leta efter en fönsterplats? (j/n)");
                    String fönster_plats = scan.nextLine();
                    //scan.nextLine();
                    switch (fönster_plats.toLowerCase()) {
                        case "j" -> {
                            long plats = fönsterplats(person_nr);
                            int int_plats = (int) plats;
                            if (plats >= 22) {
                                plats = plats(person_nr);
                                System.out.println("Det finns inga fönsterplatser, du får plats nr " + (plats + 1));
                                namn[int_plats] = fullt_namn;
                                kön_fält[int_plats] = kön;
                            }
                            System.out.println("Du har bokat plats nr " + (plats + 1));
                            namn[int_plats] = fullt_namn;
                            kön_fält[int_plats] = kön;
                        }
                        case "n" -> {
                            long plats = plats(person_nr);
                            int int_plats = (int) plats;
                            System.out.println("Du har bokat plats nr " + (plats + 1));
                            namn[int_plats] = fullt_namn;
                            kön_fält[int_plats] = kön;
                        }
                        default ->
                            System.out.println("Ogiltigt svar...");
                    }

                    break;

                case "2":
                    int lediga_platser = antal_lediga_platser();
                    System.out.println("Antal lediga platser: " + lediga_platser);
                    break;
                case "3":
                    double vinst = beräkna_vinst();
                    System.out.println("Vinsten är " + vinst + " kr");
                    break;
                case "4":
                    System.out.println("Systemet avslutas");
                    System.exit(0);
                case "5":
                    System.out.println("Skriv in ditt personnummer eller namn för att hitta bokning: ");
                    String hitta = scan.nextLine();
                    int bokning = hitta_bokning(hitta);
                    if (bokning != 0) {
                        System.out.println("Hej " + namn[bokning - 1] + " (kön: " + kön_fält[bokning - 1] + "), du har bokat plats nr " + bokning + ".");
                    } else {
                        System.out.println("Du har inte bokat någon plats.");
                    }
                    break;
                case "6":
                    System.out.println("Skriv in ditt personnummer eller namn för att ta bort bokning: ");
                    hitta = scan.nextLine();
                    bokning = radera_bokning(hitta);
                    if (bokning != 0) {
                        kön_fält[bokning - 1] = null;
                        namn[bokning - 1] = null;
                        platser[bokning - 1] = 0;
                        System.out.println("Din bokning på plats " + bokning + " har tagits bort.");
                    } else {
                        System.out.println("Det finns ingen plats bokad under det personnumret.");
                    }
                    break;
                case "7":
                    var i = 0;
                    System.out.println("Alla 18+:");
                    for (i = 0; i < 21; i++) {
                        if (platser[i] != 0) {
                            int födelseår = beräkna_ålder(platser[i]);
                            if (födelseår <= 2005) {
                                System.out.println(platser[i]);
                            }
                        }
                    }
                    System.out.println("");
                    System.out.println("Alla under 18:");
                    for (i = 0; i < 21; i++) {
                        if (platser[i] != 0) {
                            int födelseår = beräkna_ålder(platser[i]);
                            if (födelseår > 2005) {
                                System.out.println(platser[i]);
                            }
                        }
                    }
                    break;
                case "8":
                    platser_temp = platser;
                    namn_temp = namn;
                    System.out.println("Sorterad lista:");
                    for (i = 0; i < 20; i++) {
                        int minsta = i;
                        for (int x = i + 1; x < 21; x++) {

                            if (platser_temp[x] < platser_temp[minsta]) {
                                minsta = x;
                            }
                        }

                        long temp = platser_temp[minsta];
                        platser_temp[minsta] = platser_temp[i];
                        platser_temp[i] = temp;

                        String temp2 = namn_temp[minsta];
                        namn_temp[minsta] = namn_temp[i];
                        namn_temp[i] = temp2;

                    }
                    
                    for (i = 0; i < 20; i++) {
                        
                        
                        if (platser_temp[i] != 0) {

                            System.out.println(platser_temp[i] + " " + namn_temp[i]);
                        }
                    }
                    break;
                default:
                    System.out.println("Ogiltigt svar, svara igen.");

                    break;
            }
        }
    }

    //metod för att boka plats
    static int plats(long person_nr) {
        var i = 0;
        for (i = 0; i < 21; i++) {
            if (platser[i] == 0) {
                platser[i] = person_nr;
                break;
            }
        }
        return i;
    }

    static int fönsterplats(long person_nr) {
        var i = 0;

        while (i < 21) {
            if (platser[i] == 0) {
                platser[i] = person_nr;
                break;
            }

            if (i == 0 || i == 4 || i == 8 || i == 12) {
                i = i + 3;
            } else if (i == 3 || i == 7 || i == 11 || i == 15) {
                i = i + 1;
            } else if (i == 16) {
                i = i + 4;
            }
        }
        return i;
    }

    //metod för att räkna ut antal lediga platser
    static int antal_lediga_platser() {
        int lediga_platser = 0;
        var i = 1;
        for (i = 0; i < 21; i++) {
            if (platser[i] == 0) {
                lediga_platser = lediga_platser + 1;
            }
        }
        return lediga_platser;
    }

    static int hitta_bokning(String hitta) {
        int bokning = 0;
        var i = 0;
        for (i = 0; i < 21; i++) {
            if (namn[i] == null ? hitta == null : namn[i].equals(hitta) || platser[i] == Long.parseLong(hitta)) {
                bokning = i + 1;
                break;
            }
        }

        return bokning;
    }

    static int radera_bokning(String hitta) {
        int bokning = 0;
        var i = 0;
        for (i = 0; i < 21; i++) {
            if (namn[i] == null ? hitta == null : namn[i].equals(hitta) || platser[i] == Long.parseLong(hitta)) {
                bokning = i + 1;
                break;
            }
        }

        return bokning;
    }

    //metod för att beräkna vinst
    static double beräkna_vinst() {
        double vinst = 0;
        var i = 0;
        for (i = 0; i < 21; i++) {
            if (platser[i] != 0) {
                int födelseår = beräkna_ålder(platser[i]);
                if (födelseår > 2005) {
                    vinst = vinst + 149.9;
                } else if (födelseår <= 1954) {
                    vinst = vinst + 199.9;
                } else {
                    vinst = vinst + 299.9;
                }
            }
        }
        return vinst;
    }

    static int beräkna_ålder(long person_nr) {
        String födelseår = Long.toString(person_nr);
        födelseår = födelseår.substring(0, 4);
        int födelseår_int = Integer.parseInt(födelseår);
        return födelseår_int;
    }

}
