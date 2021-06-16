package pt.fmbp.soiapbackend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import pt.fmbp.soiapbackend.entity.Usuario;
import pt.fmbp.soiapbackend.service.IUsuarioService;

import java.util.HashMap;
import java.util.Map;

@Component
public class InformacionAdicionalToken implements TokenEnhancer {

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        /** Se obtiene el usuario */
        Usuario user = usuarioService.getUserByUsername(oAuth2Authentication.getName());

        Map<String, Object> info = new HashMap<>();
        info.put("nombre_usuario", user.getNombre());
        info.put("apellido_usuario", user.getApellido());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);

        return oAuth2AccessToken;
    }
}
