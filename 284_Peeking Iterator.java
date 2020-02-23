class PeekingIterator implements Iterator<Integer> {
	private Iterator<Integer> iterator;
	private Integer next;

	public PeekingIterator(Iterator<Integer> iterator) {
	    this.iterator = iterator;
	   	if (iterator.hasNext()) 
	   		this.next = iterator.next();
	}

    // the actual iterator stays but the next value is returned
    // so need to know who the next is in advance
	public Integer peek() {
  		return next;
	}

	@Override
	public Integer next() {
		int res = next;
		next = iterator.hasNext() ? iterator.next() : null;
		return res;
	}

	@Override
	public boolean hasNext() {
		return next != null;
		// the actuall go 1 step faster
		// so cann't return iterator.hasNext();
	}

}