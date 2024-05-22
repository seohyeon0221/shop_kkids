package com.example.shop_project_01.dto;

import com.example.shop_project_01.constant.UserRole;
import com.example.shop_project_01.entity.Product;
import com.example.shop_project_01.entity.UserAccount;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
//@Builder
public class UserAccountDto {

    @NotEmpty(message = "사용자 ID 입력은 필수입니다")
    private String username;
    @NotEmpty(message = "비밀번호 입력은 필수입니다")
    private String password;
    @NotEmpty(message = "비밀번호 확인은 필수입니다")
    private String passwordCheck;
    @NotEmpty(message = "이름 입력은 필수입니다")
    private String name;
    @NotEmpty(message = "전화번호 입력은 필수입니다")
    private String userPhone;
    @NotEmpty(message = "주소 입력은 필수입니다")
    private String userAddress;
    @NotEmpty(message = "이메일 입력은 필수입니다")
    private String userEmail;
    private UserRole userRole;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private int insertPoint;

    public UserAccountDto(String username, String password, String name, String userPhone, String userAddress,String userEmail, UserRole userRole, LocalDate createdAt, LocalDate updatedAt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserAccountDto(String username, String name, String userPhone, String userAddress, String userEmail, UserRole userRole, LocalDate createdAt, LocalDate updatedAt) {
        this.username = username;
        this.name = name;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserAccountDto(String name, String userPhone, String userEmail, String userAddress) {
        this.name = name;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
    }


    public static UserAccountDto fromUserAccountEntity(UserAccount userAccount){
        return new UserAccountDto(
                userAccount.getUsername(),
                userAccount.getPassword(),
                userAccount.getName(),
                userAccount.getUserPhone(),
                userAccount.getUserAddress(),
                userAccount.getUserEmail(),
                userAccount.getUserRole(),
                userAccount.getCreatedAt(),
                userAccount.getUpdatedAt()
        );
    }

    public static UserAccountDto fromUserAccountEntityNoPassword(UserAccount userAccount){
        return new UserAccountDto(
                userAccount.getUsername(),
                userAccount.getName(),
                userAccount.getUserPhone(),
                userAccount.getUserAddress(),
                userAccount.getUserEmail(),
                userAccount.getUserRole(),
                userAccount.getCreatedAt(),
                userAccount.getUpdatedAt()
        );
    }


    public static UserAccount fromUserAccountDto(UserAccountDto dto){
        UserAccount userAccount = new UserAccount();

        userAccount.setUsername(dto.getUsername());
        userAccount.setPassword(dto.getPassword());
        userAccount.setName(dto.getName());
        userAccount.setUserPhone(dto.getUserPhone());
        userAccount.setUserAddress(dto.getUserAddress());
        userAccount.setUserEmail(dto.getUserEmail());
        userAccount.setUserRole(dto.getUserRole());
        userAccount.setCreatedAt(dto.getCreatedAt());
        userAccount.setUpdatedAt(dto.getUpdatedAt());
        return userAccount;
    }






}