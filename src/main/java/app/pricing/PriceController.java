package app.pricing;

import app.models.Hotel;
import com.fasterxml.jackson.annotation.JsonInclude;
import app.handlers.ErrorHandler;
import app.models.Price;
import app.models.PriceDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gergely_Agnecz on 8/1/2017.
 */
@RestController
public class PriceController {

    private RestTemplate restTemplate = new RestTemplate();
    private JSONObject jsonObject = new JSONObject();
    private JSONArray hotelsJSON = new JSONArray();
    private JSONArray pricesJSON = new JSONArray();

    @Autowired
    PriceDAO priceDAO;

    @RequestMapping("/v1/hotels/prices")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity<?> roomPrices() {
        List<Price> prices = priceDAO.findAll();

        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    @RequestMapping("/v1/hotels/price")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity<?> pricesForAHotelByID(@RequestParam("id") int id) {

        clearContent();

        ResponseEntity<Hotel> hotelResponse = restTemplate.exchange("http://localhost:2222/v1/hotel?id={id}", HttpMethod.GET, null, new ParameterizedTypeReference<Hotel>() {
        }, id);
        Hotel hotel = hotelResponse.getBody();
        List<Price> prices = priceDAO.findAllByhotelId(id);

        if (prices.isEmpty() || hotel == null) {
            return new ResponseEntity<>(new ErrorHandler("No data was found for id: " + id), HttpStatus.NOT_FOUND);
        }

        hotelsJSON.add(prices);

        HashMap<Object, Object> hotelDetails = new HashMap<>();
        hotelDetails.put("hotelName", hotel.getHotelName());
        hotelDetails.put("country", hotel.getCountry());
        hotelDetails.put("city", hotel.getCity());
        hotelDetails.put("address", hotel.getAddress());

        jsonObject.put("result", "OK");
        jsonObject.put("hotelDetails", hotelDetails);
        jsonObject.put("pricing", prices);

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    private void clearContent() {
        hotelsJSON.clear();
        pricesJSON.clear();
        jsonObject.clear();
    }
}
