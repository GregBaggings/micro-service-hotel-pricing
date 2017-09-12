package app.pricing;

import com.fasterxml.jackson.annotation.JsonInclude;
import app.handlers.ErrorHandler;
import app.models.Price;
import app.models.PriceDAO;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Gergely_Agnecz on 8/1/2017.
 */
@RestController
public class PriceController {
    private ErrorHandler incorrectInputHandler = new ErrorHandler("Incorrect input. Please use only numbers!");
    private ErrorHandler missingParameterHandler = new ErrorHandler("Missing param: ID");

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
    public ResponseEntity<?> roomPricesForAHotelByID(@Validated @RequestParam(value = "id", required = true) int id) {
        List<Price> prices = priceDAO.findAllByhotelId(id);

        if (prices.isEmpty()) {
            return new ResponseEntity<>(new ErrorHandler("No data was found for id: " + id), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<?> wrongType(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(incorrectInputHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<?> missingParam(Exception exception, HttpServletRequest request) {
        return new ResponseEntity<>(missingParameterHandler, HttpStatus.BAD_REQUEST);
    }
}
