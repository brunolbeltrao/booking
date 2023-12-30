package com.hostfully.book.controller.dto;

import com.hostfully.book.hexagono.domain.Guest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestDTO {
    private Long id;
    private String name;

    private Guest guest = new Guest();

    public static GuestDTO build(Guest guest) {
        return  GuestDTO.builder()
                .id(guest.getId())
                .name(guest.getName())
                .build();
    }
}
