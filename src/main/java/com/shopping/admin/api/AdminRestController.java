package com.shopping.admin.api;

import com.shopping.admin.model.Item;
import com.shopping.admin.service.AdminBSI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    private AdminBSI adminBS;

    @PostMapping("/addItem")
    public ResponseEntity addNewItem(@RequestBody Item item) throws Exception {
        try {
            int i = adminBS.addNewItem(item);
        }catch (Exception ex) {
            logger.error("Exception occurred while adding item by admin", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("Item added Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteItem/itemId/{itemId}")
    public ResponseEntity removeItemById(@PathVariable("itemId") Long itemId) throws Exception {
        try {
            int i = adminBS.removeItemById(itemId);
        }catch (Exception ex) {
            logger.error("Exception occurred while removing item by admin for item id: "+ itemId, ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("Item removed Successfully", HttpStatus.OK);
    }

    @PutMapping("/updateItem")
    public ResponseEntity updateItem(@RequestBody Item item) throws Exception {
        try {
            int i = adminBS.updateItem(item);
        }catch (Exception ex) {
            logger.error("Exception occurred while updating item by admin for item id: "+ item.getId(), ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("Item updated Successfully", HttpStatus.OK);
    }
}
