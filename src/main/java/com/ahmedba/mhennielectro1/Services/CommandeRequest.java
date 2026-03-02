package com.ahmedba.mhennielectro1.Services;

import java.util.List;
import lombok.Data;

@Data
public class CommandeRequest {
    private List<CartItemDTO> items;
}