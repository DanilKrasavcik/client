package org.example;

import org.example.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class App2 {
    public static void main( String[] args ) {

        String url = "http://94.198.50.185:7081/api/users";
        String xxx  = "[JSESSIONID=1D9240D6787554C3402C04CE40C83F96; Path=/; HttpOnly]";
        String james = "James";
        String brown = "Brown";
        String thomas = "Thomas";
        String shelby = "Shelby";


        Map<String, String> data1 = new HashMap<>();
        data1.put("id", "3");
        data1.put("name", "James");
        data1.put("lastName", "Brown");
        data1.put("age", "22");

        Map<String, String> data2 = new HashMap<>();
        data2.put("id", "3");
        data2.put("name", "Thomas");
        data2.put("lastName", "Shelby");
        data2.put("age", "22");

        User user31 = new User(3L, james, brown, (byte) 22);
        User user32 = new User(3L, thomas, shelby, (byte) 22);




        RestTemplate template = new RestTemplate();

        ResponseEntity<String> forEntity = template.getForEntity(url, String.class);
        System.out.println(forEntity);
        System.out.println("STATUS1_GET:" + forEntity.getStatusCode());

        String key  = String.valueOf(forEntity.getHeaders().get("Set-Cookie"));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", key);
        System.out.println("Объект headers:" + headers);

        HttpEntity<User> requestEntityPost = new HttpEntity<>(user31, headers);
        ResponseEntity<String> responseEntityPost = restTemplate.exchange(url,
                HttpMethod.POST, requestEntityPost, String.class);
        System.out.println("Хедеры ответа ПОСТ:" + responseEntityPost);


        System.out.println("STATUS2_POST:" + responseEntityPost.getStatusCode());


        HttpHeaders headers2Post = new HttpHeaders();
        headers2Post.add("Cookie", key);

        HttpEntity<User> requestEntity2 = new HttpEntity<>(user32, headers);
        ResponseEntity<String> responseEntityPut = restTemplate.exchange(url,
            HttpMethod.PUT, requestEntity2, String.class);
        System.out.println("STATUS3_PUT:" + responseEntityPut.getStatusCode());






    }
}
