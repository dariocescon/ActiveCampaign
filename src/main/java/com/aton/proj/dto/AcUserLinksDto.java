package com.aton.proj.dto;

public record AcUserLinksDto(
        String lists,
        String userGroup,
        String dealGroupTotals,
        String dealGroupUsers,
        String configs,
        String dealConnection,
        String userConversationsPermission,
        String seatUser
) {
}
