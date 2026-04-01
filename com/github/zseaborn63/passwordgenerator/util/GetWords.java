package com.github.zseaborn63.passwordgenerator.util;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class GetWords {

    public static String[] main(){
        String[] retVal;

        // 1. Create an HttpClient instance
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // Optional: specify HTTP version
                .connectTimeout(Duration.ofSeconds(20)) // Optional: set connection timeout
                .build();
        
        // 2. Create an HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.example.com")) // Set the target URL
                .GET() // Specify the request method (GET is default)
                .build();
        
        try {
            // 3. Send the request and receive the response synchronously
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Process the response
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        retVal = new String[] {"test", "Ball", "four"};
        return retVal;
        
    }
    
}
