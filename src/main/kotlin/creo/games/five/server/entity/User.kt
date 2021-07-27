package creo.games.five.server.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

@Entity
@Table(
    indexes = [Index(name = "IDX_user_id", columnList = "id")],
    uniqueConstraints = [UniqueConstraint(name = "UK_id_enabled", columnNames = ["id", "enabled"])]
)
data class User(
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

    @Column(nullable = false, length = 50)
    var id: String? = null,

    @Column(nullable = false, length = 72)
    var pw: String? = null,

    @Column(nullable = false, length = 12, unique = true)
    var nick: String? = null,

    @Column(nullable = false)
    @CreationTimestamp
    var regDate: Date? = null,

    @Column(nullable = false)
    var enabled: Boolean? = true,

    ) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("USER"));
        return authorities;
    }

    override fun getPassword() = pw;

    override fun getUsername() = id

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}