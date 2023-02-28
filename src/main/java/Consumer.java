import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Consumer {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Введите текст для перевода!");
        Scanner scanner = new Scanner(System.in);
        String textToTranslate = scanner.nextLine();

        String IAM = "t1.9euelZqPjM2Zi5makomQzYnGy8makO3rnpWalpWYycuek5ySxpCVjJnOxprl8_cuehFg-e9OUG9R_t3z924oD2D5705Qb1H-.NK8o6B_9flQZF4sHqgCVukTgSKMzBD5dMVwFllXI0Z3NAJeltCP_YSeI0yxwJK4ug1ZYge1wahqb-yVvbwf2AQ";
        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization","Bearer "+IAM);

        Map<String,String> jsonData = new HashMap<>();

        jsonData.put("folderId","b1goaetkgqco73591fbi");
        jsonData.put("texts","["+textToTranslate+"]");
        jsonData.put("targetLanguageCode","en");


        HttpEntity<Map<String,String>> request = new HttpEntity<>(jsonData,httpHeaders);

        YandexResponse translation = restTemplate.postForObject(url,request, YandexResponse.class);
        String answer = translation.getTranslations().get(0).getText();
        System.out.println(answer.replaceAll("\\[","").replaceAll("]",""));

    }
}
