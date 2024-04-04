package ApiJwt.apiproducts.model.entity.user;

public record RegisterDTO(
        String login,

        String password,

        UserRole role
) {
}
