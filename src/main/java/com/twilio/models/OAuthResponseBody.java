package com.twilio.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;


import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@ToString
public class OAuthResponseBody {

    @JsonProperty("access_token")
    @Getter
    @Setter
    private String accessToken;

    @JsonProperty("expires_in")
    @Getter
    @Setter
    private String expiresIn;

    @JsonProperty("token_type")
    @Getter
    @Setter
    private String tokenType;

    public boolean isAccessTokenExpired() {
        DecodedJWT jwt = JWT.decode(this.accessToken);
        Date expiresAt = jwt.getExpiresAt();
        // Add a buffer of 30 seconds
        long bufferMilliseconds = 30 * 1000;
        Date bufferExpiresAt = new Date(expiresAt.getTime() - bufferMilliseconds);
        return bufferExpiresAt.before(new Date());
    }
}

/*


eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Inc3ODVXNHF0dTVVS21FcXR3dEhqTSJ9.eyJodHRwOi8vdHdpbGlvL3ZhbGlkcmVnaW9ucyI6ImRldi11czEiLCJodHRwOi8vdHdpbGlvL2FjdCI6eyJzdWIiOiJPUjg2N2I1NTQ3MGY0MTJjODYyOGVmMDBhODIxODQzZTExIn0sImh0dHA6Ly90d2lsaW8vc3ViIjoiT1I4NjdiNTU0NzBmNDEyYzg2MjhlZjAwYTgyMTg0M2UxMSIsImh0dHA6Ly90d2lsaW8vdHlwIjoidm5kLnR3aWxpby5vYXV0aC5hdCtqd3Q7IiwiaXNzIjoiaHR0cHM6Ly9hcGkudHdpbGlvLWRldi5hdXRoMGFwcC5jb20vIiwic3ViIjoiek1xd2RodlZQOFZVa3prUHJ4S2wwdlJubXVnVkpYYXJAY2xpZW50cyIsImF1ZCI6Imh0dHBzOi8vd3d3LnR3aWxpby5jb20vb3JnYW5pemF0aW9ucyIsImlhdCI6MTcxMTQ0MjI5MiwiZXhwIjoxNzExNTI4NjkyLCJhenAiOiJ6TXF3ZGh2VlA4VlVremtQcnhLbDB2Um5tdWdWSlhhciIsImd0eSI6ImNsaWVudC1jcmVkZW50aWFscyJ9.TJhBH57_KWWIyrvXw-c-dAzs9NlcfKz2uhc1EobO58ruI3njm3A_3hEBiIyqS3LT7_klz-wDqK2fP-pP8UJoA_fYLtD7iu93Q60PyKkJw-IGknkhIqL8mOpq0C5ViWKrieT9BCjHda6fN5o9zRmS0tSZ8Mhey6t3V5JUuTOs4RR_goU22TXELimlSx7LA5jBmjtt43kNDuewrOO_jrGGrZZKTNsWJo-ZYQ6dFr4BKEYYp3YzNWY-dG7CFc7QRY7BHxbkSjHNv1LrJ35aOMiLXr8XEDU1FMilErLSDtf0I9Qcb9ErsM2xMOVqbAu9JVp5W3svVGVkfjfj3cTILqDiAQ

 */
