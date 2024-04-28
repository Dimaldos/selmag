package ru.dimaldos.selmag.catalogueservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "catalogue", name = "t_product")
@NamedQueries(
        @NamedQuery(
                name = "Product.findAllByTitleLikeIgnoringCase",
                query = "select p from Product p where p.title ilike :filter"
        )
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "c_title")
    private String title;

    @Size(max = 1000)
    @Column(name = "c_details")
    private String details;
}
