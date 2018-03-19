package org.wjc.maven.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "borrower")
public class Borrower extends User {

    private static final long serialVersionUID = 1L;

//	@Column(name = "books_borrowed", nullable = false)
//	public List<Book> mBooksBorrowed;

    public Borrower() {
        // Default constructor required by hibernate
    }

    public Borrower(long id, @NotNull String email, @NotNull String name, @NotNull String username,
                    @NotNull String password) {
        mId = id;
        mEmail = email;
        mName = name;
        mUsername = username;
        mPassword = password;
    }

    @Override
    public String toString() {
        return "Borrower [id=" + mId + ", email=" + mEmail + ", name=" + mName + ", username=" + mUsername
                + ", password=" + mPassword + "]";
    }

}
