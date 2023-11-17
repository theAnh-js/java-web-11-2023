package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public interface Pageble {

	Integer getPage(); // lấy page đăng đứng, cũng như page nếu được click vào

	Integer getOffset();

	Integer getLimit();

	Sorter getSorter();
}
