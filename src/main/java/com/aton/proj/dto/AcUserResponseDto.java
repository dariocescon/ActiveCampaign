package com.aton.proj.dto;

/**
 * Wrapper interno usato esclusivamente per deserializzare la risposta
 * dell'endpoint AC GET /users/email/{email}.
 * Non viene esposto direttamente nelle API REST di questo servizio.
 */
public record AcUserResponseDto(AcUserDto user) {
}
