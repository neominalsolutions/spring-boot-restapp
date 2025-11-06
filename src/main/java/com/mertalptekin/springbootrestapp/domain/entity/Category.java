package com.mertalptekin.springbootrestapp.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;


    // CascadeType.ALL: Bu ayar, Category üzerinde yapılan işlemlerin (örneğin, silme, güncelleme) ilişkili Product varlıklarına da uygulanmasını sağlar. Örneğin, bir Category silindiğinde, o kategoriye ait tüm Product varlıkları da otomatik olarak silinir.
    // FetchType.LAZY: Bu ayar, Category varlığı yüklendiğinde ilişkili Product varlıklarının hemen yüklenmemesini sağlar. Bunun yerine, products alanına erişildiğinde Product varlıkları veritabanından getirilir. Bu, performansı artırabilir çünkü gereksiz yere tüm ilişkili verilerin yüklenmesini önler.
    // CascadeType.Remove: Bu ayar, bir Category silindiğinde, o kategoriye ait tüm Product varlıklarının da otomatik olarak silinmesini sağlar.
    // Cascade.Merge: Bu ayar, bir Category güncellendiğinde, o kategoriye ait tüm Product varlıklarının da otomatik olarak güncellenmesini sağlar.

}
