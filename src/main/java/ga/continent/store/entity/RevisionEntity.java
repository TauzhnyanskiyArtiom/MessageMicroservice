package ga.continent.store.entity;

import ga.continent.store.listener.UserRevisionListener;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@org.hibernate.envers.RevisionEntity(UserRevisionListener.class)
@Entity
@Table(name = "revision")
public class RevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    Long id;

    @RevisionTimestamp
    Long timestamp;

    String userId;

    String username;

}
