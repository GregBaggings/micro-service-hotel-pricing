package unitTests;

import app.models.PriceDAO;
import app.models.PriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class PriceDAOUnitTests {

    @Mock
    PriceRepository mock;
    PriceDAO dao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dao = new PriceDAO(mock);
    }

    @Test
    public void findAll() {
        // GIVEN
        // WHEN
        dao.findAll();
        // THEN
        Mockito.verify(mock).findAll();
    }
}
