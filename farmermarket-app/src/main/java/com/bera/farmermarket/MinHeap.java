package com.bera.farmermarket;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private List<ProductSeason> items;

    public MinHeap() {
        this.items = new ArrayList<>();
    }

    public void insert(ProductSeason item) {
        items.add(item);
        int i = items.size() - 1;
        while (i > 0) {
            int parentIdx = (i - 1) / 2;
            ProductSeason parent = items.get(parentIdx);
            if (item.getPrice() >= parent.getPrice()) break;
            items.set(i, parent);
            i = parentIdx;
        }
        items.set(i, item);
    }

    public ProductSeason remove() {
        if (items.size() == 0) return null;
        ProductSeason removedItem = items.get(0);
        ProductSeason lastItem = items.remove(items.size() - 1);
        if (items.size() > 0) {
            items.set(0, lastItem);
            heapify(0);
        }
        return removedItem;
    }

    private void heapify(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < items.size() && items.get(left).getPrice() < items.get(smallest).getPrice()) {
            smallest = left;
        }
        if (right < items.size() && items.get(right).getPrice() < items.get(smallest).getPrice()) {
            smallest = right;
        }
        if (smallest != i) {
            ProductSeason temp = items.get(i);
            items.set(i, items.get(smallest));
            items.set(smallest, temp);
            heapify(smallest);
        }
    }
}
