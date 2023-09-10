package com.example.spread.receive.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "spread_detail")
@AllArgsConstructor
@ToString
public class SpreadDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spread_id")
    private Spread spread;

    private Long userId;
    private int amount;
    private boolean allocated;

    public SpreadDetail() {}

    @Builder
    public SpreadDetail(Spread spread, int amount, Long userId, boolean allocated) {
        this.spread = spread;
        this.amount = amount;
        this.userId = userId;
        this.allocated = allocated;
    }

//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
}
