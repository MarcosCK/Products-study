package ApiJwt.apiproducts.model.entity.User;

public record AuthenticationDTO(
        String login,
        String password
) {
}
