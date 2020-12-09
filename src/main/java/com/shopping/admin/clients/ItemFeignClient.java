package com.shopping.admin.clients;

import com.shopping.admin.model.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "item-service", url = "http://localhost:58007")
public interface ItemFeignClient {

    @PostMapping("/item/add")
    public ResponseEntity<String> createItemFromItemService(@RequestBody Item item);

    @DeleteMapping("item/delete/id/{id}")
    public ResponseEntity<String> deleteItemFromItemService(@PathVariable("id") Long itemId);

    @PutMapping("item/update")
    public ResponseEntity<String> updateItemFromItemService(@RequestBody Item item);
}
