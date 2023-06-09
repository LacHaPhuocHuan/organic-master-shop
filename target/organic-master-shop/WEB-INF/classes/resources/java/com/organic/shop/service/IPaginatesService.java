package com.organic.shop.service;

import com.organic.shop.Dtos.PaginatesDto;
import org.springframework.stereotype.Service;


@Service
public interface IPaginatesService {
	public PaginatesDto getInfoPaginatesDto(int totalData, int limit, int currentPage);

}
