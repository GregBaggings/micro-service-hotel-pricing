package unitTests;

import app.models.Price;
import app.models.PriceDAO;
import app.pricing.PriceController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class PriceControllerUnitTests {
    @InjectMocks
    private PriceController roomsController;
    private MockMvc mvc;

    @Mock
    PriceDAO dao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(roomsController).build();
    }

    @Test
    public void getPricesTest() throws Exception {
        this.mvc.perform(get("/v1/hotels/prices")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getPricesByIdTest() throws Exception {
        //Mock is empty so 404 is the expected
        this.mvc.perform(get("/v1/hotels/prices/price?id=1")).andExpect(status().is4xxClientError());
    }

    @Test
    public void getPricesById2Test() throws Exception {
        Price data = new Price();
        data.setHotelId(1);
        data.setPrice(5000);
        data.setRoomId(1);
        List<Price> dummyList = new ArrayList<>();
        dummyList.add(data);
        Mockito.when(dao.findAllByhotelId(1)).thenReturn(dummyList);
        this.mvc.perform(get("/v1/hotels/prices/price?id=1")).andExpect(status().is2xxSuccessful());
    }
}
