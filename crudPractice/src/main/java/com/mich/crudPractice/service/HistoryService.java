package com.mich.crudPractice.service;

import com.mich.crudPractice.models.history.History;
import com.mich.crudPractice.models.history.HistoryRepository;
import com.mich.crudPractice.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<History>> getAll(){
        return new CustomResponse<>(
                this.historyRepository.findAll(),
                false,
                200,
                "Success get all history"

        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<History> getOne(Long id){
        Optional<History> optional = this.historyRepository.findById(id);
        if(!optional.isPresent()){
            return new CustomResponse<>(
                    null,
                    true,
                    200,
                    "Ok"
            );
        }else {
            return new CustomResponse<>(
                   null,
                     true,
                        404,
                        "History not found"
            );
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<History> save(History history){
        return new CustomResponse<>(
                this.historyRepository.save(history),
                false,
                200,
                "Success save history"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<History> update(History history){
        if (!this.historyRepository.existsById(history.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "History not found"
            );
        }
        return new CustomResponse<>(
                this.historyRepository.saveAndFlush(history),
                false,
                200,
                "Success update history"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<History> delete(Long id){
        if (!this.historyRepository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "History not found"
            );
        }
        this.historyRepository.deleteById(id);
        return new CustomResponse<>(
                null,
                false,
                200,
                "Success delete history"
        );
    }

}
