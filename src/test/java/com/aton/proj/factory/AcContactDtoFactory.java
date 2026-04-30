package com.aton.proj.factory;

import com.aton.proj.dto.AcContactDto;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Factory per la creazione di istanze di AcContactDto da utilizzare nei test.
 */
public class AcContactDtoFactory {

    private AcContactDtoFactory() {
    }

    /**
     * Crea una lista di AcContactDto con dati sintetici univoci.
     *
     * @param count numero di contatti da generare (deve essere >= 1)
     * @return lista di AcContactDto
     */
    public static List<AcContactDto> create(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("count must be >= 1, got: " + count);
        }
        return IntStream.rangeClosed(1, count)
                .mapToObj(AcContactDtoFactory::buildContact)
                .toList();
    }

    /**
     * Crea un singolo AcContactDto con indice i.
     */
    public static AcContactDto createOne(int index) {
        return buildContact(index);
    }

    private static AcContactDto buildContact(int index) {
        return new AcContactDto(
                "test.contact." + index + "@example.com",
                "FirstName" + index,
                "LastName" + index,
                "+39 333 " + String.format("%07d", index)
        );
    }
}
