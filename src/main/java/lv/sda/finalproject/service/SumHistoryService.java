package lv.sda.finalproject.service;

import lv.sda.finalproject.dto.NumberForm;
import lv.sda.finalproject.model.SumHistory;
import lv.sda.finalproject.repo.SumHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SumHistoryService {
    private SumHistoryRepository sumHistoryRepository;

    public SumHistoryService(SumHistoryRepository sumHistoryRepository) {
        this.sumHistoryRepository = sumHistoryRepository;
    }

    public Integer sum(NumberForm form) {
        SumHistory history = new SumHistory(form.getNumber1(), form.getNumber2());
        sumHistoryRepository.save(history);
        return history.getSum();
    }

    public List<SumHistory> findSumHistory() {
        List<SumHistory> history = new ArrayList<>();
        sumHistoryRepository.findAll().forEach(history::add);
        return history;
    }
}
