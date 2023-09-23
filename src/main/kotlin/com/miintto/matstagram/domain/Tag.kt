package com.miintto.matstagram.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_tag")
class Tag(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var userId: Long,

    var tagName: String,

    var memo: String? = null,

    var createdDtm: LocalDateTime = LocalDateTime.now()
)
