package com.aneeque.coding.challenge.demo.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginationDto {

    private int size;

    private  int page;

    private Sort.Direction sortDirection = Sort.Direction.DESC;

    private String sortByField = "createdAt";

}
