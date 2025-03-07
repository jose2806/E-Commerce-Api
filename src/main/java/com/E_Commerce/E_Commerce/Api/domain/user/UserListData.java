package com.E_Commerce.E_Commerce.Api.domain.user;

public record UserListData(
        Long id,
        String name,
        String email,
        Role role
) {
    public UserListData(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
