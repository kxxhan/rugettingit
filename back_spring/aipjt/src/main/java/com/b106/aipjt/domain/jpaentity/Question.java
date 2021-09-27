package com.b106.aipjt.domain.jpaentity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity(name="question")
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgUrl;
    private String imgCaption;

    public Question(String imgUrl, String imgCaption) {
        this.imgUrl = imgUrl;
        this.imgCaption = imgCaption;
    }


}
