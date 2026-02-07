package com.ahmedba.mhennielectro1.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;  // "success" ou "error"
    private String message; // Message lisible pour l'utilisateur
    private T data;         // Les données (User, Token, List, etc.) ou null
}