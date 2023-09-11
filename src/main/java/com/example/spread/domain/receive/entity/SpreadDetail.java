package com.example.spread.domain.receive.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "spread_detail")
public class SpreadDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spread_id")
    private Spread spread;

    private int amount;
    private Long userId;
    private boolean allocated;

    public SpreadDetail() {}

    @Builder
    public SpreadDetail(Spread spread, int amount, Long userId, boolean allocated) {
        this.spread = spread;
        this.amount = amount;
        this.userId = userId;
        this.allocated = allocated;
    }
}
