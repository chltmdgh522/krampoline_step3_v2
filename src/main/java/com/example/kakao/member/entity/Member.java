package com.example.kakao.member.entity;

import com.example.kakao.record.entity.Note;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {

    @Id
    private String memberId;
    private String phone;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> note = new ArrayList<>();
    @Builder
    public Member(String memberId, String phone) {
        this.memberId= memberId;
        this.phone = phone;
    }

    public Member() {

    }
}
