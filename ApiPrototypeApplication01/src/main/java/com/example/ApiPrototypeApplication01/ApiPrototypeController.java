/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ApiPrototypeApplication01;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author matthewingallinera
 */
@RestController
public class ApiPrototypeController {

    /**
     * Gets the time and date based on IP from WorldTimeApi and prints it to
     * console.
     *
     * @return
     */
    @GetMapping("/time")
    public Object getTime() {
        try {
            String url = "http://worldtimeapi.org/api/timezone/ip";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            //Prints the whole response from the WorldTimeApi to the console.
            System.out.println(root);

            //Parses the current local date and time based off of the Ip and prints to the console.
            String dateTime = root.get("datetime").asText();
            System.out.println("The time based on IP is:" + dateTime);

            //Prints the the time and date based on IP to the console.
            for (JsonNode rt : root) {
                String timeIp = rt.get("datetime").asText();
                System.out.println("The time based on IP is: " + timeIp);
            }
            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ApiPrototypeController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /time";
        }

    }
}
