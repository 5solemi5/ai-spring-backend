package com.sesac.aibackend.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sports {
    private Long id;
    private String name;
    private String category;
    private int playerCount;
    private boolean indoor;
}
