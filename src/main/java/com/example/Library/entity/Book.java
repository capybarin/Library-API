package com.example.Library.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @ApiModelProperty(notes = "The database generated ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    @ApiModelProperty(notes = "The name of the book")
    private String name;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "authorid")
    @NotNull
    @ApiModelProperty(notes = "The author ID of the book")
    private Author author;

    @Column(name = "about")
    @NotNull
    @ApiModelProperty(notes = "The description of the book")
    private String about;

    @ManyToOne(targetEntity = Tag.class)
    @JoinColumn(name = "tagid")
    @NotNull
    @ApiModelProperty(notes = "The tag ID of the book")
    private Tag tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", about='" + about + '\'' +
                ", tag=" + tag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) &&
                name.equals(book.name) &&
                author.equals(book.author) &&
                about.equals(book.about) &&
                tag.equals(book.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, about, tag);
    }
}
