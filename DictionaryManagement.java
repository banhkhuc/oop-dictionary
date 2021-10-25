import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    Trie data_dictionary = new Trie();

    public void takeDataFromText() {
        try {
            File file = new File("C:\\Users\\Admin\\Documents\\Dictionary_V\\src\\dictionaries.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String[] temp = sc.nextLine().split("#");
                String wordTarget = temp[0].trim();
                String wordExplain = "";
                for(int i = 1; i < temp.length; i++) {
                    wordExplain += temp[i] + '\n';
                }
                add(wordTarget, wordExplain);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void add(String wordTarget, String wordExplain) {
        data_dictionary.insert(new Word(wordTarget, wordExplain));
    }

    public String lookUp(String word) {
        return data_dictionary.search(word);
    }

    public ArrayList<String> exportPrefixList(String prefix ) {
        return data_dictionary.startWithPrefix(prefix);
    }

    public String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbwHsg9Ywpg25EwswiLFGGSCVKaN3eNr8QxsGrdDe9ofcfZIZds/exec" +
                "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void delete(String word) {
        data_dictionary.delete(word);
    }

}
