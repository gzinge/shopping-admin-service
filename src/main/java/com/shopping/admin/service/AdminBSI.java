package com.shopping.admin.service;

import com.shopping.admin.model.Item;

public interface AdminBSI {
    public int addNewItem(Item item) throws Exception;

    public int removeItemById(Long itemId) throws Exception;

    public int updateItem(Item item) throws Exception;
}
