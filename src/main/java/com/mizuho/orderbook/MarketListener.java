package com.mizuho.orderbook;

/**
 * The interface for outbound events from a market.
 */
public interface MarketListener {

    /**
     * An event indicating that an order book has changed.
     *
     * @param book the order book
     * @param bbo true if the best bid and offer (BBO) has changed, otherwise false
     */
    void update(OrderBook book, boolean bbo);

    /**
     * An event indicating that a trade has taken place.
     *
     * @param book the order book
     * @param side the side of the incoming order
     * @param price the trade price
     * @param size the trade size
     */
    void trade(OrderBook book, char side, double price, long size);

}