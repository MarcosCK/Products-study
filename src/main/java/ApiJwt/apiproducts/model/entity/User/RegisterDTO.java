package ApiJwt.apiproducts.model.entity.User;

public record RegisterDTO(
        String login,

        String password,

        UserRole role
) {
}
