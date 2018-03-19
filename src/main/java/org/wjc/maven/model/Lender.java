package org.wjc.maven.model;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lender")
public class Lender extends User {

    private static final long serialVersionUID = 1L;

//	@Column(name = "books_owned", nullable = false)
//	public List<Book> mBooksOwned;

    public Lender() {
        // Default constructor required by hibernate
    }

    public Lender(long id, @NotNull String email, @NotNull String name, @NotNull String username,
                  @NotNull String password) {
        mId = id;
        mEmail = email;
        mName = name;
        mUsername = username;
        mPassword = password;
    }

}
