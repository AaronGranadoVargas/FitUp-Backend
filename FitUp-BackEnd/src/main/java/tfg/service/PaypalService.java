package tfg.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class PaypalService {

    private final String clientId = "BAAikQJ-xm2SKEWdLhwOvXpv80DrUvDOhQ3Dl27DweR-W5QGBOoBl2VVVZPeoh73FlDfljV6Q5MlrBZGF4";
    private final String secret = "EEq-bMzX6p5F9fGN6-ugH-Rhe-N680w3qhBPrz0MjirqeCCvf9I8qyfIqH620jFHsUCng4uohjM19cCK";
    private final String baseURL = "https://api-m.sandbox.paypal.com";

    private String obtenerToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, secret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(baseURL + "/v1/oauth2/token", request, Map.class);
        return (String) response.getBody().get("access_token");
    }

    public String crearPedidoPaypal(Double total, String returnUrl, String cancelUrl) {
        String token = obtenerToken();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{" +
                "\"intent\": \"CAPTURE\"," +
                "\"purchase_units\": [{\"amount\": {\"currency_code\": \"EUR\", \"value\": \"" + total + "\"}}]," +
                "\"application_context\": {" +
                "\"return_url\": \"" + returnUrl + "\"," +
                "\"cancel_url\": \"" + cancelUrl + "\"" +
                "}" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(baseURL + "/v2/checkout/orders", request, Map.class);

        List<Map<String, String>> links = (List<Map<String, String>>) response.getBody().get("links");
        for (Map<String, String> link : links) {
            if (link.get("rel").equals("approve")) {
                return link.get("href");
            }
        }
        throw new RuntimeException("No se pudo generar el link de PayPal");
    }

    public boolean capturarPago(String orderId) {
        String token = obtenerToken();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>("", headers);
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(baseURL + "/v2/checkout/orders/" + orderId + "/capture", request, Map.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch(Exception e) {
            return false;
        }
    }
}