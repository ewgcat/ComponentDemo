package com.yijan.commonlib.net.httpmanager;


import android.content.Context;
import android.util.Log;


import com.hengte.retrofit.net.converterfactory.JSONObjectConverterFactory;
import com.yijan.commonlib.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * create by tdd
 * date 2016/8/5 0005
 * des 对网络请求进行初始化
 */
public class RetrofitClient {
    public static Retrofit mRetrofit;
    public static Retrofit mRetrofitForUpload;

    public static String zs_1 = "-----BEGIN CERTIFICATE-----\n" +
            "MIIFjTCCBHWgAwIBAgIQCgbj+EGDph0S68xH/vUZvTANBgkqhkiG9w0BAQsFADBu\n" +
            "MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3\n" +
            "d3cuZGlnaWNlcnQuY29tMS0wKwYDVQQDEyRFbmNyeXB0aW9uIEV2ZXJ5d2hlcmUg\n" +
            "RFYgVExTIENBIC0gRzEwHhcNMTgwNDEyMDAwMDAwWhcNMTkwNDEyMTIwMDAwWjAc\n" +
            "MRowGAYDVQQDExFoNS5kZXYuZWpveXN0LmNvbTCCASIwDQYJKoZIhvcNAQEBBQAD\n" +
            "ggEPADCCAQoCggEBAJjHMq3q3CzOigfjwrUspKjmdXfI6mtREMzXoOo3QqEpdgGO\n" +
            "jRTN/3K4tpvEK/6GFdEGHUxVP0nMfGYo3wp6oVxgMJk4sagcV/Gq6kzaucxoOqGc\n" +
            "j5WbwzTbwjHwHNdySP+6OjJeMMDVZryoe3h08G1RWqqmy8eUF13+qFJCpwAtkLNG\n" +
            "uc37cQL+0cMB6vHcY3R0OgNNNAen1UrvXSmn9BcIjd/gOcL24Ho5ayq6rmgbaNuc\n" +
            "88JxwBM/Eqzr0Ric1KNZxERBPALb1hSiC/St/mMlp/WfmuwFimE8kWlRQhlXcdHz\n" +
            "k9Nh5dEhv8Q9fFPMg8L/YP0z9/JwNHJnVqJ2HpMCAwEAAaOCAncwggJzMB8GA1Ud\n" +
            "IwQYMBaAFFV0T7JyT/VgulDR1+ZRXJoBhxrXMB0GA1UdDgQWBBTYa/wU34NtOs/A\n" +
            "lYTmFKGcQP4hyDAcBgNVHREEFTATghFoNS5kZXYuZWpveXN0LmNvbTAOBgNVHQ8B\n" +
            "Af8EBAMCBaAwHQYDVR0lBBYwFAYIKwYBBQUHAwEGCCsGAQUFBwMCMEwGA1UdIARF\n" +
            "MEMwNwYJYIZIAYb9bAECMCowKAYIKwYBBQUHAgEWHGh0dHBzOi8vd3d3LmRpZ2lj\n" +
            "ZXJ0LmNvbS9DUFMwCAYGZ4EMAQIBMIGBBggrBgEFBQcBAQR1MHMwJQYIKwYBBQUH\n" +
            "MAGGGWh0dHA6Ly9vY3NwMi5kaWdpY2VydC5jb20wSgYIKwYBBQUHMAKGPmh0dHA6\n" +
            "Ly9jYWNlcnRzLmRpZ2ljZXJ0LmNvbS9FbmNyeXB0aW9uRXZlcnl3aGVyZURWVExT\n" +
            "Q0EtRzEuY3J0MAkGA1UdEwQCMAAwggEFBgorBgEEAdZ5AgQCBIH2BIHzAPEAdwCk\n" +
            "uQmQtBhYFIe7E6LMZ3AKPDWYBPkb37jjd80OyA3cEAAAAWK5bFGkAAAEAwBIMEYC\n" +
            "IQCeAF/kRzKw6pLhl+NX1LdY0uMYGmzqxbLEKGVi4Mf91QIhAN5nXX6LIEqkC/qm\n" +
            "fLhRq/3d59Roeo5yQQr4rFoTI9UnAHYAb1N2rDHwMRnYmQCkURX/dxUcEdkCwQAp\n" +
            "Bo2yCJo32RMAAAFiuWxS+QAABAMARzBFAiA4YuwGt4rnOVPzrPSjuJ4mOaaFZ4y6\n" +
            "wJCdOpL+sB2slgIhAKE9VmLxHSvNlmX0x3vbv/F0ya3L+OlLIyfMoDOk06NfMA0G\n" +
            "CSqGSIb3DQEBCwUAA4IBAQA/22lEdeLcAVO+gsVYM0UV6bF304yZc5hPcHGGBTtF\n" +
            "gkMWst6FwunHmy8gKjHzty03x13i78MENjpMdtLkh92JK4H7T6fwuVpySpGsHwi+\n" +
            "YC6IEv7+MwctyZP36CMahEWdkPXR5Bcqdp1RIdMRfhThiR3DPyPJwtL/vYcI7ic2\n" +
            "zpe0mBIWgukaaahQL06Xnc4JTH5IhLcfv2t5QLg/LwFua2r2FQVm8GBULg+t9gK8\n" +
            "/Q21YjgPVOt1LQc0KIk+QVwdAyJA1v83YWIpmq/EialP1Wb6/5gBnAaOhfMkcQm4\n" +
            "BRpidpDD5kYQtWOoteDzBIzOwEc/yzijtC2+KACtz1Os\n" +
            "-----END CERTIFICATE-----";

    public static String zs_2 = "-----BEGIN CERTIFICATE-----\n" +
            "MIIFlTCCBH2gAwIBAgIQBXzdUCe81SiYwOyiXy2+PDANBgkqhkiG9w0BAQsFADBu\n" +
            "MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3\n" +
            "d3cuZGlnaWNlcnQuY29tMS0wKwYDVQQDEyRFbmNyeXB0aW9uIEV2ZXJ5d2hlcmUg\n" +
            "RFYgVExTIENBIC0gRzEwHhcNMTgwNDI3MDAwMDAwWhcNMTkwNDI3MTIwMDAwWjAh\n" +
            "MR8wHQYDVQQDExZid2ViYXBwLmRldi5lam95c3QuY29tMIIBIjANBgkqhkiG9w0B\n" +
            "AQEFAAOCAQ8AMIIBCgKCAQEAqTIQCtqZHyuVTR7MV3e7xrXKOS9Z/l1lA0uxLvis\n" +
            "1SZGol6EC7t4/bws9kVxYcJhZ+aseByb9QbyI1D1wrry7pmS8NYYUzWH0nDFFLMV\n" +
            "q/41m5jqQmJgKVmx/iLywWb9uVQDUg5nWSNtfFDnIh6wyOiqr6tcCOVH0cWCniMl\n" +
            "u7Ddl1umEjUy8SJ/HXoIJ0nWCiycPRPxsrXAHL5dF67BeeGTwPbdoiTgIl1be2CS\n" +
            "ZHmtckqY8wtzQzTYBgtrgYhdNGY4JaVzT7Iup7kDBXTechq5bF1rct2HQ4KFnwOC\n" +
            "5h3YuzoDYyMUM2SlqxH7rYfuydvHzAP65gk+o+sG9FxUkwIDAQABo4ICejCCAnYw\n" +
            "HwYDVR0jBBgwFoAUVXRPsnJP9WC6UNHX5lFcmgGHGtcwHQYDVR0OBBYEFHHRyc9J\n" +
            "N02ROFp0M8h0L+BN/Q6SMCEGA1UdEQQaMBiCFmJ3ZWJhcHAuZGV2LmVqb3lzdC5j\n" +
            "b20wDgYDVR0PAQH/BAQDAgWgMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcD\n" +
            "AjBMBgNVHSAERTBDMDcGCWCGSAGG/WwBAjAqMCgGCCsGAQUFBwIBFhxodHRwczov\n" +
            "L3d3dy5kaWdpY2VydC5jb20vQ1BTMAgGBmeBDAECATCBgQYIKwYBBQUHAQEEdTBz\n" +
            "MCUGCCsGAQUFBzABhhlodHRwOi8vb2NzcDIuZGlnaWNlcnQuY29tMEoGCCsGAQUF\n" +
            "BzAChj5odHRwOi8vY2FjZXJ0cy5kaWdpY2VydC5jb20vRW5jcnlwdGlvbkV2ZXJ5\n" +
            "d2hlcmVEVlRMU0NBLUcxLmNydDAJBgNVHRMEAjAAMIIBAwYKKwYBBAHWeQIEAgSB\n" +
            "9ASB8QDvAHYAu9nfvB+KcbWTlCOXqpJ7RzhXlQqrUugakJZkNo4e0YUAAAFjBzHy\n" +
            "0QAABAMARzBFAiBF4T5x9GOknwannvEN6lV5nQ7jaG6wiI4mILyiTLBS+gIhAM7h\n" +
            "Fom8SUlu+oXBpXGxeoPv7l+4lomKi0ZxcQ1ZOvCXAHUAb1N2rDHwMRnYmQCkURX/\n" +
            "dxUcEdkCwQApBo2yCJo32RMAAAFjBzHz/wAABAMARjBEAiBsDIPR4ju0PjHSMMBq\n" +
            "s+Q3zk0CaHjyCeiVnZT32kIdlwIgUNexP4DuU6K8uJEK/FvIiU/TT8Qu5HRSBz79\n" +
            "YPX6txgwDQYJKoZIhvcNAQELBQADggEBAD7p9AEA0GOcJkSYYtszO08B8BEXF+XI\n" +
            "lJQetD35WrwftGxGfirSvan9M28lVDL6v7L8GKif4VsQlOH0aZuj1S0DE8kLpT4w\n" +
            "3rEDAMC8N9gPtVUAcr5Fd5cskHu27NMGCAKJ3WdavbyuOJT9MbTefJwZX2xM3IkV\n" +
            "FOE8ueVmryQXOnXSA6iZfQ0H0BUEl0k4Oso6hfwYnqS+8GAUs02dkU7C2i21fKTF\n" +
            "bPCHx8rwui1e2FEFEVx9vGpInaCPDfLP7N/UB1tDh7wHukKGXgXwOHKTAYRYHbIB\n" +
            "8lNhFmO2iO9NAc6t6KyGzwtJkGL3B7HmIcpGcJQzffe7cwTceyYk12M=\n" +
            "-----END CERTIFICATE-----";

    public static String zs_3 = "-----BEGIN CERTIFICATE-----\n" +
            "MIIFlTCCBH2gAwIBAgIQDAvTc3BBu6hq6MCiCpvTEzANBgkqhkiG9w0BAQsFADBu\n" +
            "MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3\n" +
            "d3cuZGlnaWNlcnQuY29tMS0wKwYDVQQDEyRFbmNyeXB0aW9uIEV2ZXJ5d2hlcmUg\n" +
            "RFYgVExTIENBIC0gRzEwHhcNMTgwNDI3MDAwMDAwWhcNMTkwNDI3MTIwMDAwWjAg\n" +
            "MR4wHAYDVQQDExVid2ViYXBwLnFhLmVqb3lzdC5jb20wggEiMA0GCSqGSIb3DQEB\n" +
            "AQUAA4IBDwAwggEKAoIBAQCW2n1XqEv0gap6MfKj5KKrkse97Jd+kRTg6gUJJyKN\n" +
            "MUhxH3HUKf5MXQEijfMeYo+1dOlqg0iLqnsfolH8lMCNxSQTnDTddZ6q3TTXgqYV\n" +
            "SguCuWcZbjIvRj0sxcUchuDCSGZsp84lzDLAfcBsOUqh3pH/491CGTVlyTg1+3fp\n" +
            "84OmkmQ3kViMb8bRokW9qf3ZKLpyV4j0TjhVyeUgNx6uxgLee1ehWJUdZtrRiyRf\n" +
            "dMmYSIOecg2RkEkVlhGoUBBq7aVJFZNqnpYvhn/LWryvZ9zGWpzNAk/Q3JbD9qS0\n" +
            "eC6Nc+Gi6bR1Z1JUI1NR6EjEfZhFLDykdVI12w1R4EDHAgMBAAGjggJ7MIICdzAf\n" +
            "BgNVHSMEGDAWgBRVdE+yck/1YLpQ0dfmUVyaAYca1zAdBgNVHQ4EFgQUx6QCRRhf\n" +
            "9HSaLnkb0b005m3eKDUwIAYDVR0RBBkwF4IVYndlYmFwcC5xYS5lam95c3QuY29t\n" +
            "MA4GA1UdDwEB/wQEAwIFoDAdBgNVHSUEFjAUBggrBgEFBQcDAQYIKwYBBQUHAwIw\n" +
            "TAYDVR0gBEUwQzA3BglghkgBhv1sAQIwKjAoBggrBgEFBQcCARYcaHR0cHM6Ly93\n" +
            "d3cuZGlnaWNlcnQuY29tL0NQUzAIBgZngQwBAgEwgYEGCCsGAQUFBwEBBHUwczAl\n" +
            "BggrBgEFBQcwAYYZaHR0cDovL29jc3AyLmRpZ2ljZXJ0LmNvbTBKBggrBgEFBQcw\n" +
            "AoY+aHR0cDovL2NhY2VydHMuZGlnaWNlcnQuY29tL0VuY3J5cHRpb25FdmVyeXdo\n" +
            "ZXJlRFZUTFNDQS1HMS5jcnQwCQYDVR0TBAIwADCCAQUGCisGAQQB1nkCBAIEgfYE\n" +
            "gfMA8QB2AKS5CZC0GFgUh7sTosxncAo8NZgE+RvfuON3zQ7IDdwQAAABYwcx3bIA\n" +
            "AAQDAEcwRQIgUy0KvFclZw4vsRYtq71hgN+pb4zvMKyzNpuCa7C4szkCIQDEJDHW\n" +
            "LU2X2p/79R/rPCVwaS37koOxxCUbasLKktTNqQB3AG9Tdqwx8DEZ2JkApFEV/3cV\n" +
            "HBHZAsEAKQaNsgiaN9kTAAABYwcx34MAAAQDAEgwRgIhAIHLTEQyhuCoCh3raUat\n" +
            "WT3CRny/suiRGLp23rqZCXl0AiEAvF4V8/C1UeKtQww7BP3ue5U1kzKZQEbSZCLU\n" +
            "f/BNVMYwDQYJKoZIhvcNAQELBQADggEBACn6OC/9sLTE+iuUVPqdLLSrvsuG7n8u\n" +
            "YkEn8oAQ0ofIg7yeNNnlwHgJu+4L/eNmSHH/rjyLwRl+pW1R5pTZZCzBddRNtwXV\n" +
            "mDtpu0nkpv0HsY2HSi3DSuFr7SmFdzLWFJRJegc7dP+dIQ39B7w6xxIU6dG9ROof\n" +
            "yTBfi4poxlJ2x2MhUMQMmzVZvYX8dCHKoVdA++s1LSraRdK5LgejPJhefpc80kwp\n" +
            "PEq7aXVJGAfIJ30Yf5FpK/0vKrVDt8hwPIfjHxASLLLA0OJaom/+PHM7cl+8lBmx\n" +
            "WJsggXVRNHNqywSPhjGST8Cd6wxrXDemzQpCsthRdl2eikQuMlt4teQ=\n" +
            "-----END CERTIFICATE-----";

    //    生产
    public static String zs_4 = "-----BEGIN CERTIFICATE-----\n" +
            "MIIEqjCCA5KgAwIBAgIQAnmsRYvBskWr+YBTzSybsTANBgkqhkiG9w0BAQsFADBh\n" +
            "MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3\n" +
            "d3cuZGlnaWNlcnQuY29tMSAwHgYDVQQDExdEaWdpQ2VydCBHbG9iYWwgUm9vdCBD\n" +
            "QTAeFw0xNzExMjcxMjQ2MTBaFw0yNzExMjcxMjQ2MTBaMG4xCzAJBgNVBAYTAlVT\n" +
            "MRUwEwYDVQQKEwxEaWdpQ2VydCBJbmMxGTAXBgNVBAsTEHd3dy5kaWdpY2VydC5j\n" +
            "b20xLTArBgNVBAMTJEVuY3J5cHRpb24gRXZlcnl3aGVyZSBEViBUTFMgQ0EgLSBH\n" +
            "MTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALPeP6wkab41dyQh6mKc\n" +
            "oHqt3jRIxW5MDvf9QyiOR7VfFwK656es0UFiIb74N9pRntzF1UgYzDGu3ppZVMdo\n" +
            "lbxhm6dWS9OK/lFehKNT0OYI9aqk6F+U7cA6jxSC+iDBPXwdF4rs3KRyp3aQn6pj\n" +
            "pp1yr7IB6Y4zv72Ee/PlZ/6rK6InC6WpK0nPVOYR7n9iDuPe1E4IxUMBH/T33+3h\n" +
            "yuH3dvfgiWUOUkjdpMbyxX+XNle5uEIiyBsi4IvbcTCh8ruifCIi5mDXkZrnMT8n\n" +
            "wfYCV6v6kDdXkbgGRLKsR4pucbJtbKqIkUGxuZI2t7pfewKRc5nWecvDBZf3+p1M\n" +
            "pA8CAwEAAaOCAU8wggFLMB0GA1UdDgQWBBRVdE+yck/1YLpQ0dfmUVyaAYca1zAf\n" +
            "BgNVHSMEGDAWgBQD3lA1VtFMu2bwo+IbG8OXsj3RVTAOBgNVHQ8BAf8EBAMCAYYw\n" +
            "HQYDVR0lBBYwFAYIKwYBBQUHAwEGCCsGAQUFBwMCMBIGA1UdEwEB/wQIMAYBAf8C\n" +
            "AQAwNAYIKwYBBQUHAQEEKDAmMCQGCCsGAQUFBzABhhhodHRwOi8vb2NzcC5kaWdp\n" +
            "Y2VydC5jb20wQgYDVR0fBDswOTA3oDWgM4YxaHR0cDovL2NybDMuZGlnaWNlcnQu\n" +
            "Y29tL0RpZ2lDZXJ0R2xvYmFsUm9vdENBLmNybDBMBgNVHSAERTBDMDcGCWCGSAGG\n" +
            "/WwBAjAqMCgGCCsGAQUFBwIBFhxodHRwczovL3d3dy5kaWdpY2VydC5jb20vQ1BT\n" +
            "MAgGBmeBDAECATANBgkqhkiG9w0BAQsFAAOCAQEAK3Gp6/aGq7aBZsxf/oQ+TD/B\n" +
            "SwW3AU4ETK+GQf2kFzYZkby5SFrHdPomunx2HBzViUchGoofGgg7gHW0W3MlQAXW\n" +
            "M0r5LUvStcr82QDWYNPaUy4taCQmyaJ+VB+6wxHstSigOlSNF2a6vg4rgexixeiV\n" +
            "4YSB03Yqp2t3TeZHM9ESfkus74nQyW7pRGezj+TC44xCagCQQOzzNmzEAP2SnCrJ\n" +
            "sNE2DpRVMnL8J6xBRdjmOsC3N6cQuKuRXbzByVBjCqAA8t1L0I+9wXJerLPyErjy\n" +
            "rMKWaBFLmfK/AHNF4ZihwPGOc7w6UHczBZXH5RFzJNnww+WnKuTPI0HfnVH8lg==\n" +
            "-----END CERTIFICATE-----";
    //   预发布
    public static String zs_5 = "-----BEGIN CERTIFICATE-----\n" +
            "MIIEqjCCA5KgAwIBAgIQAnmsRYvBskWr+YBTzSybsTANBgkqhkiG9w0BAQsFADBh\n" +
            "MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3\n" +
            "d3cuZGlnaWNlcnQuY29tMSAwHgYDVQQDExdEaWdpQ2VydCBHbG9iYWwgUm9vdCBD\n" +
            "QTAeFw0xNzExMjcxMjQ2MTBaFw0yNzExMjcxMjQ2MTBaMG4xCzAJBgNVBAYTAlVT\n" +
            "MRUwEwYDVQQKEwxEaWdpQ2VydCBJbmMxGTAXBgNVBAsTEHd3dy5kaWdpY2VydC5j\n" +
            "b20xLTArBgNVBAMTJEVuY3J5cHRpb24gRXZlcnl3aGVyZSBEViBUTFMgQ0EgLSBH\n" +
            "MTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALPeP6wkab41dyQh6mKc\n" +
            "oHqt3jRIxW5MDvf9QyiOR7VfFwK656es0UFiIb74N9pRntzF1UgYzDGu3ppZVMdo\n" +
            "lbxhm6dWS9OK/lFehKNT0OYI9aqk6F+U7cA6jxSC+iDBPXwdF4rs3KRyp3aQn6pj\n" +
            "pp1yr7IB6Y4zv72Ee/PlZ/6rK6InC6WpK0nPVOYR7n9iDuPe1E4IxUMBH/T33+3h\n" +
            "yuH3dvfgiWUOUkjdpMbyxX+XNle5uEIiyBsi4IvbcTCh8ruifCIi5mDXkZrnMT8n\n" +
            "wfYCV6v6kDdXkbgGRLKsR4pucbJtbKqIkUGxuZI2t7pfewKRc5nWecvDBZf3+p1M\n" +
            "pA8CAwEAAaOCAU8wggFLMB0GA1UdDgQWBBRVdE+yck/1YLpQ0dfmUVyaAYca1zAf\n" +
            "BgNVHSMEGDAWgBQD3lA1VtFMu2bwo+IbG8OXsj3RVTAOBgNVHQ8BAf8EBAMCAYYw\n" +
            "HQYDVR0lBBYwFAYIKwYBBQUHAwEGCCsGAQUFBwMCMBIGA1UdEwEB/wQIMAYBAf8C\n" +
            "AQAwNAYIKwYBBQUHAQEEKDAmMCQGCCsGAQUFBzABhhhodHRwOi8vb2NzcC5kaWdp\n" +
            "Y2VydC5jb20wQgYDVR0fBDswOTA3oDWgM4YxaHR0cDovL2NybDMuZGlnaWNlcnQu\n" +
            "Y29tL0RpZ2lDZXJ0R2xvYmFsUm9vdENBLmNybDBMBgNVHSAERTBDMDcGCWCGSAGG\n" +
            "/WwBAjAqMCgGCCsGAQUFBwIBFhxodHRwczovL3d3dy5kaWdpY2VydC5jb20vQ1BT\n" +
            "MAgGBmeBDAECATANBgkqhkiG9w0BAQsFAAOCAQEAK3Gp6/aGq7aBZsxf/oQ+TD/B\n" +
            "SwW3AU4ETK+GQf2kFzYZkby5SFrHdPomunx2HBzViUchGoofGgg7gHW0W3MlQAXW\n" +
            "M0r5LUvStcr82QDWYNPaUy4taCQmyaJ+VB+6wxHstSigOlSNF2a6vg4rgexixeiV\n" +
            "4YSB03Yqp2t3TeZHM9ESfkus74nQyW7pRGezj+TC44xCagCQQOzzNmzEAP2SnCrJ\n" +
            "sNE2DpRVMnL8J6xBRdjmOsC3N6cQuKuRXbzByVBjCqAA8t1L0I+9wXJerLPyErjy\n" +
            "rMKWaBFLmfK/AHNF4ZihwPGOc7w6UHczBZXH5RFzJNnww+WnKuTPI0HfnVH8lg==\n" +
            "-----END CERTIFICATE-----";

    //   预发布
    public static String zs_6 = "-----BEGIN CERTIFICATE-----\n" +
            "MIIEsTCCA5mgAwIBAgIQCKWiRs1LXIyD1wK0u6tTSTANBgkqhkiG9w0BAQsFADBh\n" +
            "MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3\n" +
            "d3cuZGlnaWNlcnQuY29tMSAwHgYDVQQDExdEaWdpQ2VydCBHbG9iYWwgUm9vdCBD\n" +
            "QTAeFw0xNzExMDYxMjIzMzNaFw0yNzExMDYxMjIzMzNaMF4xCzAJBgNVBAYTAlVT\n" +
            "MRUwEwYDVQQKEwxEaWdpQ2VydCBJbmMxGTAXBgNVBAsTEHd3dy5kaWdpY2VydC5j\n" +
            "b20xHTAbBgNVBAMTFFJhcGlkU1NMIFJTQSBDQSAyMDE4MIIBIjANBgkqhkiG9w0B\n" +
            "AQEFAAOCAQ8AMIIBCgKCAQEA5S2oihEo9nnpezoziDtx4WWLLCll/e0t1EYemE5n\n" +
            "+MgP5viaHLy+VpHP+ndX5D18INIuuAV8wFq26KF5U0WNIZiQp6mLtIWjUeWDPA28\n" +
            "OeyhTlj9TLk2beytbtFU6ypbpWUltmvY5V8ngspC7nFRNCjpfnDED2kRyJzO8yoK\n" +
            "MFz4J4JE8N7NA1uJwUEFMUvHLs0scLoPZkKcewIRm1RV2AxmFQxJkdf7YN9Pckki\n" +
            "f2Xgm3b48BZn0zf0qXsSeGu84ua9gwzjzI7tbTBjayTpT+/XpWuBVv6fvarI6bik\n" +
            "KB859OSGQuw73XXgeuFwEPHTIRoUtkzu3/EQ+LtwznkkdQIDAQABo4IBZjCCAWIw\n" +
            "HQYDVR0OBBYEFFPKF1n8a8ADIS8aruSqqByCVtp1MB8GA1UdIwQYMBaAFAPeUDVW\n" +
            "0Uy7ZvCj4hsbw5eyPdFVMA4GA1UdDwEB/wQEAwIBhjAdBgNVHSUEFjAUBggrBgEF\n" +
            "BQcDAQYIKwYBBQUHAwIwEgYDVR0TAQH/BAgwBgEB/wIBADA0BggrBgEFBQcBAQQo\n" +
            "MCYwJAYIKwYBBQUHMAGGGGh0dHA6Ly9vY3NwLmRpZ2ljZXJ0LmNvbTBCBgNVHR8E\n" +
            "OzA5MDegNaAzhjFodHRwOi8vY3JsMy5kaWdpY2VydC5jb20vRGlnaUNlcnRHbG9i\n" +
            "YWxSb290Q0EuY3JsMGMGA1UdIARcMFowNwYJYIZIAYb9bAECMCowKAYIKwYBBQUH\n" +
            "AgEWHGh0dHBzOi8vd3d3LmRpZ2ljZXJ0LmNvbS9DUFMwCwYJYIZIAYb9bAEBMAgG\n" +
            "BmeBDAECATAIBgZngQwBAgIwDQYJKoZIhvcNAQELBQADggEBAH4jx/LKNW5ZklFc\n" +
            "YWs8Ejbm0nyzKeZC2KOVYR7P8gevKyslWm4Xo4BSzKr235FsJ4aFt6yAiv1eY0tZ\n" +
            "/ZN18bOGSGStoEc/JE4ocIzr8P5Mg11kRYHbmgYnr1Rxeki5mSeb39DGxTpJD4kG\n" +
            "hs5lXNoo4conUiiJwKaqH7vh2baryd8pMISag83JUqyVGc2tWPpO0329/CWq2kry\n" +
            "qv66OSMjwulUz0dXf4OHQasR7CNfIr+4KScc6ABlQ5RDF86PGeE6kdwSQkFiB/cQ\n" +
            "ysNyq0jEDQTkfa2pjmuWtMCNbBnhFXBYejfubIhaUbEv2FOQB3dCav+FPg5eEveX\n" +
            "TVyMnGo=\n" +
            "-----END CERTIFICATE-----";


    //初始化一般请求客户端
    public static Retrofit init(Context context) {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("http", message);
                    }
                });
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            builder.sslSocketFactory(getSSLSocketFactory(context,
                    new Buffer().writeUtf8(zs_1).inputStream()
                    , new Buffer().writeUtf8(zs_2).inputStream()
                    , new Buffer().writeUtf8(zs_3).inputStream()
                    , new Buffer().writeUtf8(zs_4).inputStream()
                    , new Buffer().writeUtf8(zs_5).inputStream()
                    , new Buffer().writeUtf8(zs_6).inputStream()

                    )
            );
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.writeTimeout(30, TimeUnit.SECONDS);
            OkHttpClient okHttpClient = builder.build();

            //组装retrofit
            mRetrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(JSONObjectConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("http://www.baidu.com")
                    .build();
        }
        return mRetrofit;
    }


    //初始化上传客户端
    public static Retrofit initUpload(Context context) {
        if (mRetrofitForUpload == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i("RetrofitClient", message);
                    }
                });
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            builder.sslSocketFactory(getSSLSocketFactory(context,
                    new Buffer().writeUtf8(zs_1).inputStream(),
                    new Buffer().writeUtf8(zs_2).inputStream(),
                    new Buffer().writeUtf8(zs_3).inputStream()));
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            builder.connectTimeout(5, TimeUnit.MINUTES);
            builder.readTimeout(5, TimeUnit.MINUTES);
            builder.writeTimeout(5, TimeUnit.MINUTES);
            OkHttpClient okHttpClient = builder.build();

            mRetrofitForUpload = new Retrofit.Builder()
                    .baseUrl("http://poly.hengtech.com.cn/pmsSrv/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofitForUpload;
    }


    //注册证书
    protected static SSLSocketFactory getSSLSocketFactory(Context context, InputStream... certificates) {


        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            Log.i("dd", "e->" + e.toString());
            e.printStackTrace();
        }
        return null;
    }


}
