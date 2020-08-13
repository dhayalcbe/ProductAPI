package com.interview.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.interview.review.dto.ReviewGroup;

import java.util.List;

@Entity
@Table(name="product_table",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "review_group_id"
        })
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String type;

    private int price;

    private String imageUrl;

    @OneToOne(optional =false)
    private ReviewGroup reviewGroup;


}
