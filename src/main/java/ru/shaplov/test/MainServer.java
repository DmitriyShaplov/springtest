package ru.shaplov.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dmitriy Shaplov
 */
public class MainServer {
    public static void main(String[] args) throws IOException {
//        ForkJoinPool fork = new ForkJoinPool(4);
//        try (ServerSocket server = new ServerSocket(8182)) {
//            while (!server.isClosed()) {
//                final Socket so = server.accept();
//                fork.execute(() -> {
//                    try (OutputStream out = so.getOutputStream()) {
//                        out.write(("HTTP/1.1 200 OK\n" +
//                                "Content-Type: text/html; charset=utf-8\n\n<html><body><form method='post' action='http://idp.int.sudis.at-consulting.ru/idp/profile/SAML2/POSTGOST/SSO'><input type='hidden' name='SAMLRequest' value='MIIQuQYJKoZIhvcNAQcDoIIQqjCCEKYCAQAxggJNMIICSQIBADCCAXIwggFbMSAwHgYJKoZIhvcNAQkBFhFpbmZvQGNyeXB0b3Byby5ydTEYMBYGBSqFA2QBEg0xMDM3NzAwMDg1NDQ0MRowGAYIKoUDA4EDAQESDDAwNzcxNzEwNzk5MTELMAkGA1UEBhMCUlUxGDAWBgNVBAgMDzc3INCc0L7RgdC60LLQsDEVMBMGA1UEBwwM0JzQvtGB0LrQstCwMS8wLQYDVQQJDCbRg9C7LiDQodGD0YnRkdCy0YHQutC40Lkg0LLQsNC7INC0LiAxODElMCMGA1UECgwc0J7QntCeICLQmtCg0JjQn9Ci0J4t0J/QoNCeIjFrMGkGA1UEAwxi0KLQtdGB0YLQvtCy0YvQuSDQv9C 0LTRh9C40L3QtdC90L3Ri9C5INCj0KYg0J7QntCeICLQmtCg0JjQn9Ci0J4t0J/QoNCeIiDQk9Ce0KHQoiAyMDEyICjQo9CmIDIuMCkCEQF2dXkAlKqatkEo7C054HxGMB8GCCqFAwcBAQEBMBMGByqFAwICJAAGCCqFAwcBAQICBIGsMIGpMCgEIMe0Vv/7Mp1J8sIsdnzZE3pyYmlQXYi8qGqrQUDuwpafBARjnSiKoH0GCSqFAwcBAgUBAaBmMB8GCCqFAwcBAQEBMBMGByqFAwICJAAGCCqFAwcBAQICA0MABEAtkPG QgeiJE1tPCVbi0iL6Ozh8EdLm8k1aGcH7lbkaW7FJ1YWy YAU5iYYZVzSsgKcdyY4edlH8QU7Zk6F3sQBAgycD2UTyoF9DCCDk4GCSqGSIb3DQEHATAdBgYqhQMCAhUwEwQIT9c6 m1jm04GByqFAwICHwGAgg4gdV Tmra3gOsJ9zb03Jds8W1xh7aeBVLzrsNuBmZnvGZQHFKJstumARy9m9aAQW8wDIftScHD8lma4Yqn/WhiN qVr3XN61rIrCkXPU7BS/1Sll2v4dj9LFLCpeiHXwGt6hhbWmOiVoYY4VsoKtGzgjm/5ISffbsRinPg1E2/h7XbTfeL3qesLm6Ncd8HXnUIihFZKCFcvrU8i01qds2VgFX1Vdml4a3eIJ6lyyJV5FhXm8ZZsP4tB1TccRuveEFYtJTsGqgVUTKJB2zkKdKQ4MisNsJw19aUJDvqfsxWUpzIOzghRp4VyDM4lc1JJjP2rRe8zcYecbop5kmajrykHLVawlaU5dz9C9JQ8n7Ico6/sYHHbpT6JJuQ5pwXbSweTCgD2TTQuka49W2qNIx7W7LHDYwAkSQfNaK/l3CwzU4j IYXeK EvrBbRNq/wTXudSaNV2h7/k9u9Ozfdptl4FYULxDoVliXPjMH1Pxe36jtccHFMRTkDlSOQYDr1aqsTo5A rXZU5Pe9fqPrc/uJhEGBksavZE75P1hgGr7hdXQ2IChWQs2G71A/TzxouH2YqZHAuWy8Cq1CeMerS6Eq5gEc66F4UHkIH2rmwymhsGQp6Q9ELyslXvZzN0rn7bzAvEC230G9AT1Tmugnj793PFLEd3LU7Din4JzUq7Lc5 ShjsqYJu4lqNFN5Gko NAFTz6aZpnLhdplU0XXb3dDPLe LWezF/FgwE7RtWD0jkZpKMQn1dWQYGJ111dO9WkUS6nwoLuF RfkPYE5 QoHAJaoCI183VbtFCrS//RWumtzlPGE7puTifBAu82ziMHeAte3UYRNZ5PpvKUV2FxYiMjEsfl hV/2leXsmQZPfUQ/HHD8JpHLtZDEzDxUVuTUFu3fM5qOpZ62JyEUT VQr 3eAPOgBQrAK2/LunoWuEPDUjiijw8RW1bJD1JWdrOch8tdzwTRW1HqiRmPZ5wAC4yGzEoqVTTpQEu/MRsZuJzgJsYlNVw6Hb3LJyQVgk/hszTn/r7/xCEjziGzGNJSWL4lRKbJJaSxpBQ43sbjmtW26xKZwqVzHq4JFqd0lJPip6bnI DhMikFY9tOw4Sk ADct1Qidir6 Nu3CZw0cmEPERa9UVwPKg6WQn7B11EWiDyfOU2mNMJc/4U4X BAbZtpEzQOvSfZetUGArU2fi XFZslkW2ODr7vdMtnYr8GGMH3aEtEX4AqWZeiEcBF6unpdkSimWLTIa8er92a8onow6B7MhhOgcYTy2hYSjvVP8cwZk8/mZ5y2e0HF3NIXfh5jNv2f6lYKeEnIdfQn8lVYI6eZTxtbfMAogsXlJxGwCpJEhlcAwyRcQeVC5v/AEJOTpJf2/XgeG6fQccV56dGy9yYapnNHmvjRGs2NwhDOS7c0F/VlwRfCPLeEPHH9H0giTTvdHuQ3R3HCtGh301rd1jlD5P17RTGpS6dexHxAi0xj6LyCH9XwByTwfjAfq8fBYAfmxLgr1WFLHLrOIB qef0WbaYkdx9 WIN0LwE0DYdZmRViRQhDZ0KdB xGdqtzbc9mO9LA4EKfzw4mhqHBve5MkRqNTGutMMTfb 4wHDFlXLwoSMUr3tC4neht1SsxjpxDHPPbjesuZNE7l40xuR8YN cKGw48YXwm7POuitMhpy0hPXoD63jWpHyMk3Cj5Pge6HrLnpqmjIoxKb49LXxLtPVgBhy7NhGeInZuCgmIp4qLjryHeZiXbUwVgndJkZ007AMydndyhDByQTPYLGo2oI7/8MeYKjLA6AJlRPkNxDDsOwP80j0gssFYsr8LrrgvdkVWsev0EBqTirwQ5jgSx0gHny4u4tx9LAahm3LBKK6HXoAW22KLprThWz3AOMELA2fsK43Kgh7tg8pPTXvm0i/bkTgjsIk0EqOtvnaDnhbh/aopZDbKSkKtWGbyMY9WCYnhyYU2PT8bkcYvdPr 0VYCRWp7Ooz0/7/1XjLlgj1i6pAvuyrfL1aPMPMP85rtDnHrkevMdG3It8Ue4hGTyY yzHNcbeBGtDPgpThJ0PO63nfrTvbm2HkFDefQ/l7QCE1ksO0A kaGV4tsyhO12dzQt/xsKr7eXPksqMDxrpEJm/Kuo DCQDD/F5mg tUFvk3swJLu9FO3nAMtzjSvd78CkDH35yMzbW0REl6r7XOKvZdrNDEm3iVjCB9vffcOK5rvPugGtH2uhMBWjTYsHsSBxDD 4SaReg3z32mC2a9WQu30tFLtpnLkjvuNUEMVU4ZkO gddPPNChO0lQYlQj5yv/hi 9xjB1tLCYeK 2v 4sJXKHTeHAwd6JquVdAPRJed72tsqKmctSLaO4pPVUz0oBGKBFLjaRkN7QxMKeHk9aarWbsPHkJZ DhzUoIvweEt4V1Kgh9uf2ZjU2g4Wgh3AuDAam6Wio99VSwv7iKEDqFKdVc5GMj4FmcOdUR7HBQpRnUjTbuvvqadz33///oXeLG4ylYWXS0pmhUFnM1/KhIHIQi68OzXfYocxQpV7qBA5dJeLw52cYTDZ9DUW6sf3WbyGvRl4ggujDWF7RkhnleKLDK8e2wL1CiqPk0CZW3oNmuHYXhFX9lZgL3la030aZktQ74MFzXXGBFprkDWsWyFqQNK1Sqq0zvvSfPxlsBYcBLWgYte9u8kvWM1QVtFmqG4o 3rl65SgbgZOVJgZEmWub8ahvLcxWpqx1fVB ObdxaUTMN9EwKfJW/59PjARNCS WPTod8OWBQCZ7QZehMmzWJItJHTZxz27Zr2eQbpDJFECrKjAWQXAlYdT6rsw4L4CY1yIWYZrLOdcyGnYjX5HziBW889qpGqy0eK6JJhQ9wq0N8 beVC9JNvttWK3C0mjd491m2oeWv7ULVpVWGlEZOMzggHx1tUTDkuTBXStjFKozsm3KAAYWEtrt9SqOZYkb2upgxyEnSWaC2nEcjbOa6bIUv7EovBZetUlhRISqFr 4fgIDknwi/phlpUyLfFC9C1a/NLBfGcDhU7OOZ4KESfoyS31TbAz8Ps6chd8ftxJV4woTaL57rg0cDbB07BFe1Ys73hLMTCUA2Qnnsm2 b8G6ZuZvaBakcYDXyGfVzlSAn9CUnJ1gXyee4yWi1YmnQ /cp JGQLqeJ1gR3QUDa0yIRnHFiYVwKLzGAGnJcdLts2zuj8sYqvaUgVrFwqovDgcXpklISx2bT1vn2/dUHnpS Pq/p5z08ZKXs1lp8Fv6vBU Da6sYotJ3rx3jZcLGfX2woq9EKwYqcDAFdKRoY56eu2lCtRKeJ8o1W1TDVe483iWmVTXyT5paa jkDbyFLzWHG Q5cIE6YIoVWztVO2V2ha556YZJqo7GGlmGD01jxAKqZ5xaMvkWQ6S3WlzpoDIHh1jNYkVFaWjAIHK2jT4vr4fSwBMpnE9iy4nzOko6rMlGU8x FLsAOIFcpnv0ydKLsAvGHGR7yR42Tcmp1YyO8wYbJt/Kf438vSF/ vgMx1DfCKCSLolwKtm 28tNil8K7ldXAr8TVbMb6BKecHNVRJx1ZedbJYw10yIS23g9dNSAMFTJUEoN8glMk0UvsDVjxNFDfIuuZqoYpwPJgRaQxZbJilcOfJ9GvyXLLJjKGei3O7mOcuVTAfCFXRkls7N7pOX78MGRqvc9Ien1NvGV5bDYc3lVz46FCZM3tpCFIHA Wro8KB8A29qZp9ID45UBQRwg/a6BDdynsMVT6rmBhe5 GGxLQmNofXUl/KHFyfHur4sDU2wRrw2rr2Sieo/d5G3qIDHWxlmp6C43bcjkWpGjPP79M1tGiL/SA0gsWSYFoU98BGU1bm7rIowCVvNoO1eXP Gj9qyf5Z/7eEbTWNjRhxB0ca 4FvUSexwLPJMq/j0drHh06HI698OAG Muyn4D661/v/sVhVG xwezd7E9sUgQBxjB0WezfBh1L4wS7jKGotYcz7Sovd8kV6cU2kmc989MNdbkcYNweoP5St7RiLWnDA mE4NkbL96Lbx5Wg eDZdmzkmJniSrFrytY9jkITSj4t5zdFn0iL5Uy5tJwNIhaiIcBKOEEAEOpQEgmrBPlhGA67UzdcufAoAJpQvPLQ5t NH oTVFqwdIKaV73Wp03F AP0w/UXMNPNYsFu0ygYXJI6xgK2xdvLgmrSujjSFEBwU3Q5Z6EXYn3uSW/s5olwNFPtWIYy NGuNOjt3MsN5n0uhs06dSeFL18Bg7N57viD7NoPDPA6KmTChjoLtWuJFf66YSDtAcgd/1W6umQZV4StweM fqsv9z5oWx29TKr8D0uKhQi2DU2JfzzIfO734Cc t136ePQXyG9C4byzV6rikCS0V9/9Ji iUcTjQBDbo7ts2KIANu1p7Rd4aox7yNDoGReIJsFS2hXBnW4VReLRAMYaWvMzagp vWz7Ob1WykSddTjy5Mfz9/d9rOqoSFBggARQNRXQvo9jFVdNMAxO1tqOklBDcNOv4Qf3rRtCboulA54QMOniOGz0p6cgloLV6d2Vn2dg7tmkIllr5AiMXmhUOn7EDUz9A6UYctHygRwG0J8dF6JS9OfkoIJ7QObjCHf2RBhThNN/AHXPPVAQ6iJBj9X5x dAC5kLYd2xaarAaezyqBVBJ1wJPQMi6LAkj8rdGIvKnd76YQ6nQJFl5Z dxswpP3EzNNVqpl b8CTz7a0cxjN54RaUSQ V6YP3XrFh9NO7KklPj8KqiZVDP D85d3gawMvmH1OqNCP8udjomoTCzPZmOkkRGXKhTvXhrvkCCpZ7o6t9yT VqsZ3 j Rno0izBPlchVEook7Pkxg5K04sw==' /><input type='hidden' name='RelayState' value='/' /></form><script>window.onload = function () { document.forms[0].submit(); }</script></body></html>").getBytes());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }
//        }
        Pattern p = Pattern.compile("s:(.+?):");
        Matcher matcher = p.matcher("s:gismu_user_01:5742a1cc:172.18.4.47:1591114005784:gi1-test");
// if ticket number has prefix
        if (matcher.matches()) {
            Long number = Long.valueOf(matcher.group(1));
            number++;
        }
    }
}
