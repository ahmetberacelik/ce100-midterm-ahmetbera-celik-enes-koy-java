/**
 * @file MinHeap.java
 *
 * @brief This file contains the implementation of the MinHeap class.
 */
package com.bera.farmermarket;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a MinHeap data structure for storing ProductSeason objects.
 */
public class MinHeap {
    private List<ProductSeason> items; /**< The list containing the items in the min heap. */
    /**
     * Constructs an empty MinHeap.
     */
    public MinHeap() {
        this.items = new ArrayList<>();
    }
    /**
     * Inserts a ProductSeason item into the MinHeap.
     *
     * @param item the ProductSeason item to insert
     */
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
    /**
     * Removes and returns the smallest item from the MinHeap.
     *
     * @return the smallest item in the MinHeap
     */

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
    /**
     * Adjusts the heap to maintain the heap property starting from the given index.
     *
     * @param i the index to start heapifying from
     */
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
    /**
     * Checks if the MinHeap is empty.
     *
     * @return true if the MinHeap is empty, false otherwise
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
