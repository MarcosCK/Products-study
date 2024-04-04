package ApiJwt.apiproducts.model.entity.user;

public record AuthenticationDTO(
        String login,
        String password
) {
}
