package creo.games.five.server.entity

import javax.persistence.*

@Entity
data class Room(
    @Id
    @GeneratedValue
    var seq: Long? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    var host: User? = null,

    @ManyToOne
    @JoinColumn
    var guest: User? = null
) {

}