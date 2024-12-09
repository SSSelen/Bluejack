import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bluejack {
    private static Random random = new Random();
    private static Scanner sc = new Scanner(System.in);
    private static String[] PlayerHand;
    private static String[] CompHand;
    private static String PlayerName;
    private static String[] GameHistoryArray = new String[10];
    private static int GameHistoryIndex = 0;
    private static int playerWins = 0;
    private static int compWins = 0;
    private static String[] randomRenk;
    private static String[] randomSayi;
    private static String[] renk;
    private static int[] sayi;
    private static String[] CompDeck;
    private static int compDeckTotal;
    private static int playerDeckTotal;


    public static void main(String[] args) {
        System.out.print("Enter your name: ");
        PlayerName = sc.next();

        renk = new String[]{"Red", "Blue", "Green", "Yellow"};
        sayi = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        while (true) {
            System.out.println("Feeling lucky? Write 'start' to start the game: ");
            String x = sc.next();

            if (x.equalsIgnoreCase("start")) {

                basla();
                break;
            }
        }
    }


    private static String[] olustur() {

        String[] renk1 = {"Red", "Blue", "Green", "Yellow"};
        int[] sayi1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        String[] deste1 = new String[5];

        for (int i = 0; i < 5; i++) {
            String randomRenk = renk1[new Random().nextInt(renk1.length)];
            int randomSayi = sayi1[new Random().nextInt(sayi1.length)];
            deste1[i] = randomRenk + " " + randomSayi;
        }

        String[] renk2 = {"Red", "Blue", "Green", "Yellow"};
        int[] sayi2 = {+1, +2, +3, +4, +5, +6, -1, -2, -3, -4, -5, -6};

        String[] deste2 = new String[3];

        for (int i = 0; i < 3; i++) {
            String randomRenk2 = renk2[new Random().nextInt(renk2.length)];
            int randomSayi2 = sayi2[new Random().nextInt(sayi2.length)];
            deste2[i] = randomRenk2 + " " + randomSayi2;
        }

        String[] birlesik = new String[deste1.length + deste2.length];

        System.arraycopy(deste1, 0, birlesik, 0, deste1.length);
        System.arraycopy(deste2, 0, birlesik, deste1.length, deste2.length);

        String[] deste3 = new String[2];

        for (int z = 0; z < 2; z++) {

            int olasilik = random.nextInt(100);
            if (olasilik < 21) {
                int t = random.nextInt(2);
                if (t == 1) {
                    deste3[z] = "+/-";
                } else {
                    deste3[z] = "x2";
                }
            } else {
                String[] beklerenk = {"Red", "Blue", "Green", "Yellow"};
                int[] beklesayi = {-6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6};
                String randombeklerenk = beklerenk[new Random().nextInt(beklerenk.length)];
                int randombeklesayi = beklesayi[new Random().nextInt(beklesayi.length)];
                deste3[z] = randombeklerenk + " " + randombeklesayi;
            }
        }

        String[] deste10 = new String[deste3.length + birlesik.length];
        System.arraycopy(deste3, 0, deste10, 0, deste3.length);
        System.arraycopy(birlesik, 0, deste10, deste3.length, birlesik.length);

        String[] deste = new String[4];
        for (int i = 0; i < 4; i++) {
            int randomIndex = new Random().nextInt(deste10.length);
            deste[i] = deste10[randomIndex];
            deste10 = remove(deste10, randomIndex);
        }
        return deste;
    }

    private static String[] remove(String[] array, int index) {
        String[] sil = new String[array.length - 1];
        System.arraycopy(array, 0, sil, 0, index);
        System.arraycopy(array, index + 1, sil, index, array.length - index - 1);
        return sil;
    }


    public static void basla() {
        String[] renk = {"Red", "Blue", "Green", "Yellow"};
        int[] sayi = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


        PlayerHand = olustur();
        CompHand = olustur();
        CompDeck = new String[1];


        while (true) {
            String randomRenk = renk[random.nextInt(renk.length)];
            int randomSayi = sayi[random.nextInt(sayi.length)];
            String[] PlayerDeck = {randomRenk + " " + randomSayi};
            System.out.println("Player's Deck: " + randomRenk + " " + randomSayi);
            System.out.println("deneme yapmak");

            randomRenk = renk[random.nextInt(renk.length)];
            randomSayi = sayi[random.nextInt(sayi.length)];
            String[] CompDeck = {randomRenk + " " + randomSayi};
            System.out.println("Comp's Deck: " + randomRenk + " " + randomSayi);
            System.out.println("deneme6");


            System.out.println("Player's Hand: " + Arrays.toString(PlayerHand));



            int playerDeckTotal = calculateDeckTotal(PlayerDeck, random.nextInt(11) + 1);
            int compDeckTotal = calculateDeckTotal(CompDeck, random.nextInt(11) + 1);


            while (true) {
                System.out.println("Stand, Card or End?: ");
                String m = sc.next();
                if (m.equalsIgnoreCase("end")) {
                    randomRenk = renk[random.nextInt(renk.length)];
                    randomSayi = sayi[random.nextInt(sayi.length)];
                    String playerRandomNew = randomRenk + " " + randomSayi;
                    System.out.println("Player Draw: " + playerRandomNew);
                    String[] newPlayerDeck = new String[PlayerDeck.length + 1];
                    System.arraycopy(PlayerDeck, 0, newPlayerDeck, 0, PlayerDeck.length);
                    newPlayerDeck[PlayerDeck.length] = playerRandomNew;
                    PlayerDeck = newPlayerDeck;
                    playerDeckTotal = calculateDeckTotal(PlayerDeck, random.nextInt(11) + 1);
                    System.out.println("Player's Deck Total: " + playerDeckTotal);

                    String cardToPlay = findCardToComplete20(compDeckTotal);

                    if(compDeckTotal <= 15){
                        randomRenk = renk[random.nextInt(renk.length)];
                        randomSayi = sayi[random.nextInt(sayi.length)];
                        String compRandomNew = randomRenk + " " + randomSayi;
                        System.out.println("Comp Draw: " + compRandomNew);
                        String[] newCompDeck = new String[CompDeck.length + 1];
                        System.arraycopy(CompDeck, 0, newCompDeck, 0, CompDeck.length);
                        newCompDeck[CompDeck.length] = compRandomNew;
                        CompDeck = newCompDeck;
                        compDeckTotal = calculateDeckTotal(CompDeck, random.nextInt(11) + 1);
                        System.out.println("Comp's Deck Total: " + compDeckTotal);

                    }else if (compDeckTotal >= 18 && compDeckTotal <= 20){
                        //stop and control
                        System.out.println("Computer stands.");
                        if (playerDeckTotal == 20 && compDeckTotal != 20) {
                            System.out.println("You win!");
                            playerWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (20 > compDeckTotal && compDeckTotal > playerDeckTotal) {
                            System.out.println("You lose!");
                            compWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (20 > playerDeckTotal && playerDeckTotal > compDeckTotal) {
                            System.out.println("You win!");
                            playerWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (20 == playerDeckTotal && 20 == compDeckTotal) {
                            System.out.println("So who won? Friendship.");
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (compDeckTotal == playerDeckTotal) {
                            System.out.println("So who won? Friendship.");
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else {
                            System.out.println("You lose!");
                            compWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        }
                    } else if (compDeckTotal == 16 || compDeckTotal == 17) {
// % +50 ihtimalle end ya da stand
                        int t = random.nextInt(2);
                        if (t == 1) {
                            System.out.println("Computer stands.");
                            if (playerDeckTotal == 20 && compDeckTotal != 20) {
                                System.out.println("You win!");
                                playerWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (20 > compDeckTotal && compDeckTotal > playerDeckTotal) {
                                System.out.println("You lose!");
                                compWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (20 > playerDeckTotal && playerDeckTotal > compDeckTotal) {
                                System.out.println("You win!");
                                playerWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (20 == playerDeckTotal && 20 == compDeckTotal) {
                                System.out.println("So who won? Friendship.");
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (compDeckTotal == playerDeckTotal) {
                                System.out.println("So who won? Friendship.");
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else {
                                System.out.println("You lose!");
                                compWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            }
                        } else {
                            randomRenk = renk[random.nextInt(renk.length)];
                            randomSayi = sayi[random.nextInt(sayi.length)];
                            String compRandomNew = randomRenk + " " + randomSayi;
                            System.out.println("Comp Draw: " + compRandomNew);
                            String[] newCompDeck = new String[CompDeck.length + 1];
                            System.arraycopy(CompDeck, 0, newCompDeck, 0, CompDeck.length);
                            newCompDeck[CompDeck.length] = compRandomNew;
                            CompDeck = newCompDeck;
                            compDeckTotal = calculateDeckTotal(CompDeck, random.nextInt(11) + 1);
                            System.out.println("Comp's Deck Total: " + compDeckTotal);

                        }
                    } else {
                        //String cardToPlay = findCardToComplete20(compDeckTotal);
                        if (cardToPlay != null) {
                            System.out.println("Comp played: " + cardToPlay);
                            String[] newCompDeck = new String[CompDeck.length + 1];
                            System.arraycopy(CompDeck, 0, newCompDeck, 0, CompDeck.length);
                            newCompDeck[CompDeck.length] = cardToPlay;
                            CompDeck = newCompDeck;
                            compDeckTotal = calculateDeckTotal(CompDeck, random.nextInt(11) + 1);
                            System.out.println("Comp's Deck Total: " + compDeckTotal);
                            return;
                        }
                    }

                    if (compDeckTotal > 20 && playerDeckTotal > 20) {
                        System.out.println("So who won? Friendship.");
                        System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                        if (playerWins == 3 || compWins == 3) {
                            History(PlayerName, playerWins, compWins);
                            System.out.println("GAME OVER");
                            return;
                        }
                        System.out.println("Ready for another round?(I won't take no for an answer): ");
                        String l = sc.next();
                        if (l.equalsIgnoreCase("yes")) {
                            break;
                        }
                        break;
                    } else if (playerDeckTotal > 20 && 21 > compDeckTotal) {
                        System.out.println("Bust! Easy there tiger, you passed 20.");
                        compWins++;
                        System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                        if (playerWins == 3 || compWins == 3) {
                            History(PlayerName, playerWins, compWins);
                            System.out.println("GAME OVER");
                            return;
                        }
                        System.out.println("Ready for another round?(I won't take no for an answer): ");
                        String l = sc.next();
                        if (l.equalsIgnoreCase("yes")) {
                            break;
                        }
                        break;
                    } else if (compDeckTotal > 20 && 21 > playerDeckTotal) {
                        System.out.println("Comp busted! You got lucky.");
                        playerWins++;
                        System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                        if (playerWins == 3 || compWins == 3) {
                            History(PlayerName, playerWins, compWins);
                            System.out.println("GAME OVER");
                            return;
                        }
                        System.out.println("Ready for another round?(I won't take no for an answer): ");
                        String l = sc.next();
                        if (l.equalsIgnoreCase("yes")) {
                            break;
                        }
                        break;
                    }
                } else if (m.equalsIgnoreCase("stand")) {

                    String cardToPlay = findCardToComplete20(compDeckTotal);

                    if(compDeckTotal <= 15){
                        randomRenk = renk[random.nextInt(renk.length)];
                        randomSayi = sayi[random.nextInt(sayi.length)];
                        String compRandomNew = randomRenk + " " + randomSayi;
                        System.out.println("Comp Draw: " + compRandomNew);
                        String[] newCompDeck = new String[CompDeck.length + 1];
                        System.arraycopy(CompDeck, 0, newCompDeck, 0, CompDeck.length);
                        newCompDeck[CompDeck.length] = compRandomNew;
                        CompDeck = newCompDeck;
                        compDeckTotal = calculateDeckTotal(CompDeck, random.nextInt(11) + 1);
                        System.out.println("Comp's Deck Total: " + compDeckTotal);

                    }else if (compDeckTotal >= 18 && compDeckTotal <= 20){
                        //stop and control
                        System.out.println("Computer stands.");
                        if (playerDeckTotal == 20 && compDeckTotal != 20) {
                            System.out.println("You win!");
                            playerWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (20 > compDeckTotal && compDeckTotal > playerDeckTotal) {
                            System.out.println("You lose!");
                            compWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (20 > playerDeckTotal && playerDeckTotal > compDeckTotal) {
                            System.out.println("You win!");
                            playerWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (20 == playerDeckTotal && 20 == compDeckTotal) {
                            System.out.println("So who won? Friendship.");
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (compDeckTotal == playerDeckTotal) {
                            System.out.println("So who won? Friendship.");
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else {
                            System.out.println("You lose!");
                            compWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        }
                    } else if (compDeckTotal == 16 || compDeckTotal == 17) {
// % +50 ihtimalle end ya da stand
                        int t = random.nextInt(2);
                        if (t == 1) {
                            System.out.println("Computer stands.");
                            if (playerDeckTotal == 20 && compDeckTotal != 20) {
                                System.out.println("You win!");
                                playerWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (20 > compDeckTotal && compDeckTotal > playerDeckTotal) {
                                System.out.println("You lose!");
                                compWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (20 > playerDeckTotal && playerDeckTotal > compDeckTotal) {
                                System.out.println("You win!");
                                playerWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (20 == playerDeckTotal && 20 == compDeckTotal) {
                                System.out.println("So who won? Friendship.");
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (compDeckTotal == playerDeckTotal) {
                                System.out.println("So who won? Friendship.");
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else {
                                System.out.println("You lose!");
                                compWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            }
                        } else {
                            randomRenk = renk[random.nextInt(renk.length)];
                            randomSayi = sayi[random.nextInt(sayi.length)];
                            String compRandomNew = randomRenk + " " + randomSayi;
                            System.out.println("Comp Draw: " + compRandomNew);
                            String[] newCompDeck = new String[CompDeck.length + 1];
                            System.arraycopy(CompDeck, 0, newCompDeck, 0, CompDeck.length);
                            newCompDeck[CompDeck.length] = compRandomNew;
                            CompDeck = newCompDeck;
                            compDeckTotal = calculateDeckTotal(CompDeck, random.nextInt(11) + 1);
                            System.out.println("Comp's Deck Total: " + compDeckTotal);

                        }
                    } else {
                        //String cardToPlay = findCardToComplete20(compDeckTotal);
                        if (cardToPlay != null) {
                            System.out.println("Comp played: " + cardToPlay);
                            String[] newCompDeck = new String[CompDeck.length + 1];
                            System.arraycopy(CompDeck, 0, newCompDeck, 0, CompDeck.length);
                            newCompDeck[CompDeck.length] = cardToPlay;
                            CompDeck = newCompDeck;
                            compDeckTotal = calculateDeckTotal(CompDeck, random.nextInt(11) + 1);
                            System.out.println("Comp's Deck Total: " + compDeckTotal);
                            return;
                        }
                    }
                    if (playerDeckTotal == 20 && compDeckTotal != 20) {
                        System.out.println("You win!");
                        playerWins++;
                        System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                        if (playerWins == 3 || compWins == 3) {
                            History(PlayerName, playerWins, compWins);
                            System.out.println("GAME OVER");
                            return;
                        }
                        System.out.println("Ready for another round?(I won't take no for an answer): ");
                        String l = sc.next();
                        if (l.equalsIgnoreCase("yes")) {
                            break;
                        }
                        break;
                    } else if (20 > compDeckTotal && compDeckTotal > playerDeckTotal) {
                        System.out.println("You lose!");
                        compWins++;
                        System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                        if (playerWins == 3 || compWins == 3) {
                            History(PlayerName, playerWins, compWins);
                            System.out.println("GAME OVER");
                            return;
                        }
                        System.out.println("Ready for another round?(I won't take no for an answer): ");
                        String l = sc.next();
                        if (l.equalsIgnoreCase("yes")) {
                            break;
                        }
                        break;
                    } else if (20 > playerDeckTotal && playerDeckTotal > compDeckTotal) {
                        System.out.println("You win!");
                        playerWins++;
                        System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                        if (playerWins == 3 || compWins == 3) {
                            History(PlayerName, playerWins, compWins);
                            System.out.println("GAME OVER");
                            return;
                        }
                        System.out.println("Ready for another round?(I won't take no for an answer): ");
                        String l = sc.next();
                        if (l.equalsIgnoreCase("yes")) {
                            break;
                        }
                        break;
                    } else if(compDeckTotal > 20 && playerDeckTotal < 21){
                        System.out.println("You win!");
                        playerWins++;
                        System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                        if (playerWins == 3 || compWins == 3) {
                            History(PlayerName, playerWins, compWins);
                            System.out.println("GAME OVER");
                            return;
                        }
                        System.out.println("Ready for another round?(I won't take no for an answer): ");
                        String l = sc.next();
                        if (l.equalsIgnoreCase("yes")) {
                            break;
                        }
                        break;
                    } else if (20 == playerDeckTotal && 20 == compDeckTotal) {
                        System.out.println("So who won? Friendship.");
                        System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                        if (playerWins == 3 || compWins == 3) {
                            History(PlayerName, playerWins, compWins);
                            System.out.println("GAME OVER");
                            return;
                        }
                        System.out.println("Ready for another round?(I won't take no for an answer): ");
                        String l = sc.next();
                        if (l.equalsIgnoreCase("yes")) {
                            break;
                        }
                        break;
                    } else if (compDeckTotal == playerDeckTotal) {
                        System.out.println("So who won? Friendship.");
                        System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                        if (playerWins == 3 || compWins == 3) {
                            History(PlayerName, playerWins, compWins);
                            System.out.println("GAME OVER");
                            return;
                        }
                        System.out.println("Ready for another round?(I won't take no for an answer): ");
                        String l = sc.next();
                        if (l.equalsIgnoreCase("yes")) {
                            break;
                        }
                        break;
                    }
                } else if (m.equalsIgnoreCase("card")) {
                    if (PlayerHand.length == 0) {
                        System.out.println("You have no card left.");
                        break;


                    } else {
                        System.out.println("Player's Hand: " + Arrays.toString(PlayerHand));



                        System.out.println("Please enter the index of the card you want to play (1-4): ");
                        int cardIndex = sc.nextInt();

                        if (cardIndex >= 1 && cardIndex <= 4) {

                            String playedCard = PlayerHand[cardIndex - 1];
                            System.out.println("You played: " + playedCard);


                            if (playedCard.equals("x2")) {
                                String[] newPlayerDeck = new String[PlayerDeck.length + 1];
                                System.arraycopy(PlayerDeck, 0, newPlayerDeck, 0, PlayerDeck.length);

                                newPlayerDeck[PlayerDeck.length] = String.valueOf(random.nextInt(11) + 1) + " " + "x2";

                                PlayerDeck = newPlayerDeck;
                                playerDeckTotal = calculateDeckTotal(PlayerDeck, random.nextInt(11) + 1);

                                PlayerHand = remove(PlayerHand, cardIndex - 1);

                                System.out.println("Player's Deck Total: " + playerDeckTotal);
                            } else if (playedCard.equals("+/-")) {


                                String[] newPlayerDeck = new String[PlayerDeck.length + 1];
                                System.arraycopy(PlayerDeck, 0, newPlayerDeck, 0, PlayerDeck.length);

                                newPlayerDeck[PlayerDeck.length] = String.valueOf(random.nextInt(11) + 1) + " " + "+/-";

                                PlayerDeck = newPlayerDeck;
                                playerDeckTotal = calculateDeckTotal(PlayerDeck, random.nextInt(11) + 1);

                                PlayerHand = remove(PlayerHand, cardIndex - 1);

                                System.out.println("Player's Deck Total: " + playerDeckTotal);


                            } else {
                                String[] newPlayerDeck = new String[PlayerDeck.length + 1];

                                System.arraycopy(PlayerDeck, 0, newPlayerDeck, 0, PlayerDeck.length);
                                newPlayerDeck[PlayerDeck.length] = playedCard;
                                PlayerDeck = newPlayerDeck;
                                playerDeckTotal = calculateDeckTotal(PlayerDeck, random.nextInt(11) + 1);

                                PlayerHand = remove(PlayerHand, cardIndex - 1);

                                System.out.println("Player's Deck Total: " + playerDeckTotal);


                            }
                        } else {
                            System.out.println("Invalid card index. Please enter a number between 1 and 4.");
                        }
                    }
                    String cardToPlay = findCardToComplete20(compDeckTotal);
                    if(compDeckTotal <= 15){
                        randomRenk = renk[random.nextInt(renk.length)];
                        randomSayi = sayi[random.nextInt(sayi.length)];
                        String compRandomNew = randomRenk + " " + randomSayi;
                        System.out.println("Comp Draw: " + compRandomNew);
                        String[] newCompDeck = new String[CompDeck.length + 1];
                        System.arraycopy(CompDeck, 0, newCompDeck, 0, CompDeck.length);
                        newCompDeck[CompDeck.length] = compRandomNew;
                        CompDeck = newCompDeck;
                        compDeckTotal = calculateDeckTotal(CompDeck, random.nextInt(11) + 1);
                        System.out.println("Comp's Deck Total: " + compDeckTotal);

                    }else if (compDeckTotal >= 18 && compDeckTotal <= 20){
                        //stop and control
                        System.out.println("Computer stands.");

                        if (playerDeckTotal == 20 && compDeckTotal != 20) {
                            System.out.println("You win!");
                            playerWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (20 > compDeckTotal && compDeckTotal > playerDeckTotal) {
                            System.out.println("You lose!");
                            compWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (20 > playerDeckTotal && playerDeckTotal > compDeckTotal) {
                            System.out.println("You win!");
                            playerWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (20 == playerDeckTotal && 20 == compDeckTotal) {
                            System.out.println("So who won? Friendship.");
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else if (compDeckTotal == playerDeckTotal) {
                            System.out.println("So who won? Friendship.");
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        } else {
                            System.out.println("You lose!");
                            compWins++;
                            System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                            if (playerWins == 3 || compWins == 3) {
                                History(PlayerName, playerWins, compWins);
                                System.out.println("GAME OVER");
                                return;
                            }
                            System.out.println("Ready for another round?(I won't take no for an answer): ");
                            String l = sc.next();
                            if (l.equalsIgnoreCase("yes")) {
                                break;
                            }
                            break;
                        }
                    } else if (compDeckTotal == 16 || compDeckTotal == 17) {
// % +50 ihtimalle end ya da stand
                        int t = random.nextInt(2);
                        if (t == 1) {
                            System.out.println("Computer stands.");
                            if (playerDeckTotal == 20 && compDeckTotal != 20) {
                                System.out.println("You win!");
                                playerWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (20 > compDeckTotal && compDeckTotal > playerDeckTotal) {
                                System.out.println("You lose!");
                                compWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (20 > playerDeckTotal && playerDeckTotal > compDeckTotal) {
                                System.out.println("You win!");
                                playerWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (20 == playerDeckTotal && 20 == compDeckTotal) {
                                System.out.println("So who won? Friendship.");
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else if (compDeckTotal == playerDeckTotal) {
                                System.out.println("So who won? Friendship.");
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            } else {
                                System.out.println("You lose!");
                                compWins++;
                                System.out.println("Player's Wins= " + playerWins + " | Comp's Wins= " + compWins);
                                if (playerWins == 3 || compWins == 3) {
                                    History(PlayerName, playerWins, compWins);
                                    System.out.println("GAME OVER");
                                    return;
                                }
                                System.out.println("Ready for another round?(I won't take no for an answer): ");
                                String l = sc.next();
                                if (l.equalsIgnoreCase("yes")) {
                                    break;
                                }
                                break;
                            }
                        } else {
                            randomRenk = renk[random.nextInt(renk.length)];
                            randomSayi = sayi[random.nextInt(sayi.length)];
                            String compRandomNew = randomRenk + " " + randomSayi;
                            System.out.println("Comp Draw: " + compRandomNew);
                            String[] newCompDeck = new String[CompDeck.length + 1];
                            System.arraycopy(CompDeck, 0, newCompDeck, 0, CompDeck.length);
                            newCompDeck[CompDeck.length] = compRandomNew;
                            CompDeck = newCompDeck;
                            compDeckTotal = calculateDeckTotal(CompDeck, random.nextInt(11) + 1);
                            System.out.println("Comp's Deck Total: " + compDeckTotal);

                        }
                    } else {
                        // String cardToPlay = findCardToComplete20(compDeckTotal);
                        if (cardToPlay != null) {
                            System.out.println("Comp played: " + cardToPlay);
                            String[] newCompDeck = new String[CompDeck.length + 1];
                            System.arraycopy(CompDeck, 0, newCompDeck, 0, CompDeck.length);
                            newCompDeck[CompDeck.length] = cardToPlay;
                            CompDeck = newCompDeck;
                            compDeckTotal = calculateDeckTotal(CompDeck, random.nextInt(11) + 1);
                            System.out.println("Comp's Deck Total: " + compDeckTotal);
                            return;
                        }
                    }
                }


            }
        }

    }



    private static String findCardToComplete20(int compDeckTotal) {
        for (String card : CompHand) {
            int cardValue = getValue(card, random.nextInt(11) + 1);
            if (compDeckTotal + cardValue == 20) {
                CompHand = remove(CompHand, Arrays.asList(CompHand).indexOf(card));
                return card;
            }
        }
        return null;
    }

    private static void History(String PlayerName, int playerWins, int compWins) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(new Date());


        String gameHistory = PlayerName + ": " + playerWins + " - Comp: " + compWins + "  " + date;


        GameHistoryArray[GameHistoryIndex] = gameHistory;
        GameHistoryIndex = (GameHistoryIndex + 1) % 10;


        writeGameHistoryToFile();
    }

    private static void writeGameHistoryToFile() {
        FileWriter writer = null;

        try {

            writer = new FileWriter("game_history.txt");


            for (String game : GameHistoryArray) {
                if (game != null) {
                    writer.write(game + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int calculateDeckTotal(String[] deck, int playerRandomNew) {
        int total = 0;
        boolean BlueCards = true;
        for (String card : deck) {
            total += getValue(card, playerRandomNew);
            if (card != null && !card.startsWith("Blue")) {
                BlueCards = false;
            }
        }


        if (total == 20 && BlueCards) {
            System.out.println("Player wins with a total of 20 using only blue cards!");
            System.exit(0);
        }

        return total;
    }

    private static int getValue(String card, int playerRandomNew) {
        if (card != null) {

            String[] parts = card.split(" ");
            if (parts.length == 2) {
                if ("x2".equals(parts[1])) {
                    return playerRandomNew;
                } else if ("+/-".equals(parts[1])) {
                    return -2 * playerRandomNew;
                } else {
                    return Integer.parseInt(parts[1]);
                }

            }
        }

        return 0;

    }
}