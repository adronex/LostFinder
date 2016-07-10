package by.lostFinder.controllers;

import by.lostFinder.dto.ExternalOAuthDto;
import by.lostFinder.dto.ExternalResponseDto;
import by.lostFinder.security.CustomExternalOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.POST, value = "/facebook")
    public ExternalOAuthDto getFacebookAccessToken(@RequestBody ExternalResponseDto dto) {
        return service.facebookProcess(dto.getCode(), dto.getRedirectUri(), dto.getClientId());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/google")
    public ExternalOAuthDto getGoogleAccessToken(@RequestBody ExternalResponseDto dto) {
        return service.googleProcess(dto.getCode(), dto.getRedirectUri(), dto.getClientId());
    }
}
