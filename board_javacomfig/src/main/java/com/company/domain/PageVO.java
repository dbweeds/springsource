package com.company.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageVO {
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int total;
	private Criteria cri;

	public PageVO(Criteria cri, int total) {
		this.total = total; // 전체 게시물 수
		this.cri = cri; // 현재페이지번호,페이지당 게시물 갯수

		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = endPage - 9;

		int realEnd = (int) (Math.ceil((total / 1.0) / cri.getAmount()));
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
