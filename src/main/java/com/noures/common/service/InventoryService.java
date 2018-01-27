package com.noures.common.service;

import com.noures.common.domain.Product;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface InventoryService {
    Map<Product, Long> getTotalInventoryOnHand();
    long getQuantityOnHand(String itemId);
    void adjustInventory(String itemId, long quantity);
}
