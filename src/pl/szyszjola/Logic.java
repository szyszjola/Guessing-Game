package pl.szyszjola;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Logic {

    private List<String> titles;
    private String searchingTitle;
    private String hiddenTitle;
    private Boolean correct;
    private String wrongLetter;
    private Boolean win;
    private Boolean repeat;
    private Set<Character> usesCharacters = new HashSet<>();

    public String gameResult()
    {
        if(win)
            return "Wygragłeś!" + " Szukane hasło to: " + searchingTitle;
        else
            return "Przegrałeś!" + " Szukane hasło to: " + searchingTitle;
    }

    public Boolean getRepeat() {
        return repeat;
    }

    public String getHiddenTitle() {
        return hiddenTitle;
    }

    public void setHiddenTitle(String hiddenTitle) {
        this.hiddenTitle = hiddenTitle;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public Logic() {
        titles = new ArrayList<>();
        loadTitles();
        searchingTitle = randomTitle();
        hiddenTitle = searchingTitle.replaceAll("[a-zA-Z0-9żźćńółęąśŻŹĆĄŚĘŁÓŃ]", "_");
        correct = false;
        wrongLetter = "";
        win = false;
        repeat = false;
    }

    public Boolean getWin() {
        return win;
    }

    private boolean newLetter(String letter)
    {
        if(usesCharacters.contains(letter.charAt(0))) {
            System.out.println("Ta literka już była!");
            repeat = true;
            return false;
        }
        else
        {
            usesCharacters.add(letter.toLowerCase().charAt(0));
            usesCharacters.add(letter.toUpperCase().charAt(0));
            repeat = false;
           return true;
        }
    }

    public void play(String letter) {

        if(newLetter(letter))
        {
            correct = false;
            String tmp = searchingTitle;
            String[] hiddenArray = hiddenTitle.split("");
            String smallLetter = letter.toLowerCase();
            String bigLetter = letter.toUpperCase();
            if (tmp.contains(smallLetter) || tmp.contains(bigLetter)) {
                while (tmp.contains(smallLetter) || tmp.contains(bigLetter)) {
                    if (tmp.lastIndexOf(smallLetter) > tmp.lastIndexOf(bigLetter)) {
                        int index = tmp.lastIndexOf(smallLetter);
                        hiddenArray[index] = smallLetter;
                        tmp = tmp.substring(0, index);
                    } else {
                        int index = tmp.lastIndexOf(bigLetter);
                        hiddenArray[index] = bigLetter;
                        tmp = tmp.substring(0, index);
                    }
                }
                StringBuilder builder = new StringBuilder("");
                for (String s : hiddenArray)
                    builder.append(s);
                hiddenTitle = builder.toString();
                correct = true;
                if (searchingTitle.equals(hiddenTitle))
                    win = true;
            } else {
                wrongLetter += letter.toLowerCase() + " ";
            }
        }
    }

    public String getWrongLetter() {
        return wrongLetter;
    }

    private String randomTitle() {
        int rand = (int) (Math.random() * titles.size());
        return titles.get(rand);
    }

    private void loadTitles() {
        try {
            File file = new File("filmy.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String tmp = scanner.nextLine();
                titles.add(tmp);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
