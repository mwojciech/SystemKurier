package com.prz.systemkurier.controller;

import com.prz.systemkurier.criteria.Criteria;
import com.prz.systemkurier.domain.Pack;
import com.prz.systemkurier.dto.PaginationData;
import com.prz.systemkurier.service.PackService;
import com.prz.systemkurier.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/packs")
@RestController
public class PackController extends PaginationController<Pack> {

    @Autowired
    private PackService packService;

    @Autowired
    private RoleService roleService;

    private Logger logger = Logger.getLogger(PackController.class);

    @Override
    public PaginationData<Pack> fetch(Criteria criteria) throws Exception {
        List<Pack> data = packService.getPacksWithCriteriaPaginated(criteria);
        Integer totalItems = packService.countPacksWithCriteria(criteria);
        return new PaginationData<Pack>(totalItems, data);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Pack> getAll() throws SQLException {
        return packService.getAllPacks();
    }

    @RequestMapping(value = "/pack", method = RequestMethod.POST)
    public ResponseEntity<Void> savePack(@RequestBody Pack pack) throws SQLException {
        //logger.info("test");
        packService.savePack(pack);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value = "/packToUpdate", method = RequestMethod.POST)
    public ResponseEntity<Void> updatePack(@RequestBody Pack pack) throws SQLException {
        packService.updatePack(pack);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //test
    @RequestMapping(value = "/packs/paginated", method = RequestMethod.GET)
    public ResponseEntity<List<Pack>> getPaginatedPacks() {
        List<Pack> pack = new ArrayList<Pack>();
        try {
            pack = packService.getAllPacksPaginated(new Criteria(0, 10));
        } catch (SQLException e) {
            logger.error(e);
            return new ResponseEntity<List<Pack>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Pack>>(pack, HttpStatus.OK);
    }
}
