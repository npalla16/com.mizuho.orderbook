package com.mizuho.orderbook;

import it.unimi.dsi.fastutil.longs.Long2LongRBTreeMap;
import it.unimi.dsi.fastutil.longs.LongComparators;
import it.unimi.dsi.fastutil.longs.LongSortedSet;

/**
 * An order book.
 */
public class OrderBook {

    private final long instrument;

    private final Long2LongRBTreeMap bids;
    private final Long2LongRBTreeMap asks;

    OrderBook(long instrument) {
        this.instrument = instrument;

        this.bids = new Long2LongRBTreeMap(LongComparators.OPPOSITE_COMPARATOR);
        this.asks = new Long2LongRBTreeMap(LongComparators.NATURAL_COMPARATOR);
    }

    /**
     * Get the instrument.
     *
     * @return the instrument
     */
    public long getInstrument() {
        return instrument;
    }

    /**
     * Get the best bid price.
     *
     * @return the best bid price or zero if there are no bids
     */
    public long getBestBidPrice() {
        if (bids.isEmpty())
            return 0;

        return bids.firstLongKey();
    }

    /**
     * Get the bid prices.
     *
     * @return the bid prices
     */
    public LongSortedSet getBidPrices() {
        return bids.keySet();
    }

    /**
     * Get a bid level size.
     *
     * @param price the bid price
     * @return the bid level size
     */
    public long getBidSize(long price) {
        return bids.get(price);
    }

    /**
     * Get the best ask price.
     *
     * @return the best ask price or zero if there are no asks
     */
    public long getBestAskPrice() {
        if (asks.isEmpty())
            return 0;

        return asks.firstLongKey();
    }

    /**
     * Get the ask prices.
     *
     * @return the ask prices
     */
    public LongSortedSet getAskPrices() {
        return asks.keySet();
    }

    /**
     * Get an ask level size.
     *
     * @param price the ask price
     * @return the ask level size
     */
    public long getAskSize(long price) {
        return asks.get(price);
    }

    boolean add(char side, long price, long quantity) {
        Long2LongRBTreeMap levels = getLevels(side);

        levels.addTo(price, quantity);

        return price == levels.firstLongKey();
    }

    @SuppressWarnings("deprecation")
	boolean update(char side, double price, long quantity) {
        Long2LongRBTreeMap levels = getLevels(side);

        long oldSize = levels.get((long)price);
        long newSize = oldSize + quantity;

        boolean onBestLevel = price == levels.firstLongKey();

        if (newSize > 0)
            levels.put((long) price, newSize);
        else
            levels.remove((long)price);

        return onBestLevel;
    }

    private Long2LongRBTreeMap getLevels(char side) {
        return side == Side.BUY ? bids : asks;
    }

}