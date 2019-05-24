import com.paysera.sdk.wallet.ClientServerTimeSynchronizationConfiguration
import com.paysera.sdk.wallet.NonceGenerator
import com.paysera.sdk.wallet.RequestSigner
import com.paysera.sdk.wallet.Router
import com.paysera.sdk.wallet.clients.WalletAsyncClient
import com.paysera.sdk.wallet.entities.Credentials
import com.paysera.sdk.wallet.factories.HttpClientFactory
import com.paysera.sdk.wallet.factories.RetrofitFactory
import com.paysera.sdk.wallet.helpers.OkHTTPQueryStringConverter
import com.paysera.sdk.wallet.interfaces.TimestampSynchronizedCallback
import com.paysera.sdk.wallet.providers.TimestampProvider
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.*

object GetTransaction {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val userAgent = "Java wallet sdk library"

        val credentials = Credentials()

        credentials.accessToken = "jwDWbAqnMHMu5BD8"
        credentials.macKey = "7IUbtZrcUmhiJKQjF6wf9YkHN1oiYXHJ"
        credentials.validUntil = Date(System.currentTimeMillis() + 3000000)

        val timestampProvider = TimestampProvider()
        val clientServerTimeSynchronizationConfiguration = ClientServerTimeSynchronizationConfiguration()
        clientServerTimeSynchronizationConfiguration.isEnabled = true
        clientServerTimeSynchronizationConfiguration.timestampSynchronizedCallback = TimestampSynchronizedCallback { serverTime, currentTime -> }
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHTTPQueryStringConverter = OkHTTPQueryStringConverter()
        val requestSigner = RequestSigner(NonceGenerator(), okHTTPQueryStringConverter)
        val retrofitFactory = RetrofitFactory(Router())

        val httpClientFactory = HttpClientFactory(requestSigner, null, timestampProvider)

        var okHttpClient = httpClientFactory.createHttpClient(credentials, userAgent)
        okHttpClient = okHttpClient.newBuilder().addInterceptor(httpLoggingInterceptor).build()

        val publicWalletApiClient = retrofitFactory.createPublicWalletApiClient(
            okHttpClient
        )
        val walletApiClient = retrofitFactory.createWalletApiClient(
            okHttpClient
        )

        val walletClient = WalletAsyncClient(
            timestampProvider,
            clientServerTimeSynchronizationConfiguration,
            publicWalletApiClient,
            walletApiClient,
            retrofitFactory.createRetrofit("https://wallet-api.paysera.com/rest/v1/", okHttpClient),
            okHTTPQueryStringConverter
        )

        val fields = mutableListOf<String>()
        fields.add("transaction_key")
        fields.add("valid_for_payment_card_debit")
        fields.add("payments")
        fields.add("payments.beneficiary.wallet")
        fields.add("project")
        fields.add("project.wallet")


        val task = walletClient.getTransaction("9MwP2JuWJg2TbObSMFaq0j7tR0N3QZIB", fields).continueWith {
            println("${it.result}")
            System.exit(0)
        }
    }
}
