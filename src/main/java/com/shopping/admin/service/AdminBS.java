package com.shopping.admin.service;

import com.shopping.admin.clients.ItemFeignClient;
import com.shopping.admin.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;


@Service
public class AdminBS implements AdminBSI{

    @Autowired
    private ItemFeignClient itemFeignClient;

    @Override
    public int addNewItem(Item item) throws Exception {
        //1 if added, 0 if not added
        ResponseEntity<String> ret = createItemFromItemService(item);
        return ret != null ? 1:0;
    }

    public ResponseEntity<String> createItemFromItemService(Item item) {
        ResponseEntity<String> ret =  itemFeignClient.createItemFromItemService(item);
        return ret;
    }

    public ResponseEntity<String> fallBackForCallItemServiceForCreation(Item item) {
        ResponseEntity<String> ret = new ResponseEntity<String>("Not able to create Item", HttpStatus.INTERNAL_SERVER_ERROR);
        return ret;
    }

    @Override
    public int removeItemById(Long itemId) throws Exception {
        ResponseEntity<String> ret =  deleteItemFromItemService(itemId);
        return ret != null ? 1:0;
    }

    public ResponseEntity<String> deleteItemFromItemService(Long itemId) {
        return  itemFeignClient.deleteItemFromItemService(itemId);
    }

    @Override
    public int updateItem(Item item) throws Exception {
        ResponseEntity<String> ret = itemFeignClient.updateItemFromItemService(item);
        return ret != null ? 1:0;
    }

    public ResponseEntity<String> updateItemFromItemService(Item item) {
        return itemFeignClient.updateItemFromItemService(item);
    }
}
