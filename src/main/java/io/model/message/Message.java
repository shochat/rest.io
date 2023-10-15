package io.model.message;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "messages")
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 100)
    private String text;

    @Column(name = "auther_id")
    private Long autherId;

    @Column(name = "vote_rate")
    private Integer voteRate;
}
