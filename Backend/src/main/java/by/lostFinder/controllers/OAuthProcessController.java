package by.lostFinder.controllers;

import by.lostFinder.dto.ExternalOAuthDto;
import by.lostFinder.dto.ExternalResponseDto;
import by.lostFinder.oauth.external.CustomExternalOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Default class description.
 *
 * @author P.Sinitsky.
 * @date 10.07.2016.
 */
@RestController
@RequestMapping("/api/oauth")
public class OAuthProcessController {

    @Autowired
    private CustomExternalOAuthService service;

    @PostMapping(value = "/facebook")
    public ExternalOAuthDto getFacebookAccessToken(@RequestBody ExternalResponseDto dto) {
        return service.facebookProcess(dto.getCode(), dto.getRedirectUri(), dto.getClientId());
    }

    @PostMapping(value = "/google")
    public ExternalOAuthDto getGoogleAccessToken(@RequestBody ExternalResponseDto dto) {
        return service.googleProcess(dto.getCode(), dto.getRedirectUri(), dto.getClientId());
    }
}
