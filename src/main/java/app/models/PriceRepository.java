package app.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Gergely_Agnecz on 8/1/2017.
 */
public interface PriceRepository extends JpaRepository<Price, Integer> {
    List<Price> findAll();

    List<Price> findAllByhotelId(int id);
}
