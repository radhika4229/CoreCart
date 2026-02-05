package com.radhika.corecart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    @Column(name = "image", nullable = false)
    @JsonIgnore
    private byte[] image;
    private String downloadUrl;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;


    public String getFileName() {
        return fileName;
    }


    public void setImage(byte[] bytes) {
        this.image = bytes;
    }
}
