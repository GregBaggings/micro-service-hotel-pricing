package app.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Gergely_Agnecz on 8/1/2017.
 */
@RestController
public class PriceDAO {

    private PriceRepository repository;

    @Autowired
    public PriceDAO(PriceRepository repository) {
        super();
        this.repository = repository;
    }

    public List<Price> findAll() {
        return repository.findAll();
    }

    public List<Price> findAllByhotelId(int id) {
        return repository.findAllByhotelId(id);
    }
}
