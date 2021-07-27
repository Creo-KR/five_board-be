package creo.games.five.server.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.hibernate.id.UUIDGenerator
import java.util.*
import javax.persistence.*

@Entity
data class Room(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID", strategy = "org.hibernate.id.UUIDGenerator",
        parameters = [
            Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        ]
    )
    @Column(columnDefinition = "BINARY(16)")
    var uuid: UUID? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    var host: User? = null,

    @ManyToOne
    @JoinColumn
    var guest: User? = null,

    @Column(nullable = false)
    @CreationTimestamp
    var regDate: Date? = null
) {

}