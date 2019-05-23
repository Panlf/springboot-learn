JWT简介
    JSON Web Token由三部分组成 - Header、Payload、Signature，他们之间用原点(.)连接，
    即是Header.Payload.Signature。

    Header
        Header 部分是一个 JSON 对象，描述 JWT 的元数据。
        由两部分组成：
            alg属性表示签名的算法(algorithm)，默认是 HMAC SHA256(写成 HS256)
            typ属性表示这个令牌(token)的类型(type)，JWT 令牌统一写为JWT

    Payload
        Payload 部分也是一个 JSON 对象，用来存放实际需要传递的数据。
        iss(issuer):签发人
        exp(expiration time):过期时间
        sub(subject):主题
        aud(audience):受众
        nbf(Not Before):生效时间
        iat(Issued At):签发时间
        jti(JWT ID):编号

        注意，JWT 默认是不加密的，任何人都可以读到，所以不要把秘密信息放在这个部分。
        这个 JSON 对象也要使用 Base64URL 算法转成字符串。

    Signature
        Signature 部分是对前两部分的签名，防止数据篡改。
        首先，需要指定一个密钥（secret）。这个密钥只有服务器才知道，不能泄露给用户。
        然后，使用 Header 里面指定的签名算法（默认是 HMAC SHA256），按照下面的公式产生签名。
        HMACSHA256(
          base64UrlEncode(header) + "." +
          base64UrlEncode(payload),
          secret)

    客户端收到服务器返回的 JWT，可以储存在 Cookie 里面，也可以储存在 localStorage。

    此后，客户端每次与服务器通信，都要带上这个 JWT。
    你可以把它放在 Cookie 里面自动发送，但是这样不能跨域，
    所以更好的做法是放在 HTTP 请求的头信息Authorization字段里面。

   特点
    （1）JWT 默认是不加密，但也是可以加密的。生成原始 Token 以后，可以用密钥再加密一次。
    （2）JWT 不加密的情况下，不能将秘密数据写入 JWT。
    （3）JWT 不仅可以用于认证，也可以用于交换信息。有效使用 JWT，可以降低服务器查询数据库的次数。
    （4）JWT 的最大缺点是，由于服务器不保存 session 状态，因此无法在使用过程中废止某个 token，或者更改 token 的权限。也就是说，一旦 JWT 签发了，在到期之前就会始终有效，除非服务器部署额外的逻辑。
    （5）JWT 本身包含了认证信息，一旦泄露，任何人都可以获得该令牌的所有权限。为了减少盗用，JWT 的有效期应该设置得比较短。对于一些比较重要的权限，使用时应该再次对用户进行认证。
    （6）为了减少盗用，JWT 不应该使用 HTTP 协议明码传输，要使用 HTTPS 协议传输。