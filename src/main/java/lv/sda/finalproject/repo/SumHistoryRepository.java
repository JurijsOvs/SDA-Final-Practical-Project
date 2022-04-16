package lv.sda.finalproject.repo;

import lv.sda.finalproject.model.SumHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SumHistoryRepository extends CrudRepository<SumHistory, Long>{

}
