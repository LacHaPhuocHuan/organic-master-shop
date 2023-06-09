package com.organic.shop.serviceImpl;

import com.organic.shop.Dtos.PaginatesDto;
import com.organic.shop.service.IPaginatesService;
import org.springframework.stereotype.Service;

@Service
public class PaginatesServiceImpl implements IPaginatesService {

	@Override
	public PaginatesDto getInfoPaginatesDto(int totalData, int limit, int currentPage) {
		PaginatesDto paginatesDto=new PaginatesDto();
		paginatesDto.setLimit(limit);
		paginatesDto.setTotalPage(setInfoTotalPage(limit, totalData));
		paginatesDto.setCurrentPage(checkCurrentPage(currentPage,paginatesDto.getTotalPage() ));
		paginatesDto.setStart(findStart(currentPage,limit));
		paginatesDto.setEnd(findEnd(paginatesDto.getStart(),limit,totalData));
		return paginatesDto;
	}

	private int findEnd(int start, int limit, int totalData) {
		
		return start+limit< totalData? start+limit-1: totalData;
	}

	private int findStart(int currentPage, int limit) {
		
		return (currentPage-1)*limit+1;
	}

	private int checkCurrentPage(int currentPage, int totalPage) {
		if(currentPage<1) return 1;
		if(currentPage>totalPage) return totalPage;
		return currentPage;
	}

	private int setInfoTotalPage(int limit, int totalData) {
		int totalPage=0;
		totalPage=totalData/limit;
		totalPage=limit<totalData? totalPage+1:totalPage;
		return totalPage;
	}

}
