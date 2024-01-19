package com.bushansirgur.springbootmongodb.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoDTO {
    @Id
    private String id;
    @NotNull(message = "todo cannot be null")
    private String todo;
    @NotNull(message = "description cannot be null")
    private String description;
    @NotNull(message = "compled cannot be null")
    private Boolean compled;
    @NotNull(message = "createAt cannot be null")
    private Date createAt;
    private Date updateAt;
}
