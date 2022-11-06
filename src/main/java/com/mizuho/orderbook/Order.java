package com.mizuho.orderbook;

public class Order {
	private long id; //id of order
	private double price;
	private char side;  
	private long size;
	private OrderBook orderBook;
	
	public Order(OrderBook orderBook,long id,char side,double price,long size) {
		this.orderBook=orderBook;
		this.id = id;
		this.side = side;
		this.price = price;
		this.size = size;
	}
	
	public OrderBook getOrderBook() {
		return orderBook;
	}
	
	public long getId() {
		return id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public char getSide() {
		return side;
}
	
	 /**
     * Get the remaining quantity.
     *
     * @return the remaining quantity
     */
	public long getSize(){
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}

    void reduce(long oldSize) {
        size -= oldSize;
    }
}
