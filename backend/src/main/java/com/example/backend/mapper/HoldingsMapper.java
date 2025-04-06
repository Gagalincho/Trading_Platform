package com.example.backend.mapper;

import com.example.backend.dto.HoldingsDTO;
import com.example.backend.model.Holdings;

import java.util.List;
import java.util.stream.Collectors;

public class HoldingsMapper {
    public static HoldingsDTO toDTO(Holdings h) {
        return new HoldingsDTO(
            h.getCryptoSymbol(),
            h.getQuantity()
        );
    }

    public static List<HoldingsDTO> toDTOList(List<Holdings> holdings) {
        return holdings.stream().map(HoldingsMapper::toDTO).collect(Collectors.toList());
    }
}
