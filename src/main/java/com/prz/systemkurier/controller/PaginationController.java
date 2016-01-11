package com.prz.systemkurier.controller;

import com.prz.systemkurier.criteria.Criteria;
import com.prz.systemkurier.dto.PaginationData;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public abstract class PaginationController<T> {

    private Logger logger = Logger.getLogger(PaginationController.class);

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<PaginationData<T>> paginateData(@RequestBody Criteria criteria){
        PaginationData<T> data;
        try {
            data = fetch(criteria);
        }catch (Exception e){
            logger.error("Exception occured during invocation of fetch()", e);
            return new ResponseEntity<PaginationData<T>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PaginationData<T>>(data, HttpStatus.OK);
    }

    public abstract PaginationData<T> fetch(Criteria criteria) throws Exception;

}
