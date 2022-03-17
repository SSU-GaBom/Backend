package GaBom.Bom.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long user_id;

    private String username;
    private String password;
    private Boolean enabled;

    public User(long user_id, String username, String password, Boolean enabled) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
