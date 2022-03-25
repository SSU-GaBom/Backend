package com.example.bom.gabom.model.etc;

import com.example.bom.gabom.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAuthSet {
    private User user;
    private int authNum;
}
